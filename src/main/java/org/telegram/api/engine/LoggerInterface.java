package org.telegram.api.engine;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 11.11.13
 * Time: 4:48
 */
public interface LoggerInterface {
    /**
     * W void.
     *
     * @param tag the tag
     * @param message the message
     */
    void w(String tag, String message);

    /**
     * D void.
     *
     * @param tag the tag
     * @param message the message
     */
    void d(String tag, String message);

    /**
     * E void.
     *
     * @param tag the tag
     * @param t the t
     */
    void e(String tag, Throwable t);
}
