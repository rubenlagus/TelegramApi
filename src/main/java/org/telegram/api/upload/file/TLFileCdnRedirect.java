package org.telegram.api.upload.file;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLFileCdnRedirect extends TLAbsFile {
    public static final int CLASS_ID = 0x1508485a;

    private int dcId;
    private TLBytes fileToken;
    private TLBytes encryptionKey;
    private TLBytes encryptionIv;

    public TLFileCdnRedirect() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getDcId() {
        return dcId;
    }

    public TLBytes getEncryptionKey() {
        return encryptionKey;
    }

    public TLBytes getFileToken() {
        return fileToken;
    }

    public TLBytes getEncryptionIv() {
        return encryptionIv;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeTLBytes(this.fileToken, stream);
        StreamingUtils.writeTLBytes(this.encryptionKey, stream);
        StreamingUtils.writeTLBytes(this.encryptionIv, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.dcId = StreamingUtils.readInt(stream);
        this.fileToken = StreamingUtils.readTLBytes(stream, context);
        this.encryptionKey = StreamingUtils.readTLBytes(stream, context);
        this.encryptionIv = StreamingUtils.readTLBytes(stream, context);
    }

    public String toString() {
        return "upload.fileCdnRedirect#1508485a";
    }
}