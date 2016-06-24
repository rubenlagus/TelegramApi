package org.telegram.api.decryptedmessage;

import org.telegram.api.sendmessage.action.TLAbsSendMessageAction;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message action typing.
 */
public class TLDecryptedMessageActionTyping extends TLDecryptedMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc4f40be;

    private TLAbsSendMessageAction action;

    /**
     * Instantiates a new TL decrypted message action typing.
     */
    public TLDecryptedMessageActionTyping() {
    }

    /**
     * Instantiates a new TL decrypted message action typing.
     *
     * @param action the action
     */
    public TLDecryptedMessageActionTyping(TLAbsSendMessageAction action) {
        this.action = action;
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets action.
     *
     * @return the action
     */
    public TLAbsSendMessageAction getAction() {
        return this.action;
    }

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(TLAbsSendMessageAction action) {
        this.action = action;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.action, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.action = (TLAbsSendMessageAction) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "decryptedMessageActionSetMessageTTL#c4f40be";
    }
}