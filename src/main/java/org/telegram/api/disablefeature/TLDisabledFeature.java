/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.disablefeature;

import org.telegram.tl.StreamingUtils;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Disabled feature by Telegram server.
 * @author Ruben Bermudez
 * @version 2.0
 * @date 11 of April of 2015
 */
public class TLDisabledFeature extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xae636f24;

    /**
     * @brief Disable feature name
     *
     * Value                    | Explanation
     * ------------------------ | -------------
     * bigchat_upload_photo     | Can upload photos to a chat with many members
     * bigchat_upload_document  | Can upload documents to a chat with many members
     * bigchat_upload_audio     | Can upload audios to a chat with many members
     * bigchat_message          | Can send messages to a chat with many members
     * chat_upload_photo        | Can upload photos to a chat
     * chat_upload_document     | Can upload documents to a chat
     * chat_upload_audio        | Can upload audios to a chat
     * chat_message             | Can send messages to a chat
     * pm_upload_photo          | Can upload photos to a conversation with an user
     * pm_upload_document       | Can upload documents to a conversation with an user
     * pm_upload_audio          | Can upload audios to a conversation with an user
     * pm_message               | Can send messages to a conversation with an user
     * chat_create              | Can create chats
     * broadcast_create         | Can create broadcasts
     *
     * @note The appearence of any of those values means that the feature is disabled and can't be done
     * @note Big size groups are, by default, those with more than 10 users or value from @ref TLConfig
     */
    private String feature;
    private String description; ///< Feature description

    /**
     * Instantiates a new TL disabled feature.
     */
    public TLDisabledFeature() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets feature.
     *
     * @return the feature
     */
    public String getFeature() {
        return this.feature;
    }

    /**
     * Sets feature.
     *
     * @param feature the feature
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.feature, stream);
        StreamingUtils.writeTLString(this.description, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.feature = StreamingUtils.readTLString(stream);
        this.description = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "disableFeature#ae636f24";
    }
}
