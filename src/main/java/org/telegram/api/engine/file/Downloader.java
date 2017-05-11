package org.telegram.api.engine.file;

import org.telegram.api.engine.Logger;
import org.telegram.api.engine.TelegramApi;
import org.telegram.api.input.filelocation.TLAbsInputFileLocation;
import org.telegram.api.storage.file.TLAbsFileType;
import org.telegram.api.upload.cdn.TLAbsCdnFile;
import org.telegram.api.upload.cdn.TLCdnFile;
import org.telegram.api.upload.file.TLAbsFile;
import org.telegram.api.upload.file.TLFile;
import org.telegram.api.upload.file.TLFileCdnRedirect;
import org.telegram.tl.TLBytes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ruben Bermudez on 18.11.13.
 */
public class Downloader {
    /**
     * The constant FILE_QUEUED.
     */
    public static final int FILE_QUEUED = 0;
    /**
     * The constant FILE_DOWNLOADING.
     */
    public static final int FILE_DOWNLOADING = 1;
    /**
     * The constant FILE_COMPLETED.
     */
    public static final int FILE_COMPLETED = 2;
    /**
     * The constant FILE_CANCELED.
     */
    public static final int FILE_CANCELED = 3;
    /**
     * The constant FILE_FAILURE.
     */
    public static final int FILE_FAILURE = 4;
    private static final long DOWNLOAD_TIMEOUT = 30 * 1000;
    private static final long DEFAULT_DELAY = 15 * 1000;
    private static final int BLOCK_SIZE = 256 * 1024;
    private static final int PARALLEL_DOWNLOAD_COUNT = 2;
    private static final int PARALLEL_PARTS_COUNT = 4;
    private static final int BLOCK_QUEUED = 0;
    private static final int BLOCK_DOWNLOADING = 1;
    private static final int BLOCK_COMPLETED = 2;
    private final AtomicInteger fileIds = new AtomicInteger(1);
    private final String TAG;
    private final Object threadLocker = new Object();
    private TelegramApi api;
    private ArrayList<DownloadTask> tasks = new ArrayList<DownloadTask>();
    private ArrayList<DownloadFileThread> threads = new ArrayList<DownloadFileThread>();
    private Random rnd = new Random();

    /**
     * Instantiates a new Downloader.
     *
     * @param api the api
     */
    public Downloader(TelegramApi api) {
        this.TAG = api.toString() + "#Downloader";
        this.api = api;

        for (int i = 0; i < PARALLEL_PARTS_COUNT; i++) {
            DownloadFileThread thread = new DownloadFileThread();
            thread.start();
            this.threads.add(thread);
        }
    }

    /**
     * Gets api.
     *
     * @return the api
     */
    public TelegramApi getApi() {
        return this.api;
    }

    private synchronized DownloadTask getTask(int taskId) {
        for (DownloadTask task : this.tasks) {
            if (task.taskId == taskId) {
                return task;
            }
        }
        return null;
    }

    /**
     * Cancel task.
     *
     * @param taskId the task id
     */
    public synchronized void cancelTask(int taskId) {
        DownloadTask task = getTask(taskId);
        if (task != null && task.state != FILE_COMPLETED) {
            task.state = FILE_CANCELED;
            Logger.d(this.TAG, "File #" + task.taskId + "| Canceled");
        }
        updateFileQueueStates();
    }

    /**
     * Gets task state.
     *
     * @param taskId the task id
     * @return the task state
     */
    public synchronized int getTaskState(int taskId) {
        DownloadTask task = getTask(taskId);
        if (task != null) {
            return task.state;
        }

        return FILE_CANCELED;
    }

    /**
     * Wait for task.
     *
     * @param taskId the task id
     */
    public void waitForTask(int taskId) {
        while (true) {
            int state = getTaskState(taskId);
            if ((state == FILE_COMPLETED) || (state == FILE_FAILURE) || (state == FILE_CANCELED)) {
                return;
            }
            synchronized (this.threadLocker) {
                try {
                    this.threadLocker.wait(DEFAULT_DELAY);
                } catch (InterruptedException e) {
                    Logger.e(this.TAG, e);
                    return;
                }
            }
        }
    }

    /**
     * Request task.
     *
     * @param dcId the dc id
     * @param location the location
     * @param size the size
     * @param destFile the dest file
     * @param listener the listener
     * @return the int
     */
    public synchronized int requestTask(int dcId, TLAbsInputFileLocation location, int size, String destFile, DownloadListener listener) {
        int blockSize = BLOCK_SIZE;
        int totalBlockCount = (int) Math.ceil(((double) size) / blockSize);

        DownloadTask task = new DownloadTask();
        task.listener = listener;
        task.blockSize = blockSize;
        task.destFile = destFile;
        try {
            task.file = new RandomAccessFile(destFile, "rw");
            task.file.setLength(size);
        } catch (FileNotFoundException e) {
            Logger.e(this.TAG, e);
        } catch (IOException e) {
            Logger.e(this.TAG, e);
        }
        task.taskId = this.fileIds.getAndIncrement();
        task.dcId = dcId;
        task.location = location;
        task.size = size;
        task.blocks = new DownloadBlock[totalBlockCount];
        for (int i = 0; i < totalBlockCount; i++) {
            task.blocks[i] = new DownloadBlock();
            task.blocks[i].task = task;
            task.blocks[i].index = i;
            task.blocks[i].state = BLOCK_QUEUED;
        }
        task.state = FILE_QUEUED;
        task.queueTime = System.nanoTime();
        this.tasks.add(task);

        Logger.d(this.TAG, "File #" + task.taskId + "| Requested");

        updateFileQueueStates();

        return task.taskId;
    }

    private synchronized DownloadTask[] getActiveTasks() {
        ArrayList<DownloadTask> res = new ArrayList<DownloadTask>();
        for (DownloadTask task : this.tasks) {
            if (task.state == FILE_DOWNLOADING) {
                res.add(task);
            }
        }
        return res.toArray(new DownloadTask[res.size()]);
    }

    private synchronized void updateFileQueueStates() {
        DownloadTask[] activeTasks = getActiveTasks();
        outer:
        for (DownloadTask task : activeTasks) {
            for (DownloadBlock block : task.blocks) {
                if (block.state != BLOCK_COMPLETED) {
                    continue outer;
                }
            }
            onTaskCompleted(task);
        }
        activeTasks = getActiveTasks();

        int count = activeTasks.length;
        while (count < PARALLEL_DOWNLOAD_COUNT) {
            long mintime = Long.MAX_VALUE;
            DownloadTask minTask = null;
            for (DownloadTask task : this.tasks) {
                if (task.state == FILE_QUEUED && task.queueTime < mintime) {
                    minTask = task;
                }
            }

            if (minTask == null) {
                break;
            }
            minTask.state = FILE_DOWNLOADING;
            Logger.d(this.TAG, "File #" + minTask.taskId + "| Downloading");
        }

        synchronized (this.threadLocker) {
            this.threadLocker.notifyAll();
        }
    }

    private synchronized void onTaskCompleted(DownloadTask task) {
        if (task.state != FILE_COMPLETED) {
            Logger.d(this.TAG, "File #" + task.taskId + "| Completed in " + (System.nanoTime() - task.queueTime) / (1000 * 1000L) + " ms");
            task.state = FILE_COMPLETED;
            try {
                if (task.file != null) {
                    task.file.close();
                    task.file = null;
                }
                if (task.listener != null) {
                    task.listener.onDownloaded(task);
                }
            } catch (IOException e) {
                Logger.e(this.TAG, e);
            }
        }
        updateFileQueueStates();
    }

    private synchronized void onTaskFailure(DownloadTask task) {
        if (task.state != FILE_FAILURE) {
            Logger.d(this.TAG, "File #" + task.taskId + "| Failure in " + (System.nanoTime() - task.queueTime) / (1000 * 1000L) + " ms");
            task.state = FILE_FAILURE;
            try {
                if (task.file != null) {
                    task.file.close();
                    task.file = null;
                }
            } catch (IOException e) {
                Logger.e(this.TAG, e);
            }
        }
        updateFileQueueStates();
    }

    private synchronized DownloadTask fetchTask() {
        DownloadTask[] activeTasks = getActiveTasks();
        if (activeTasks.length == 0) {
            return null;
        } else if (activeTasks.length == 1) {
            return activeTasks[0];
        } else {
            return activeTasks[this.rnd.nextInt(activeTasks.length)];
        }
    }

    private synchronized DownloadBlock fetchBlock() {
        DownloadTask task = fetchTask();
        if (task == null) {
            return null;
        }

        for (int i = 0; i < task.blocks.length; i++) {
            if (task.blocks[i].state == BLOCK_QUEUED) {
                task.blocks[i].state = BLOCK_DOWNLOADING;
                if (task.lastSuccessBlock == 0) {
                    task.lastSuccessBlock = System.nanoTime();
                }
                return task.blocks[i];
            }
        }

        return null;
    }

    private synchronized void onBlockDownloaded(DownloadBlock block, TLBytes data) {
        try {
            if (block.task.file != null) {
                block.task.file.seek(block.index * block.task.blockSize);
                block.task.file.write(data.getData(), data.getOffset(), data.getLength());
            } else {
                return;
            }
        } catch (IOException e) {
            Logger.e(this.TAG, e);
        } finally {
            this.api.getApiContext().releaseBytes(data);
        }
        block.task.lastSuccessBlock = System.nanoTime();
        block.state = BLOCK_COMPLETED;
        if (block.task.listener != null) {
            int downloadedCount = 0;
            for (DownloadBlock b : block.task.blocks) {
                if (b.state == BLOCK_COMPLETED) {
                    downloadedCount++;
                }
            }

            int percent = downloadedCount * 100 / block.task.blocks.length;
            block.task.listener.onPartDownloaded(percent, downloadedCount);
        }
        updateFileQueueStates();
    }

    private synchronized void onBlockFailure(DownloadBlock block) {
        block.state = BLOCK_QUEUED;
        if (block.task.lastSuccessBlock != 0 && (System.nanoTime() - block.task.lastSuccessBlock > DOWNLOAD_TIMEOUT * 1000L * 1000L)) {
            onTaskFailure(block.task);
        }
        updateFileQueueStates();
    }

    /**
     * The type Download task.
     */
    public class DownloadTask {

        /**
         * The Listener.
         */
        public DownloadListener listener;
        /**
         * The Last notify time.
         */
        public long lastNotifyTime;

        /**
         * The Task id.
         */
        public int taskId;

        /**
         * The Block size.
         */
        public int blockSize;

        /**
         * The Dc id.
         */
        public int dcId;
        /**
         * The Location.
         */
        public TLAbsInputFileLocation location;
        /**
         * The Size.
         */
        public int size;

        /**
         * The Queue time.
         */
        public long queueTime;

        /**
         * The State.
         */
        public int state;

        /**
         * The Blocks.
         */
        public DownloadBlock[] blocks;

        /**
         * The Dest file.
         */
        public String destFile;

        /**
         * The File.
         */
        public RandomAccessFile file;

        /**
         * The Last success block.
         */
        public long lastSuccessBlock;

        /**
         * The Type.
         */
        public TLAbsFileType type;
    }

    private class DownloadBlock {
        /**
         * The Task.
         */
        public DownloadTask task;
        /**
         * The State.
         */
        public int state;
        /**
         * The Index.
         */
        public int index;
    }

    private class DownloadFileThread extends Thread {

        /**
         * Instantiates a new Download file thread.
         */
        public DownloadFileThread() {
            setName("DownloadFileThread#" + hashCode());
        }

        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            while (true) {
                Logger.d(Downloader.this.TAG, "DownloadFileThread iteration");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                DownloadBlock block = fetchBlock();
                if (block == null) {
                    synchronized (Downloader.this.threadLocker) {
                        try {
                            Downloader.this.threadLocker.wait();
                            continue;
                        } catch (InterruptedException e) {
                            Logger.e(Downloader.this.TAG, e);
                            return;
                        }
                    }
                }

                long start = System.nanoTime();
                Logger.d(Downloader.this.TAG, "Block #" + block.index + " of #" + block.task.taskId + "| Starting");
                try {
                    TLAbsFile file = Downloader.this.api.doGetFile(block.task.dcId, block.task.location, block.index * block.task.blockSize, block.task.blockSize);
                    Logger.d(Downloader.this.TAG, "Block #" + block.index + " of #" + block.task.taskId + "| Downloaded in " + (System.nanoTime() - start) / (1000 * 1000L) + " ms");
                    TLBytes data = null;

                    if (file instanceof TLFile) {
                        data = ((TLFile) file).getBytes();
                        if (block.task.type == null) {
                            block.task.type = ((TLFile) file).getType();
                        }
                    } else if (file instanceof TLFileCdnRedirect) {
                        TLAbsCdnFile cdnFile = Downloader.this.api.doGetCdnFile(((TLFileCdnRedirect) file).getDcId(),
                                ((TLFileCdnRedirect) file).getFileToken(),block.index * block.task.blockSize, block.task.blockSize);

                        if (cdnFile instanceof TLCdnFile) {
                            data = ((TLCdnFile) cdnFile).getBytes();
                        } else {
                            Logger.d(Downloader.this.TAG, "File need to be reuploaded");
                            onBlockFailure(block);
                        }
                    }

                    if (data == null) {
                        Logger.d(Downloader.this.TAG, "Failed to download file data");
                        onBlockFailure(block);
                    }
                    onBlockDownloaded(block, data);
                } catch (IOException | TimeoutException e) {
                    Logger.d(Downloader.this.TAG, "Block #" + block.index + " of #" + block.task.taskId + "| Failure");
                    Logger.e(Downloader.this.TAG, e);
                    onBlockFailure(block);
                }
            }
        }

        private TLAbsCdnFile getFileFromCdn(TLFileCdnRedirect file) {
            return null;
        }
    }
}
