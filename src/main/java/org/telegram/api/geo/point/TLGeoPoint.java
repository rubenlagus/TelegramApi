package org.telegram.api.geo.point;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL geo point.
 */
public class TLGeoPoint extends TLAbsGeoPoint {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2049d70c;

    private double lon;
    private double lat;

    /**
     * Instantiates a new TL geo point.
     */
    public TLGeoPoint() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets lon.
     *
     * @return the lon
     */
    public double getLon() {
        return this.lon;
    }

    /**
     * Sets lon.
     *
     * @param lon the lon
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * Gets lat.
     *
     * @return the lat
     */
    public double getLat() {
        return this.lat;
    }

    /**
     * Sets lat.
     *
     * @param lat the lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeDouble(this.lon, stream);
        StreamingUtils.writeDouble(this.lat, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.lon = StreamingUtils.readDouble(stream);
        this.lat = StreamingUtils.readDouble(stream);
    }

    @Override
    public String toString() {
        return "geoPoint#2049d70c";
    }
}