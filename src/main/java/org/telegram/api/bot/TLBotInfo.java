package org.telegram.api.bot;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of July of 2015
 */
public class TLBotInfo extends TLObject {
    public static final int CLASS_ID = 0x98e81d3a;

    private int userId;
    private String description;
    private TLVector<TLBotCommand> commands = new TLVector<>();

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TLVector<TLBotCommand> getCommands() {
        return this.commands;
    }

    public void setCommands(TLVector<TLBotCommand> commands) {
        this.commands = commands;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLVector(this.commands, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.description = StreamingUtils.readTLString(stream);
        this.commands = (TLVector<TLBotCommand>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "bot.BotInfo#98e81d3a";
    }
}
