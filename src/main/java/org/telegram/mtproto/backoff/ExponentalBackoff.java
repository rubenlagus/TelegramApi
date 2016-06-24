package org.telegram.mtproto.backoff;

import org.telegram.mtproto.log.Logger;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ruben Bermudez on 27.12.13.
 */
public class ExponentalBackoff {

    private static final int MIN_DELAY = 100;
    private static final int MAX_DELAY = 15000;
    private static final int MAX_FAILURE_COUNT = 50;
    private final String TAG;
    private Random rnd = new Random();
    private AtomicInteger currentFailureCount = new AtomicInteger();

    public ExponentalBackoff(String logTag) {
        this.TAG = logTag;
    }

    public void onFailure() throws InterruptedException {
        int val = this.currentFailureCount.incrementAndGet();
        if (val > 50) {
            this.currentFailureCount.compareAndSet(val, MAX_FAILURE_COUNT);
            val = MAX_FAILURE_COUNT;
        }

        int delay = MIN_DELAY + ((MAX_DELAY - MIN_DELAY) / MAX_FAILURE_COUNT) * val;

        synchronized (this) {
            Logger.d(this.TAG, "onFailure: wait " + delay + " ms");
            wait(delay);
        }
    }

    public void onFailureNoWait() {
        Logger.d(this.TAG, "onFailureNoWait");
        int val = this.currentFailureCount.incrementAndGet();
        if (val > 50) {
            this.currentFailureCount.compareAndSet(val, MAX_FAILURE_COUNT);
            val = MAX_FAILURE_COUNT;
        }
    }

    public void onSuccess() {
        Logger.d(this.TAG, "onSuccess");
        reset();
    }

    public void reset() {
        Logger.d(this.TAG, "reset");
        this.currentFailureCount.set(0);

        synchronized (this) {
            notifyAll();
        }
    }
}
