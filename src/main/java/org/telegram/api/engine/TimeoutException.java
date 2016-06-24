package org.telegram.api.engine;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 06.11.13
 * Time: 1:27
 */
public class TimeoutException extends IOException {
    /**
     * Instantiates a new Timeout exception.
     */
    public TimeoutException() {
    }

    /**
     * Instantiates a new Timeout exception.
     *
     * @param s the s
     */
    public TimeoutException(String s) {
        super(s);
    }

    /**
     * Instantiates a new Timeout exception.
     *
     * @param s the s
     * @param throwable the throwable
     */
    public TimeoutException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * Instantiates a new Timeout exception.
     *
     * @param throwable the throwable
     */
    public TimeoutException(Throwable throwable) {
        super(throwable);
    }
}
