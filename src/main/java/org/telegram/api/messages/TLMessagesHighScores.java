package org.telegram.api.messages;

import org.telegram.api.TLHighScore;
import org.telegram.api.user.TLAbsUser;
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
 * @date 26 of September of 2016
 */
public class TLMessagesHighScores extends TLObject {
    public static final int CLASS_ID = 0x9a3bfd99;

    private TLVector<TLHighScore> scores;
    private TLVector<TLAbsUser> users;

    public TLMessagesHighScores() {
        super();
    }

    public TLVector<TLHighScore> getScores() {
        return scores;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(scores, stream);
        StreamingUtils.writeTLVector(users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        scores = StreamingUtils.readTLVector(stream, context, TLHighScore.class);
        users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "messages.highScores#9a3bfd99";
    }
}
