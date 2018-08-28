package org.telegram.api.engine.file.downloader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Download task in the buffer.
 * 
 */
public class DownloadTaskBuffer extends DownloadTask {

	private Map<Integer, List<Byte>> index2bytes = new HashMap<Integer, List<Byte>>();
	private List<Byte> bytes;

	public Map<Integer, List<Byte>> getIndex2bytes() {
		return index2bytes;
	}

	public List<Byte> getBytes() {
		return bytes;
	}

	public void setBytes(List<Byte> bytes) {
		this.bytes = bytes;
	}

}
