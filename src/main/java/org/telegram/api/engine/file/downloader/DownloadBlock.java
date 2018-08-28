package org.telegram.api.engine.file.downloader;

/**
 * Download block structure
 * 
 */
public class DownloadBlock {
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