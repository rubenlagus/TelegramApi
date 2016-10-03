package org.telegram.api.decryptedmessage;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message action notify layer.
 */
public class TLDecryptedMessageActionNotifyLayer extends TLDecryptedMessageAction {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf3048883;

    private int layer = 23;

    /**
     * Instantiates a new TL decrypted message action notify layer.
     */
    public TLDecryptedMessageActionNotifyLayer() {
        super();
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.layer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.layer = StreamingUtils.readInt(stream);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets layer.
     *
     * @return the layer
     */
    public int getLayer() {
        return this.layer;
    }

    /**
     * Sets layer.
     *
     * @param layer the layer
     */
    public void setLayer(int layer) {
        this.layer = layer;
    }

    @Override
    public String toString() {
        return "decryptedMessageActionNotifyLayer#f3048883";
    }
}