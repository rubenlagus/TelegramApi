/**
 * Abstract class to represent contact information
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
package org.telegram.api.contact;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represent information of a blocked contact
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLContactBlocked extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x561bc879;

    private int userId; ///< User id
    private int date; ///< Block date

    /**
     * Instantiates a new TL contact blocked.
     */
    public TLContactBlocked() {
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
     * @param value the value
     */
    public void setUserId(int value) {
        this.userId = value;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param value the value
     */
    public void setDate(int value) {
        this.date = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeInt(this.date, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
    }

    public String toString() {
        return "contactBlocked#561bc879";
    }
}