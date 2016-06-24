package org.telegram.api.engine;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 11.11.13
 * Time: 4:48
 */
public class Logger {
    private static LoggerInterface logInterface;

    /**
     * Register interface.
     *
     * @param logInterface the log interface
     */
    public static void registerInterface(LoggerInterface logInterface) {
        Logger.logInterface = logInterface;
    }

    /**
     * W void.
     *
     * @param tag the tag
     * @param message the message
     */
    public static void w(String tag, String message) {
        if (logInterface != null) {
            logInterface.w(tag, message);
        } else {
            System.out.println(tag + ":" + message);
        }
    }

    /**
     * D void.
     *
     * @param tag the tag
     * @param message the message
     */
    public static void d(String tag, String message) {
        if (logInterface != null) {
            logInterface.d(tag, message);
        } else {
            System.out.println(tag + ":" + message);
        }
    }

    /**
     * E void.
     *
     * @param tag the tag
     * @param t the t
     */
    public static void e(String tag, Throwable t) {
        if (logInterface != null) {
            logInterface.e(tag, t);
        } else {
            t.printStackTrace();
        }
    }
}
