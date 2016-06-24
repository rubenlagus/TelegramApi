package org.telegram.api.updates.channel.differences;

import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief The abstract type TL updates channel differences.
 * @date 20 of September of 2015
 */
public abstract class TLAbsUpdatesChannelDifferences extends TLObject {
    protected int flags;
    protected int pts;
    protected int timeout;

    public TLAbsUpdatesChannelDifferences() {
        super();
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
