package org.telegram.api.phone.call.discardreason;

import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPhoneCallDiscardReasonMissed extends TLAbsPhoneCallDiscardReason {
    public static final int CLASS_ID = 0x85e42301;

    public TLPhoneCallDiscardReasonMissed() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "phoneCallDiscardReasonMissed#85e42301";
    }
}
