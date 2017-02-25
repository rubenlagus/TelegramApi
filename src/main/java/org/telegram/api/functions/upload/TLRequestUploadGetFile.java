package org.telegram.api.functions.upload;

import org.telegram.api.input.filelocation.TLAbsInputFileLocation;
import org.telegram.api.upload.file.TLAbsFile;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request upload get file.
 */
public class TLRequestUploadGetFile extends TLMethod<TLAbsFile> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe3a6cfb5;

    private TLAbsInputFileLocation location;
    private int offset;
    private int limit;

    /**
     * Instantiates a new TL request upload get file.
     */
    public TLRequestUploadGetFile() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsFile deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsFile))
            return (TLAbsFile) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsFile.class.getCanonicalName()
                + ", got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public TLAbsInputFileLocation getLocation() {
        return this.location;
    }

    /**
     * Sets location.
     *
     * @param value the value
     */
    public void setLocation(TLAbsInputFileLocation value) {
        this.location = value;
    }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Sets offset.
     *
     * @param value the value
     */
    public void setOffset(int value) {
        this.offset = value;
    }

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * Sets limit.
     *
     * @param value the value
     */
    public void setLimit(int value) {
        this.limit = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.location, stream);
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.location = ((TLAbsInputFileLocation) StreamingUtils.readTLObject(stream, context));
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "upload.getFile#e3a6cfb5";
    }
}