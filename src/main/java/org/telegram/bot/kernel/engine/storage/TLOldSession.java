package org.telegram.bot.kernel.engine.storage;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readBytes;
import static org.telegram.tl.StreamingUtils.writeByteArray;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 08.11.13
 * Time: 23:51
 */
public class TLOldSession extends TLObject {
    public static final int CLASS_ID = 0x8b973f91;

    private byte[] session;

    public TLOldSession(byte[] session) {
        this.session = session;
    }

    public TLOldSession() {

    }

    public byte[] getSession() {
        return session;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "oldSession#8b973f91";
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeByteArray(session, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        session = readBytes(8, stream);
    }
}
