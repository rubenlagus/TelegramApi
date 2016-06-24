package org.telegram.api.wallpaper;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL wall paper solid.
 */
public class TLWallPaperSolid extends TLAbsWallPaper {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x63117f24;

    private int bgColor;

    /**
     * Instantiates a new TL wall paper solid.
     */
    public TLWallPaperSolid() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets bg color.
     *
     * @return the bg color
     */
    public int getBgColor() {
        return this.bgColor;
    }

    /**
     * Sets bg color.
     *
     * @param bgColor the bg color
     */
    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeInt(this.bgColor, stream);
        StreamingUtils.writeInt(this.color, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.bgColor = StreamingUtils.readInt(stream);
        this.color = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "wallPaperSolid#63117f24";
    }
}