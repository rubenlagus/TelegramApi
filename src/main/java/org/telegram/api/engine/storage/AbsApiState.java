package org.telegram.api.engine.storage;

import org.telegram.api.TLConfig;
import org.telegram.api.auth.TLAuthorization;
import org.telegram.mtproto.state.AbsMTProtoState;
import org.telegram.mtproto.state.ConnectionInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 07.11.13
 * Time: 10:19
 */
public interface AbsApiState {

    /**
     * Gets primary dc.
     *
     * @return the primary dc
     */
    int getPrimaryDc();

    /**
     * Sets primary dc.
     *
     * @param dc the dc
     */
    void setPrimaryDc(int dc);

    /**
     * Is authenticated in primary dc
     *
     * @return the boolean
     */
    boolean isAuthenticated();

    /**
     * Is authenticated.
     *
     * @param dcId the dc id
     * @return the boolean
     */
    boolean isAuthenticated(int dcId);

    /**
     * Sets authenticated.
     *
     * @param dcId the dc id
     * @param auth the auth
     */
    void setAuthenticated(int dcId, boolean auth);

    /**
     * Update settings.
     *
     * @param config the config
     */
    void updateSettings(TLConfig config);


    /**
     * Get auth key.
     *
     * @param dcId the dc id
     * @return the byte [ ]
     */
    byte[] getAuthKey(int dcId);

    /**
     * Put auth key.
     *
     * @param dcId the dc id
     * @param key the key
     */
    void putAuthKey(int dcId, byte[] key);

    /**
     * Get available connections.
     *
     * @param dcId the dc id
     * @return the connection info [ ]
     */
    ConnectionInfo[] getAvailableConnections(int dcId);

    /**
     * Gets mt proto state.
     *
     * @param dcId the dc id
     * @return the mt proto state
     */
    AbsMTProtoState getMtProtoState(int dcId);

    void doAuth(TLAuthorization authorization);

    /**
     * Reset auth.
     */
    void resetAuth();

    /**
     * Reset void.
     */
    void reset();

    int getUserId();
}
