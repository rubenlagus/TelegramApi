package org.telegram.api.engine;

import org.telegram.api.updates.TLAbsUpdates;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 11.11.13
 * Time: 7:42
 */
public interface ApiCallback {
    /**
     * On auth cancelled.
     *
     * @param api the api
     */
    void onAuthCancelled(TelegramApi api);

    /**
     * On updates invalidated.
     *
     * @param api the api
     */
    void onUpdatesInvalidated(TelegramApi api);

    /**
     * On update.
     *
     * @param updates the updates
     */
    void onUpdate(TLAbsUpdates updates);
}
