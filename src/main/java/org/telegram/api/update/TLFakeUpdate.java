package org.telegram.api.update;

/**
 * The type TL abs update.
 */
public class TLFakeUpdate extends TLAbsUpdate {
    private int pts;
    private int ptsCount;

    public TLFakeUpdate() {
        super();
    }

    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getPts() {
        return pts;
    }

    public int getPtsCount() {
        return ptsCount;
    };

    @Override
    public int getClassId() {
        return 0;
    }

    @Override
    public String toString() {
        return "fakeUpdate#0";
    }
}