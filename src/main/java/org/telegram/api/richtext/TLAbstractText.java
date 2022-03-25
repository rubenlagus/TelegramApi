package org.telegram.api.richtext;

import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public abstract class TLAbstractText extends TLAbsRichText {
    public TLAbsRichText text;

    public TLAbstractText() {
        super();
    }

    public TLAbsRichText getText() {
        return text;
    }
}
