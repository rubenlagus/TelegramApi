package org.telegram.bot.handlers;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.update.*;
import org.telegram.api.update.encrypted.TLUpdateEncryptedChatTyping;
import org.telegram.api.update.encrypted.TLUpdateEncryptedMessagesRead;
import org.telegram.api.update.encrypted.TLUpdateEncryption;
import org.telegram.api.update.encrypted.TLUpdateNewEncryptedMessage;
import org.telegram.api.updates.TLUpdateShortChatMessage;
import org.telegram.api.updates.TLUpdateShortMessage;
import org.telegram.api.updates.TLUpdateShortSentMessage;
import org.telegram.api.user.TLAbsUser;
import org.telegram.bot.kernel.IKernelComm;
import org.telegram.bot.kernel.database.DatabaseManager;
import org.telegram.bot.kernel.differenceparameters.IDifferenceParametersService;

import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of April of 2016
 */
public class DefaultUpdatesHandler extends UpdatesHandlerBase {
    public DefaultUpdatesHandler(IKernelComm kernelComm, IDifferenceParametersService differenceParametersService, DatabaseManager databaseManager) {
        super(kernelComm, differenceParametersService, databaseManager);
    }

    @Override
    protected void onTLUpdateChatParticipantsCustom(TLUpdateChatParticipants update) {

    }

    @Override
    protected void onTLUpdateNewMessageCustom(TLUpdateNewMessage update) {

    }

    @Override
    protected void onTLUpdateChannelNewMessageCustom(TLUpdateChannelNewMessage update) {

    }

    @Override
    protected void onTLUpdateChannelCustom(TLUpdateChannel update) {

    }

    @Override
    protected void onTLUpdateBotInlineQueryCustom(TLUpdateBotInlineQuery update) {

    }

    @Override
    protected void onTLUpdateBotInlineSendCustom(TLUpdateBotInlineSend update) {

    }

    @Override
    protected void onTLUpdateChannelMessageViewsCustom(TLUpdateChannelMessageViews update) {

    }

    @Override
    protected void onTLUpdateChannelPinnedMessageCustom(TLUpdateChannelPinnedMessage update) {

    }

    @Override
    protected void onTLUpdateChatAdminCustom(TLUpdateChatAdmin update) {

    }

    @Override
    protected void onTLUpdateChatParticipantAddCustom(TLUpdateChatParticipantAdd update) {

    }

    @Override
    protected void onTLUpdateChatParticipantAdminCustom(TLUpdateChatParticipantAdmin update) {

    }

    @Override
    protected void onTLUpdateChatParticipantDeleteCustom(TLUpdateChatParticipantDelete update) {

    }

    @Override
    protected void onTLUpdateChatUserTypingCustom(TLUpdateChatUserTyping update) {

    }

    @Override
    protected void onTLUpdateContactLinkCustom(TLUpdateContactLink update) {

    }

    @Override
    protected void onTLUpdateContactRegisteredCustom(TLUpdateContactRegistered update) {

    }

    @Override
    protected void onTLUpdateDcOptionsCustom(TLUpdateDcOptions update) {

    }

    @Override
    protected void onTLUpdateDeleteChannelMessagesCustom(TLUpdateDeleteChannelMessages update) {

    }

    @Override
    protected void onTLUpdateDeleteMessagesCustom(TLUpdateDeleteMessages update) {

    }

    @Override
    protected void onTLUpdateEditChannelMessageCustom(TLUpdateEditChannelMessage update) {

    }

    @Override
    protected void onTLUpdateMessageIdCustom(TLUpdateMessageId update) {

    }

    @Override
    protected void onTLUpdateNewStickerSetCustom(TLUpdateNewStickerSet update) {

    }

    @Override
    protected void onTLUpdateNotifySettingsCustom(TLUpdateNotifySettings update) {

    }

    @Override
    protected void onTLUpdatePrivacyCustom(TLUpdatePrivacy update) {

    }

    @Override
    protected void onTLUpdateReadChannelInboxCustom(TLUpdateReadChannelInbox update) {

    }

    @Override
    protected void onTLUpdateReadMessagesContentsCustom(TLUpdateReadMessagesContents update) {

    }

    @Override
    protected void onTLUpdateReadMessagesInboxCustom(TLUpdateReadMessagesInbox update) {

    }

    @Override
    protected void onTLUpdateReadMessagesOutboxCustom(TLUpdateReadMessagesOutbox update) {

    }

    @Override
    protected void onTLUpdateSavedGifsCustom(TLUpdateSavedGifs update) {

    }

    @Override
    protected void onTLUpdateServiceNotificationCustom(TLUpdateServiceNotification update) {

    }

    @Override
    protected void onTLUpdateStickerSetsCustom(TLUpdateStickerSets update) {

    }

    @Override
    protected void onTLUpdateStickerSetsOrderCustom(TLUpdateStickerSetsOrder update) {

    }

    @Override
    protected void onTLUpdateUserBlockedCustom(TLUpdateUserBlocked update) {

    }

    @Override
    protected void onTLUpdateUserNameCustom(TLUpdateUserName update) {

    }

    @Override
    protected void onTLUpdateUserPhoneCustom(TLUpdateUserPhone update) {

    }

    @Override
    protected void onTLUpdateUserPhotoCustom(TLUpdateUserPhoto update) {

    }

    @Override
    protected void onTLUpdateUserStatusCustom(TLUpdateUserStatus update) {

    }

    @Override
    protected void onTLUpdateUserTypingCustom(TLUpdateUserTyping update) {

    }

    @Override
    protected void onTLUpdateWebPageCustom(TLUpdateWebPage update) {

    }

    @Override
    protected void onTLFakeUpdateCustom(TLFakeUpdate update) {

    }

    @Override
    protected void onTLUpdateShortMessageCustom(TLUpdateShortMessage update) {

    }

    @Override
    protected void onTLUpdateShortChatMessageCustom(TLUpdateShortChatMessage update) {

    }

    @Override
    protected void onTLUpdateShortSentMessageCustom(TLUpdateShortSentMessage update) {

    }

    @Override
    protected void onTLAbsMessageCustom(TLAbsMessage message) {

    }

    @Override
    protected void onUsersCustom(List<TLAbsUser> users) {

    }

    @Override
    protected void onChatsCustom(List<TLAbsChat> chats) {

    }

    @Override
    protected void onTLUpdateEncryptionCustom(TLUpdateEncryption update) {

    }

    @Override
    protected void onTLUpdateEncryptedMessagesReadCustom(TLUpdateEncryptedMessagesRead update) {

    }

    @Override
    protected void onTLUpdateNewEncryptedMessageCustom(TLUpdateNewEncryptedMessage update) {

    }

    @Override
    protected void onTLUpdateEncryptedChatTypingCustom(TLUpdateEncryptedChatTyping update) {

    }

    @Override
    protected void onTLUpdateConfigCustom(TLUpdateConfig update) {

    }

    @Override
    protected void onTLUpdateDraftMessageCustom(TLUpdateDraftMessage update) {

    }

    @Override
    protected void onTLUpdatePtsChangedCustom(TLUpdatePtsChanged update) {

    }

    @Override
    protected void onTLUpdateReadChannelOutboxCustom(TLUpdateReadChannelOutbox update) {

    }

    @Override
    protected void onTLUpdateReadFeaturedStickersCustom(TLUpdateReadFeaturedStickers update) {

    }

    @Override
    protected void onTLUpdateRecentStickersCustom(TLUpdateRecentStickers update) {

    }

    @Override
    protected void onTLUpdateBotCallbackQueryCustom(TLUpdateBotCallbackQuery update) {

    }

    @Override
    protected void onTLUpdateEditMessageCustom(TLUpdateEditMessage update) {

    }

    @Override
    protected void onTLUpdateInlineBotCallbackQueryCustom(TLUpdateInlineBotCallbackQuery update) {

    }

    @Override
    protected void onTLUpdateChannelWebPageCustom(TLUpdateChannelWebPage update) {

    }

    @Override
    protected void onTLUpdatePhoneCallCustom(TLUpdatePhoneCall update) {

    }

    @Override
    protected void onTLUpdateDialogPinnedCustom(TLUpdateDialogPinned update) {

    }

    @Override
    protected void onTLUpdatePinnedDialogsCustom(TLUpdatePinnedDialogs update) {

    }

    @Override
    protected void onTLUpdateBotWebhookJSONCustom(TLUpdateBotWebhookJSON update) {

    }

    @Override
    protected void onTLUpdateBotWebhookJSONQueryCustom(TLUpdateBotWebhookJSONQuery update) {

    }

    @Override
    protected void onTLUpdateBotShippingQueryCustom(TLUpdateBotShippingQuery update) {

    }

    @Override
    protected void onTLUpdateBotPrecheckoutQueryCustom(TLUpdateBotPrecheckoutQuery update) {

    }
}
