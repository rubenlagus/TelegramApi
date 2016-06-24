/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.decryptedmessage;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Geopoint media content of an encrypted message
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLDecryptedMessageMediaGeoPoint extends TLAbsDecryptedMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x35480a59;

    private double latitude; ///< Latitude of point
    private double longtitude; ///< Longtitude of poin

    /**
     * Instantiates a new TL decrypted message media geo point.
     */
    public TLDecryptedMessageMediaGeoPoint() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longtitude.
     *
     * @return the longtitude
     */
    public double getLongtitude() {
        return this.longtitude;
    }

    /**
     * Sets longtitude.
     *
     * @param longtitude the longtitude
     */
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeDouble(this.latitude, stream);
        StreamingUtils.writeDouble(this.longtitude, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.latitude = StreamingUtils.readDouble(stream);
        this.longtitude = StreamingUtils.readDouble(stream);
    }

    @Override
    public String toString() {
        return "decryptedMessageMediaGeoPoint#35480a59";
    }
}