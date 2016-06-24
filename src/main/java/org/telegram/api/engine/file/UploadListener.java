package org.telegram.api.engine.file;

/**
 * Created by Ruben Bermudez on 19.11.13.
 */
public interface UploadListener {
    /**
     * On part uploaded.
     *
     * @param percent the percent
     * @param downloadedSize the downloaded size
     */
    void onPartUploaded(int percent, int downloadedSize);

    /**
     * On uploaded.
     *
     * @param task the task
     */
    void onUploaded(Uploader.UploadTask task);
}
