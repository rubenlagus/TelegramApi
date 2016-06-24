package org.telegram.api.input;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input app event.
 */
public class TLInputAppEvent extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x770656a8;

    private double time;
    private String type;
    private long peer;
    private String data;

    /**
     * Instantiates a new TL input app event.
     */
    public TLInputAppEvent() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public double getTime() {
        return this.time;
    }

    /**
     * Sets time.
     *
     * @param value the value
     */
    public void setTime(double value) {
        this.time = value;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets type.
     *
     * @param value the value
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public long getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(long value) {
        this.peer = value;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public String getData() {
        return this.data;
    }

    /**
     * Sets data.
     *
     * @param value the value
     */
    public void setData(String value) {
        this.data = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeDouble(this.time, stream);
        StreamingUtils.writeTLString(this.type, stream);
        StreamingUtils.writeLong(this.peer, stream);
        StreamingUtils.writeTLString(this.data, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.time = StreamingUtils.readDouble(stream);
        this.type = StreamingUtils.readTLString(stream);
        this.peer = StreamingUtils.readLong(stream);
        this.data = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "inputAppEvent#770656a8";
    }
}