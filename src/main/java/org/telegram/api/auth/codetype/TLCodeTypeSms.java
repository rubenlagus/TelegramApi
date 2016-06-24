package org.telegram.api.auth.codetype;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 16 of March of 2016
 */
public class TLCodeTypeSms extends TLAbsCodeType {
    public static final int CLASS_ID = 0x72a3158c;

    public TLCodeTypeSms() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "auth.codeTypeSms#72a3158c";
    }
}
