package org.telegram.api.input.photo.crop;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input photo crop.
 */
public class TLInputPhotoCrop extends TLAbsInputPhotoCrop {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd9915325;

    private double cropLeft;
    private double cropTop;
    private double cropWidth;

    /**
     * Instantiates a new TL input photo crop.
     */
    public TLInputPhotoCrop() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets crop left.
     *
     * @return the crop left
     */
    public double getCropLeft() {
        return this.cropLeft;
    }

    /**
     * Sets crop left.
     *
     * @param cropLeft the crop left
     */
    public void setCropLeft(double cropLeft) {
        this.cropLeft = cropLeft;
    }

    /**
     * Gets crop top.
     *
     * @return the crop top
     */
    public double getCropTop() {
        return this.cropTop;
    }

    /**
     * Sets crop top.
     *
     * @param cropTop the crop top
     */
    public void setCropTop(double cropTop) {
        this.cropTop = cropTop;
    }

    /**
     * Gets crop width.
     *
     * @return the crop width
     */
    public double getCropWidth() {
        return this.cropWidth;
    }

    /**
     * Sets crop width.
     *
     * @param cropWidth the crop width
     */
    public void setCropWidth(double cropWidth) {
        this.cropWidth = cropWidth;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeDouble(this.cropLeft, stream);
        StreamingUtils.writeDouble(this.cropTop, stream);
        StreamingUtils.writeDouble(this.cropWidth, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.cropLeft = StreamingUtils.readDouble(stream);
        this.cropTop = StreamingUtils.readDouble(stream);
        this.cropWidth = StreamingUtils.readDouble(stream);
    }

    public String toString() {
        return "inputPhotoCrop#d9915325";
    }
}