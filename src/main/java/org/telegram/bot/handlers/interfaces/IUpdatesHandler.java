package org.telegram.bot.handlers.interfaces;

import org.jetbrains.annotations.NotNull;
import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.update.TLAbsUpdate;
import org.telegram.api.updates.TLUpdatesState;
import org.telegram.api.updates.difference.TLAbsDifference;
import org.telegram.api.user.TLAbsUser;
import org.telegram.bot.kernel.UpdateWrapper;

import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Updates handler interface
 * @date 21 of March of 2016
 */
public interface IUpdatesHandler {
    void getDifferences();
    void onTLAbsDifference(@NotNull TLAbsDifference absDifference);
    void onTLChannelDifferences(List<TLAbsUser> users, List<TLAbsMessage> messages, List<TLAbsUpdate> newUpdates, List<TLAbsChat> chats);
    void updateStateModification(TLUpdatesState state);

    boolean checkSeq(int seq, int seqStart, int date);
    void processUpdate(UpdateWrapper updateWrapper);
    void onTLUpdatesTooLong();
    void onUsers(List<TLAbsUser> users);
    void onChats(List<TLAbsChat> chats);
}
