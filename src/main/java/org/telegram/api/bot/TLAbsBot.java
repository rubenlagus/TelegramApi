package org.telegram.api.bot;

import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

/**
 * The type TL abs photos.
 */
public abstract class TLAbsBot extends TLObject {
    private int userId = 0;
    public String description = "";

    public int getUserId () {
        return this.userId;
    }

    public String getDescription () {
        return this.description;
    }

    public void setUserId (int userId) {
        this.userId = userId;
    }

    public void setDescription (String description) {
        this.description = description;
    }
}