package org.telegram.api.updates.difference;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.encrypted.message.TLAbsEncryptedMessage;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.update.TLAbsUpdate;
import org.telegram.api.updates.TLUpdatesState;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL difference slice.
 */
public class TLDifferenceSlice extends TLAbsDifference {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa8fb1981;

    private TLUpdatesState intermediateState;

    /**
     * Instantiates a new TL difference slice.
     */
    public TLDifferenceSlice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets intermediate state.
     *
     * @return the intermediate state
     */
    public TLUpdatesState getIntermediateState() {
        return this.intermediateState;
    }

    /**
     * Sets intermediate state.
     *
     * @param intermediateState the intermediate state
     */
    public void setIntermediateState(TLUpdatesState intermediateState) {
        this.intermediateState = intermediateState;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.newMessages, stream);
        StreamingUtils.writeTLVector(this.newEncryptedMessages, stream);
        StreamingUtils.writeTLVector(this.otherUpdates, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
        StreamingUtils.writeTLObject(this.intermediateState, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.newMessages = (TLVector<TLAbsMessage>) StreamingUtils.readTLVector(stream, context);
        this.newEncryptedMessages = (TLVector<TLAbsEncryptedMessage>) StreamingUtils.readTLVector(stream, context);
        this.otherUpdates = (TLVector<TLAbsUpdate>) StreamingUtils.readTLVector(stream, context);
        this.chats = (TLVector<TLAbsChat>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
        this.intermediateState = (TLUpdatesState) StreamingUtils.readTLObject(stream, context);
    }

    @Override
    public String toString() {
        return "updates.differenceSlice#a8fb1981";
    }
}