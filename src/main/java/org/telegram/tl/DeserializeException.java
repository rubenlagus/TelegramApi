package org.telegram.tl;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 02.11.13
 * Time: 6:52
 */
public class DeserializeException extends IOException {
    public DeserializeException() {
    }

    public DeserializeException(String s) {
        super(s);
    }

    public DeserializeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DeserializeException(Throwable throwable) {
        super(throwable);
    }
}
