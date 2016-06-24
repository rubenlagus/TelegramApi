package org.telegram.bot.kernel.engine.storage;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readInt;
import static org.telegram.tl.StreamingUtils.readLong;
import static org.telegram.tl.StreamingUtils.writeInt;
import static org.telegram.tl.StreamingUtils.writeLong;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 08.11.13
 * Time: 23:43
 */
public class TLLastKnownSalt extends TLObject {

    public static final int CLASS_ID = 0x0f054c60;

    private int validSince;
    private int validUntil;
    private long salt;

    public TLLastKnownSalt(int validSince, int validUntil, long salt) {
        this.validSince = validSince;
        this.validUntil = validUntil;
        this.salt = salt;
    }

    public TLLastKnownSalt() {

    }

    public int getValidSince() {
        return validSince;
    }

    public int getValidUntil() {
        return validUntil;
    }

    public long getSalt() {
        return salt;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "lastKnownSalt#0f054c60";
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeInt(validSince, stream);
        writeInt(validUntil, stream);
        writeLong(salt, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        validSince = readInt(stream);
        validUntil = readInt(stream);
        salt = readLong(stream);
    }
}
