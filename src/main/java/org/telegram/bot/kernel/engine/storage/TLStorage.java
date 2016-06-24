package org.telegram.bot.kernel.engine.storage;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.telegram.tl.StreamingUtils.readInt;
import static org.telegram.tl.StreamingUtils.readTLBool;
import static org.telegram.tl.StreamingUtils.readTLString;
import static org.telegram.tl.StreamingUtils.readTLVector;
import static org.telegram.tl.StreamingUtils.writeInt;
import static org.telegram.tl.StreamingUtils.writeTLBool;
import static org.telegram.tl.StreamingUtils.writeTLString;
import static org.telegram.tl.StreamingUtils.writeTLVector;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 08.11.13
 * Time: 23:38
 */
@SuppressWarnings("ALL")
public class TLStorage extends TLObject {

    public static final int CLASS_ID = 0x3ef9c4b4;

    private TLVector keys;

    private TLVector dcInfos;

    private int primaryDc;

    private boolean isAuthorized;

    private int uid;

    private String phone;

    public TLStorage() {
        keys = new TLVector<>();
        dcInfos = new TLVector<>();
        primaryDc = 1;
        isAuthorized = false;
        uid = 0;
        phone = "";
    }

    public TLVector<TLKey> getKeys() {
        return keys;
    }

    public TLVector<TLDcInfo> getDcInfos() {
        return dcInfos;
    }

    public int getPrimaryDc() {
        return primaryDc;
    }

    public void setPrimaryDc(int primaryDc) {
        this.primaryDc = primaryDc;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "storage#3ef9c4b4";
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeTLVector(keys, stream);
        writeTLVector(dcInfos, stream);
        writeInt(primaryDc, stream);
        writeTLBool(isAuthorized, stream);
        writeInt(uid, stream);
        writeTLString(phone, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        keys = readTLVector(stream, context);
        dcInfos = readTLVector(stream, context);
        primaryDc = readInt(stream);
        isAuthorized = readTLBool(stream);
        uid = readInt(stream);
        phone = readTLString(stream);
    }
}