package org.telegram.api.page.block;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockUnsupported extends TLAbsPageBlock {
    public static final int CLASS_ID = 0x13567e8a;

    public TLPageBlockUnsupported() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "pageBlockUnsupported#13567e8a";
    }
}
