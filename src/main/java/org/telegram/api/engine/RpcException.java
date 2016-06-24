package org.telegram.api.engine;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 05.11.13
 * Time: 13:59
 */
public class RpcException extends IOException {
    private static final Pattern REGEXP_PATTERN = Pattern.compile("[A-Z_0-9]+");
    private int errorCode;
    private String errorTag;

    /**
     * Instantiates a new Rpc exception.
     *
     * @param errorCode the error code
     * @param message the message
     */
    public RpcException(int errorCode, String message) {
        super(getErrorMessage(message));
        this.errorCode = errorCode;
        this.errorTag = getErrorTag(message);
    }

    private static String getErrorTag(String srcMessage) {
        if (srcMessage == null) {
            return "UNKNOWN";
        }
        Matcher matcher = REGEXP_PATTERN.matcher(srcMessage);
        if (matcher.find()) {
            return matcher.group();
        }
        return "UNKNOWN";
    }

    private static String getErrorMessage(String srcMessage) {
        if (srcMessage == null) {
            return "Unknown error";
        }
        int index = srcMessage.indexOf(":");
        if (index > 0) {
            return srcMessage.substring(index);
        } else {
            return srcMessage;
        }
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return this.errorCode;
    }

    /**
     * Gets error tag.
     *
     * @return the error tag
     */
    public String getErrorTag() {
        return this.errorTag;
    }
}
