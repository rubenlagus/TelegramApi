package org.telegram.api.engine.file.downloader;

import org.telegram.api.engine.file.downloader.DownloadBlock;
import org.telegram.api.engine.file.downloader.DownloadTask;
import org.telegram.tl.TLBytes;

/**
 * Operation interface for loading data
 * 
 * @author <a href="mailto:onixbed@gmail.com">amaksimov</a>
 */
public interface DownloaderOperation {

	void onBlockDownloaded(DownloadBlock block, TLBytes data);

	void onTaskCompleted(DownloadTask task);

	void onTaskFailure(DownloadTask task);
}
