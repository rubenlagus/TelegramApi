package org.telegram.api.input.messages.filter;

/**
 * The type TL messages filter photos.
 */
public class TLMessagesFilterPhotos extends TLAbsMessagesFilter {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9609a51c;

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputMessagesFilterPhotos#9609a51c";
    }
}