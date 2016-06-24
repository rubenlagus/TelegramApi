package org.telegram.bot.kernel.engine.storage;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readInt;
import static org.telegram.tl.StreamingUtils.readTLString;
import static org.telegram.tl.StreamingUtils.writeInt;
import static org.telegram.tl.StreamingUtils.writeTLString;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 08.11.13
 * Time: 23:54
 */
public class TLDcInfo extends TLObject {

    public static final int CLASS_ID = 0x5d8c6cc;

    private int flags;
    private int dcId;
    private String address;
    private int port;
    private int version;

    public TLDcInfo(int flags, int dcId, String address, int port, int version) {
        this.flags = flags;
        this.dcId = dcId;
        this.address = address;
        this.port = port;
        this.version = version;
    }

    public TLDcInfo() {

    }

    public int getFlags() {
        return flags;
    }

    public int getDcId() {
        return dcId;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "dcInfo#5d8c6cc";
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeInt(flags, stream);
        writeInt(dcId, stream);
        writeTLString(address, stream);
        writeInt(port, stream);
        writeInt(version, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = readInt(stream);
        dcId = readInt(stream);
        address = readTLString(stream);
        port = readInt(stream);
        version = readInt(stream);
    }
}
