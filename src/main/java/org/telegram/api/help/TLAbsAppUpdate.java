package org.telegram.api.help;

import org.telegram.tl.TLObject;

/**
 * The type TL abs app update.
 */
public abstract class TLAbsAppUpdate extends TLObject {
    /**
     * The Id.
     */
    protected int id;
    /**
     * The Critical.
     */
    protected boolean critical;
    /**
     * The Url.
     */
    protected String url;
    /**
     * The Text.
     */
    protected String text;

    /**
     * Instantiates a new TL abs app update.
     */
    protected TLAbsAppUpdate() {
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
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Is critical.
     *
     * @return the boolean
     */
    public boolean isCritical() {
        return this.critical;
    }

    /**
     * Sets critical.
     *
     * @param critical the critical
     */
    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }
}