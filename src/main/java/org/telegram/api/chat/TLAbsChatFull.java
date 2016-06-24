package org.telegram.api.chat;

import org.telegram.api.chat.invite.TLAbsChatInvite;
import org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings;
import org.telegram.api.photo.TLAbsPhoto;
import org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Abstract chat full
 * @date 23 of September of 2015
 */
public abstract class TLAbsChatFull extends TLObject {

    protected int id; ///< Chat id
    protected TLAbsPhoto photo;
    protected TLAbsPeerNotifySettings notifySettings;
    protected TLAbsChatInvite exportedInvite;

    protected TLAbsChatFull() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TLAbsPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(TLAbsPhoto photo) {
        this.photo = photo;
    }

    public TLAbsPeerNotifySettings getNotifySettings() {
        return notifySettings;
    }

    public void setNotifySettings(TLAbsPeerNotifySettings notifySettings) {
        this.notifySettings = notifySettings;
    }

    public TLAbsChatInvite getExportedInvite() {
        return exportedInvite;
    }

    public void setExportedInvite(TLAbsChatInvite exportedInvite) {
        this.exportedInvite = exportedInvite;
    }
}
