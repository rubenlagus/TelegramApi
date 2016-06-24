package org.telegram.mtproto.tl;

import org.telegram.tl.TLContext;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 8:22
 */
public class MTProtoContext extends TLContext {
    @Override
    protected void init() {
        registerClass(MTPing.CLASS_ID, MTPing.class);
        registerClass(MTPingDelayDisconnect.CLASS_ID, MTPingDelayDisconnect.class);
        registerClass(MTPong.CLASS_ID, MTPong.class);
        registerClass(MTMsgsAck.CLASS_ID, MTMsgsAck.class);
        registerClass(MTNewSessionCreated.CLASS_ID, MTNewSessionCreated.class);
        registerClass(MTBadMessageNotification.CLASS_ID, MTBadMessageNotification.class);
        registerClass(MTBadServerSalt.CLASS_ID, MTBadServerSalt.class);
        registerClass(MTDestroySessionOk.CLASS_ID, MTDestroySessionOk.class);
        registerClass(MTDestroySessionNone.CLASS_ID, MTDestroySessionNone.class);
        registerClass(MTMessageCopy.CLASS_ID, MTMessageCopy.class);
        registerClass(MTNewMessageDetailedInfo.CLASS_ID, MTNewMessageDetailedInfo.class);
        registerClass(MTMessageDetailedInfo.CLASS_ID, MTMessageDetailedInfo.class);
        registerClass(MTNeedResendMessage.CLASS_ID, MTNeedResendMessage.class);
        registerClass(MTMessagesContainer.CLASS_ID, MTMessagesContainer.class);
        registerClass(MTRpcError.CLASS_ID, MTRpcError.class);
        registerClass(MTRpcDropAnswer.CLASS_ID, MTRpcDropAnswer.class);
        registerClass(MTRpcResult.CLASS_ID, MTRpcResult.class);
        registerClass(MTRpcAnswerUnknown.CLASS_ID, MTRpcAnswerUnknown.class);
        registerClass(MTRpcAnswerDroppedRunning.CLASS_ID, MTRpcAnswerDroppedRunning.class);
        registerClass(MTRpcAnswerDropped.CLASS_ID, MTRpcAnswerDropped.class);
        registerClass(MTDestroySession.CLASS_ID, MTDestroySession.class);
        registerClass(MTHttpWait.CLASS_ID, MTHttpWait.class);
        registerClass(MTGetFutureSalts.CLASS_ID, MTGetFutureSalts.class);
        registerClass(MTFutureSalt.CLASS_ID, MTFutureSalt.class);
        registerClass(MTFutureSalts.CLASS_ID, MTFutureSalts.class);
        registerClass(MTMessagesAllInfo.CLASS_ID, MTMessagesAllInfo.class);
        registerClass(MTMessagesStateInfo.CLASS_ID, MTMessagesStateInfo.class);
        registerClass(MTMsgsStateReq.CLASS_ID, MTMsgsStateReq.class);
    }
}
