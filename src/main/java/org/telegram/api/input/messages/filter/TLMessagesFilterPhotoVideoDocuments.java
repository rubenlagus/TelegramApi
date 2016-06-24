package org.telegram.api.input.messages.filter;

/**
 * The type TL messages filter photo video documents.
 */
public class TLMessagesFilterPhotoVideoDocuments extends TLAbsMessagesFilter {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd95e73bb;

    /**
     * Instantiates a new TL messages filter photo video documents.
     */
    public TLMessagesFilterPhotoVideoDocuments() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputMessagesFilterPhotoVideo#d95e73bb";
    }
}
