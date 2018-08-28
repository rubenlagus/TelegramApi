package org.telegram.api.engine.file.downloader;


import org.telegram.api.engine.file.DownloadListener;
import org.telegram.api.input.filelocation.TLAbsInputFileLocation;
import org.telegram.api.storage.file.TLAbsFileType;

/**
 * Structure for storing tasks for download data
 * 
 */
public abstract class DownloadTask {


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
     * The Last success block.
     */
    public long lastSuccessBlock;

    /**
     * The Type.
     */
    public TLAbsFileType type;
}
