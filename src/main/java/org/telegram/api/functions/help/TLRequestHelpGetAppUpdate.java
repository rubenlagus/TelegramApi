package org.telegram.api.functions.help;

import org.telegram.api.help.TLAbsAppUpdate;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request help get app update.
 */
public class TLRequestHelpGetAppUpdate extends TLMethod<TLAbsAppUpdate> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc812ac7e;

    private String deviceModel;
    private String systemVersion;
    private String appVersion;
    private String langCode;

    /**
     * Instantiates a new TL request help get app update.
     */
    public TLRequestHelpGetAppUpdate() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsAppUpdate deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAbsAppUpdate))
            return (TLAbsAppUpdate) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLAbsAppUpdate, got: " + res.getClass().getCanonicalName());
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

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.deviceModel, stream);
        StreamingUtils.writeTLString(this.systemVersion, stream);
        StreamingUtils.writeTLString(this.appVersion, stream);
        StreamingUtils.writeTLString(this.langCode, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.deviceModel = StreamingUtils.readTLString(stream);
        this.systemVersion = StreamingUtils.readTLString(stream);
        this.appVersion = StreamingUtils.readTLString(stream);
        this.langCode = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "help.getAppUpdate#c812ac7e";
    }
}