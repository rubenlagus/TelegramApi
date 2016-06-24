package org.telegram.api;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL authorization.
 * @author Ruben Bermudez
 * @version 2.0
 * @brief TLAuthorization
 * @date 11 of April of 2015
 */
public class TLAuthorization extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7bf2e6f6;

    private long hash;
    private int flags;
    private String deviceModel;
    private String platform;
    private String systemVersion;
    private int apiId;
    private String appName;
    private String appVersion;
    private int dateCreated;
    private int dateActive;
    private String ip;
    private String country;
    private String region;

    /**
     * Instantiates a new TL authorization.
     */
    public TLAuthorization() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets hash.
     *
     * @return the hash
     */
    public long getHash() {
        return this.hash;
    }

    /**
     * Sets hash.
     *
     * @param hash the hash
     */
    public void setHash(long hash) {
        this.hash = hash;
    }

    /**
     * Gets flags.
     *
     * @return the flags
     */
    public int getFlags() {
        return this.flags;
    }

    /**
     * Sets flags.
     *
     * @param flags the flags
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Gets device model.
     *
     * @return the device model
     */
    public String getDeviceModel() {
        return this.deviceModel;
    }

    /**
     * Sets device model.
     *
     * @param deviceModel the device model
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    /**
     * Gets platform.
     *
     * @return the platform
     */
    public String getPlatform() {
        return this.platform;
    }

    /**
     * Sets platform.
     *
     * @param platform the platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * Gets system version.
     *
     * @return the system version
     */
    public String getSystemVersion() {
        return this.systemVersion;
    }

    /**
     * Sets system version.
     *
     * @param systemVersion the system version
     */
    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    /**
     * Gets api id.
     *
     * @return the api id
     */
    public int getApiId() {
        return this.apiId;
    }

    /**
     * Sets api id.
     *
     * @param apiId the api id
     */
    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    /**
     * Gets app name.
     *
     * @return the app name
     */
    public String getAppName() {
        return this.appName;
    }

    /**
     * Sets app name.
     *
     * @param appName the app name
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Gets app version.
     *
     * @return the app version
     */
    public String getAppVersion() {
        return this.appVersion;
    }

    /**
     * Sets app version.
     *
     * @param appVersion the app version
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * Gets date created.
     *
     * @return the date created
     */
    public int getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Sets date created.
     *
     * @param dateCreated the date created
     */
    public void setDateCreated(int dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets date active.
     *
     * @return the date active
     */
    public int getDateActive() {
        return this.dateActive;
    }

    /**
     * Sets date active.
     *
     * @param dateActive the date active
     */
    public void setDateActive(int dateActive) {
        this.dateActive = dateActive;
    }

    /**
     * Gets ip.
     *
     * @return the ip
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Sets ip.
     *
     * @param ip the ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets region.
     *
     * @return the region
     */
    public String getRegion() {
        return this.region;
    }

    /**
     * Sets region.
     *
     * @param region the region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.hash, stream);
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.deviceModel, stream);
        StreamingUtils.writeTLString(this.platform, stream);
        StreamingUtils.writeTLString(this.systemVersion, stream);
        StreamingUtils.writeInt(this.apiId, stream);
        StreamingUtils.writeTLString(this.appName, stream);
        StreamingUtils.writeTLString(this.appVersion, stream);
        StreamingUtils.writeInt(this.dateCreated, stream);
        StreamingUtils.writeInt(this.dateActive, stream);
        StreamingUtils.writeTLString(this.ip, stream);
        StreamingUtils.writeTLString(this.country, stream);
        StreamingUtils.writeTLString(this.region, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.hash = StreamingUtils.readLong(stream);
        this.flags = StreamingUtils.readInt(stream);
        this.deviceModel = StreamingUtils.readTLString(stream);
        this.platform = StreamingUtils.readTLString(stream);
        this.systemVersion = StreamingUtils.readTLString(stream);
        this.apiId = StreamingUtils.readInt(stream);
        this.appName = StreamingUtils.readTLString(stream);
        this.appVersion = StreamingUtils.readTLString(stream);
        this.dateCreated = StreamingUtils.readInt(stream);
        this.dateActive = StreamingUtils.readInt(stream);
        this.ip = StreamingUtils.readTLString(stream);
        this.country = StreamingUtils.readTLString(stream);
        this.region = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "authorization#7bf2e6f6";
    }
}
