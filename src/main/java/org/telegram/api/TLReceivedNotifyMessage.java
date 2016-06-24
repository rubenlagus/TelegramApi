package org.telegram.api;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL received notify message.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLReceivedNotifyMessage
 * @date 02 of May of 2015
 */
public class TLReceivedNotifyMessage extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa384b779;

    private int id;
    private int flags;

    /**
     * Instantiates a new TL received notify message.
     */
    public TLReceivedNotifyMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets flags.
     *
     * @return the flags
     */
    public int getFlags() {
        return this.flags;
    }

    /**
     * Sets flags.
     *
     * @param flags the flags
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.id = StreamingUtils.readInt(stream);
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.receivedNotifyMessage#a384b779";
    }
}
