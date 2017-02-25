package org.telegram.bot.kernel;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.api.document.attribute.TLAbsDocumentAttribute;
import org.telegram.api.document.attribute.TLDocumentAttributeFilename;
import org.telegram.api.document.attribute.TLDocumentAttributeSticker;
import org.telegram.api.engine.*;
import org.telegram.api.engine.TimeoutException;
import org.telegram.api.engine.file.Downloader;
import org.telegram.api.engine.file.Uploader;
import org.telegram.api.engine.storage.AbsApiState;
import org.telegram.api.functions.channels.TLRequestChannelsReadHistory;
import org.telegram.api.functions.messages.TLRequestMessagesReadHistory;
import org.telegram.api.functions.messages.TLRequestMessagesSendMedia;
import org.telegram.api.functions.messages.TLRequestMessagesSendMessage;
import org.telegram.api.functions.updates.TLRequestUpdatesGetState;
import org.telegram.api.input.chat.TLInputChannel;
import org.telegram.api.input.file.TLInputFile;
import org.telegram.api.input.media.TLAbsInputMedia;
import org.telegram.api.input.media.TLInputMediaUploadedDocument;
import org.telegram.api.input.peer.TLAbsInputPeer;
import org.telegram.api.input.peer.TLInputPeerChannel;
import org.telegram.api.input.sticker.set.TLInputStickerSetEmpty;
import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.api.message.entity.TLMessageEntityBold;
import org.telegram.api.message.entity.TLMessageEntityCode;
import org.telegram.api.message.entity.TLMessageEntityItalic;
import org.telegram.api.messages.TLAffectedHistory;
import org.telegram.api.messages.TLAffectedMessages;
import org.telegram.api.paymentapi.payments.result.TLPaymentsPaymentResult;
import org.telegram.api.update.TLFakeUpdate;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.api.updates.TLUpdateShort;
import org.telegram.bot.GenericErrorTelegramFunctionCallback;
import org.telegram.bot.TelegramFunctionCallback;
import org.telegram.bot.factories.TLFactory;
import org.telegram.bot.services.BotLogger;
import org.telegram.bot.services.NotificationsService;
import org.telegram.bot.structure.Chat;
import org.telegram.bot.structure.IUser;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hendrik Hofstadt
 * @author Ruben Bermudez
 * @version 2.0
 * @brief Communication service to send messages via Telegram
 * @date 16.03.14
 */
public class KernelComm implements IKernelComm {
    private static final String LOGTAG = "KERNELCOMM";
    private static final String LANGUAGE_EN = "en";
    private static final String botVersion = "0.1";
    private static final String botName = "Deepthought";
    private static final int BYTES = 1024;

    private static final Pattern boldMarkdownRegex = Pattern.compile("\\*.+?\\*");
    private static final Pattern italicMarkdownRegex =  Pattern.compile("\\_.+?\\_");
    private static final Pattern codeMarkdownRegex = Pattern.compile("\\`.+?\\`");

    private final ExecutorService exe = Executors.newCachedThreadPool();
    private final SecureRandom random = new SecureRandom();
    private final AbsApiState apiState;
    private final int apiKey;
    private TelegramApi api;
    private MainHandler mainHandler;

    public KernelComm(int apiKey, AbsApiState apiState) {
        NotificationsService.getInstance().addObserver(this, NotificationsService.updatesInvalidated);
        this.apiKey = apiKey;
        this.apiState = apiState;
        try {
            final File URANDOM_FILE = new File("/dev/urandom");
            final FileInputStream sUrandomIn = new FileInputStream(URANDOM_FILE);
            final byte[] buffer = new byte[BYTES];
            sUrandomIn.read(buffer);
            sUrandomIn.close();
            this.random.setSeed(buffer);
        } catch (FileNotFoundException e) {
            BotLogger.info(LOGTAG, e);
        } catch (Exception e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    public void setMainHandler(@NotNull MainHandler mainHandler) {
        this.mainHandler = mainHandler;
    }

    @Override
    public boolean init() {
        BotLogger.debug(LOGTAG, "Initializing kernelComm");
        createApi();
        BotLogger.debug(LOGTAG, "KernelComm initialized");
        return true;
    }

    private void createApi() {
        BotLogger.trace(LOGTAG, "Creating TelegramApi");
        this.api = new TelegramApi(apiState, new AppInfo(apiKey, "bot", botName, botVersion, LANGUAGE_EN), new ApiCallback() {

            @Override
            public void onAuthCancelled(TelegramApi api) {
                apiState.resetAuth();
                BotLogger.severe(LOGTAG, "Auth cancelled");
            }

            @Override
            public void onUpdatesInvalidated(TelegramApi api) {
                if (apiState.isAuthenticated()) {
                    NotificationsService.getInstance().postNotification(NotificationsService.updatesInvalidated, api);
                }
            }

            @Override
            public void onUpdate(TLAbsUpdates updates) {
                handleUpdates(updates);
            }
        });
        BotLogger.debug(LOGTAG, "Api created");
    }

    private void handleUpdates(TLAbsUpdates updates) {
        if (mainHandler != null) {
            mainHandler.onUpdate(updates);
        } else {
            BotLogger.severe(LOGTAG, "Main Handler not found from kernelcomm");
        }
    }

    private void handleFakeUpdate(int pts, int ptsCount) {
        final TLFakeUpdate fakeUpdate = new TLFakeUpdate();
        fakeUpdate.setPts(pts);
        fakeUpdate.setPtsCount(ptsCount);
        final TLUpdateShort updateShort = new TLUpdateShort();
        updateShort.setDate(0);
        updateShort.setUpdate(fakeUpdate);
        mainHandler.onUpdate(updateShort);
    }

    private void handleAffectedMessagesAndHistory(TLObject object) {
        if (object instanceof TLAffectedHistory) {
            handleFakeUpdate(((TLAffectedHistory) object).getPts(), ((TLAffectedHistory) object).getPtsCount());
        } else if (object instanceof TLAffectedMessages) {
            handleFakeUpdate(((TLAffectedMessages) object).getPts(), ((TLAffectedMessages) object).getPtsCount());
        } else if (object instanceof TLPaymentsPaymentResult) {
            mainHandler.onUpdate(((TLPaymentsPaymentResult) object).getUpdates());
        }
    }

    @Override
    public <T extends TLObject> T doRpcCallSync(final TLMethod<T> method) throws ExecutionException, RpcException {
        T answer = null;
        if (getApi() != null) {
            final Future<T> result = this.exe.submit(() -> {
                T internalAnswer = null;
                try {
                    internalAnswer = getApi().doRpcCall(method);
                } catch (RpcException e) {
                    BotLogger.debug(LOGTAG, "Rpc call failed", e);
                    throw e;
                } catch (TimeoutException e) {
                    BotLogger.debug(LOGTAG, "timeout");
                } catch (Exception e) {
                    BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSync", e);
                }

                return internalAnswer;
            });
            try {
                answer = result.get();
            } catch (InterruptedException e) {
                BotLogger.error(LOGTAG, e);
            } catch (Exception e) {
                BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSync", e);
            }
        }

        handleAffectedMessagesAndHistory(answer);
        return answer;
    }

    @Override
    public <T extends TLObject> T doRpcCallSyncNoAuth(final TLMethod<T> method) throws ExecutionException {
        T answer = null;
        final Future<T> result = this.exe.submit(() -> {
            T answerInternal = null;
            try {
                answerInternal = this.api.doRpcCallNonAuth(method);
            } catch (RpcException e) {
                BotLogger.error(LOGTAG, "Rpc call failed", e);
                throw e;
            } catch (Exception e) {
                BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSyncNoAuth");
            }
            return answerInternal;
        });
        try {
            answer = result.get();
        } catch (InterruptedException e) {
            BotLogger.error(LOGTAG, e);
        } catch (Exception e) {
            BotLogger.severe(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSyncNoAuth");
        }

        handleAffectedMessagesAndHistory(answer);
        return answer;
    }

    @Override
    public <T extends TLObject> void doRpcCallAsync(final TLMethod<T> method, TelegramFunctionCallback<T> callback) {
        if (getApi() != null) {
            final CompletableFuture<T> result = CompletableFuture.supplyAsync(() -> {
                T internalAnswer = null;
                try {
                    internalAnswer = getApi().doRpcCall(method);
                    handleAffectedMessagesAndHistory(internalAnswer);
                } catch (RpcException e) {
                    BotLogger.debug(LOGTAG, "Rpc call failed", e);
                    callback.onRpcError(e);
                } catch (TimeoutException e) {
                    BotLogger.debug(LOGTAG, "timeout");
                    callback.onTimeout(e);
                } catch (Exception e) {
                    BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSync", e);
                    callback.onUnknownError(e);
                }

                return internalAnswer;
            });
            result.handleAsync((ok, ex) -> {
                if (ok != null) {
                    callback.onSuccess(ok);
                } else {
                    callback.onUnknownError(ex);
                }
                return null;
            });
        }
    }

    @Override
    public void doRpcCallAsyncNoReturn(final TLMethod<TLObject> method) {
        this.exe.submit(() -> {
            try {
                final TLObject answer = this.api.doRpcCall(method);
                handleAffectedMessagesAndHistory(answer);
            } catch (RpcException e) {
                BotLogger.severe(LOGTAG, "Rpc call failed", e);
            } catch (TimeoutException e) {
                BotLogger.severe(LOGTAG, "timeout");
            } catch (IOException e) {
                BotLogger.error(LOGTAG, e);
            } catch (Exception e) {
                BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallAsyncNoReturn");
            }
        });
    }

    private void sendMessageInternal(@NotNull IUser user, @Nullable String message, @Nullable Integer replayToMsg,
                                     @Nullable TLVector<TLAbsMessageEntity> entities,
                                     boolean enableWebPreview, boolean parseMarkdown) throws RpcException {
        boolean canSend = false;
        final TLRequestMessagesSendMessage request = new TLRequestMessagesSendMessage();
        request.setPeer(TLFactory.createTLInputPeer(user, null));

        if ((message != null) && !message.isEmpty()) {
            if (parseMarkdown) {
                if (entities == null) {
                    entities = new TLVector<>();
                }
                message = readEntities(message, entities);
            }
            request.setMessage(message);
            canSend = true;
        }

        if (replayToMsg != null) {
            BotLogger.info(LOGTAG, "Sending message " + replayToMsg + " as reply to: " + user.getUserId());
            request.setReplyToMsgId(replayToMsg);
        }
        if (!enableWebPreview) {
            BotLogger.info(LOGTAG, "Sending message without preview to: " + user.getUserId());
            request.disableWebPreview();
        }

        if (entities != null && !entities.isEmpty()) {
            request.setEntities(entities);
        }

        if (canSend) {
            BotLogger.info(LOGTAG, "Sending message to: " + user.getUserId());
            performSendMessageSyncInternal(request);
        }
    }

    private void sendMessageInternalAsync(@NotNull IUser user, @Nullable String message, @Nullable Integer replayToMsg,
                                          @Nullable TLVector<TLAbsMessageEntity> entities,
                                          boolean enableWebPreview, boolean parseMarkdown, TelegramFunctionCallback<TLAbsUpdates> callback) {
        boolean canSend = false;
        final TLRequestMessagesSendMessage request = new TLRequestMessagesSendMessage();
        request.setPeer(TLFactory.createTLInputPeer(user, null));

        if ((message != null) && !message.isEmpty()) {
            if (parseMarkdown) {
                if (entities == null) {
                    entities = new TLVector<>();
                }
                message = readEntities(message, entities);
            }
            request.setMessage(message);
            canSend = true;
        }

        if (replayToMsg != null) {
            BotLogger.info(LOGTAG, "Sending message " + replayToMsg + " as reply to: " + user.getUserId());
            request.setReplyToMsgId(replayToMsg);
        }
        if (!enableWebPreview) {
            BotLogger.info(LOGTAG, "Sending message without preview to: " + user.getUserId());
            request.disableWebPreview();
        }

        if (entities != null && !entities.isEmpty()) {
            request.setEntities(entities);
        }

        if (canSend) {
            BotLogger.info(LOGTAG, "Sending message to: " + user.getUserId());
            final int id = this.random.nextInt();
            request.setRandomId(id);

            doRpcCallAsync(request, callback);
        }
    }

    private void sendMessageGroupInternal(@NotNull Chat group, @Nullable String message, @Nullable Integer replayToMsg,
                                          @Nullable TLVector<TLAbsMessageEntity> entities,
                                          boolean enableWebPreview, boolean parseMarkdown) throws RpcException {
        boolean canSend = false;
        final TLRequestMessagesSendMessage request = new TLRequestMessagesSendMessage();
        request.setPeer(TLFactory.createTLInputPeer(null, group));

        if ((message != null) && !message.isEmpty()) {
            if (parseMarkdown) {
                if (entities == null) {
                    entities = new TLVector<>();
                }
                message = readEntities(message, entities);
            }
            request.setMessage(message);
            canSend = true;
        }

        if (replayToMsg != null) {
            BotLogger.info(LOGTAG, "Sending group message " + replayToMsg + " as reply to: " + group.getId());
            request.setReplyToMsgId(replayToMsg);
        }

        if (!enableWebPreview) {
            BotLogger.info(LOGTAG, "Sending group message without preview to: " + group.getId());
            request.disableWebPreview();
        }

        if (entities != null && !entities.isEmpty()) {
            request.setEntities(entities);
        }

        if (canSend) {
            BotLogger.info(LOGTAG, "Sending message to: " + group.getId());
            performSendMessageSyncInternal(request);
        }
    }

    private void sendMessageChannelInternal(@NotNull Chat channel, @Nullable String message, @Nullable Integer replayToMsg,
                                            @Nullable TLVector<TLAbsMessageEntity> entities,
                                            boolean enableWebPreview, boolean parseMarkdown, boolean asAdmin)
            throws RpcException {

        boolean canSend = false;
        final TLRequestMessagesSendMessage request = new TLRequestMessagesSendMessage();

        if ((message != null) && !message.isEmpty()) {
            if (parseMarkdown) {
                if (entities == null) {
                    entities = new TLVector<>();
                }
                message = readEntities(message, entities);
            }
            request.setMessage(message);
            canSend = true;
        }

        if (asAdmin) {
            request.enableBroadcast(true);
        }

        if (replayToMsg != null) {
            BotLogger.info(LOGTAG, "Sending channel message " + replayToMsg + " as reply to: " + channel.getId());
            request.setReplyToMsgId(replayToMsg);
        }
        if (!enableWebPreview) {
            BotLogger.info(LOGTAG, "Sending channel message without preview to: " + channel.getId());
            request.disableWebPreview();
        }

        if (entities != null && !entities.isEmpty()) {
            request.setEntities(entities);
        }

        if (canSend) {
            request.setPeer(TLFactory.createTLInputPeer(null, channel));
            BotLogger.info(LOGTAG, "Sending channel message to: " + channel.getId());
            performSendMessageSyncInternal(request);
        }
    }

    private void performSendMessageSyncInternal(TLRequestMessagesSendMessage request) throws RpcException {
        final int id = this.random.nextInt();
        request.setRandomId(id);

        try {
            final TLAbsUpdates updates = doRpcCallSync(request);
            if (updates != null) {
                handleUpdates(updates);
            }
        } catch (ExecutionException e) {
            BotLogger.error(LOGTAG, e);
        } finally {
            BotLogger.info(LOGTAG, "Sending message " + id + " was successful");
        }
    }

    @Override
    public void sendMessage(@NotNull IUser user, @NotNull String message) throws RpcException {
        sendMessageInternal(user, message, null,null, false, false);
    }

    @Override
    public void sendMessage(@NotNull IUser user, @NotNull String message, @NotNull Boolean hasWebPreview) throws RpcException {
        sendMessageInternal(user, message, null, null,hasWebPreview, false);
    }

    @Override
    public void sendMessage(@NotNull IUser user, @Nullable String message, @Nullable Integer replayToMsg, @Nullable TLVector<TLAbsMessageEntity> entities, boolean enableWebPreview, boolean parseMarkdown) throws RpcException {
        sendMessageInternal(user, message, replayToMsg, entities, enableWebPreview, parseMarkdown);
    }

    /**
     * Send a message without web preview
     * @param user Destination user
     * @param message Message to send
     * @param hasWebPreview If the message must have web preview
     * @param callback Callback to execute after sent. If no provided, a default one will be executed.
     *
     * @note Default callback just send received updates to KernelHandler and add the id to the sent by implementation messages list.
     */
    @Override
    public void sendMessageAsync(@NotNull IUser user, @NotNull String message, @NotNull Boolean hasWebPreview, @Nullable TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }
        sendMessageInternalAsync(user, message, null, null,hasWebPreview, false, callback);
    }

    /**
     * Send a message
     * @param user Destination user
     * @param message Message to send
     * @param callback Callback to execute after sent. If no provided, a default one will be executed.
     *
     * @note Default callback just send received updates to KernelHandler and add the id to the sent by implementation messages list.
     */
    @Override
    public void sendMessageAsync(@NotNull IUser user, @NotNull String message, TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }
        sendMessageInternalAsync(user, message, null, null, true, false, callback);
    }

    @Override
    public void sendMessageAsReply(@NotNull IUser user, @NotNull String message, @NotNull Integer replayToMsg) throws RpcException {
        sendMessageInternal(user, message, replayToMsg, null, true, false);
    }

    @Override
    public void sendMessageAsync(@NotNull IUser user, @Nullable String message, @Nullable Integer replayToMsg, @Nullable TLVector<TLAbsMessageEntity> entities, boolean enableWebPreview, boolean parseMarkdown, TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }
        sendMessageInternalAsync(user, message, replayToMsg, entities, enableWebPreview, parseMarkdown, callback);
    }

    /**
     * Send a message as reply
     * @param user Destination user
     * @param message Message to send
     * @param replayToMsg Id of the message to answer
     * @param callback Callback to execute after sent. If no provided, a default one will be executed.
     *
     * @note Default callback just send received updates to KernelHandler and add the id to the sent by implementation messages list.
     */
    @Override
    public void sendMessageAsReplyAsync(@NotNull IUser user, @NotNull String message, @NotNull Integer replayToMsg, TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }

        sendMessageInternalAsync(user, message, replayToMsg, null,true, false, callback);
    }

    @Override
    public void sendMessageWithMarkdown(@NotNull IUser user, @NotNull String message) throws RpcException {
        sendMessageInternal(user, message, null, null,true, true);
    }

    @Override
    public void sendMessageWithEntities(@NotNull IUser user, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) throws RpcException {
        sendMessageInternal(user, message, null, entities,true, true);
    }

    @Override
    public void sendMessageWithoutPreview(@NotNull IUser user, @NotNull String message) throws RpcException {
        sendMessageInternal(user, message, null, null,false, false);
    }

    /**
     * Send a message without web preview
     * @param user Destination user
     * @param message Message to send
     * @param callback Callback to execute after sent. If no provided, a default one will be executed.
     *
     * @note Default callback just send received updates to KernelHandler and add the id to the sent by bot messages list.
     */
    @Override
    public void sendMessageWithoutPreviewAsync(@NotNull IUser user, @NotNull String message, @Nullable TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }
        sendMessageInternalAsync(user, message, null, null, false, false, callback);
    }

    @Override
    public void sendMessageWithEntitiesAsync(@NotNull IUser user, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities, TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }
        sendMessageInternalAsync(user, message, null, entities, false, false, callback);
    }

    @Override
    public void sendGroupMessage(@NotNull Chat group, @NotNull String message) throws RpcException {
        sendMessageGroupInternal(group, message, null, null, true, false);
    }

    @Override
    public void sendGroupMessage(@NotNull Chat group, @Nullable String message, @Nullable Integer replayToMsg, @Nullable TLVector<TLAbsMessageEntity> entities, boolean enableWebPreview, boolean parseMarkdown) throws RpcException {
        sendMessageGroupInternal(group, message, replayToMsg, entities, enableWebPreview, parseMarkdown);
    }

    @Override
    public void sendGroupMessageWithMarkdown(@NotNull Chat group, @NotNull String message) throws RpcException {
        sendMessageGroupInternal(group, message, null, null, true, true);
    }

    @Override
    public void sendGroupMessageWithEntities(@NotNull Chat group, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) throws RpcException {
        sendMessageGroupInternal(group, message, null, entities, true, true);
    }

    @Override
    public void sendGroupMessageWithoutPreview(@NotNull Chat group, @NotNull String message) throws RpcException {
        sendMessageGroupInternal(group, message, null, null, false, false);
    }

    @Override
    public void sendChannelMessage(@NotNull Chat channel, @NotNull String message, boolean asAdmin) throws RpcException {
        sendMessageChannelInternal(channel, message, null,null, true, false, asAdmin);
    }

    @Override
    public void sendChannelMessage(@NotNull Chat channel, @Nullable String message, @Nullable Integer replayToMsg, @Nullable TLVector<TLAbsMessageEntity> entities, boolean enableWebPreview, boolean parseMarkdown, boolean asAdmin) throws RpcException {
        sendMessageChannelInternal(channel, message, replayToMsg,entities, enableWebPreview, parseMarkdown, asAdmin);
    }

    @Override
    public void sendChannelMessageWithMarkdown(@NotNull Chat channel, @NotNull String message, boolean asAdmin) throws RpcException {
        sendMessageChannelInternal(channel, message, null, null, true, true, asAdmin);
    }

    @Override
    public void sendChannelMessageWithEntities(@NotNull Chat channel, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities, boolean asAdmin) throws RpcException {
        sendMessageChannelInternal(channel, message, null, entities, true, true, asAdmin);
    }

    @Override
    public void sendChannelMessageWithoutPreview(@NotNull Chat channel, @NotNull String message, boolean asAdmin) throws RpcException {
        sendMessageChannelInternal(channel, message, null, null, false, false, asAdmin);
    }

    @Override
    public void sendMedia(@NotNull IUser user, @NotNull TLAbsInputMedia media) throws RpcException{
        final int id = this.random.nextInt();
        try {
            BotLogger.debug(LOGTAG, "Sending media " + id + " to: " + user + " : " + media);
            BotLogger.debug(LOGTAG, "Sending media " + id + " to: " + user);
            final TLRequestMessagesSendMedia request = new TLRequestMessagesSendMedia();
            request.setPeer(TLFactory.createTLInputPeer(user, null));
            request.setMedia(media);
            request.setRandomId(id);
            final TLAbsUpdates updates = doRpcCallSync(request);
            if (updates != null) {
                handleUpdates(updates);
            }
        } catch (ExecutionException e) {
            BotLogger.warn(LOGTAG,"Sending media " + id + " failed");
        }
    }

    @Override
    public void sendGroupMedia(@NotNull Chat chat, @NotNull TLAbsInputMedia media) throws RpcException {
        final int id = this.random.nextInt();
        try {
            BotLogger.debug(LOGTAG, "Sending media " + id + " to group: " + chat.getId() + " : " + media);
            BotLogger.info(LOGTAG, "Sending media " + id + " to group: " + chat.getId());
            final TLRequestMessagesSendMedia request = new TLRequestMessagesSendMedia();
            request.setPeer(TLFactory.createTLInputPeer(null, chat));
            request.setMedia(media);
            request.setRandomId(id);
            final TLAbsUpdates updates = doRpcCallSync(request);
            if (updates != null) {
                handleUpdates(updates);
            }
        } catch (ExecutionException e) {
            BotLogger.warn(LOGTAG, "Sending media " + id + " failed");
        }
    }

    @Override
    public void sendUploadedSticker(@NotNull String title, @NotNull String mimetype, @NotNull IUser user, long idFile, int parts) throws RpcException {
        final int id = this.random.nextInt();
        try {
            final TLVector<TLAbsDocumentAttribute> attributes = new TLVector<>();
            final TLDocumentAttributeFilename fileName = new TLDocumentAttributeFilename();
            fileName.setFileName(title);
            attributes.add(fileName);
            final TLDocumentAttributeSticker sticker = new TLDocumentAttributeSticker();
            sticker.setAlt("");
            sticker.setStickerset(new TLInputStickerSetEmpty());
            attributes.add(sticker);
            final TLInputFile inputFile = new TLInputFile();
            inputFile.setId(idFile);
            inputFile.setMd5Checksum("");
            inputFile.setName(title);
            inputFile.setParts(parts);
            final TLInputMediaUploadedDocument inputMediaUploadedDocument = new TLInputMediaUploadedDocument();
            inputMediaUploadedDocument.setFile(inputFile);
            inputMediaUploadedDocument.setMimeType(mimetype);
            inputMediaUploadedDocument.setAttributes(attributes);
            inputMediaUploadedDocument.setCaption("");
            sendMedia(user, inputMediaUploadedDocument);
        } finally {
            BotLogger.info(LOGTAG, "Sending file " + id + " to " + user.getUserId() + " was successful");
        }
    }

    @Override
    public void sendUploadedGroupSticker(@NotNull String title, @NotNull String mimetype, @NotNull Chat group, long idFile, int parts) throws RpcException {
        final int id = this.random.nextInt();
        try {
            final TLVector<TLAbsDocumentAttribute> attributes = new TLVector<>();
            final TLDocumentAttributeFilename fileName = new TLDocumentAttributeFilename();
            fileName.setFileName(title);
            attributes.add(fileName);
            final TLDocumentAttributeSticker sticker = new TLDocumentAttributeSticker();
            sticker.setAlt("");
            sticker.setStickerset(new TLInputStickerSetEmpty());
            attributes.add(sticker);
            final TLInputFile inputFile = new TLInputFile();
            inputFile.setId(idFile);
            inputFile.setMd5Checksum("");
            inputFile.setName(title);
            inputFile.setParts(parts);
            final TLInputMediaUploadedDocument inputMediaUploadedDocument = new TLInputMediaUploadedDocument();
            inputMediaUploadedDocument.setFile(inputFile);
            inputMediaUploadedDocument.setMimeType(mimetype);
            inputMediaUploadedDocument.setAttributes(attributes);
            inputMediaUploadedDocument.setCaption("");
            final TLRequestMessagesSendMedia request = new TLRequestMessagesSendMedia();
            request.setPeer(TLFactory.createTLInputPeer(null, group));
            request.setMedia(inputMediaUploadedDocument);
            request.setRandomId(id);
            final TLAbsUpdates updates = doRpcCallSync(request);
            if (updates != null) {
                handleUpdates(updates);
            }
        } catch (ExecutionException e) {
            BotLogger.error(LOGTAG, e);
            BotLogger.warn(LOGTAG, "Sending file " + id + " to group " + group.getId() + " failed");
        } finally {
            BotLogger.info(LOGTAG, "Sending file " + id + " to group " + group.getId() + " was successful");
        }
    }

    @Override
    public void performMarkAsRead(@NotNull IUser user, int messageId) throws RpcException {
        performMarkAsReadInternal(TLFactory.createTLInputPeer(user, null), messageId);
    }

    @Override
    public void performMarkGroupAsRead(@NotNull Chat group, int messageId) throws RpcException {
        performMarkAsReadInternal(TLFactory.createTLInputPeer(null, group), messageId);
    }

    private void performMarkAsReadInternal(TLAbsInputPeer inputPeer, int messageId) throws RpcException {
        try {
            if (inputPeer instanceof TLInputPeerChannel) {
                final TLInputChannel inputChannel = new TLInputChannel();
                inputChannel.setChannelId(((TLInputPeerChannel) inputPeer).getChannelId());
                inputChannel.setAccessHash(((TLInputPeerChannel) inputPeer).getAccessHash());
                final TLRequestChannelsReadHistory tlRequestChannelsReadHistory = new TLRequestChannelsReadHistory();
                tlRequestChannelsReadHistory.setChannel(inputChannel);
                tlRequestChannelsReadHistory.setMaxId(messageId);
                doRpcCallSync(tlRequestChannelsReadHistory);
            } else {
                final TLRequestMessagesReadHistory tlRequestMessagesReadHistory = new TLRequestMessagesReadHistory();
                tlRequestMessagesReadHistory.setPeer(inputPeer);
                tlRequestMessagesReadHistory.setMaxId(messageId);
                doRpcCallSync(tlRequestMessagesReadHistory);
            }

        } catch (ExecutionException e) {
            BotLogger.severe(LOGTAG, e);
        }
    }

    @Override
    public int getCurrentUserId() {
        return this.apiState.getUserId();
    }

    @NotNull
    private String readEntities(@NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) {
        message = readBoldEntities(entities, message);
        message = readItalicEntities(entities, message);
        message = readCodeEntities(entities, message);

        return message;
    }

    @NotNull
    private String readBoldEntities(@NotNull TLVector<TLAbsMessageEntity> entities, @NotNull String message) {
        final StringBuilder finalMessage = new StringBuilder();
        int lastAddedIndex = 0;
        final Matcher matcher = boldMarkdownRegex.matcher(message);

        while (matcher.find()) {
            final int startIndex = matcher.start();
            final int lastIndex = matcher.end();
            finalMessage.append(message.substring(lastAddedIndex, startIndex));
            final int initMarkdown = finalMessage.length();
            finalMessage.append(message.substring(startIndex + 1, lastIndex-1));
            lastAddedIndex = lastIndex;
            final TLMessageEntityBold boldEntity = new TLMessageEntityBold();
            boldEntity.setOffset(initMarkdown);
            boldEntity.setLength(lastIndex - startIndex - 2);
            entities.add(boldEntity);
        }

        if (lastAddedIndex != message.length()) {
            finalMessage.append(message.substring(lastAddedIndex));
        }

        return finalMessage.toString();
    }

    @NotNull
    private String readItalicEntities(@NotNull TLVector<TLAbsMessageEntity> entities, @NotNull String message) {
        final StringBuilder finalMessage = new StringBuilder();
        int lastAddedIndex = 0;
        final Matcher matcher = italicMarkdownRegex.matcher(message);

        while (matcher.find()) {
            final int startIndex = matcher.start();
            final int lastIndex = matcher.end();
            finalMessage.append(message.substring(lastAddedIndex, startIndex));
            final int initMarkdown = finalMessage.length();
            finalMessage.append(message.substring(startIndex + 1, lastIndex-1));
            lastAddedIndex = lastIndex;
            final TLMessageEntityItalic italicEntity = new TLMessageEntityItalic();
            italicEntity.setOffset(initMarkdown);
            italicEntity.setLength(lastIndex - startIndex - 2);
            entities.add(italicEntity);
        }

        if (lastAddedIndex != message.length()) {
            finalMessage.append(message.substring(lastAddedIndex));
        }

        return finalMessage.toString();
    }

    @NotNull
    private String readCodeEntities(@NotNull TLVector<TLAbsMessageEntity> entities, @NotNull String message) {
        final StringBuilder finalMessage = new StringBuilder();
        int lastAddedIndex = 0;
        final Matcher matcher = codeMarkdownRegex.matcher(message);

        while (matcher.find()) {
            final int startIndex = matcher.start();
            final int lastIndex = matcher.end();
            finalMessage.append(message.substring(lastAddedIndex, startIndex));
            final int initMarkdown = finalMessage.length();
            finalMessage.append(message.substring(startIndex + 1, lastIndex-1));
            lastAddedIndex = lastIndex;
            final TLMessageEntityCode codeEntity = new TLMessageEntityCode();
            codeEntity.setOffset(initMarkdown);
            codeEntity.setLength(lastIndex - startIndex - 2);
            entities.add(codeEntity);
        }

        if (lastAddedIndex != message.length()) {
            finalMessage.append(message.substring(lastAddedIndex));
        }

        return finalMessage.toString();
    }

    @Override
    public void onNotificationReceived(int notificationId, Object... args) {
        if (notificationId == NotificationsService.updatesInvalidated) {
            if (args.length == 1) {
                try {
                    ((TelegramApi) args[0]).doRpcCall(new TLRequestUpdatesGetState());
                    BotLogger.debug(LOGTAG, "Updated recreated session");
                } catch (IOException | java.util.concurrent.TimeoutException e) {
                    BotLogger.error(LOGTAG, "Updating recreated session failed", e);
                }
                BotLogger.debug(LOGTAG, "Updated recreated session");
                NotificationsService.getInstance().postNotification(NotificationsService.needGetUpdates);
            }
        }
    }

    @Override
    public Downloader getDownloader() {
        return this.api.getDownloader();
    }

    @Override
    public Uploader getUploader() {
        return this.api.getUploader();
    }

    @Override
    public TelegramApi getApi() {
        return this.api;
    }

    @Contract("_ -> !null")
    private TelegramFunctionCallback<TLAbsUpdates> getDefaultSendMessageCallback(final @NotNull IUser user) {
        return new GenericErrorTelegramFunctionCallback<TLAbsUpdates>() {
            @Override
            public void onError(Throwable e) {
                BotLogger.error(LOGTAG, "Failed sending message to: " + user.getUserId(), e);
            }

            @Override
            public void onSuccess(TLAbsUpdates result) {
                if (result != null) {
                    KernelComm.this.handleUpdates(result);
                }
            }
        };
    }

    @Override
    protected void finalize() throws Throwable {
        NotificationsService.getInstance().removeObserver(this, NotificationsService.updatesInvalidated);
        super.finalize();
    }
}