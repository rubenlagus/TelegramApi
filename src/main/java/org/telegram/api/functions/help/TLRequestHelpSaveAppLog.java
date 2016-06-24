package org.telegram.api.functions.help;

import org.telegram.api.input.TLInputAppEvent;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLBool;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request help save app log.
 */
public class TLRequestHelpSaveAppLog extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6f02f748;

    private TLVector<TLInputAppEvent> events;

    /**
     * Instantiates a new TL request help save app log.
     */
    public TLRequestHelpSaveAppLog() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets events.
     *
     * @return the events
     */
    public TLVector<TLInputAppEvent> getEvents() {
        return this.events;
    }

    /**
     * Sets events.
     *
     * @param value the value
     */
    public void setEvents(TLVector<TLInputAppEvent> value) {
        this.events = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.events, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.events = StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "help.saveAppLog#6f02f748";
    }
}