package org.telegram.api.update;

import org.telegram.api.draft.TLAbsDraftMessage;
import org.telegram.api.peer.TLAbsPeer;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel new message
 */
public class TLUpdateDraftMessage extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xee2bb969;

    private TLAbsPeer peer;
    private TLAbsDraftMessage draft;

    /**
     * Instantiates a new TL update channel new message
     */
    public TLUpdateDraftMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    public TLAbsDraftMessage getDraft() {
        return draft;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeTLObject(draft, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = (TLAbsPeer) StreamingUtils.readTLObject(stream, context);
        this.draft = (TLAbsDraftMessage) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "updateDraftMessage#ee2bb969";
    }
}