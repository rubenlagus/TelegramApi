package org.telegram.mtproto.transport;

/**
 * Created by Ruben Bermudez on 26.11.13.
 */
public class ConnectionType {
    public static final int TYPE_TCP = 0;

    private int id;
    private String host;
    private int port;
    private int connectionType;

    public ConnectionType(int id, String host, int port, int connectionType) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.connectionType = connectionType;
    }

    public int getId() {
        return this.id;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public int getConnectionType() {
        return this.connectionType;
    }
}
