package org.telegram.api.input.messages.filter;

/**
 * The type TL messages filter video.
 */
public class TLMessagesFilterVideo extends TLAbsMessagesFilter {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9fc00e65;

    /**
     * Instantiates a new TL messages filter video.
     */
    public TLMessagesFilterVideo() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputMessagesFilterVideo#9fc00e65";
    }
}