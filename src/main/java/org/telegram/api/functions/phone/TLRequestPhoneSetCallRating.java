package org.telegram.api.functions.phone;

import org.telegram.api.input.phonecall.TLInputPhoneCall;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPhoneSetCallRating extends TLMethod<TLAbsUpdates> {
    public static final int CLASS_ID = 0x1c536a34;


    private TLInputPhoneCall peer;
    private int rating;
    private String comment;

    public TLRequestPhoneSetCallRating() {
        super();
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }


    public TLInputPhoneCall getPeer() {
        return peer;
    }

    public void setPeer(TLInputPhoneCall peer) {
        this.peer = peer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeInt(rating, stream);
        StreamingUtils.writeTLString(comment, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        peer = StreamingUtils.readTLObject(stream, context, TLInputPhoneCall.class);
        rating = StreamingUtils.readInt(stream);
        comment = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "phone.setCallRating#1c536a34";
    }
}
