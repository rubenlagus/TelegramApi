package org.telegram.mtproto;

import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 07.11.13
 * Time: 3:56
 */
public interface CallWrapper {
    TLObject wrapObject(TLMethod srcRequest);
}
