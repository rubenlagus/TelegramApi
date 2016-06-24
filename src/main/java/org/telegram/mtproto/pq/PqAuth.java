package org.telegram.mtproto.pq;

import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 8:14
 */
public class PqAuth {
    private byte[] authKey;
    private long serverSalt;
    private Socket socket;

    public PqAuth(byte[] authKey, long serverSalt, Socket socket) {
        this.authKey = authKey;
        this.serverSalt = serverSalt;
        this.socket = socket;
    }

    public byte[] getAuthKey() {
        return this.authKey;
    }

    public long getServerSalt() {
        return this.serverSalt;
    }

    public Socket getSocket() {
        return this.socket;
    }
}
