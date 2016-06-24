package org.telegram.api.input.geopoint;

/**
 * The type TL input geo point empty.
 */
public class TLInputGeoPointEmpty extends TLAbsInputGeoPoint {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe4c123d6;

    /**
     * Instantiates a new TL input geo point empty.
     */
    public TLInputGeoPointEmpty() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public String toString() {
        return "inputGeoPointEmpty#e4c123d6";
    }
}