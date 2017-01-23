package org.telegram.bot.handlers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.api.channel.filters.TLChannelMessagesFilterEmpty;
import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.engine.RpcException;
import org.telegram.api.functions.updates.TLRequestUpdatesGetChannelDifference;
import org.telegram.api.functions.updates.TLRequestUpdatesGetDifference;
import org.telegram.api.input.chat.TLInputChannel;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.update.TLAbsUpdate;
import org.telegram.api.updates.TLUpdatesState;
import org.telegram.api.updates.channel.differences.TLAbsUpdatesChannelDifferences;
import org.telegram.api.updates.channel.differences.TLUpdatesChannelDifferences;
import org.telegram.api.updates.channel.differences.TLUpdatesChannelDifferencesEmpty;
import org.telegram.api.updates.channel.differences.TLUpdatesChannelDifferencesTooLong;
import org.telegram.api.updates.difference.TLAbsDifference;
import org.telegram.api.updates.difference.TLDifference;
import org.telegram.api.updates.difference.TLDifferenceSlice;
import org.telegram.api.updates.difference.TLDifferenceTooLong;
import org.telegram.api.user.TLAbsUser;
import org.telegram.bot.handlers.interfaces.IDifferencesHandler;
import org.telegram.bot.handlers.interfaces.IUpdatesHandler;
import org.telegram.bot.kernel.IKernelComm;
import org.telegram.bot.kernel.differenceparameters.IDifferenceParametersService;
import org.telegram.bot.services.BotLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Implementation of the differences handler
 * @date 22 of March of 2016
 */
public class DifferencesHandler implements IDifferencesHandler {
    private static final String LOGTAG = "DIFFERENCESHANDLER";
    private static final Object differencesLock = new Object();

    private final IDifferenceParametersService differenceParametersService;
    private final IKernelComm kernelComm;
    private final IUpdatesHandler updatesHandlerBase;

    public DifferencesHandler(IKernelComm kernelComm, IDifferenceParametersService differenceParametersService, IUpdatesHandler updatesHandlerBase) {
        this.differenceParametersService = differenceParametersService;
        this.kernelComm = kernelComm;
        this.updatesHandlerBase = updatesHandlerBase;
    }

    @Override
    public void getDifferences() {
        final boolean canGetDifferences;
        synchronized (differencesLock) {
            canGetDifferences = canGetDifferences();
            if (canGetDifferences) {
                BotLogger.info(LOGTAG, "Getting differences");

                final TLRequestUpdatesGetDifference requestUpdatesGetDifference = new TLRequestUpdatesGetDifference();
                requestUpdatesGetDifference.setQts(0);
                TLAbsDifference absDifference = null;
                do {
                    requestUpdatesGetDifference.setDate(differenceParametersService.getDate(0));
                    requestUpdatesGetDifference.setPts(differenceParametersService.getPts(0));
                    try {
                        absDifference = kernelComm.doRpcCallSync(requestUpdatesGetDifference);
                        if (absDifference != null) {
                            onTLAbsDifferences(absDifference);
                        }
                    } catch (ExecutionException | RpcException
                            e) {
                        BotLogger.error(LOGTAG, e);
                    }
                    try {
                        synchronized (this) {
                            if ((absDifference instanceof TLDifferenceSlice)) {
                                this.wait(100);
                            }
                        }
                    } catch (InterruptedException e) {
                        BotLogger.error(LOGTAG, e);
                    }
                } while ((absDifference instanceof TLDifferenceSlice));
                BotLogger.info(LOGTAG, "Getting differences finished");
            }
        }
    }

    @Override
    public void updateStateModification(@NotNull TLUpdatesState state, boolean isGettingDifferent) {
        if (!isGettingDifferent && (differenceParametersService.getPts(0) != 0)
                && (differenceParametersService.getSeq(0) != 0)) {
            getDifferences();
        } else {
            differenceParametersService.setNewUpdateParams(0, state.getPts(), state.getSeq(), state.getDate());
        }
    }

    @Override
    public void getChannelDifferences(int chatId, long accessHash) {
        if (accessHash == 0) {
            getDifferences();
        } else {
            getChannelDifferencesInternal(chatId, accessHash);
        }
    }

    private void getChannelDifferencesInternal(int chatId, long accessHash) {
        synchronized (differencesLock) {
            BotLogger.info(LOGTAG, "Getting differences");

            final TLRequestUpdatesGetChannelDifference requestGetChannelDifference = new TLRequestUpdatesGetChannelDifference();
            requestGetChannelDifference.setFilter(new TLChannelMessagesFilterEmpty());
            final TLInputChannel inputChannel = new TLInputChannel();
            inputChannel.setChannelId(chatId);
            inputChannel.setAccessHash(accessHash);
            requestGetChannelDifference.setChannel(inputChannel);
            TLAbsUpdatesChannelDifferences absDifference = null;
            do {
                final int pts = differenceParametersService.getPts(chatId);
                requestGetChannelDifference.setPts((pts == 0) ? 1 : pts);
                requestGetChannelDifference.setLimit(100);
                try {
                    absDifference = kernelComm.doRpcCallSync(requestGetChannelDifference);
                    if ((absDifference != null) && !(absDifference instanceof TLUpdatesChannelDifferencesEmpty)) {
                        onTLAbsUpdatesChannelDifferences(chatId, absDifference);
                    }
                } catch (ExecutionException | RpcException e) {
                    BotLogger.error(LOGTAG, e);
                }
                try {
                    synchronized (this) {
                        if ((absDifference instanceof TLUpdatesChannelDifferencesTooLong)) {
                            this.wait(100);
                        }
                    }
                } catch (InterruptedException e) {
                    BotLogger.error(LOGTAG, e);
                }
            } while ((absDifference instanceof TLUpdatesChannelDifferencesTooLong));
            BotLogger.info(LOGTAG, "Getting differences finished");
        }
    }

    @Override
    public void updateChannelStateModification(int chatId, @Nullable Long accessHash, int pts, boolean isGettingDifferent) {
        if (!isGettingDifferent && (differenceParametersService.getPts(chatId) != 0)
                && (differenceParametersService.getSeq(chatId) != 0) && (accessHash != null)) {
            getChannelDifferences(chatId, accessHash);
        } else {
            differenceParametersService.setNewUpdateParams(chatId, pts, null, 0);
        }
    }

    /**
     * Handles TLAbsDifferences
     * @param absDifference AbsDifferences to handle
     */
    private void onTLAbsDifferences(@NotNull TLAbsDifference absDifference) {
        BotLogger.info(LOGTAG, "Received differences");
        final TLUpdatesState updatesState;

        if (absDifference instanceof TLDifferenceSlice) {
            updatesState = ((TLDifferenceSlice) absDifference).getIntermediateState();
            handleDifferences(absDifference, updatesState);
        } else if (absDifference instanceof TLDifference) {
            updatesState = ((TLDifference) absDifference).getState();
            handleDifferences(absDifference, updatesState);
        } else if (absDifference instanceof TLDifferenceTooLong) {
            TLUpdatesState state = new TLUpdatesState();
            state.setPts(((TLDifferenceTooLong) absDifference).getPts());
            updateStateModification(state, true);
        }
    }

    private void onTLAbsUpdatesChannelDifferences(int chatId, TLAbsUpdatesChannelDifferences absDifference) {
        if (absDifference instanceof TLUpdatesChannelDifferences) {
            final TLUpdatesChannelDifferences differences = (TLUpdatesChannelDifferences) absDifference;
            handleChannelDifferences(chatId, differences.getPts(), differences.getUsers(), differences.getNewMessages(),
                    differences.getOtherUpdates(), differences.getChats());
        } else if (absDifference instanceof TLUpdatesChannelDifferencesTooLong) {
            final TLUpdatesChannelDifferencesTooLong differences = (TLUpdatesChannelDifferencesTooLong) absDifference;
            handleChannelDifferences(chatId, differences.getPts(), differences.getUsers(), differences.getMessages(),
                    new ArrayList<>(), differences.getChats());
        }
    }

    private void handleChannelDifferences(int chatId, int pts, List<TLAbsUser> users, List<TLAbsMessage> messages, List<TLAbsUpdate> updates, List<TLAbsChat> chats) {
        BotLogger.info(LOGTAG, "Handling channel differences");
        updatesHandlerBase.onTLChannelDifferences(users, messages, updates, chats);
        updateChannelStateModification(chatId, null, pts, true);
    }

    /**
     * Handle Differences
     * @param absDifference AbsDifferences to handle
     * @param updatesState Updates state of differences
     */
    private void handleDifferences(@NotNull TLAbsDifference absDifference, @NotNull TLUpdatesState updatesState) {
        BotLogger.info(LOGTAG, "Handling differences");
        updatesHandlerBase.onTLAbsDifference(absDifference);
        updateStateModification(updatesState, true);
    }

    @Contract(pure = true)
    private boolean canGetDifferences() {
        return (differenceParametersService.getPts(0) != 0) && (differenceParametersService.getSeq(0) != 0);
    }
}
