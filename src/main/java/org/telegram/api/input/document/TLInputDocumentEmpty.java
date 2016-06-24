package org.telegram.api.input.document;

/**
 * The type TL input document empty.
 */
public class TLInputDocumentEmpty extends TLAbsInputDocument {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x72f0eaae;

    /**
     * Instantiates a new TL input document empty.
     */
    public TLInputDocumentEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputDocumentEmpty#72f0eaae";
    }
}