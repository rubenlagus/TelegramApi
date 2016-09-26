package org.telegram.api.keyboard.button;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Represetation of a button in keyboard
 * @date 07 of July of 2015
 */
public class TLKeyboardButtonGame extends TLAbsKeyboardButton {
    public static final int CLASS_ID = 0x28fc3164;

    private String text;
    private String gameTitle;
    private int gameId;
    private String startParam;

    public TLKeyboardButtonGame() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String getText() {
        return text;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public int getGameId() {
        return gameId;
    }

    public String getStartParam() {
        return startParam;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(text, stream);
        StreamingUtils.writeTLString(gameTitle, stream);
        StreamingUtils.writeInt(gameId, stream);
        StreamingUtils.writeTLString(startParam, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLString(stream);
        this.gameTitle = StreamingUtils.readTLString(stream);
        this.gameId = StreamingUtils.readInt(stream);
        this.startParam = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "keyboardButtonGame#28fc3164";
    }
}
