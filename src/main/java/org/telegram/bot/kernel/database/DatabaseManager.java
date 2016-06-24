package org.telegram.bot.kernel.database;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.bot.structure.Chat;
import org.telegram.bot.structure.IUser;

import java.util.Map;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 11 of April of 2016
 */
public interface DatabaseManager {
    @Nullable Chat getChatById(int chatId);
    @Nullable IUser getUserById(int userId);
    @NotNull Map<Integer, int[]> getDifferencesData();
    boolean updateDifferencesData(int botId, int pts, int date, int seq);
}
