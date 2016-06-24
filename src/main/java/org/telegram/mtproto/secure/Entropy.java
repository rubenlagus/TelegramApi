package org.telegram.mtproto.secure;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.telegram.mtproto.secure.CryptoUtils.xor;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 4:05
 */
public final class Entropy {
    private static SecureRandom random;
    private static Entropy instance;

    private Entropy() {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            random = new SecureRandom();
        }
    }

    public static Entropy getInstance() {
        if (instance == null) {
            instance = new Entropy();
        }
        return instance;
    }

    public byte[] generateSeed(int size) {
        synchronized (random) {
            return random.generateSeed(size);
        }
    }

    public byte[] generateSeed(byte[] sourceSeed) {
        synchronized (random) {
            return xor(random.generateSeed(sourceSeed.length), sourceSeed);
        }
    }

    public long generateRandomId() {
        synchronized (random) {
            return random.nextLong();
        }
    }

    public int randomInt() {
        synchronized (random) {
            return random.nextInt();
        }
    }

    public void feedEntropy(byte[] data) {
        synchronized (random) {
            random.setSeed(data);
        }
    }
}
