package org.telegram.api.functions.messages;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.sendmessage.action.TLAbsSendMessageAction;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages set typing.
 */
public class TLRequestMessagesSetTyping extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa3825e50;

    private TLAbsInputPeer peer;
    private TLAbsSendMessageAction action;

    /**
     * Instantiates a new TL request messages set typing.
     */
    public TLRequestMessagesSetTyping() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLAbsInputPeer value) {
        this.peer = value;
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
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.action, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = ((TLAbsInputPeer) StreamingUtils.readTLObject(stream, context));
        this.action = (TLAbsSendMessageAction) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "messages.setTyping#a3825e50";
    }
}