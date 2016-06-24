package org.telegram.api.input.file;

import org.telegram.tl.TLObject;

/**
 * The type TL abs input file.
 */
public abstract class TLAbsInputFile extends TLObject {
    /**
     * The Id.
     */
    protected long id;
    /**
     * The Parts.
     */
    protected int parts;
    /**
     * The Name.
     */
    protected String name;

    /**
     * Instantiates a new TL abs input file.
     */
    protected TLAbsInputFile() {
        super();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets parts.
     *
     * @return the parts
     */
    public int getParts() {
        return this.parts;
    }

    /**
     * Sets parts.
     *
     * @param value the value
     */
    public void setParts(int value) {
        this.parts = value;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param value the value
     */
    public void setName(String value) {
        this.name = value;
    }
}