package org.telegram.api.input.chat.photo;

/**
 * The type TL input chat photo empty.
 */
public class TLInputChatPhotoEmpty extends TLAbsInputChatPhoto {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1ca48f57;

    /**
     * Instantiates a new TL input chat photo empty.
     */
    public TLInputChatPhotoEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputChatPhotoEmpty#1ca48f57";
    }
}