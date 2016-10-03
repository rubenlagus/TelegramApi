package org.telegram.api.message.action;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TLMessageActionGameScore.
 */
public class TLMessageActionGameScore extends TLAbsMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x92a72876;

    private long gameId;
    private int score;

    /**
     * Instantiates a new TL message action pin message.
     */
    public TLMessageActionGameScore() {
        super();
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(gameId, stream);
        StreamingUtils.writeInt(score, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        gameId = StreamingUtils.readLong(stream);
        score = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messageActionGameScore#92a72876";
    }
}