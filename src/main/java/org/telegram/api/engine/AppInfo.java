package org.telegram.api.engine;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 10.11.13
 * Time: 2:31
 */
public class AppInfo {
    /**
     * The Api id.
     */
    protected int apiId;
    /**
     * The Device model.
     */
    protected String deviceModel;
    /**
     * The System version.
     */
    protected String systemVersion;
    /**
     * The App version.
     */
    protected String appVersion;
    /**
     * The Lang code.
     */
    protected String langCode;

    /**
     * Instantiates a new App info.
     *
     * @param apiId the api id
     * @param deviceModel the device model
     * @param systemVersion the system version
     * @param appVersion the app version
     * @param langCode the lang code
     */
    public AppInfo(int apiId, String deviceModel, String systemVersion, String appVersion, String langCode) {
        this.apiId = apiId;
        this.deviceModel = deviceModel;
        this.systemVersion = systemVersion;
        this.appVersion = appVersion;
        this.langCode = langCode;
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
     * Gets device model.
     *
     * @return the device model
     */
    public String getDeviceModel() {
        return this.deviceModel;
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
     * Gets app version.
     *
     * @return the app version
     */
    public String getAppVersion() {
        return this.appVersion;
    }

    /**
     * Gets lang code.
     *
     * @return the lang code
     */
    public String getLangCode() {
        return this.langCode;
    }
}
