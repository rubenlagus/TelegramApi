package org.telegram.api.messages.dhconfig;

import org.telegram.tl.TLBytes;
import org.telegram.tl.TLObject;

/**
 * The type TL abs dh config.
 */
public abstract class TLAbsDhConfig extends TLObject {
    /**
     * The G.
     */
    public int g;
    /**
     * The P.
     */
    public TLBytes p;
    /**
     * The Version.
     */
    public int version;
    /**
     * The Random.
     */
    protected TLBytes random;

    /**
     * Instantiates a new TL abs dh config.
     */
    protected TLAbsDhConfig() {
        super();
    }

    /**
     * Gets random.
     *
     * @return the random
     */
    public TLBytes getRandom() {
        return this.random;
    }

    /**
     * Sets random.
     *
     * @param value the value
     */
    public void setRandom(TLBytes value) {
        this.random = value;
    }

    /**
     * Gets g.
     *
     * @return the g
     */
    public int getG() {
        return this.g;
    }

    /**
     * Sets g.
     *
     * @param g the g
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * Gets p.
     *
     * @return the p
     */
    public TLBytes getP() {
        return this.p;
    }

    /**
     * Sets p.
     *
     * @param p the p
     */
    public void setP(TLBytes p) {
        this.p = p;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(int version) {
        this.version = version;
    }
}