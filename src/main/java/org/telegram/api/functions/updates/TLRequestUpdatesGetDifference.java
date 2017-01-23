package org.telegram.api.functions.updates;

import org.telegram.api.updates.difference.TLAbsDifference;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TLRequestUpdatesGetDifference extends TLMethod<TLAbsDifference> {
    public static final int CLASS_ID = 0x25939651;

    private static final int FLAG_PTS_TOTAL_LIMIT    = 0x00000001; // 0

    private int flags;
    private int pts;
    private int ptsTotalLimit;
    private int date;
    private int qts;


    public TLRequestUpdatesGetDifference() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsDifference deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsDifference))
            return (TLAbsDifference) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsDifference.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    public int getFlags() {
        return flags;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getPtsTotalLimit() {
        return ptsTotalLimit;
    }

    public void setPtsTotalLimit(int ptsTotalLimit) {
        this.ptsTotalLimit = ptsTotalLimit > 0 ? ptsTotalLimit : 0;
        setPtsTotalLimit(ptsTotalLimit > 0);
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getQts() {
        return qts;
    }

    public void setQts(int qts) {
        this.qts = qts;
    }

    private void setPtsTotalLimit(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_PTS_TOTAL_LIMIT;
        } else {
            this.flags &= ~FLAG_PTS_TOTAL_LIMIT;
        }
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.pts, stream);
        if ((flags & FLAG_PTS_TOTAL_LIMIT) != 0) {
            StreamingUtils.writeInt(this.ptsTotalLimit, stream);
        }
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.qts, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        if ((flags & FLAG_PTS_TOTAL_LIMIT) != 0) {
            this.ptsTotalLimit = StreamingUtils.readInt(stream);
        }
        this.date = StreamingUtils.readInt(stream);
        this.qts = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updates.getDifference#25939651";
    }
}
