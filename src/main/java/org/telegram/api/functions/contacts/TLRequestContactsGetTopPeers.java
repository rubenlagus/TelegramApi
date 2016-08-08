package org.telegram.api.functions.contacts;

import org.telegram.api.contacts.toppeers.TLAbsContactsTopPeers;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request contacts get contacts.
 */
public class TLRequestContactsGetTopPeers extends TLMethod<TLAbsContactsTopPeers> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd4982db5;

    private static final int FLAG_CORRESPONDENTS     = 0x00000001; // 0
    private static final int FLAG_BOTS_PM            = 0x00000002; // 1
    private static final int FLAG_BOTS_INLINE        = 0x00000004; // 2
    private static final int FLAG_UNUSED_3           = 0x00000008; // 3
    private static final int FLAG_UNUSED_4           = 0x00000010; // 4
    private static final int FLAG_UNUSED_5           = 0x00000020; // 5
    private static final int FLAG_UNUSED_6           = 0x00000040; // 6
    private static final int FLAG_UNUSED_7           = 0x00000080; // 7
    private static final int FLAG_UNUSED_8           = 0x00000100; // 8
    private static final int FLAG_UNUSED_9           = 0x00000200; // 9
    private static final int FLAG_GROUPS             = 0x00000400; // 10
    private static final int FLAG_UNUSED_11          = 0x00000800; // 11
    private static final int FLAG_UNUSED_12          = 0x00001000; // 12
    private static final int FLAG_UNUSED_13          = 0x00002000; // 13
    private static final int FLAG_UNUSED_14          = 0x00004000; // 14
    private static final int FLAG_CHANNELS           = 0x00008000; // 15

    private int flags;
    private int offset;
    private int limit;
    private int hash;

    /**
     * Instantiates a new TL request contacts get contacts.
     */
    public TLRequestContactsGetTopPeers() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsContactsTopPeers deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsContactsTopPeers))
            return (TLAbsContactsTopPeers) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsContactsTopPeers.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public boolean hasCorrespondents() {
        return (flags & FLAG_CORRESPONDENTS) != 0;
    }

    public void enableCorrespondents() {
        flags |= FLAG_CORRESPONDENTS;
    }

    public boolean hasBotsPM() {
        return (flags & FLAG_BOTS_PM) != 0;
    }

    public void enableBotsPM() {
        flags |= FLAG_BOTS_PM;
    }

    public boolean hasBotsInline() {
        return (flags & FLAG_BOTS_INLINE) != 0;
    }

    public void enableBotsInline() {
        flags |= FLAG_BOTS_INLINE;
    }

    public boolean hasGroups() {
        return (flags & FLAG_GROUPS) != 0;
    }

    public void enableGroups() {
        flags |= FLAG_GROUPS;
    }

    public boolean hasChannels() {
        return (flags & FLAG_CHANNELS) != 0;
    }

    public void enableChannels() {
        flags |= FLAG_CHANNELS;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeInt(offset, stream);
        StreamingUtils.writeInt(limit, stream);
        StreamingUtils.writeInt(hash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        offset = StreamingUtils.readInt(stream);
        limit = StreamingUtils.readInt(stream);
        hash = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "contacts.getTopPeers#d4982db5";
    }
}