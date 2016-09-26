package org.telegram.api;

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
 * @date 26 of September of 2016
 */
public class TLHighScore extends TLObject {
    public static final int CLASS_ID = 0x58fffcd0;

    private int pos;
    private int userId;
    private int score;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getPos() {
        return pos;
    }

    public int getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(pos, stream);
        StreamingUtils.writeInt(userId, stream);
        StreamingUtils.writeInt(score, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        pos = StreamingUtils.readInt(stream);
        userId = StreamingUtils.readInt(stream);
        score = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "highScore#58fffcd0";
    }
}
