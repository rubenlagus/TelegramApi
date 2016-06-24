package org.telegram.api.functions.account;

import org.telegram.api.wallpaper.TLAbsWallPaper;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request account get wall papers.
 */
public class TLRequestAccountGetWallPapers extends TLMethod<TLVector<TLAbsWallPaper>> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc04cfac2;

    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsWallPaper> deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "account.getWallPapers#c04cfac2";
    }
}