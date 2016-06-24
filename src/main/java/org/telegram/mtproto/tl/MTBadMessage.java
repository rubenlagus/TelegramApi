package org.telegram.mtproto.tl;

import org.telegram.tl.TLObject;

public abstract class MTBadMessage extends TLObject {
    protected long badMsgId;
    protected int badMsqSeqno;
    protected int errorCode;
    protected long newServerSalt;

    public long getBadMsgId() {
        return this.badMsgId;
    }

    public void setBadMsgId(long badMsgId) {
        this.badMsgId = badMsgId;
    }

    public int getBadMsqSeqno() {
        return this.badMsqSeqno;
    }

    public void setBadMsqSeqno(int badMsqSeqno) {
        this.badMsqSeqno = badMsqSeqno;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public long getNewServerSalt() {
        return this.newServerSalt;
    }

    public void setNewServerSalt(long newServerSalt) {
        this.newServerSalt = newServerSalt;
    }
}
