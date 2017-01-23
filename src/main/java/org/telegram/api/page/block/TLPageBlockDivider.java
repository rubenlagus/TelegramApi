package org.telegram.api.page.block;

import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockDivider extends TLObject {
    public static final int CLASS_ID = 0xdb20b188;

    public TLPageBlockDivider() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "pageBlockDivider#db20b188";
    }
}
