package org.telegram.api.phone.call;

import org.telegram.api.phone.call.discardreason.TLAbsPhoneCallDiscardReason;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPhoneCallDiscarded extends TLAbsPhoneCall {
    public static final int CLASS_ID = 0x50ca4de1;

    private static final int FLAG_DISCARD_REASON           = 0x00000001; // 0
    private static final int FLAG_DURATION                 = 0x00000002; // 1
    private static final int FLAG_NEED_RATING              = 0x00000002; // 1

    private int flags;
    private long id;
    private TLAbsPhoneCallDiscardReason reason;
    private int duration;

    public TLPhoneCallDiscarded() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public TLAbsPhoneCallDiscardReason getReason() {
        return reason;
    }

    public int getDuration() {
        return duration;
    }

    public boolean needRatting() {
        return (flags & FLAG_NEED_RATING) != 0;
    }

    public boolean hasDuration() {
        return (flags & FLAG_DURATION) != 0;
    }

    public boolean hasDiscardReason() {
        return (flags & FLAG_DISCARD_REASON) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeLong(id, stream);
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
        id = StreamingUtils.readLong(stream);
        if ((flags & FLAG_DISCARD_REASON) != 0) {
            reason = StreamingUtils.readTLObject(stream, context, TLAbsPhoneCallDiscardReason.class);
        }
        if ((flags & FLAG_DURATION) != 0) {
            duration = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "phoneCallDiscarded#50ca4de1";
    }
}
