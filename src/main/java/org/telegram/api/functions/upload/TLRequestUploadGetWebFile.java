package org.telegram.api.functions.upload;

import org.telegram.api.input.paymentapi.TLInputWebFileLocation;
import org.telegram.api.upload.TLWebFile;
import org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestUploadGetWebFile extends TLMethod<TLWebFile> {
    public static final int CLASS_ID = 0x24e6818d;

    private TLInputWebFileLocation location;
    private int offset;
    private int limit;

    public TLRequestUploadGetWebFile() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLWebFile deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLWebFile))
            return (TLWebFile) res;
        throw new IOException("Incorrect response type. Expected " + TLWebFile.class.getCanonicalName()
                + ", got: " + res.getClass().getCanonicalName());
    }

    public TLInputWebFileLocation getLocation() {
        return location;
    }

    public void setLocation(TLInputWebFileLocation location) {
        this.location = location;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.location, stream);
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.location = StreamingUtils.readTLObject(stream, context, TLInputWebFileLocation.class);
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "upload.getWebFile#24e6818d";
    }
}