package org.telegram.api.bot;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 09 of April of 2016
 */
public class TLInlineBotSwitchPm extends TLObject {
    public static final int CLASS_ID = 0x3c20629f;

    private String text;
    private String startParam;

    public TLInlineBotSwitchPm() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getText() {
        return text;
    }

    public String getStartParam() {
        return startParam;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(text, stream);
        StreamingUtils.writeTLString(startParam, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        text = StreamingUtils.readTLString(stream);
        startParam = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inlineBotSwitchPM#3c20629f";
    }
}
