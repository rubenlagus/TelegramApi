package org.telegram.api.engine;

import org.telegram.tl.TLObject;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 05.11.13
 * Time: 14:10
 * @param <T>  the type parameter
 */
public interface RpcCallback<T extends TLObject> {
    /**
     * On result.
     *
     * @param result the result
     */
    void onResult(T result);

    /**
     * On error.
     *
     * @param errorCode the error code
     * @param message the message
     */
    void onError(int errorCode, String message);
}
