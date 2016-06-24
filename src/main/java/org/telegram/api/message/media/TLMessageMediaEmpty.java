package org.telegram.api.message.media;

/**
 * The type TL message media empty.
 */
public class TLMessageMediaEmpty extends TLAbsMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3ded6320;

    /**
     * Instantiates a new TL message media empty.
     */
    public TLMessageMediaEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageMediaEmpty#3ded6320";
    }
}