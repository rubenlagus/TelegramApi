package org.telegram.bot.kernel.engine;

import org.jetbrains.annotations.NotNull;
import org.telegram.api.TLConfig;
import org.telegram.api.TLDcOption;
import org.telegram.api.auth.TLAuthorization;
import org.telegram.api.engine.storage.AbsApiState;
import org.telegram.api.user.TLUser;
import org.telegram.bot.kernel.engine.storage.TLDcInfo;
import org.telegram.bot.kernel.engine.storage.TLKey;
import org.telegram.bot.kernel.engine.storage.TLLastKnownSalt;
import org.telegram.bot.kernel.engine.storage.TLStorage;
import org.telegram.mtproto.state.AbsMTProtoState;
import org.telegram.mtproto.state.ConnectionInfo;
import org.telegram.mtproto.state.KnownSalt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 09.11.13
 * Time: 0:41
 */
public class MemoryApiState extends TLPersistence<TLStorage> implements AbsApiState {

    private static final String TAG = "ApiStorage";

    public MemoryApiState(String filename) {
        super(filename, TLStorage.class);

        if (getObj().getDcInfos().isEmpty()) {
            getObj().getDcInfos().add(new TLDcInfo(0, 1, "149.154.175.50", 443, 0));
            getObj().getDcInfos().add(new TLDcInfo(0, 2, "149.154.167.51", 443, 0));
            getObj().getDcInfos().add(new TLDcInfo(0, 3, "149.154.175.100", 443, 0));
            getObj().getDcInfos().add(new TLDcInfo(0, 4, "149.154.167.91", 443, 0));
            getObj().getDcInfos().add(new TLDcInfo(0, 5, "149.154.171.5", 443, 0));
            getObj().getDcInfos().add(new TLDcInfo(0, 6, "173.240.5.1", 443, 0));
        }
    }

    public int[] getKnownDc() {
        HashSet<Integer> dcs = new HashSet<Integer>();
        for (TLDcInfo dcInfo : getObj().getDcInfos()) {
            dcs.add(dcInfo.getDcId());
        }
        Integer[] dcsArray = dcs.toArray(new Integer[0]);
        int[] res = new int[dcs.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dcsArray[i];
        }
        return res;
    }


    private TLKey findKey(int dc) {
        for (TLKey key : getObj().getKeys()) {
            if (key.getDcId() == dc) {
                return key;
            }
        }
        return null;
    }

    public synchronized boolean isAuthenticated() {
        return isAuthenticated(getPrimaryDc());
    }

    public synchronized void doAuth(@NotNull TLAuthorization authorization) {
        if (authorization.getUser() instanceof TLUser) {
            final TLUser user = (TLUser) authorization.getUser();
            final TLKey key = findKey(getPrimaryDc());
            key.setAuthorised(true);
            getObj().setUid(user.getId());
            getObj().setPhone(user.getPhone());
            write();
        }
    }

    public synchronized void doAuth(int uid, String phone) {
        TLKey key = findKey(getPrimaryDc());
        key.setAuthorised(true);
        getObj().setUid(uid);
        getObj().setPhone(phone);
        write();
    }

    @Override
    public synchronized int getPrimaryDc() {
        return getObj().getPrimaryDc();
    }

    @Override
    public synchronized void setPrimaryDc(int dc) {
        getObj().setPrimaryDc(dc);
        write();
    }

    @Override
    public synchronized boolean isAuthenticated(int dcId) {
        TLKey key = findKey(dcId);
        return (key != null && key.isAuthorised());
    }

    @Override
    public synchronized void setAuthenticated(int dcId, boolean auth) {
        TLKey key = findKey(dcId);
        key.setAuthorised(auth);
        write();
    }

    @Override
    public synchronized void updateSettings(TLConfig config) {
        int version = 0;
        for (TLDcInfo info : getObj().getDcInfos()) {
            version = Math.max(version, info.getVersion());
        }

        boolean hasUpdates = false;
        for (TLDcOption option : config.getDcOptions()) {
            if (option.getFlags() != 0) {
                continue;
            }
            boolean contains = false;
            org.telegram.tl.TLVector<TLDcInfo> var = getObj().getDcInfos();
            for (TLDcInfo info : var.toArray(new TLDcInfo[var.size()])) {
                if (info.getAddress().equals(option.getIpAddress()) && info.getPort() == option.getPort() && info.getDcId() == option.getId() && info.getVersion() == version) {
                    contains = true;
                    break;
                }
            }

            if (!contains) {
                hasUpdates = true;
            }
        }

        if (!hasUpdates) {
            return;
        }

        int nextVersion = version + 1;
        for (TLDcOption option : config.getDcOptions()) {
            if (option.getFlags() != 0) {
                continue;
            }
            org.telegram.tl.TLVector<TLDcInfo> var = getObj().getDcInfos();
            for (TLDcInfo info : var.toArray(new TLDcInfo[var.size()])) {
                if (info.getAddress().equals(option.getIpAddress()) && info.getDcId() == option.getId()) {
                    getObj().getDcInfos().remove(info);
                }
            }
            getObj().getDcInfos().add(new TLDcInfo(option.getFlags(), option.getId(), option.getIpAddress(), option.getPort(), nextVersion));
        }
        write();
    }

    public synchronized void updateDCInfo(int flags, int dcId, String ip, int port) {
        org.telegram.tl.TLVector<TLDcInfo> var = getObj().getDcInfos();
        for (TLDcInfo info : var.toArray(new TLDcInfo[var.size()])) {
            if (info.getAddress().equals(ip) && info.getPort() == port && info.getDcId() == dcId) {
                getObj().getDcInfos().remove(info);
            }
        }

        int version = 0;
        for (TLDcInfo info : getObj().getDcInfos()) {
            version = Math.max(version, info.getVersion());
        }

        getObj().getDcInfos().add(new TLDcInfo(flags, dcId, ip, port, version));
        write();
    }

    @Override
    public synchronized byte[] getAuthKey(int dcId) {
        TLKey key = findKey(dcId);
        return key != null ? key.getAuthKey() : null;
    }

    @Override
    public synchronized void putAuthKey(int dcId, byte[] authKey) {
        TLKey key = findKey(dcId);
        if (key != null) {
            return;
        }
        getObj().getKeys().add(new TLKey(dcId, authKey));
        write();
    }

    @Override
    public ConnectionInfo[] getAvailableConnections(int dcId) {
        ArrayList<TLDcInfo> infos = new ArrayList<TLDcInfo>();
        int maxVersion = 0;
        for (TLDcInfo info : getObj().getDcInfos()) {
            if (info.getDcId() == dcId) {
                infos.add(info);
                maxVersion = Math.max(maxVersion, info.getVersion());
            }
        }

        ArrayList<ConnectionInfo> res = new ArrayList<ConnectionInfo>();

        // Maximum version addresses
        HashMap<String, DcAddress> mainAddresses = new HashMap<>();
        for (TLDcInfo i : infos) {
            if (i.getVersion() != maxVersion) {
                continue;
            }

            if (mainAddresses.containsKey(i.getAddress())) {
                mainAddresses.get(i.getAddress()).ports.put(i.getPort(), 1);
            } else {
                DcAddress address = new DcAddress();
                address.ports.put(i.getPort(), 1);
                address.host = i.getAddress();
                mainAddresses.put(i.getAddress(), address);
            }
        }

        for (DcAddress address : mainAddresses.values()) {
            address.ports.put(443, 2);
            address.ports.put(80, 1);
            address.ports.put(25, 0);
        }

        HashMap<Integer, HashMap<String, DcAddress>> otherAddresses = new HashMap<>();

        for (TLDcInfo i : infos) {
            if (i.getVersion() == maxVersion) {
                continue;
            }

            if (!otherAddresses.containsKey(i.getVersion())) {
                otherAddresses.put(i.getVersion(), new HashMap<>());
            }

            HashMap<String, DcAddress> addressHashMap = otherAddresses.get(i.getVersion());

            if (addressHashMap.containsKey(i.getAddress())) {
                addressHashMap.get(i.getAddress()).ports.put(i.getPort(), 1);
            } else {
                DcAddress address = new DcAddress();
                address.ports.put(i.getPort(), 1);
                address.host = i.getAddress();
                addressHashMap.put(i.getAddress(), address);
            }
        }

        for (Integer version : otherAddresses.keySet()) {
            for (DcAddress address : otherAddresses.get(version).values()) {
                if (mainAddresses.containsKey(address.host)) {
                    continue;
                }
                address.ports.put(443, 2);
                address.ports.put(80, 1);
                address.ports.put(25, 0);
            }
        }


        // Writing main addresses
        int index = 0;

        for (DcAddress address : mainAddresses.values()) {
            for (Integer port : address.ports.keySet()) {
                int priority = maxVersion + address.ports.get(port);
                res.add(new ConnectionInfo(index++, priority, address.host, port));
            }
        }

        // Writing other addresses

        for (Integer version : otherAddresses.keySet()) {
            for (DcAddress address : otherAddresses.get(version).values()) {
                for (Integer port : address.ports.keySet()) {
                    int priority = version + address.ports.get(port);
                    res.add(new ConnectionInfo(index++, priority, address.host, port));
                }
            }
        }


        return res.toArray(new ConnectionInfo[res.size()]);
    }

    private synchronized void writeKnownSalts(int dcId, KnownSalt[] salts) {
        TLKey key = findKey(dcId);
        key.getSalts().clear();
        for (KnownSalt salt : salts) {
            key.getSalts().add(new TLLastKnownSalt(salt.getValidSince(), salt.getValidUntil(), salt.getSalt()));
        }
        write();
    }

    private synchronized KnownSalt[] readKnownSalts(int dcId) {
        TLKey key = findKey(dcId);
        KnownSalt[] salts = new KnownSalt[key.getSalts().size()];
        for (int i = 0; i < salts.length; i++) {
            TLLastKnownSalt sourceSalt = key.getSalts().get(i);
            salts[i] = new KnownSalt(sourceSalt.getValidSince(), sourceSalt.getValidUntil(), sourceSalt.getSalt());
        }
        return salts;
    }

    @Override
    public synchronized AbsMTProtoState getMtProtoState(final int dcId) {
        return new AbsMTProtoState() {

            private KnownSalt[] knownSalts = null;

            @Override
            public byte[] getAuthKey() {
                return MemoryApiState.this.getAuthKey(dcId);
            }

            @Override
            public ConnectionInfo[] getAvailableConnections() {
                return MemoryApiState.this.getAvailableConnections(dcId);
            }

            @Override
            public KnownSalt[] readKnownSalts() {
                if (knownSalts == null) {
                    knownSalts = MemoryApiState.this.readKnownSalts(dcId);
                }
                return knownSalts;
            }

            @Override
            protected void writeKnownSalts(KnownSalt[] salts) {
                MemoryApiState.this.writeKnownSalts(dcId, salts);
                knownSalts = null;
            }
        };
    }

    @Override
    public synchronized void resetAuth() {
        for (TLKey key : getObj().getKeys()) {
            key.setAuthorised(false);
        }
        getObj().setAuthorized(false);
        getObj().setUid(0);
        write();
    }

    @Override
    public synchronized void reset() {
        getObj().getKeys().clear();
        getObj().setAuthorized(false);
        getObj().setUid(0);
        write();
    }

    private class DcAddress {
        public final HashMap<Integer, Integer> ports = new HashMap<>();
        public String host;
    }

    public int getUserId() {
        return getObj().getUid();
    }
}
