/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 12/11/14.
 */
package org.telegram.api.sendmessage.action;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL send message upload audio action.
 * @author Ruben Bermudez
 * @version 2.0
 */
public class TLSendMessageUploadRoundAction extends TLAbsSendMessageAction {
    public static final int CLASS_ID = 0x243e1c66;

    private int progress;

    public TLSendMessageUploadRoundAction() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeInt(this.progress, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.progress = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "sendMessageUploadRoundAction#243e1c66";
    }
}
