package org.telegram.api.input.media;

import org.telegram.api.input.geopoint.TLAbsInputGeoPoint;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media geo point.
 */
public class TLInputMediaGeoPoint extends TLAbsInputMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf9c44144;

    /**
     * The Geo point.
     */
    protected TLAbsInputGeoPoint geoPoint;

    /**
     * Instantiates a new TL input media geo point.
     */
    public TLInputMediaGeoPoint() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets geo point.
     *
     * @return the geo point
     */
    public TLAbsInputGeoPoint getGeoPoint() {
        return this.geoPoint;
    }

    /**
     * Sets geo point.
     *
     * @param value the value
     */
    public void setGeoPoint(TLAbsInputGeoPoint value) {
        this.geoPoint = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.geoPoint, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.geoPoint = ((TLAbsInputGeoPoint) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "inputMediaGeoPoint#f9c44144";
    }
}