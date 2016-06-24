package org.telegram.mtproto.util;

/**
 * Created by Ruben Bermudez on 13.11.13.
 */
public class TimeUtil {
    public static int getUnixTime(long msgId) {
        return (int) (msgId >> 32);
    }
}
