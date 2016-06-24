package org.telegram.api.message.media;

import org.telegram.api.geo.point.TLAbsGeoPoint;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message media geo.
 */
public class TLMessageMediaGeo extends TLAbsMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x56e0d474;
    private TLAbsGeoPoint geo;

    /**
     * Instantiates a new TL message media geo.
     */
    public TLMessageMediaGeo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets geo.
     *
     * @return the geo
     */
    public TLAbsGeoPoint getGeo() {
        return this.geo;
    }

    /**
     * Sets geo.
     *
     * @param geo the geo
     */
    public void setGeo(TLAbsGeoPoint geo) {
        this.geo = geo;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.geo, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.geo = ((TLAbsGeoPoint) StreamingUtils.readTLObject(stream, context));
    }

    @Override
    public String toString() {
        return "messageMediaGeo#56e0d474";
    }
}