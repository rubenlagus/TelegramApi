package org.telegram.api.engine.file.downloader;

import java.io.IOException;

import org.telegram.api.engine.Logger;
import org.telegram.api.engine.file.Downloader;
import org.telegram.api.engine.file.downloader.DownloadBlock;
import org.telegram.api.engine.file.downloader.DownloadTask;
import org.telegram.api.engine.file.downloader.DownloadTaskFile;
import org.telegram.tl.TLBytes;

/**
 * Implementing the operations of downloader data into the file
 * 
 * @author <a href="mailto:onixbed@gmail.com">amaksimov</a>
 */
public class DownloaderFile implements DownloaderOperation {

	private Downloader download;

	public DownloaderFile(Downloader download) {
		this.download = download;
	}

	public synchronized void onBlockDownloaded(DownloadBlock block, TLBytes data) {
		try {
			DownloadTaskFile taskFile = getTaskFile(block.task);
			if (taskFile.file != null) {
				taskFile.file.seek(block.index * block.task.blockSize);
				taskFile.file.write(data.getData(), data.getOffset(), data.getLength());
			} else {
				return;
			}
		} catch (IOException e) {
			Logger.e(download.getTag(), e);
		} finally {
			download.getApi().getApiContext().releaseBytes(data);
		}
		block.task.lastSuccessBlock = System.nanoTime();
		block.state = Downloader.BLOCK_COMPLETED;
		if (block.task.listener != null) {
			int downloadedCount = 0;
			for (DownloadBlock b : block.task.blocks) {
				if (b.state == Downloader.BLOCK_COMPLETED) {
					downloadedCount++;
				}
			}

			int percent = downloadedCount * 100 / block.task.blocks.length;
			block.task.listener.onPartDownloaded(percent, downloadedCount);
		}
		download.updateFileQueueStates();
	}

	@Override
	public synchronized void onTaskCompleted(DownloadTask task) {
		DownloadTaskFile taskFile = getTaskFile(task);
		if (task.state != Downloader.FILE_COMPLETED) {
			Logger.d(download.getTag(), "File #" + task.taskId + "| Completed in "
					+ (System.nanoTime() - task.queueTime) / (1000 * 1000L) + " ms");
			task.state = Downloader.FILE_COMPLETED;
			try {
				if (taskFile.file != null) {
					taskFile.file.close();
					taskFile.file = null;
				}
				if (taskFile.listener != null) {
					taskFile.listener.onDownloaded(task);
				}
			} catch (IOException e) {
				Logger.e(download.getTag(), e);
			}
		}
		download.updateFileQueueStates();
	}

	@Override
	public synchronized void onTaskFailure(DownloadTask task) {
		DownloadTaskFile taskFile = getTaskFile(task);
		if (task.state != Downloader.FILE_FAILURE) {
			Logger.d(download.getTag(), "File #" + task.taskId + "| Failure in "
					+ (System.nanoTime() - task.queueTime) / (1000 * 1000L) + " ms");
			task.state = Downloader.FILE_FAILURE;
			try {
				if (taskFile.file != null) {
					taskFile.file.close();
					taskFile.file = null;
				}
			} catch (IOException e) {
				Logger.e(download.getTag(), e);
			}
		}
		download.updateFileQueueStates();
	}
	
	private DownloadTaskFile getTaskFile(DownloadTask task) {
		DownloadTaskFile taskFile = null;
		if(task instanceof DownloadTaskFile ) {
			taskFile = (DownloadTaskFile)task;
		}
		return taskFile;
	}
}
