package org.telegram.api.functions.account;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request account register device.
 */
public class TLRequestAccountRegisterDevice extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x446c712c;

    private int tokenType;
    private String token;
    private String deviceModel;
    private String systemVersion;
    private String appVersion;
    private boolean appSandbox;
    private String langCode;

    /**
     * Instantiates a new TL request account register device.
     */
    public TLRequestAccountRegisterDevice() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets token type.
     *
     * @return the token type
     */
    public int getTokenType() {
        return this.tokenType;
    }

    /**
     * Sets token type.
     *
     * @param value the value
     */
    public void setTokenType(int value) {
        this.tokenType = value;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Sets token.
     *
     * @param value the value
     */
    public void setToken(String value) {
        this.token = value;
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
     * Gets app sandbox.
     *
     * @return the app sandbox
     */
    public boolean getAppSandbox() {
        return this.appSandbox;
    }

    /**
     * Sets app sandbox.
     *
     * @param value the value
     */
    public void setAppSandbox(boolean value) {
        this.appSandbox = value;
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.tokenType, stream);
        StreamingUtils.writeTLString(this.token, stream);
        StreamingUtils.writeTLString(this.deviceModel, stream);
        StreamingUtils.writeTLString(this.systemVersion, stream);
        StreamingUtils.writeTLString(this.appVersion, stream);
        StreamingUtils.writeTLBool(this.appSandbox, stream);
        StreamingUtils.writeTLString(this.langCode, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.tokenType = StreamingUtils.readInt(stream);
        this.token = StreamingUtils.readTLString(stream);
        this.deviceModel = StreamingUtils.readTLString(stream);
        this.systemVersion = StreamingUtils.readTLString(stream);
        this.appVersion = StreamingUtils.readTLString(stream);
        this.appSandbox = StreamingUtils.readTLBool(stream);
        this.langCode = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "account.registerDevice#446c712c";
    }
}