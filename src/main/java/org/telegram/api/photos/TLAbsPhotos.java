package org.telegram.api.photos;

import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;

/**
 * The type TL abs photos.
 */
public abstract class TLAbsPhotos extends TLObject {
    /**
     * The Photos.
     */
    protected TLVector<TLAbsPhoto> photos;
    /**
     * The Users.
     */
    protected TLVector<TLAbsUser> users;

    /**
     * Instantiates a new TL abs photos.
     */
    protected TLAbsPhotos() {
        super();
    }

    /**
     * Gets photos.
     *
     * @return the photos
     */
    public TLVector<TLAbsPhoto> getPhotos() {
        return this.photos;
    }

    /**
     * Sets photos.
     *
     * @param value the value
     */
    public void setPhotos(TLVector<TLAbsPhoto> value) {
        this.photos = value;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param value the value
     */
    public void setUsers(TLVector<TLAbsUser> value) {
        this.users = value;
    }
}