package org.telegram.api.phone;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPhoneCallProtocol extends TLObject {
    public static final int CLASS_ID = 0xa2bb35cb;

    private static final int FLAG_DP_P2P           = 0x00000001; // 0
    private static final int FLAG_UDP_REFLECTOR    = 0x00000002; // 1

    private int flags;
    private int minLayer;
    private int maxLayer;

    public TLPhoneCallProtocol() {
        super();
    }

    public int getFlags() {
        return flags;
    }

    public int getMinLayer() {
        return minLayer;
    }

    public int getMaxLayer() {
        return maxLayer;
    }

    public boolean isDpP2p() {
        return (flags & FLAG_DP_P2P) != 0;
    }

    public boolean isUdpReflector() {
        return (flags & FLAG_UDP_REFLECTOR) != 0;
    }

    public void setMinLayer(int minLayer) {
        this.minLayer = minLayer;
    }

    public void setMaxLayer(int maxLayer) {
        this.maxLayer = maxLayer;
    }

    public void setDpP2p(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_DP_P2P;
        } else {
            this.flags &= ~FLAG_DP_P2P;
        }
    }

    public void setUdpReflector(boolean enabled) {
        if (enabled) {
            this.flags |= FLAG_UDP_REFLECTOR;
        } else {
            this.flags &= ~FLAG_UDP_REFLECTOR;
        }
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(flags, stream);
        StreamingUtils.writeInt(minLayer, stream);
        StreamingUtils.writeInt(maxLayer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        minLayer = StreamingUtils.readInt(stream);
        maxLayer = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "phoneCallProtocol#a2bb35cb";
    }
}
