package org.telegram.api.functions;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request init connection.
 */
public class TLRequestInitConnection extends TLMethod<TLObject> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x69796de9;

    private int apiId;
    private String deviceModel;
    private String systemVersion;
    private String appVersion;
    private String langCode;
    private TLMethod query;

    /**
     * Instantiates a new TL request init connection.
     */
    public TLRequestInitConnection() {
        super();
    }

    /**
     * Instantiates a new TL request init connection.
     *
     * @param apiId the api id
     * @param deviceModel the device model
     * @param systemVersion the system version
     * @param appVersion the app version
     * @param langCode the lang code
     * @param query the query
     */
    public TLRequestInitConnection(int apiId, String deviceModel, String systemVersion, String appVersion, String langCode, TLMethod query) {
        super();
        this.apiId = apiId;
        this.deviceModel = deviceModel;
        this.systemVersion = systemVersion;
        this.appVersion = appVersion;
        this.langCode = langCode;
        this.query = query;
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLObject deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return this.query.deserializeResponse(stream, context);
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
     * @param value the value
     */
    public void setApiId(int value) {
        this.apiId = value;
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
     * @param value the value
     */
    public void setDeviceModel(String value) {
        this.deviceModel = value;
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
     * @param value the value
     */
    public void setSystemVersion(String value) {
        this.systemVersion = value;
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
     * @param value the value
     */
    public void setAppVersion(String value) {
        this.appVersion = value;
    }

    /**
     * Gets lang code.
     *
     * @return the lang code
     */
    public String getLangCode() {
        return this.langCode;
    }

    /**
     * Sets lang code.
     *
     * @param value the value
     */
    public void setLangCode(String value) {
        this.langCode = value;
    }

    /**
     * Gets query.
     *
     * @return the query
     */
    public TLMethod getQuery() {
        return this.query;
    }

    /**
     * Sets query.
     *
     * @param value the value
     */
    public void setQuery(TLMethod value) {
        this.query = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.apiId, stream);
        StreamingUtils.writeTLString(this.deviceModel, stream);
        StreamingUtils.writeTLString(this.systemVersion, stream);
        StreamingUtils.writeTLString(this.appVersion, stream);
        StreamingUtils.writeTLString(this.langCode, stream);
        StreamingUtils.writeTLMethod(this.query, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.apiId = StreamingUtils.readInt(stream);
        this.deviceModel = StreamingUtils.readTLString(stream);
        this.systemVersion = StreamingUtils.readTLString(stream);
        this.appVersion = StreamingUtils.readTLString(stream);
        this.langCode = StreamingUtils.readTLString(stream);
        this.query = StreamingUtils.readTLMethod(stream, context);
    }

    public String toString() {
        return "initConnection#69796de9";
    }
}