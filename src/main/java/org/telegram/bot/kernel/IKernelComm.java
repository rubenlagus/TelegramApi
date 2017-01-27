package org.telegram.bot.kernel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.api.engine.RpcException;
import org.telegram.api.engine.TelegramApi;
import org.telegram.api.engine.file.Downloader;
import org.telegram.api.engine.file.Uploader;
import org.telegram.api.input.media.TLAbsInputMedia;
import org.telegram.api.message.entity.TLAbsMessageEntity;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.bot.TelegramFunctionCallback;
import org.telegram.bot.services.NotificationsService;
import org.telegram.bot.structure.Chat;
import org.telegram.bot.structure.IUser;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.util.concurrent.ExecutionException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Interface for the helper class to perform Telegram API request
 * @date 22 of March of 2016
 */
public interface IKernelComm extends NotificationsService.NotificationObserver {
    boolean init();

    void setMainHandler(@NotNull MainHandler mainHandler);

    <T extends TLObject> T doRpcCallSync(TLMethod<T> method) throws ExecutionException, RpcException;

    <T extends TLObject> T doRpcCallSyncNoAuth(TLMethod<T> method) throws ExecutionException, RpcException;

    <T extends TLObject> void doRpcCallAsync(TLMethod<T> method, TelegramFunctionCallback<T> callback);

    void doRpcCallAsyncNoReturn(TLMethod<TLObject> method);

    void sendMessage(@NotNull IUser user, @NotNull String message) throws RpcException;

    void sendMessageWithMarkdown(@NotNull IUser user, @NotNull String message) throws RpcException;

    void sendMessageWithEntities(@NotNull IUser user, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) throws RpcException;

    void sendMessage(@NotNull IUser user, @NotNull String message, @NotNull Boolean hasWebPreview) throws RpcException;

    void sendMessage(@NotNull IUser user, @Nullable String message, @Nullable Integer replayToMsg,
                     @Nullable TLVector<TLAbsMessageEntity> entities,
                     boolean enableWebPreview, boolean parseMarkdown) throws RpcException;

    void sendMessageAsync(@NotNull IUser user, @NotNull String message, @NotNull Boolean hasWebPreview, @Nullable TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendMessageAsync(@NotNull IUser user, @NotNull String message, TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendMessageAsync(@NotNull IUser user, @Nullable String message, @Nullable Integer replayToMsg,
                          @Nullable TLVector<TLAbsMessageEntity> entities,
                          boolean enableWebPreview, boolean parseMarkdown, TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendMessageWithEntitiesAsync(@NotNull IUser user, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities, TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendMessageAsReply(@NotNull IUser user, @NotNull String message, @NotNull Integer replayToMsg) throws RpcException;

    void sendMessageAsReplyAsync(@NotNull IUser user, @NotNull String message, @NotNull Integer replayToMsg, TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendMessageWithoutPreview(@NotNull IUser user, @NotNull String message) throws RpcException;

    void sendMessageWithoutPreviewAsync(@NotNull IUser user, @NotNull String message, @Nullable TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendGroupMessage(@NotNull Chat group, @NotNull String message) throws RpcException;

    void sendGroupMessage(@NotNull Chat group, @Nullable String message, @Nullable Integer replayToMsg,
                          @Nullable TLVector<TLAbsMessageEntity> entities,
                          boolean enableWebPreview, boolean parseMarkdown) throws RpcException;

    void sendGroupMessageWithMarkdown(@NotNull Chat group, @NotNull String message) throws RpcException;

    void sendGroupMessageWithEntities(@NotNull Chat group, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) throws RpcException;

    void sendGroupMessageWithoutPreview(@NotNull Chat group, @NotNull String message) throws RpcException;

    void sendChannelMessage(@NotNull Chat channel, @NotNull String message, boolean asAdmin) throws RpcException;

    void sendChannelMessage(@NotNull Chat channel, @Nullable String message, @Nullable Integer replayToMsg,
                            @Nullable TLVector<TLAbsMessageEntity> entities,
                            boolean enableWebPreview, boolean parseMarkdown, boolean asAdmin)
            throws RpcException;
    void sendChannelMessageWithMarkdown(@NotNull Chat channel, @NotNull String message, boolean asAdmin) throws RpcException;

    void sendChannelMessageWithEntities(@NotNull Chat channel, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities, boolean asAdmin) throws RpcException;

    void sendChannelMessageWithoutPreview(@NotNull Chat channel, @NotNull String message, boolean asAdmin) throws RpcException;

    void sendMedia(@NotNull IUser user, @NotNull TLAbsInputMedia media) throws RpcException;

    void sendGroupMedia(@NotNull Chat group, @NotNull TLAbsInputMedia media) throws RpcException;

    void sendUploadedSticker(@NotNull String title, @NotNull String mimetype, @NotNull IUser user, long idFile, int parts) throws RpcException;

    void sendUploadedGroupSticker(@NotNull String title, @NotNull String mimetype, @NotNull Chat group, long idFile, int parts) throws RpcException;

    void performMarkAsRead(@NotNull IUser user, int messageId) throws RpcException;

    void performMarkGroupAsRead(@NotNull Chat group, int messageId) throws RpcException;

    int getCurrentUserId();

    Downloader getDownloader();

    Uploader getUploader();

    TelegramApi getApi();
}
