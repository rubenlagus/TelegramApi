package org.telegram.mtproto.tl;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.telegram.tl.StreamingUtils.readInt;
import static org.telegram.tl.StreamingUtils.readTLString;
import static org.telegram.tl.StreamingUtils.writeInt;
import static org.telegram.tl.StreamingUtils.writeTLString;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 21:42
 */
public class MTRpcError extends TLObject {

    public static final int CLASS_ID = 0x2144ca19;
    private static final Pattern REGEXP_PATTERN = Pattern.compile("[A-Z_0-9]+");
    private int errorCode;

    private String message;

    public MTRpcError(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public MTRpcError() {

    }

    public String getErrorTag() {
        if (this.message == null) {
            return "DEFAULT";
        }
        Matcher matcher = REGEXP_PATTERN.matcher(this.message);
        if (matcher.find()) {
            return matcher.group();
        }
        return "DEFAULT";
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeInt(this.errorCode, stream);
        writeTLString(this.message, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.errorCode = readInt(stream);
        this.message = readTLString(stream);
    }

    @Override
    public String toString() {
        return "rpc_error#2144ca19";
    }
}
