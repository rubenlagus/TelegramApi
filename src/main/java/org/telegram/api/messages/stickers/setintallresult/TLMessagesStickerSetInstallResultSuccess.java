package org.telegram.api.messages.stickers.setintallresult;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 08 of August of 2016
 */
public class TLMessagesStickerSetInstallResultSuccess extends TLAbsMessagesStickerSetInstallResult {
    public static final int CLASS_ID = 0x38641628;

    public TLMessagesStickerSetInstallResultSuccess() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.stickerSetInstallResultSuccess#38641628";
    }
}
