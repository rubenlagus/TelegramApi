package org.telegram.bot.handlers.interfaces;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.api.updates.TLUpdatesState;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Differences handler interface
 * @date 22 of March of 2016
 */
public interface IDifferencesHandler {
    /**
     * Load differences from server
     */
    void getDifferences();

    /**
     * Modify updates state
     * @param state New updates state
     * @param isGettingDifferent true if differences are being loaded, false otherwise
     */
    void updateStateModification(@NotNull TLUpdatesState state, boolean isGettingDifferent);

    /**
     * Load differences from server
     */
    void getChannelDifferences(int chatId, long accessHash);

    /**
     * Modify updates state
     * @param state New updates state
     * @param isGettingDifferent true if differences are being loaded, false otherwise
     */
    void updateChannelStateModification(int chatId, @Nullable Long accessHash, int pts, boolean isGettingDifferent);
}
