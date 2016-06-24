/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.notify.peer;

import org.telegram.api.peer.TLAbsPeer;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL notify peer.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLNotifyPeer
 * @date 13 /11/14
 */
public class TLNotifyPeer extends TLAbsNotifyPeer {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9fd40bd8;

    private TLAbsPeer peer;

    /**
     * Instantiates a new TL notify peer.
     */
    public TLNotifyPeer() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.peer = (TLAbsPeer) StreamingUtils.readTLObject(stream, context);
    }

    @Override
    public String toString() {
        return "notifyPeer#9fd40bd8";
    }
}
