package org.telegram.bot;

import org.telegram.api.engine.RpcException;
import org.telegram.api.engine.TimeoutException;
import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Callback to execute telegram api method
 * @date 26 of September of 2015
 */
public interface TelegramFunctionCallback<T extends TLObject> {
    void onSuccess(T result);
    void onRpcError(RpcException e);
    void onTimeout(TimeoutException e);
    void onUnknownError(Throwable e);
}
