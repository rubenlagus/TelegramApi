package org.telegram.api.contacts.toppeers;

import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.toppeer.TLTopPeerCategoryPeers;
import org.telegram.api.user.TLAbsUser;
import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLContactsTopPeers extends TLAbsContactsTopPeers {
    public static final int CLASS_ID = 0x70b772a8;

    private TLVector<TLTopPeerCategoryPeers> categories;
    private TLVector<TLAbsChat> chats;
    private TLVector<TLAbsUser> users;

    public TLContactsTopPeers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLTopPeerCategoryPeers> getCategories() {
        return categories;
    }

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(categories, stream);
        StreamingUtils.writeTLVector(chats, stream);
        StreamingUtils.writeTLVector(users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        categories = StreamingUtils.readTLVector(stream, context, TLTopPeerCategoryPeers.class);
        chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "contacts.topPeers#70b772a8";
    }
}
