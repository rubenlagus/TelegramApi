/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.chat;

import org.telegram.api.chat.photo.TLAbsChatPhoto;
import org.telegram.api.geo.point.TLAbsGeoPoint;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Information of a public chat connection to a location
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLGeoChat extends TLAbsChat {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x75eaea5a;

    private String title; ///< Chat title
    private int date; ///< Chat creation date
    private long accessHash; ///< Chat access hash
    private String address; ///< Chat address of the geographic point
    private String venue; ///< ID of a corresponding venue on foursquare
    private TLAbsGeoPoint geo; ///< Coordinates of geographic point
    private TLAbsChatPhoto photo; ///< Chat profile photo
    private int participantsCount; ///< Number of participants
    private boolean checkedIn; ///< true if the user checked in the geographic point
    private int version; ///< Geo chat version

    /**
     * Instantiates a new TL geo chat.
     */
    public TLGeoChat() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
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
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Gets access hash.
     *
     * @return the access hash
     */
    public long getAccessHash() {
        return this.accessHash;
    }

    /**
     * Sets access hash.
     *
     * @param accessHash the access hash
     */
    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
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
     * Gets venue.
     *
     * @return the venue
     */
    public String getVenue() {
        return this.venue;
    }

    /**
     * Sets venue.
     *
     * @param venue the venue
     */
    public void setVenue(String venue) {
        this.venue = venue;
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
     * Gets photo.
     *
     * @return the photo
     */
    public TLAbsChatPhoto getPhoto() {
        return this.photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(TLAbsChatPhoto photo) {
        this.photo = photo;
    }

    /**
     * Gets participants count.
     *
     * @return the participants count
     */
    public int getParticipantsCount() {
        return this.participantsCount;
    }

    /**
     * Sets participants count.
     *
     * @param participantsCount the participants count
     */
    public void setParticipantsCount(int participantsCount) {
        this.participantsCount = participantsCount;
    }

    /**
     * Is checked in.
     *
     * @return the boolean
     */
    public boolean isCheckedIn() {
        return this.checkedIn;
    }

    /**
     * Sets checked in.
     *
     * @param checkedIn the checked in
     */
    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.address, stream);
        StreamingUtils.writeTLString(this.venue, stream);
        StreamingUtils.writeTLObject(this.geo, stream);
        StreamingUtils.writeTLObject(this.photo, stream);
        StreamingUtils.writeInt(this.participantsCount, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLBool(this.checkedIn, stream);
        StreamingUtils.writeInt(this.version, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.address = StreamingUtils.readTLString(stream);
        this.venue = StreamingUtils.readTLString(stream);
        this.geo = ((TLAbsGeoPoint) StreamingUtils.readTLObject(stream, context));
        this.photo = ((TLAbsChatPhoto) StreamingUtils.readTLObject(stream, context));
        this.participantsCount = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.checkedIn = StreamingUtils.readTLBool(stream);
        this.version = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "geoChat#75eaea5a";
    }
}