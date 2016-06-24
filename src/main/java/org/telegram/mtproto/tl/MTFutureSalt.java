package org.telegram.mtproto.tl;

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
 * Date: 07.11.13
 * Time: 8:00
 */
public class MTFutureSalt extends TLObject {

    public static final int CLASS_ID = 0x0949d9dc;

    private int validSince;
    private int validUntil;
    private long salt;

    public MTFutureSalt(int validSince, int validUntil, long salt) {
        this.validSince = validSince;
        this.validUntil = validUntil;
        this.salt = salt;
    }

    public MTFutureSalt() {

    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getValidSince() {
        return this.validSince;
    }

    public int getValidUntil() {
        return this.validUntil;
    }

    public long getSalt() {
        return this.salt;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeInt(this.validSince, stream);
        writeInt(this.validUntil, stream);
        writeLong(this.salt, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.validSince = readInt(stream);
        this.validUntil = readInt(stream);
        this.salt = readLong(stream);
    }

    @Override
    public String toString() {
        return "future_salt#0949d9dc";
    }
}
