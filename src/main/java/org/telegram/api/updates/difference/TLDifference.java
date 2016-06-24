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
 * The type TL difference.
 */
public class TLDifference extends TLAbsDifference {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf49ca0;

    private TLUpdatesState state;

    /**
     * Instantiates a new TL difference.
     */
    public TLDifference() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public TLUpdatesState getState() {
        return this.state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(TLUpdatesState state) {
        this.state = state;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.newMessages, stream);
        StreamingUtils.writeTLVector(this.newEncryptedMessages, stream);
        StreamingUtils.writeTLVector(this.otherUpdates, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
        StreamingUtils.writeTLObject(this.state, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.newMessages = (TLVector<TLAbsMessage>) StreamingUtils.readTLVector(stream, context);
        this.newEncryptedMessages = (TLVector<TLAbsEncryptedMessage>) StreamingUtils.readTLVector(stream, context);
        this.otherUpdates = (TLVector<TLAbsUpdate>) StreamingUtils.readTLVector(stream, context);
        this.chats = (TLVector<TLAbsChat>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
        this.state = ((TLUpdatesState) StreamingUtils.readTLObject(stream, context));
    }

    @Override
    public String toString() {
        return "updates.difference#f49ca0";
    }
}