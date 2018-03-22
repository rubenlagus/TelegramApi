package org.telegram.api.engine.file;

import org.telegram.api.engine.file.downloader.DownloadTask;

/**
 * Created by Ruben Bermudez on 18.11.13.
 */
public interface DownloadListener {
    /**
     * On part downloaded.
     *
     * @param percent the percent
     * @param downloadedSize the downloaded size
     */
    void onPartDownloaded(int percent, int downloadedSize);

    /**
     * On downloaded.
     *
     * @param task the task
     */
    void onDownloaded(DownloadTask task);

    /**
     * On failed.
     */
    void onFailed();
}
