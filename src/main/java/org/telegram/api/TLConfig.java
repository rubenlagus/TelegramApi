package org.telegram.api;

import org.telegram.api.disablefeature.TLDisabledFeature;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL config.
 */
public class TLConfig extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x317ceef4;

    private int date;
    private int expires;
    private boolean testMode;
    private int thisDc;
    private TLVector<TLDcOption> dcOptions;
    private int chatSizeMax;
    private int megaGroupSizeMax;
    private int forwardedCountMax;
    private int onlineUpdatePeriodMs;
    private int offlineBlurTimeoutMs;
    private int offlineIdleTimeoutMs;
    private int onlineCloudTimeoutMs;
    private int notifyCloudDelayMs;
    private int notifyDefaultDelayMs;
    private int chatBigSize;
    private int pushChatPeriodMs;
    private int pushChatLimit;
    private int savedGifsLimits;
    private int editTimeLimit;
    private TLVector<TLDisabledFeature> disabledFeatures = new TLVector<>();

    /**
     * Instantiates a new TL config.
     */
    public TLConfig() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
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
     * @param value the value
     */
    public void setDate(int value) {
        this.date = value;
    }

    /**
     * Gets test mode.
     *
     * @return the test mode
     */
    public boolean getTestMode() {
        return this.testMode;
    }

    /**
     * Gets this dc.
     *
     * @return the this dc
     */
    public int getThisDc() {
        return this.thisDc;
    }

    /**
     * Sets this dc.
     *
     * @param value the value
     */
    public void setThisDc(int value) {
        this.thisDc = value;
    }

    /**
     * Gets dc options.
     *
     * @return the dc options
     */
    public TLVector<TLDcOption> getDcOptions() {
        return this.dcOptions;
    }

    /**
     * Sets dc options.
     *
     * @param value the value
     */
    public void setDcOptions(TLVector<TLDcOption> value) {
        this.dcOptions = value;
    }

    /**
     * Gets chat size max.
     *
     * @return the chat size max
     */
    public int getChatSizeMax() {
        return this.chatSizeMax;
    }

    /**
     * Sets chat size max.
     *
     * @param value the value
     */
    public void setChatSizeMax(int value) {
        this.chatSizeMax = value;
    }

    /**
     * Is test mode.
     *
     * @return the boolean
     */
    public boolean isTestMode() {
        return this.testMode;
    }

    /**
     * Sets test mode.
     *
     * @param value the value
     */
    public void setTestMode(boolean value) {
        this.testMode = value;
    }

    /**
     * Gets expires.
     *
     * @return the expires
     */
    public int getExpires() {
        return this.expires;
    }

    /**
     * Sets expires.
     *
     * @param expires the expires
     */
    public void setExpires(int expires) {
        this.expires = expires;
    }

    /**
     * Gets forwarded count max.
     *
     * @return the forwarded count max
     */
    public int getForwardedCountMax() {
        return this.forwardedCountMax;
    }

    /**
     * Sets forwarded count max.
     *
     * @param forwardedCountMax the forwarded count max
     */
    public void setForwardedCountMax(int forwardedCountMax) {
        this.forwardedCountMax = forwardedCountMax;
    }

    /**
     * Gets online update period ms.
     *
     * @return the online update period ms
     */
    public int getOnlineUpdatePeriodMs() {
        return this.onlineUpdatePeriodMs;
    }

    /**
     * Sets online update period ms.
     *
     * @param onlineUpdatePeriodMs the online update period ms
     */
    public void setOnlineUpdatePeriodMs(int onlineUpdatePeriodMs) {
        this.onlineUpdatePeriodMs = onlineUpdatePeriodMs;
    }

    /**
     * Gets offline blur timeout ms.
     *
     * @return the offline blur timeout ms
     */
    public int getOfflineBlurTimeoutMs() {
        return this.offlineBlurTimeoutMs;
    }

    /**
     * Sets offline blur timeout ms.
     *
     * @param offlineBlurTimeoutMs the offline blur timeout ms
     */
    public void setOfflineBlurTimeoutMs(int offlineBlurTimeoutMs) {
        this.offlineBlurTimeoutMs = offlineBlurTimeoutMs;
    }

    /**
     * Gets offline idle timeout ms.
     *
     * @return the offline idle timeout ms
     */
    public int getOfflineIdleTimeoutMs() {
        return this.offlineIdleTimeoutMs;
    }

    /**
     * Sets offline idle timeout ms.
     *
     * @param offlineIdleTimeoutMs the offline idle timeout ms
     */
    public void setOfflineIdleTimeoutMs(int offlineIdleTimeoutMs) {
        this.offlineIdleTimeoutMs = offlineIdleTimeoutMs;
    }

    /**
     * Gets online cloud timeout ms.
     *
     * @return the online cloud timeout ms
     */
    public int getOnlineCloudTimeoutMs() {
        return this.onlineCloudTimeoutMs;
    }

    /**
     * Sets online cloud timeout ms.
     *
     * @param onlineCloudTimeoutMs the online cloud timeout ms
     */
    public void setOnlineCloudTimeoutMs(int onlineCloudTimeoutMs) {
        this.onlineCloudTimeoutMs = onlineCloudTimeoutMs;
    }

    /**
     * Gets notify cloud delay ms.
     *
     * @return the notify cloud delay ms
     */
    public int getNotifyCloudDelayMs() {
        return this.notifyCloudDelayMs;
    }

    /**
     * Sets notify cloud delay ms.
     *
     * @param notifyCloudDelayMs the notify cloud delay ms
     */
    public void setNotifyCloudDelayMs(int notifyCloudDelayMs) {
        this.notifyCloudDelayMs = notifyCloudDelayMs;
    }

    /**
     * Gets notify default delay ms.
     *
     * @return the notify default delay ms
     */
    public int getNotifyDefaultDelayMs() {
        return this.notifyDefaultDelayMs;
    }

    /**
     * Sets notify default delay ms.
     *
     * @param notifyDefaultDelayMs the notify default delay ms
     */
    public void setNotifyDefaultDelayMs(int notifyDefaultDelayMs) {
        this.notifyDefaultDelayMs = notifyDefaultDelayMs;
    }

    /**
     * Gets chat big size.
     *
     * @return the chat big size
     */
    public int getChatBigSize() {
        return this.chatBigSize;
    }

    /**
     * Sets chat big size.
     *
     * @param chatBigSize the chat big size
     */
    public void setChatBigSize(int chatBigSize) {
        this.chatBigSize = chatBigSize;
    }

    /**
     * Gets push chat period ms.
     *
     * @return the push chat period ms
     */
    public int getPushChatPeriodMs() {
        return this.pushChatPeriodMs;
    }

    /**
     * Sets push chat period ms.
     *
     * @param pushChatPeriodMs the push chat period ms
     */
    public void setPushChatPeriodMs(int pushChatPeriodMs) {
        this.pushChatPeriodMs = pushChatPeriodMs;
    }

    /**
     * Gets push chat limit.
     *
     * @return the push chat limit
     */
    public int getPushChatLimit() {
        return this.pushChatLimit;
    }

    /**
     * Sets push chat limit.
     *
     * @param pushChatLimit the push chat limit
     */
    public void setPushChatLimit(int pushChatLimit) {
        this.pushChatLimit = pushChatLimit;
    }

    public int getMegaGroupSizeMax() {
        return megaGroupSizeMax;
    }

    public void setMegaGroupSizeMax(int megaGroupSizeMax) {
        this.megaGroupSizeMax = megaGroupSizeMax;
    }

    public int getSavedGifsLimits() {
        return savedGifsLimits;
    }

    public void setSavedGifsLimits(int savedGifsLimits) {
        this.savedGifsLimits = savedGifsLimits;
    }

    public int getEditTimeLimit() {
        return editTimeLimit;
    }

    public void setEditTimeLimit(int editTimeLimit) {
        this.editTimeLimit = editTimeLimit;
    }

    /**
     * Gets disabled features.
     *
     * @return the disabled features
     */
    public TLVector<TLDisabledFeature> getDisabledFeatures() {
        return this.disabledFeatures;
    }

    /**
     * Sets disabled features.
     *
     * @param disabledFeatures the disabled features
     */
    public void setDisabledFeatures(TLVector<TLDisabledFeature> disabledFeatures) {
        this.disabledFeatures = disabledFeatures;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.expires, stream);
        StreamingUtils.writeTLBool(this.testMode, stream);
        StreamingUtils.writeInt(this.thisDc, stream);
        StreamingUtils.writeTLVector(this.dcOptions, stream);
        StreamingUtils.writeInt(this.chatSizeMax, stream);
        StreamingUtils.writeInt(this.megaGroupSizeMax, stream);
        StreamingUtils.writeInt(this.forwardedCountMax, stream);
        StreamingUtils.writeInt(this.onlineUpdatePeriodMs, stream);
        StreamingUtils.writeInt(this.offlineBlurTimeoutMs, stream);
        StreamingUtils.writeInt(this.offlineIdleTimeoutMs, stream);
        StreamingUtils.writeInt(this.onlineCloudTimeoutMs, stream);
        StreamingUtils.writeInt(this.notifyCloudDelayMs, stream);
        StreamingUtils.writeInt(this.notifyDefaultDelayMs, stream);
        StreamingUtils.writeInt(this.chatBigSize, stream);
        StreamingUtils.writeInt(this.pushChatPeriodMs, stream);
        StreamingUtils.writeInt(this.pushChatLimit, stream);
        StreamingUtils.writeInt(this.savedGifsLimits, stream);
        StreamingUtils.writeInt(this.editTimeLimit, stream);
        StreamingUtils.writeTLVector(this.disabledFeatures, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.date = StreamingUtils.readInt(stream);
        this.expires = StreamingUtils.readInt(stream);
        this.testMode = StreamingUtils.readTLBool(stream);
        this.thisDc = StreamingUtils.readInt(stream);
        this.dcOptions = (TLVector<TLDcOption>) StreamingUtils.readTLVector(stream, context);
        this.chatSizeMax = StreamingUtils.readInt(stream);
        this.megaGroupSizeMax = StreamingUtils.readInt(stream);
        this.forwardedCountMax = StreamingUtils.readInt(stream);
        this.onlineUpdatePeriodMs = StreamingUtils.readInt(stream);
        this.offlineBlurTimeoutMs = StreamingUtils.readInt(stream);
        this.offlineIdleTimeoutMs = StreamingUtils.readInt(stream);
        this.onlineCloudTimeoutMs = StreamingUtils.readInt(stream);
        this.notifyCloudDelayMs = StreamingUtils.readInt(stream);
        this.notifyDefaultDelayMs = StreamingUtils.readInt(stream);
        this.chatBigSize = StreamingUtils.readInt(stream);
        this.pushChatPeriodMs = StreamingUtils.readInt(stream);
        this.pushChatLimit = StreamingUtils.readInt(stream);
        this.savedGifsLimits = StreamingUtils.readInt(stream);
        this.editTimeLimit = StreamingUtils.readInt(stream);
        this.disabledFeatures = (TLVector<TLDisabledFeature>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "config#317ceef4";
    }
}