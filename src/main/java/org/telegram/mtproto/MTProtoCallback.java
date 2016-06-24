package org.telegram.mtproto;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 04.11.13
 * Time: 22:11
 */
public interface MTProtoCallback {
    void onSessionCreated(MTProto proto);

    void onAuthInvalidated(MTProto proto);

    void onApiMessage(byte[] message, MTProto proto);

    void onRpcResult(int callId, byte[] response, MTProto proto);

    void onRpcError(int callId, int errorCode, String message, MTProto proto);

    void onConfirmed(int callId);
}
