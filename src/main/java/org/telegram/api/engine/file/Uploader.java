package org.telegram.api.engine.file;

import org.telegram.api.engine.Logger;
import org.telegram.api.engine.TelegramApi;
import org.telegram.mtproto.secure.CryptoUtils;
import org.telegram.mtproto.secure.Entropy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ruben Bermudez on 19.11.13.
 */
public class Uploader {

    /**
     * The constant FILE_QUEUED.
     */
    public static final int FILE_QUEUED = 0;
    /**
     * The constant FILE_IN_PROGRESS.
     */
    public static final int FILE_IN_PROGRESS = 1;
    /**
     * The constant FILE_COMPLETED.
     */
    public static final int FILE_COMPLETED = 2;
    /**
     * The constant FILE_CANCELED.
     */
    public static final int FILE_CANCELED = 3;
    private static final int KB = 1024;
    private static final int MB = 1024 * KB;
    private static final int BLOCK_QUEUED = 0;
    private static final int BLOCK_DOWNLOADING = 1;
    private static final int BLOCK_COMPLETED = 2;
    private static final int PARALLEL_DOWNLOAD_COUNT = 2;
    private static final int PARALLEL_PARTS_COUNT = 4;
    private static final int[] BLOCK_SIZES = new int[]{256 * KB, 512 * KB};
    private static final long DEFAULT_DELAY = 15 * 1000;
    private static final int BIG_FILE_MIN = 10 * 1024 * 1024;
    private static final int MAX_BLOCK_COUNT = 3000;
    private final AtomicInteger fileIds = new AtomicInteger(1);
    private final String TAG;
    private final Object threadLocker = new Object();
    private TelegramApi api;
    private ArrayList<UploadTask> tasks = new ArrayList<UploadTask>();
    private ArrayList<UploadFileThread> threads = new ArrayList<UploadFileThread>();
    private Random rnd = new Random();

    /**
     * Instantiates a new Uploader.
     *
     * @param api the api
     */
    public Uploader(TelegramApi api) {
        this.TAG = api.toString() + "#Uploader";
        this.api = api;

        for (int i = 0; i < PARALLEL_PARTS_COUNT; i++) {
            UploadFileThread thread = new UploadFileThread();
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

    private synchronized UploadTask getTask(int taskId) {
        for (UploadTask task : this.tasks) {
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
        UploadTask task = getTask(taskId);
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
        UploadTask task = getTask(taskId);
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
            if ((state == FILE_COMPLETED) || (state == FILE_CANCELED)) {
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
     * Gets upload result.
     *
     * @param taskId the task id
     * @return the upload result
     */
    public UploadResult getUploadResult(int taskId) {
        UploadTask task = getTask(taskId);
        if (task == null) {
            return null;
        }
        if (task.state != FILE_COMPLETED) {
            return null;
        }

        return new UploadResult(task.uniqId, task.blocks.length, task.hash, task.usedBigFile);
    }

    /**
     * Request task.
     *
     * @param srcFile the src file
     * @param listener the listener
     * @return the int
     */
    public synchronized int requestTask(String srcFile, UploadListener listener) {
        UploadTask task = new UploadTask();
        task.taskId = this.fileIds.getAndIncrement();
        task.uniqId = Entropy.getInstance().generateRandomId();
        task.listener = listener;
        task.srcFile = srcFile;
        try {
            File testFile = new File(srcFile);
            if (testFile.exists()) {
                Logger.d(this.TAG, "File exists");
            } else {
                Logger.d(this.TAG, "File doesn't exists");
            }
            task.file = new RandomAccessFile(new File(srcFile), "r");
            task.size = (int) task.file.length();
            if (task.size >= BIG_FILE_MIN) {
                task.usedBigFile = true;
                Logger.d(this.TAG, "File #" + task.uniqId + "| Using big file method");
            } else {
                task.usedBigFile = false;
            }
            long start = System.currentTimeMillis();
            Logger.d(this.TAG, "File #" + task.uniqId + "| Calculating hash");
            task.hash = CryptoUtils.MD5(task.file);
            Logger.d(this.TAG, "File #" + task.uniqId + "| Hash " + task.hash + " in " + (System.currentTimeMillis() - start) + " ms");
        } catch (FileNotFoundException e) {
            Logger.e(this.TAG, e);
        } catch (IOException e) {
            Logger.e(this.TAG, e);
        }

        task.blockSize = BLOCK_SIZES[BLOCK_SIZES.length - 1];
        for (int size : BLOCK_SIZES) {
            int totalBlockCount = (int) Math.ceil(((double) task.size) / size);
            if (totalBlockCount < MAX_BLOCK_COUNT) {
                task.blockSize = size;
                break;
            }
        }

        Logger.d(this.TAG, "File #" + task.uniqId + "| Using block size: " + task.blockSize);

        int totalBlockCount = (int) Math.ceil(((double) task.size) / task.blockSize);
        task.blockCount = totalBlockCount;
        Logger.w(this.TAG, "Number of blocks " + totalBlockCount);
        task.blocks = new UploadBlock[totalBlockCount];
        for (int i = 0; i < totalBlockCount; i++) {
            task.blocks[i] = new UploadBlock();
            task.blocks[i].task = task;
            task.blocks[i].index = i;
            task.blocks[i].state = BLOCK_QUEUED;
        }
        task.state = FILE_QUEUED;
        task.queueTime = System.nanoTime();
        this.tasks.add(task);

        Logger.d(this.TAG, "File #" + task.uniqId + "| Requested");

        updateFileQueueStates();

        return task.taskId;
    }

    private synchronized UploadTask[] getActiveTasks() {
        ArrayList<UploadTask> res = new ArrayList<UploadTask>();
        for (UploadTask task : this.tasks) {
            if (task.state == FILE_IN_PROGRESS) {
                res.add(task);
            }
        }
        return res.toArray(new UploadTask[res.size()]);
    }

    private synchronized void updateFileQueueStates() {
        UploadTask[] activeTasks = getActiveTasks();
        outer:
        for (UploadTask task : activeTasks) {
            for (UploadBlock block : task.blocks) {
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
            UploadTask minTask = null;
            for (UploadTask task : this.tasks) {
                if (task.state == FILE_QUEUED && task.queueTime < mintime) {
                    minTask = task;
                }
            }

            if (minTask == null) {
                break;
            }
            minTask.state = FILE_IN_PROGRESS;
            Logger.d(this.TAG, "File #" + minTask.uniqId + "| Uploading");
        }

        synchronized (this.threadLocker) {
            this.threadLocker.notifyAll();
        }
    }

    private synchronized void onTaskCompleted(UploadTask task) {
        if (task.state != FILE_COMPLETED) {
            Logger.d(this.TAG, "File #" + task.uniqId + "| Completed in " + (System.nanoTime() - task.queueTime) / (1000 * 1000L) + " ms");
            task.state = FILE_COMPLETED;
            try {
                if (task.file != null) {
                    task.file.close();
                    task.file = null;
                }
                if (task.listener != null) {
                    task.listener.onUploaded(task);
                }
            } catch (IOException e) {
                Logger.e(this.TAG, e);
            }
        }
        updateFileQueueStates();
    }

    private synchronized UploadTask fetchTask() {
        UploadTask[] activeTasks = getActiveTasks();
        if (activeTasks.length == 0) {
            return null;
        } else if (activeTasks.length == 1) {
            return activeTasks[0];
        } else {
            return activeTasks[this.rnd.nextInt(activeTasks.length)];
        }
    }

    private synchronized UploadBlock fetchBlock() {
        UploadTask task = fetchTask();
        if (task == null) {
            return null;
        }

        for (int i = 0; i < task.blocks.length; i++) {
            if (task.blocks[i].state == BLOCK_QUEUED) {
                task.blocks[i].state = BLOCK_DOWNLOADING;
                byte[] block = new byte[Math.min(task.size - task.blockSize * i, task.blockSize)];
                try {
                    task.file.seek(task.blockSize * i);
                    task.file.readFully(block);
                } catch (IOException e) {
                    Logger.e(this.TAG, e);
                }
                task.blocks[i].workData = block;
                return task.blocks[i];
            }
        }

        return null;
    }

    private synchronized void onBlockUploaded(UploadBlock block) {
        block.state = BLOCK_COMPLETED;
        if (block.task.listener != null) {
            int downloadedCount = 0;
            for (UploadBlock b : block.task.blocks) {
                if (b.state == BLOCK_COMPLETED) {
                    downloadedCount++;
                }
            }
            int percent = downloadedCount * 100 / block.task.blocks.length;
            block.task.listener.onPartUploaded(percent, downloadedCount);
        }
        updateFileQueueStates();
    }

    private synchronized void onBlockFailure(UploadBlock block) {
        block.state = BLOCK_QUEUED;
        updateFileQueueStates();
    }

    /**
     * The type Upload result.
     */
    public static class UploadResult {
        private long fileId;
        private boolean usedBigFile;
        private int partsCount;
        private String hash;

        /**
         * Instantiates a new Upload result.
         *
         * @param fileId the file id
         * @param partsCount the parts count
         * @param hash the hash
         * @param usedBigFile the used big file
         */
        public UploadResult(long fileId, int partsCount, String hash, boolean usedBigFile) {
            this.fileId = fileId;
            this.partsCount = partsCount;
            this.hash = hash;
            this.usedBigFile = usedBigFile;
        }

        /**
         * Gets file id.
         *
         * @return the file id
         */
        public long getFileId() {
            return this.fileId;
        }

        /**
         * Is used big file.
         *
         * @return the boolean
         */
        public boolean isUsedBigFile() {
            return this.usedBigFile;
        }

        /**
         * Gets parts count.
         *
         * @return the parts count
         */
        public int getPartsCount() {
            return this.partsCount;
        }

        /**
         * Gets hash.
         *
         * @return the hash
         */
        public String getHash() {
            return this.hash;
        }
    }

    /**
     * The type Upload task.
     */
    public class UploadTask {

        /**
         * The Listener.
         */
        public UploadListener listener;

        /**
         * The Used big file.
         */
        public boolean usedBigFile;

        /**
         * The Uniq id.
         */
        public long uniqId;

        /**
         * The Task id.
         */
        public int taskId;

        /**
         * The Block size.
         */
        public int blockSize;

        /**
         * The Queue time.
         */
        public long queueTime;

        /**
         * The State.
         */
        public int state;

        /**
         * The Size.
         */
        public int size;

        /**
         * The Blocks.
         */
        public UploadBlock[] blocks;

        /**
         * The Src file.
         */
        public String srcFile;

        /**
         * The File.
         */
        public RandomAccessFile file;

        /**
         * The Hash.
         */
        public String hash;

        /**
         * The Block count.
         */
        public int blockCount;
    }

    private class UploadBlock {
        /**
         * The Task.
         */
        public UploadTask task;
        /**
         * The State.
         */
        public int state;
        /**
         * The Index.
         */
        public int index;
        /**
         * The Work data.
         */
        public byte[] workData;
    }

    private class UploadFileThread extends Thread {

        /**
         * Instantiates a new Upload file thread.
         */
        public UploadFileThread() {
            setName("UploadFileThread#" + hashCode());
        }

        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            while (true) {
                Logger.d(Uploader.this.TAG, "UploadFileThread iteration");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                UploadBlock block = fetchBlock();
                if (block == null) {
                    synchronized (Uploader.this.threadLocker) {
                        try {
                            Uploader.this.threadLocker.wait();
                            continue;
                        } catch (InterruptedException e) {
                            Logger.e(Uploader.this.TAG, e);
                            return;
                        }
                    }
                }

                long start = System.nanoTime();
                Logger.d(Uploader.this.TAG, "Block #" + block.index + " of #" + block.task.uniqId + "| Starting");
                try {
                    if (block.task.usedBigFile) {
                        Uploader.this.api.doSaveBigFilePart(block.task.uniqId, block.index, block.task.blocks.length, block.workData);
                    } else {
                        Uploader.this.api.doSaveFilePart(block.task.uniqId, block.index, block.workData);
                    }
                    block.workData = null;
                    Logger.d(Uploader.this.TAG, "Block #" + block.index + " of #" + block.task.uniqId + "| Uploaded in " + (System.nanoTime() - start) / (1000 * 1000L) + " ms");
                    onBlockUploaded(block);
                } catch (IOException | TimeoutException e) {
                    Logger.d(Uploader.this.TAG, "Block #" + block.index + " of #" + block.task.uniqId + "| Failure");
                    Logger.e(Uploader.this.TAG, e);
                    onBlockFailure(block);
                }
            }
        }
    }
}
