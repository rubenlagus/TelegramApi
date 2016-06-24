package org.telegram.mtproto.tl;

import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief MTRpcAnswerDroppedRunning
 * @date 21/02/15
 */
public class MTRpcAnswerDroppedRunning extends TLObject {
    public static final int CLASS_ID = 0xcd78e586;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "MTPRpcAnswerDropper#cd78e586";
    }
}
