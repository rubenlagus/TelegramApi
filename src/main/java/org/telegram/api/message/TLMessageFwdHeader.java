package org.telegram.api.message;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 17 of March of 2016
 */
public class TLMessageFwdHeader extends TLObject {
    public static final int CLASS_ID = 0xc786ddcb;

    private static final int FLAG_FROM_ID           = 0x00000001; // 0
    private static final int FLAG_CHANNEL_ID        = 0x00000002; // 1
    private static final int FLAG_CHANNEL_POST      = 0x00000004; // 2

    private int flags;
    private int fromId;
    private int date;
    private int channelId;
    private int channelPost;

    public TLMessageFwdHeader() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getChannelPost() {
        return channelPost;
    }

    public void setChannelPost(int channelPost) {
        this.channelPost = channelPost;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        if ((flags & FLAG_FROM_ID) != 0) {
            StreamingUtils.writeInt(fromId, stream);
        }
        StreamingUtils.writeInt(date, stream);
        if ((flags & FLAG_CHANNEL_ID) != 0) {
            StreamingUtils.writeInt(channelId, stream);
        }
        if ((flags & FLAG_CHANNEL_POST) != 0) {
            StreamingUtils.writeInt(channelPost, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        if ((flags & FLAG_FROM_ID) != 0) {
            fromId = StreamingUtils.readInt(stream);
        }
        date = StreamingUtils.readInt(stream);
        if ((flags & FLAG_CHANNEL_ID) != 0) {
            channelId = StreamingUtils.readInt(stream);
        }
        if ((flags & FLAG_CHANNEL_POST) != 0) {
            channelPost = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "message.messageFwdHeader#c786ddcb";
    }
}
