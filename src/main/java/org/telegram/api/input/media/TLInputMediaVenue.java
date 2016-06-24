package org.telegram.api.input.media;

import org.telegram.api.input.geopoint.TLAbsInputGeoPoint;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media venue.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLInputMediaVenue
 * @date 02 of May of 2015
 */
public class TLInputMediaVenue extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2827a81a;

    // geo_point:InputGeoPoint title:string address:string provider:string venue_id:string
    private TLAbsInputGeoPoint inputGeoPoint;
    private String title;
    private String address;
    private String provider;
    private String venueId;

    /**
     * Instantiates a new TL input media venue.
     */
    public TLInputMediaVenue() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets input geo point.
     *
     * @return the input geo point
     */
    public TLAbsInputGeoPoint getInputGeoPoint() {
        return this.inputGeoPoint;
    }

    /**
     * Sets input geo point.
     *
     * @param inputGeoPoint the input geo point
     */
    public void setInputGeoPoint(TLAbsInputGeoPoint inputGeoPoint) {
        this.inputGeoPoint = inputGeoPoint;
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
     * Gets venue id.
     *
     * @return the venue id
     */
    public String getVenueId() {
        return this.venueId;
    }

    /**
     * Sets venue id.
     *
     * @param venueId the venue id
     */
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeTLObject(this.inputGeoPoint, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.address, stream);
        StreamingUtils.writeTLString(this.provider, stream);
        StreamingUtils.writeTLString(this.venueId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.inputGeoPoint = (TLAbsInputGeoPoint) StreamingUtils.readTLObject(stream, context);
        this.title = StreamingUtils.readTLString(stream);
        this.address = StreamingUtils.readTLString(stream);
        this.provider = StreamingUtils.readTLString(stream);
        this.venueId = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "input.mediaVenue#2827a81a";
    }

}
