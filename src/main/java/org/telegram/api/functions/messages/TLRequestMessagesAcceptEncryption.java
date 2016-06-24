package org.telegram.api.functions.messages;

import org.telegram.api.encrypted.chat.TLAbsEncryptedChat;
import org.telegram.api.input.encrypted.TLInputEncryptedChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBytes;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages accept encryption.
 */
public class TLRequestMessagesAcceptEncryption extends TLMethod<TLAbsEncryptedChat> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3dbc0415;

    private TLInputEncryptedChat peer;
    private TLBytes gB;
    private long keyFingerprint;

    /**
     * Instantiates a new TL request messages accept encryption.
     */
    public TLRequestMessagesAcceptEncryption() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsEncryptedChat deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsEncryptedChat))
            return (TLAbsEncryptedChat) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.encrypted.chat.TLAbsEncryptedChat, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLInputEncryptedChat getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLInputEncryptedChat value) {
        this.peer = value;
    }

    /**
     * Gets gB.
     *
     * @return the gB
     */
    public TLBytes getGB() {
        return this.gB;
    }

    /**
     * Sets gB.
     *
     * @param value the value
     */
    public void setGB(TLBytes value) {
        this.gB = value;
    }

    /**
     * Gets key fingerprint.
     *
     * @return the key fingerprint
     */
    public long getKeyFingerprint() {
        return this.keyFingerprint;
    }

    /**
     * Sets key fingerprint.
     *
     * @param value the value
     */
    public void setKeyFingerprint(long value) {
        this.keyFingerprint = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLBytes(this.gB, stream);
        StreamingUtils.writeLong(this.keyFingerprint, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLInputEncryptedChat) StreamingUtils.readTLObject(stream, context));
        this.gB = StreamingUtils.readTLBytes(stream, context);
        this.keyFingerprint = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "messages.acceptEncryption#3dbc0415";
    }
}