package org.telegram.bot.handlers;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.message.TLAbsMessage;
import org.telegram.api.update.TLFakeUpdate;
import org.telegram.api.update.TLUpdateBotCallbackQuery;
import org.telegram.api.update.TLUpdateBotInlineQuery;
import org.telegram.api.update.TLUpdateBotInlineSend;
import org.telegram.api.update.TLUpdateChannel;
import org.telegram.api.update.TLUpdateChannelGroup;
import org.telegram.api.update.TLUpdateChannelMessageViews;
import org.telegram.api.update.TLUpdateChannelNewMessage;
import org.telegram.api.update.TLUpdateChannelPinnedMessage;
import org.telegram.api.update.TLUpdateChatAdmin;
import org.telegram.api.update.TLUpdateChatParticipantAdd;
import org.telegram.api.update.TLUpdateChatParticipantAdmin;
import org.telegram.api.update.TLUpdateChatParticipantDelete;
import org.telegram.api.update.TLUpdateChatParticipants;
import org.telegram.api.update.TLUpdateChatUserTyping;
import org.telegram.api.update.TLUpdateContactLink;
import org.telegram.api.update.TLUpdateContactRegistered;
import org.telegram.api.update.TLUpdateDcOptions;
import org.telegram.api.update.TLUpdateDeleteChannelMessages;
import org.telegram.api.update.TLUpdateDeleteMessages;
import org.telegram.api.update.TLUpdateEditChannelMessage;
import org.telegram.api.update.TLUpdateEditMessage;
import org.telegram.api.update.TLUpdateInlineBotCallbackQuery;
import org.telegram.api.update.TLUpdateMessageId;
import org.telegram.api.update.TLUpdateNewAuthorization;
import org.telegram.api.update.TLUpdateNewMessage;
import org.telegram.api.update.TLUpdateNewStickerSet;
import org.telegram.api.update.TLUpdateNotifySettings;
import org.telegram.api.update.TLUpdatePrivacy;
import org.telegram.api.update.TLUpdateReadChannelInbox;
import org.telegram.api.update.TLUpdateReadMessagesContents;
import org.telegram.api.update.TLUpdateReadMessagesInbox;
import org.telegram.api.update.TLUpdateReadMessagesOutbox;
import org.telegram.api.update.TLUpdateSavedGifs;
import org.telegram.api.update.TLUpdateServiceNotification;
import org.telegram.api.update.TLUpdateStickerSets;
import org.telegram.api.update.TLUpdateStickerSetsOrder;
import org.telegram.api.update.TLUpdateUserBlocked;
import org.telegram.api.update.TLUpdateUserName;
import org.telegram.api.update.TLUpdateUserPhone;
import org.telegram.api.update.TLUpdateUserPhoto;
import org.telegram.api.update.TLUpdateUserStatus;
import org.telegram.api.update.TLUpdateUserTyping;
import org.telegram.api.update.TLUpdateWebPage;
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
    protected void onTLUpdateChannelGroupCustom(TLUpdateChannelGroup update) {

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
    protected void onTLUpdateNewAuthorizationCustom(TLUpdateNewAuthorization update) {

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
    protected void onTLUpdateBotCallbackQueryCustom(TLUpdateBotCallbackQuery update) {

    }

    @Override
    protected void onTLUpdateEditMessageCustom(TLUpdateEditMessage update) {

    }

    @Override
    protected void onTLUpdateInlineBotCallbackQueryCustom(TLUpdateInlineBotCallbackQuery update) {

    }
}
