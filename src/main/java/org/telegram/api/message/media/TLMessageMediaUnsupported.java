package org.telegram.api.message.media;

/**
 * The type TL message media unsupported.
 */
public class TLMessageMediaUnsupported extends TLAbsMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9f84f49e;

    /**
     * Instantiates a new TL message media unsupported.
     */
    public TLMessageMediaUnsupported() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "messageMediaUnsupported#9f84f49e";
    }
}