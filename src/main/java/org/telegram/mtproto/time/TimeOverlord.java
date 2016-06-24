package org.telegram.mtproto.time;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 02.11.13
 * Time: 21:35
 */
public class TimeOverlord {
    private static TimeOverlord instance;
    protected long timeDelta;
    private long nanotimeShift;

    private long timeAccuracy = Long.MAX_VALUE;
    private TimeOverlord() {
        this.nanotimeShift = System.currentTimeMillis() - System.nanoTime() / 1000;
    }

    public static synchronized TimeOverlord getInstance() {
        if (instance == null) {
            instance = new TimeOverlord();
        }
        return instance;
    }

    public long createWeakMessageId() {
        return (getServerTime() / 1000) << 32;
    }

    public long getLocalTime() {
        return System.currentTimeMillis();
    }

    public long getServerTime() {
        return getLocalTime() + this.timeDelta;
    }

    public long getTimeAccuracy() {
        return this.timeAccuracy;
    }

    public long getTimeDelta() {
        return this.timeDelta;
    }

    public void setTimeDelta(long timeDelta, long timeAccuracy) {
        this.timeDelta = timeDelta;
        this.timeAccuracy = timeAccuracy;
    }

    public void onForcedServerTimeArrived(long serverTime, long duration) {
        this.timeDelta = serverTime - getLocalTime();
        this.timeAccuracy = duration;
    }

    public void onServerTimeArrived(long serverTime, long duration) {
        if (duration < 0) {
            return;
        }
        if (duration < this.timeAccuracy) {
            this.timeDelta = serverTime - getLocalTime();
            this.timeAccuracy = duration;
        } else if (Math.abs(getLocalTime() - serverTime) > (duration / 2 + this.timeAccuracy / 2)) {
            this.timeDelta = serverTime - getLocalTime();
            this.timeAccuracy = duration;
        }
    }

    public void onMethodExecuted(long sentId, long responseId, long duration) {
        if (duration < 0) {
            return;
        }

        onServerTimeArrived((responseId >> 32) * 1000, duration);
    }
}