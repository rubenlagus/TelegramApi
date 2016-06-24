package org.telegram.bot.kernel;

import org.jetbrains.annotations.NotNull;
import org.telegram.api.engine.RpcException;
import org.telegram.api.functions.updates.TLRequestUpdatesGetState;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.api.updates.TLUpdateShort;
import org.telegram.api.updates.TLUpdateShortChatMessage;
import org.telegram.api.updates.TLUpdateShortMessage;
import org.telegram.api.updates.TLUpdateShortSentMessage;
import org.telegram.api.updates.TLUpdates;
import org.telegram.api.updates.TLUpdatesCombined;
import org.telegram.api.updates.TLUpdatesState;
import org.telegram.api.updates.TLUpdatesTooLong;
import org.telegram.bot.handlers.UpdatesHandlerBase;
import org.telegram.bot.handlers.interfaces.IUpdatesHandler;
import org.telegram.bot.services.BotLogger;
import org.telegram.bot.services.NotificationsService;

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @author Hendrik Hofstadt
 * @author Ruben Bermudez
 * @version 2.0
 * @brief Bot kernel
 * @date 13.001.14
 */
@SuppressWarnings({"OverlyComplexClass", "OverlyLongMethod", "OverlyComplexMethod", "InfiniteLoopStatement", "MethodOnlyUsedFromInnerClass"})
public class MainHandler implements NotificationsService.NotificationObserver {
    private static final String LOGTAG = "KERNELHANDLER";
    private final IKernelComm kernelComm;

    private boolean running;
    private final AtomicBoolean gettingDifferences = new AtomicBoolean(false);
    private final AtomicBoolean needGetUpdateState = new AtomicBoolean(true);
    private final ConcurrentLinkedDeque<TLAbsUpdates> updatesQueue = new ConcurrentLinkedDeque<>();
    private final IUpdatesHandler updatesHandler;
    private final UpdateHandlerThread updateHandlerThread;

    MainHandler(IKernelComm kernelComm, UpdatesHandlerBase updatesHandler) {
        NotificationsService.getInstance().addObserver(this, NotificationsService.needGetUpdates);
        this.kernelComm = kernelComm;
        this.updatesHandler = updatesHandler;
        this.running = false;
        new UpdatesHandlerThread().start();
        updateHandlerThread = new UpdateHandlerThread();
        updateHandlerThread.start();
        kernelComm.setMainHandler(this);
    }

    public void start() {
        this.updatesQueue.clear();
        this.running = true;
    }

    void stop() {
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }

    /**
     * Add a TLAbsUpdates to the updates queue.
     * Load Updates states from database if needed
     * @param updates Updates to add
     */
    void onUpdate(@NotNull final TLAbsUpdates updates) {
        if (this.running) {
            this.updatesQueue.addLast(updates);
            synchronized (this.updatesQueue) {
                this.updatesQueue.notifyAll();
            }
        }
    }

    /**
     * Handles TLAbsUpdates
     * @param updates Updates to handle
     */
    private void onTLAbsUpdates(@NotNull final TLAbsUpdates updates) {
        BotLogger.debug(LOGTAG, "Received:" + updates.toString());
        if (updates instanceof TLUpdateShortMessage) {
            final TLUpdateShortMessage updateShortMessage = (TLUpdateShortMessage) updates;
            final UpdateWrapper wrapper = new UpdateWrapper(updateShortMessage);
            wrapper.setParams(updateShortMessage.getPts(), updateShortMessage.getPtsCount(),
                    updateShortMessage.getDate(), 0, 0);
            updateHandlerThread.addUpdate(wrapper);
        } else if (updates instanceof TLUpdateShortChatMessage) {
            final TLUpdateShortChatMessage updateShortChatMessage = (TLUpdateShortChatMessage) updates;
            final UpdateWrapper wrapper = new UpdateWrapper(updateShortChatMessage);
            wrapper.setParams(updateShortChatMessage.getPts(), updateShortChatMessage.getPtsCount(),
                    updateShortChatMessage.getDate(), 0, 0);
            updateHandlerThread.addUpdate(wrapper);
        } else if (updates instanceof TLUpdateShort) {
            final TLUpdateShort updateShort = (TLUpdateShort) updates;
            final UpdateWrapper wrapper = new UpdateWrapper(updateShort.getUpdate());
            wrapper.setParams(updateShort.getUpdate().getPts(), updateShort.getUpdate().getPtsCount(),
                    updateShort.getDate(), 0, 0);
            updateHandlerThread.addUpdate(wrapper);
        } else if (updates instanceof TLUpdates) {
            final TLUpdates tlUpdates = (TLUpdates) updates;
            final boolean disablePtsCheck = tlUpdates.getSeq() != 0;

            boolean correctSeq = true;
            if (disablePtsCheck) {
                correctSeq = updatesHandler.checkSeq(tlUpdates.getSeq(), 0,tlUpdates.getDate());
            }

            if (correctSeq) {
                updatesHandler.onChats(tlUpdates.getChats());
                updatesHandler.onUsers(tlUpdates.getUsers());
                tlUpdates.getUpdates().forEach(x -> {
                    final UpdateWrapper wrapper = new UpdateWrapper(x);
                    wrapper.setParams(x.getPts(), x.getPtsCount(), tlUpdates.getDate(), tlUpdates.getSeq(), 0);
                    if (disablePtsCheck) {
                        wrapper.disablePtsCheck();
                    }
                    updateHandlerThread.addUpdate(wrapper);
                });
            } else {
                updatesHandler.getDifferences();
            }
        } else if (updates instanceof TLUpdatesCombined) {
            final TLUpdatesCombined updatesCombined = (TLUpdatesCombined) updates;
            final boolean disablePtsCheck = updatesCombined.getSeq() != 0;

            boolean correctSeq = true;
            if (disablePtsCheck) {
                correctSeq = updatesHandler.checkSeq(updatesCombined.getSeq(), updatesCombined.getSeqStart(), updatesCombined.getDate());
            }

            if (correctSeq) {
                updatesHandler.onChats(updatesCombined.getChats());
                updatesHandler.onUsers(updatesCombined.getUsers());
                updatesCombined.getUpdates().forEach(x -> {
                    final UpdateWrapper wrapper = new UpdateWrapper(x);
                    wrapper.setParams(x.getPts(), x.getPtsCount(), updatesCombined.getDate(), updatesCombined.getSeq(), updatesCombined.getSeqStart());
                    if (disablePtsCheck) {
                        wrapper.disablePtsCheck();
                    }
                    updateHandlerThread.addUpdate(wrapper);
                });
            } else {
                updatesHandler.getDifferences();
            }
        } else if (updates instanceof TLUpdateShortSentMessage) {
            final TLUpdateShortSentMessage updateShortSentMessage = (TLUpdateShortSentMessage) updates;
            final UpdateWrapper wrapper = new UpdateWrapper(updateShortSentMessage);
            wrapper.setParams(updateShortSentMessage.getPts(), updateShortSentMessage.getPtsCount(),
                    updateShortSentMessage.getDate(), 0, 0);
            updateHandlerThread.addUpdate(wrapper);
        } else if (updates instanceof TLUpdatesTooLong) {
            updatesHandler.onTLUpdatesTooLong();
        } else {
            BotLogger.debug(LOGTAG, "Unsupported TLAbsUpdates: " + updates.toString());
        }
    }

    public void processUpdates(@NotNull List<UpdateWrapper> updates) {
        updateHandlerThread.addUpdates(updates);
    }

    /**
     * Load updats state from server
     */
    private void getUpdatesState() {
        try {
            final TLUpdatesState state = kernelComm.doRpcCallSync(new TLRequestUpdatesGetState());
            if (state != null) {
                BotLogger.error(LOGTAG, "Received updates state");
                updatesHandler.updateStateModification(state);
                needGetUpdateState.set(false);
            } else {
                BotLogger.error(LOGTAG, "Error getting updates state");
            }
        } catch (ExecutionException | RpcException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    /**
     * Force getting updates state on next update received
     */
    public void needGetUpdates() {
        this.needGetUpdateState.set(true);
        synchronized (this.updatesQueue) {
            this.updatesQueue.notifyAll();
        }
    }

    public IUpdatesHandler getUpdatesHandler() {
        return updatesHandler;
    }

    @Override
    public void onNotificationReceived(int notificationId, Object... args) {
        if (notificationId == NotificationsService.needGetUpdates) {
            needGetUpdates();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        NotificationsService.getInstance().removeObserver(this, NotificationsService.needGetUpdates);
        super.finalize();
    }

    /**
     * Updates handler thread
     */
    private class UpdatesHandlerThread extends Thread {
        boolean isAlive = true;

        private UpdatesHandlerThread() {
            super();
        }

        @Override
        public void interrupt() {
            this.isAlive = false;
            super.interrupt();
        }

        @Override
        public void run() {
            TLAbsUpdates updates;
            while(isAlive) {
                try {
                    if (needGetUpdateState.get() && !gettingDifferences.get()) {
                        getUpdatesState();
                    }
                    updates = MainHandler.this.updatesQueue.pollFirst();
                    if (updates == null) {
                        synchronized (MainHandler.this.updatesQueue) {
                            try {
                                MainHandler.this.updatesQueue.wait();
                            } catch (InterruptedException e) {
                                BotLogger.error(LOGTAG, e);
                            }
                        }
                    } else {
                        onTLAbsUpdates(updates);
                    }
                } catch (Exception e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        }
    }

    private class UpdateHandlerThread extends Thread {
        private boolean isAlive = true;
        private final PriorityQueue<UpdateWrapper> updates = new PriorityQueue<>(new UpdateWrapper.UpdateWrapperComparator());

        private UpdateHandlerThread() {
            super();
        }

        void addUpdate(UpdateWrapper newUpdate) {
            synchronized (updates) {
                updates.offer(newUpdate);
                updates.notifyAll();
            }
        }

        void addUpdates(List<UpdateWrapper> newUpdates) {
            synchronized (updates) {
                updates.addAll(newUpdates);
                updates.notifyAll();
            }
        }

        @Override
        public void interrupt() {
            this.isAlive = false;
            super.interrupt();
        }

        @Override
        public void run() {
            while (isAlive) {
                UpdateWrapper update;
                while(isAlive) {
                    try {
                        synchronized (updates) {
                            update = updates.poll();
                        }
                        if (update == null) {
                            synchronized (updates) {
                                try {
                                    updates.wait();
                                } catch (InterruptedException e) {
                                    BotLogger.error(LOGTAG, e);
                                }
                            }
                        } else {
                            updatesHandler.processUpdate(update);
                        }
                    } catch (Exception e) {
                        BotLogger.error(LOGTAG, e);
                    }
                }
            }
        }
    }
}