package org.telegram.api.message.media;

import org.telegram.api.geo.point.TLAbsGeoPoint;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message media venue.
 */
public class TLMessageMediaVenue extends TLAbsMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7912b71f;

    private TLAbsGeoPoint geo;
    private String title;
    private String address;
    private String provider;
    private String venue_id;

    /**
     * Instantiates a new TL message media venue.
     */
    public TLMessageMediaVenue() {
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

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets provider.
     *
     * @return the provider
     */
    public String getProvider() {
        return this.provider;
    }

    /**
     * Sets provider.
     *
     * @param provider the provider
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * Gets venue _ id.
     *
     * @return the venue _ id
     */
    public String getVenue_id() {
        return this.venue_id;
    }

    /**
     * Sets venue _ id.
     *
     * @param venue_id the venue _ id
     */
    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.geo, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.address, stream);
        StreamingUtils.writeTLString(this.provider, stream);
        StreamingUtils.writeTLString(this.venue_id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.geo = ((TLAbsGeoPoint) StreamingUtils.readTLObject(stream, context));
        this.title = StreamingUtils.readTLString(stream);
        this.address = StreamingUtils.readTLString(stream);
        this.provider = StreamingUtils.readTLString(stream);
        this.venue_id = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "messageMediaVenue#7912b71f";
    }
}