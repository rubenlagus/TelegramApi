package org.telegram.api.geo.point;

/**
 * The type TL geo point empty.
 */
public class TLGeoPointEmpty extends TLAbsGeoPoint {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1117dd5f;

    /**
     * Instantiates a new TL geo point empty.
     */
    public TLGeoPointEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "geoPointEmpty#1117dd5f";
    }
}