package org.telegram.api.input.game;

import org.telegram.api.input.user.TLAbsInputUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TLInputGameShortName
 * @date 04 of October of 2016
 */
public class TLInputGameShortName extends TLAbsInputGame {
    public static final int CLASS_ID = 0xc331e80a;

    private TLAbsInputUser botId;
    private String shortName;

    public TLAbsInputUser getBotId() {
        return botId;
    }

    public void setBotId(TLAbsInputUser botId) {
        this.botId = botId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(botId, stream);
        StreamingUtils.writeTLString(shortName, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        botId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        shortName = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inputGameShortName#c331e80a";
    }
}
