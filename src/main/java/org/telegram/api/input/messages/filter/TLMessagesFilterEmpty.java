package org.telegram.api.input.messages.filter;

/**
 * The type TL messages filter empty.
 */
public class TLMessagesFilterEmpty extends TLAbsMessagesFilter {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x57e2f66c;

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputMessagesFilterEmpty#57e2f66c";
    }
}