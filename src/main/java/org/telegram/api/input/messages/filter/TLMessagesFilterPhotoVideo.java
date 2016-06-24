package org.telegram.api.input.messages.filter;

/**
 * The type TL messages filter photo video.
 */
public class TLMessagesFilterPhotoVideo extends TLAbsMessagesFilter {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x56e9f0e4;

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputMessagesFilterPhotoVideo#56e9f0e4";
    }
}
