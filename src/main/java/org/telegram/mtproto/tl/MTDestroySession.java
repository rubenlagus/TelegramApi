package org.telegram.mtproto.tl;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readLong;
import static org.telegram.tl.StreamingUtils.writeLong;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 07.11.13
 * Time: 8:50
 */
public class MTDestroySession extends TLObject {
    public static final int CLASS_ID = 0xe7512126;

    private Long session_id;

    public MTDestroySession(Long session_id) {
        this.session_id = session_id;
    }

    public MTDestroySession() {
    }

    public Long getSession_id() {
        return this.session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.session_id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.session_id = readLong(stream);
    }

    @Override
    public String toString() {
        return "MTDestroySession#e7512126";
    }
}
