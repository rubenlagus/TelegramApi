package org.telegram.api.functions.updates;

import org.telegram.api.updates.TLUpdatesState;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request updates get state.
 */
public class TLRequestUpdatesGetState extends TLMethod<TLUpdatesState> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xedd4882a;

    /**
     * Instantiates a new TL request updates get state.
     */
    public TLRequestUpdatesGetState() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLUpdatesState deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLUpdatesState))
            return (TLUpdatesState) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.updates.TLState, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "updates.getState#edd4882a";
    }
}