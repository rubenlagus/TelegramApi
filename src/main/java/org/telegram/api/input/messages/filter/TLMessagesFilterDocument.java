package org.telegram.api.input.messages.filter;

/**
 * The type TL messages filter document.
 */
public class TLMessagesFilterDocument extends TLAbsMessagesFilter {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9eddf188;

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputMessagesFilterDocument#9eddf188";
    }
}