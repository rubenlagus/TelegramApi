package org.telegram.api.engine.file.downloader;

import java.io.RandomAccessFile;

/**
 * The type Download task in the file.
 * 
 */
public class DownloadTaskFile extends DownloadTask {

	/**
	 * The Dest file.
	 */
	public String destFile;

	/**
	 * The File.
	 */
	public RandomAccessFile file;
}
