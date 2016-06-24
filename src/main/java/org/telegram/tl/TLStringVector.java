package org.telegram.tl;

/**
 * TL Vector of strings. @see org.telegram.tl.TLVector
 *
 * @author Ruben Bermudez
 */
public class TLStringVector extends TLVector<String> {
    public TLStringVector() {
        setDestClass(String.class);
    }

    @Override
    public String toString() {
        return "vector<string>#1cb5c415";
    }
}
