package org.telegram.api.functions.help;

import org.telegram.api.help.changelog.TLAbsAppChangelog;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request help get app changelog.
 */
public class TLRequestHelpGetAppChangelog extends TLMethod<TLAbsAppChangelog> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5bab7fb2;

    private String deviceModel;
    private String systemVersion;
    private String appVersion;
    private String langCode;

    /**
     * Instantiates a new TL request help get app changelog.
     */
    public TLRequestHelpGetAppChangelog() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsAppChangelog deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsAppChangelog)) {
            return (TLAbsAppChangelog) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsAppChangelog.class.getName() + ", got: " + res.getClass().getCanonicalName());
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(deviceModel, stream);
        StreamingUtils.writeTLString(systemVersion, stream);
        StreamingUtils.writeTLString(appVersion, stream);
        StreamingUtils.writeTLString(langCode, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.deviceModel = StreamingUtils.readTLString(stream);
        this.systemVersion = StreamingUtils.readTLString(stream);
        this.appVersion = StreamingUtils.readTLString(stream);
        this.langCode = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "functions.help.TLRequestHelpGetAppChangelog#5bab7fb2";
    }
}