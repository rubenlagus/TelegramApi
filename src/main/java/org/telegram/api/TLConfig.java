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
    public static final int CLASS_ID = 0xcb601684;

    private static final int FLAG_TMP_SESSIONS         = 0x00000001; // 0
    private static final int FLAG_PHONE_CALLS_ENABLED  = 0x00000002; // 1

    private int flags;
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
    private int ratingEDecay;
    private int stickersRecentLimit;
    private int tmpSessions;
    private int pinnedDialogsCountMax;
    private int callReceiveTimeoutMs;
    private int callRingTimeoutMs;
    private int callConnectTimeoutMs;
    private int callPacketTimeoutMs;
    private String meUrlPrefix;
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

    public int getDate() {
        return date;
    }

    public int getExpires() {
        return expires;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public int getThisDc() {
        return thisDc;
    }

    public TLVector<TLDcOption> getDcOptions() {
        return dcOptions;
    }

    public int getChatSizeMax() {
        return chatSizeMax;
    }

    public int getMegaGroupSizeMax() {
        return megaGroupSizeMax;
    }

    public int getForwardedCountMax() {
        return forwardedCountMax;
    }

    public int getOnlineUpdatePeriodMs() {
        return onlineUpdatePeriodMs;
    }

    public int getOfflineBlurTimeoutMs() {
        return offlineBlurTimeoutMs;
    }

    public int getOfflineIdleTimeoutMs() {
        return offlineIdleTimeoutMs;
    }

    public int getOnlineCloudTimeoutMs() {
        return onlineCloudTimeoutMs;
    }

    public int getNotifyCloudDelayMs() {
        return notifyCloudDelayMs;
    }

    public int getNotifyDefaultDelayMs() {
        return notifyDefaultDelayMs;
    }

    public int getChatBigSize() {
        return chatBigSize;
    }

    public int getPushChatPeriodMs() {
        return pushChatPeriodMs;
    }

    public int getPushChatLimit() {
        return pushChatLimit;
    }

    public int getSavedGifsLimits() {
        return savedGifsLimits;
    }

    public int getEditTimeLimit() {
        return editTimeLimit;
    }

    public int getRatingEDecay() {
        return ratingEDecay;
    }

    public int getStickersRecentLimit() {
        return stickersRecentLimit;
    }

    public int getTmpSessions() {
        return tmpSessions;
    }

    public TLVector<TLDisabledFeature> getDisabledFeatures() {
        return disabledFeatures;
    }

    public boolean hasTemporalSessions() {
        return (flags & FLAG_TMP_SESSIONS) != 0;
    }

    public boolean hasPhoneCalls() {
        return (flags & FLAG_PHONE_CALLS_ENABLED) != 0;
    }

    public int getPinnedDialogsCountMax() {
        return pinnedDialogsCountMax;
    }

    public int getCallReceiveTimeoutMs() {
        return callReceiveTimeoutMs;
    }

    public int getCallRingTimeoutMs() {
        return callRingTimeoutMs;
    }

    public int getCallConnectTimeoutMs() {
        return callConnectTimeoutMs;
    }

    public int getCallPacketTimeoutMs() {
        return callPacketTimeoutMs;
    }

    public String getMeUrlPrefix() {
        return meUrlPrefix;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(flags, stream);
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
        StreamingUtils.writeInt(this.ratingEDecay, stream);
        StreamingUtils.writeInt(this.stickersRecentLimit, stream);
        if ((flags & FLAG_TMP_SESSIONS) != 0) {
            StreamingUtils.writeInt(this.tmpSessions, stream);
        }
        StreamingUtils.writeInt(this.pinnedDialogsCountMax, stream);
        StreamingUtils.writeInt(this.callReceiveTimeoutMs, stream);
        StreamingUtils.writeInt(this.callRingTimeoutMs, stream);
        StreamingUtils.writeInt(this.callConnectTimeoutMs, stream);
        StreamingUtils.writeInt(this.callPacketTimeoutMs, stream);
        StreamingUtils.writeTLString(this.meUrlPrefix, stream);
        StreamingUtils.writeTLVector(this.disabledFeatures, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        flags = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.expires = StreamingUtils.readInt(stream);
        this.testMode = StreamingUtils.readTLBool(stream);
        this.thisDc = StreamingUtils.readInt(stream);
        this.dcOptions = StreamingUtils.readTLVector(stream, context, TLDcOption.class);
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
        this.ratingEDecay = StreamingUtils.readInt(stream);
        this.stickersRecentLimit = StreamingUtils.readInt(stream);
        if ((flags & FLAG_TMP_SESSIONS) != 0) {
            this.tmpSessions = StreamingUtils.readInt(stream);
        }
        this.pinnedDialogsCountMax = StreamingUtils.readInt(stream);
        this.callReceiveTimeoutMs = StreamingUtils.readInt(stream);
        this.callRingTimeoutMs = StreamingUtils.readInt(stream);
        this.callConnectTimeoutMs = StreamingUtils.readInt(stream);
        this.callPacketTimeoutMs = StreamingUtils.readInt(stream);
        this.meUrlPrefix = StreamingUtils.readTLString(stream);
        this.disabledFeatures = StreamingUtils.readTLVector(stream, context, TLDisabledFeature.class);
    }

    public String toString() {
        return "config#cb601684";
    }
}