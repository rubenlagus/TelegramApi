package org.telegram.api.message.action;

import org.telegram.api.phone.call.discardreason.TLAbsPhoneCallDiscardReason;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class TLMessageActionPhoneCall extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x80e11a7f;

    private static final int FLAG_DISCARD_REASON           = 0x00000001; // 0
    private static final int FLAG_DURATION                 = 0x00000002; // 1

    private int flags;
    private long callId;
    private TLAbsPhoneCallDiscardReason reason;
    private int duration;

    public TLMessageActionPhoneCall() {
        super();
    }

    public int getFlags() {
        return flags;
    }

    public long getCallId() {
        return callId;
    }

    public TLAbsPhoneCallDiscardReason getReason() {
        return reason;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeLong(callId, stream);
        if ((flags & FLAG_DISCARD_REASON) != 0) {
            StreamingUtils.writeTLObject(reason, stream);
        }
        if ((flags & FLAG_DURATION) != 0) {
            StreamingUtils.writeInt(duration, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        callId = StreamingUtils.readLong(stream);
        if ((flags & FLAG_DISCARD_REASON) != 0) {
            reason = StreamingUtils.readTLObject(stream, context, TLAbsPhoneCallDiscardReason.class);
        }
        if ((flags & FLAG_DURATION) != 0) {
            duration = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "messageActionPhoneCall#80e11a7f";
    }
}