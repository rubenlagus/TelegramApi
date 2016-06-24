package org.telegram.mtproto.transport;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief States of the connection
 * @date 06 of February of 2016
 */
enum ConnectionState {
    TcpConnectionStageSuspended,
    TcpConnectionStageIdle,
    TcpConnectionStageConnecting,
    TcpConnectionStageReconnecting,
    TcpConnectionStageConnected
}
