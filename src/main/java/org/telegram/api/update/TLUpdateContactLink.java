package org.telegram.api.update;

import org.telegram.api.contact.link.TLAbsContactLink;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update contact link.
 */
public class TLUpdateContactLink extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9d2e67c5;

    private int userId;
    private TLAbsContactLink foreignLink;
    private TLAbsContactLink myLink;

    /**
     * Instantiates a new TL update contact link.
     */
    public TLUpdateContactLink() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets foreign link.
     *
     * @return the foreign link
     */
    public TLAbsContactLink getForeignLink() {
        return this.foreignLink;
    }

    /**
     * Sets foreign link.
     *
     * @param foreignLink the foreign link
     */
    public void setForeignLink(TLAbsContactLink foreignLink) {
        this.foreignLink = foreignLink;
    }

    /**
     * Gets my link.
     *
     * @return the my link
     */
    public TLAbsContactLink getMyLink() {
        return this.myLink;
    }

    /**
     * Sets my link.
     *
     * @param myLink the my link
     */
    public void setMyLink(TLAbsContactLink myLink) {
        this.myLink = myLink;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLObject(this.myLink, stream);
        StreamingUtils.writeTLObject(this.foreignLink, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.myLink = ((TLAbsContactLink) StreamingUtils.readTLObject(stream, context));
        this.foreignLink = ((TLAbsContactLink) StreamingUtils.readTLObject(stream, context));
    }

    public String toString() {
        return "updateContactLink#9d2e67c5";
    }
}