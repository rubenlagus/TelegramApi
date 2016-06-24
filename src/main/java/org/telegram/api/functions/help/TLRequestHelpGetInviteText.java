package org.telegram.api.functions.help;

import org.telegram.api.help.TLInviteText;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request help get invite text.
 */
public class TLRequestHelpGetInviteText extends TLMethod<TLInviteText> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa4a95186;

    private String langCode;

    /**
     * Instantiates a new TL request help get invite text.
     */
    public TLRequestHelpGetInviteText() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLInviteText deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLInviteText))
            return (TLInviteText) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLInviteText, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets lang code.
     *
     * @return the lang code
     */
    public String getLangCode() {
        return this.langCode;
    }

    /**
     * Sets lang code.
     *
     * @param value the value
     */
    public void setLangCode(String value) {
        this.langCode = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLString(this.langCode, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.langCode = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "help.getInviteText#a4a95186";
    }
}