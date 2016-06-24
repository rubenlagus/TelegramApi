package org.telegram.api.input.messages.filter;

/**
 * The type TL messages filter audio.
 */
public class TLMessagesFilterUrl extends TLAbsMessagesFilter {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7ef0dd87;

    /**
     * Instantiates a new TL messages filter audio.
     */
    public TLMessagesFilterUrl() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputMessagesFilterUrl#7ef0dd87";
    }
}