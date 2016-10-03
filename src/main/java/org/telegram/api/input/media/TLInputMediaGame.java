package org.telegram.api.input.media;

import org.telegram.api.input.game.TLAbsInputGame;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media photo.
 */
public class TLInputMediaGame extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd33f43f3;

    private TLAbsInputGame id;

    /**
     * Instantiates a new TL input media photo.
     */
    public TLInputMediaGame() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputGame getId() {
        return id;
    }

    public void setId(TLAbsInputGame id) {
        this.id = id;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        id = StreamingUtils.readTLObject(stream, context, TLAbsInputGame.class);
    }

    public String toString() {
        return "inputMediaGame#d33f43f3";
    }
}