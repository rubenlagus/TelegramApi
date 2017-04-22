package org.telegram.api.richtext;

import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLTextEmpty extends TLAbsRichText {
    public static final int CLASS_ID = 0xdc3d824f;

    public TLTextEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "textEmpty#dc3d824f";
    }
}
