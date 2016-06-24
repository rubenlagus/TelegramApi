package org.telegram.api.wallpaper;

import org.telegram.tl.TLObject;

/**
 * The type TL abs wall paper.
 */
public abstract class TLAbsWallPaper extends TLObject {
    /**
     * The Id.
     */
    protected int id;
    /**
     * The Title.
     */
    protected String title;
    /**
     * The Color.
     */
    protected int color;

    /**
     * Instantiates a new TL abs wall paper.
     */
    protected TLAbsWallPaper() {
        super();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param value the value
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public int getColor() {
        return this.color;
    }

    /**
     * Sets color.
     *
     * @param value the value
     */
    public void setColor(int value) {
        this.color = value;
    }
}