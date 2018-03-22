package org.telegram.api.engine.file.downloader;

import java.util.ArrayList;
import java.util.List;

import org.telegram.api.engine.Logger;
import org.telegram.api.engine.file.Downloader;
import org.telegram.api.engine.file.downloader.DownloadBlock;
import org.telegram.api.engine.file.downloader.DownloadTask;
import org.telegram.api.engine.file.downloader.DownloadTaskBuffer;
import org.telegram.tl.TLBytes;

/**
 * Implementing the operations of downloader data into the buffer
 * 
 * @author <a href="mailto:onixbed@gmail.com">amaksimov</a>
 */
public class DownloaderBudder implements DownloaderOperation {

	private Downloader download;

	public DownloaderBudder(Downloader download) {
		this.download = download;
	}

	@Override
	public synchronized void onBlockDownloaded(DownloadBlock block, TLBytes data) {

		try {
			DownloadTaskBuffer taskBuffer = getTaskBuffer(block.task);
			if (taskBuffer.bytes != null) {
				int seek = block.index * block.task.blockSize;
				List<Byte> listByte = arrayByte2List(data.getData());
				taskBuffer.bytes.addAll(seek + data.getOffset(), listByte);
			} else {
				return;
			}
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
		DownloadTaskBuffer taskBuffer = getTaskBuffer(task);
		if (task.state != Downloader.FILE_COMPLETED) {
			Logger.d(download.getTag(), "File #" + task.taskId + "| Completed in "
					+ (System.nanoTime() - task.queueTime) / (1000 * 1000L) + " ms");
			task.state = Downloader.FILE_COMPLETED;
			if (taskBuffer.bytes != null) {
				// ничего не делаем
			}
			if (taskBuffer.listener != null) {
				taskBuffer.listener.onDownloaded(task);
			}
		}
		download.updateFileQueueStates();
	}

	@Override
	public synchronized void onTaskFailure(DownloadTask task) {
		DownloadTaskBuffer taskBuffer = getTaskBuffer(task);
		if (task.state != Downloader.FILE_FAILURE) {
			Logger.d(download.getTag(), "File #" + task.taskId + "| Failure in "
					+ (System.nanoTime() - task.queueTime) / (1000 * 1000L) + " ms");
			task.state = Downloader.FILE_FAILURE;
			if (taskBuffer.bytes != null) {
				taskBuffer.bytes = null;
			}
		}
		download.updateFileQueueStates();
	}

	private DownloadTaskBuffer getTaskBuffer(DownloadTask task) {
		DownloadTaskBuffer taskBuffer = null;
		if (task instanceof DownloadTaskBuffer) {
			taskBuffer = (DownloadTaskBuffer) task;
		}
		return taskBuffer;
	}

	/**
	 * Конвертировать массива байт в список байт
	 * 
	 * @param bytes
	 *            массив байт
	 * @return список байт
	 */
	public static List<Byte> arrayByte2List(byte[] bytes) {
		List<Byte> listBytes = new ArrayList<Byte>();
		for (byte aByte : bytes) {
			listBytes.add(aByte);
		}
		return listBytes;
	}

}
