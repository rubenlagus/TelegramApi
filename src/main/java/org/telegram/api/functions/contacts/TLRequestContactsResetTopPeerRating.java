package org.telegram.api.functions.contacts;

import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.toppeer.category.TLAbsTopPeerCategory;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request contacts get contacts.
 */
public class TLRequestContactsResetTopPeerRating extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1ae373ac;

    private TLAbsTopPeerCategory category;
    private TLAbsInputPeer peer;

    /**
     * Instantiates a new TL request contacts get contacts.
     */
    public TLRequestContactsResetTopPeerRating() {
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
        throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public TLAbsTopPeerCategory getCategory() {
        return category;
    }

    public void setCategory(TLAbsTopPeerCategory category) {
        this.category = category;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(category, stream);
        StreamingUtils.writeTLObject(peer, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        category = StreamingUtils.readTLObject(stream, context, TLAbsTopPeerCategory.class);
        peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
    }

    public String toString() {
        return "contacts.resetTopPeerRating#1ae373ac";
    }
}