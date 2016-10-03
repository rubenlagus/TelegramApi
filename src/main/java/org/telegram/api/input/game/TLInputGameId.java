package org.telegram.api.input.game;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 04 of October of 2016
 */
public class TLInputGameId extends TLAbsInputGame {
    public static final int CLASS_ID = 0x32c3e77;

    private long id;
    private long accessHash;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(id, stream);
        StreamingUtils.writeLong(accessHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readLong(stream);
        accessHash = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "inputGameID#32c3e77";
    }
}
