package org.telegram.api.update;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update chat participant delete.
 */
public class TLUpdateChatParticipantDelete extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6e5f8c22;
    private int chatId;
    private int userId;
    private int version;

    /**
     * Instantiates a new TL update chat participant delete.
     */
    public TLUpdateChatParticipantDelete() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets chat id.
     *
     * @return the chat id
     */
    public int getChatId() {
        return this.chatId;
    }

    /**
     * Sets chat id.
     *
     * @param chatId the chat id
     */
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeInt(this.version, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "updateChatParticipantDelete#6e5f8c22";
    }
}