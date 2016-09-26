package org.telegram.api.sticker;

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
public class TLMaskCoords extends TLObject {
    public static final int CLASS_ID = 0xaed6dbb2;

    private int n;
    private int x;
    private int y;
    private double zoom;

    public TLMaskCoords() {
    }

    public int getN() {
        return n;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getZoom() {
        return zoom;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(n, stream);
        StreamingUtils.writeInt(x, stream);
        StreamingUtils.writeInt(y, stream);
        StreamingUtils.writeDouble(zoom, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        n = StreamingUtils.readInt(stream);
        x = StreamingUtils.readInt(stream);
        y = StreamingUtils.readInt(stream);
        zoom = StreamingUtils.readDouble(stream);
    }

    @Override
    public String toString() {
        return "maskCoords#aed6dbb2";
    }
}
