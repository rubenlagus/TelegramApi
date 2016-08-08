package org.telegram.api.chat.channel;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Channel information
 * @date 18 of September of 2015
 */
public class TLChannelForbidden extends TLAbsChat {
    public static final int CLASS_ID = 0x8537784f;

    private static final int FLAG_UNUSED_0           = 0x00000001; // 0
    private static final int FLAG_UNUSED_1           = 0x00000002; // 1
    private static final int FLAG_UNUSED_2           = 0x00000004; // 2
    private static final int FLAG_UNUSED_3           = 0x00000008; // 3
    private static final int FLAG_UNUSED_4           = 0x00000010; // 4
    private static final int FLAG_BROADCAST          = 0x00000020; // 5
    private static final int FLAG_UNUSED_6           = 0x00000040; // 6
    private static final int FLAG_UNUSED_7           = 0x00000080; // 7
    private static final int FLAG_MEGAGROUP          = 0x00000100; // 8

    private int flags;
    private long accessHash;
    private String title;

    public TLChannelForbidden() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public String getTitle() {
        return title;
    }

    public boolean isMegagroup() {
        return (flags & FLAG_MEGAGROUP) != 0;
    }

    public boolean isBroadcast() {
        return (flags & FLAG_BROADCAST) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeInt(id, stream);
        StreamingUtils.writeLong(accessHash, stream);
        StreamingUtils.writeTLString(title, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.title = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "channelForbidden#8537784f";
    }
}
