package org.telegram.api.photo.size;

import org.telegram.api.file.location.TLAbsFileLocation;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL photo cached size.
 */
public class TLPhotoCachedSize extends TLAbsPhotoSize {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe9a734fa;
    private String type;
    private TLAbsFileLocation location;
    private int w;
    private int h;
    private TLBytes bytes;

    /**
     * Instantiates a new TL photo cached size.
     */
    public TLPhotoCachedSize() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
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
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public TLAbsFileLocation getLocation() {
        return this.location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(TLAbsFileLocation location) {
        this.location = location;
    }

    /**
     * Gets w.
     *
     * @return the w
     */
    public int getW() {
        return this.w;
    }

    /**
     * Sets w.
     *
     * @param w the w
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * Gets h.
     *
     * @return the h
     */
    public int getH() {
        return this.h;
    }

    /**
     * Sets h.
     *
     * @param h the h
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * Gets bytes.
     *
     * @return the bytes
     */
    public TLBytes getBytes() {
        return this.bytes;
    }

    /**
     * Sets bytes.
     *
     * @param bytes the bytes
     */
    public void setBytes(TLBytes bytes) {
        this.bytes = bytes;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.type, stream);
        StreamingUtils.writeTLObject(this.location, stream);
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
        StreamingUtils.writeTLBytes(this.bytes, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.type = StreamingUtils.readTLString(stream);
        this.location = ((TLAbsFileLocation) StreamingUtils.readTLObject(stream, context));
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
        this.bytes = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "photoCachedSize#e9a734fa";
    }
}