package org.telegram.tl;

/**
 * TL Vector of longs. @see org.telegram.tl.TLVector
 *
 * @author Ruben Bermudez
 */
public class TLLongVector extends TLVector<Long> {
    public TLLongVector() {
        setDestClass(Long.class);
    }

    @Override
    public String toString() {
        return "vector<long>#1cb5c415";
    }
}
