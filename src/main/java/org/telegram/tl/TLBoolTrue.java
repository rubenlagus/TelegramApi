package org.telegram.tl;

/**
 * Packed type of tl-bool true value
 *
 * @author Ruben Bermudez
 */
public class TLBoolTrue extends TLBool {

    public static final int CLASS_ID = 0x997275b5;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "boolTrue#997275b5";
    }
}
