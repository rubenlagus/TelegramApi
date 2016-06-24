package org.telegram.mtproto.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.WeakHashMap;

/**
 * Created by Ruben Bermudez on 04.02.14.
 */
public class BytesCache {

    private static BytesCache instance = new BytesCache("GlobalByteCache");
    private final int[] SIZES = new int[]{64, 128, 3072, 20 * 1024, 40 * 1024};
    private final int MAX_SIZE = 40 * 1024;
    private final boolean TRACK_ALLOCATIONS = false;
    private final String TAG;
    private HashMap<Integer, HashSet<byte[]>> fastBuffers = new HashMap<Integer, HashSet<byte[]>>();
    private HashSet<byte[]> mainFilter = new HashSet<byte[]>();
    private HashSet<byte[]> byteBuffer = new HashSet<byte[]>();
    private WeakHashMap<byte[], StackTraceElement[]> references = new WeakHashMap<byte[], StackTraceElement[]>();

    public BytesCache(String logTag) {
        this.TAG = logTag;
        for (int i = 0; i < this.SIZES.length; i++) {
            this.fastBuffers.put(this.SIZES[i], new HashSet<byte[]>());
        }
    }

    public static BytesCache getInstance() {
        return instance;
    }

    public synchronized void put(byte[] data) {
        this.references.remove(data);

        if (this.mainFilter.add(data)) {
            for (Integer i : this.SIZES) {
                if (data.length == i) {
                    this.fastBuffers.get(i).add(data);
                    return;
                }
            }
            if (data.length <= this.MAX_SIZE) {
                return;
            }
            this.byteBuffer.add(data);
        }
    }

    public synchronized byte[] allocate(int minSize) {
        if (minSize <= this.MAX_SIZE) {
            for (int i = 0; i < this.SIZES.length; i++) {
                if (minSize < this.SIZES[i]) {
                    if (!this.fastBuffers.get(this.SIZES[i]).isEmpty()) {
                        Iterator<byte[]> interator = this.fastBuffers.get(this.SIZES[i]).iterator();
                        byte[] res = interator.next();
                        interator.remove();

                        this.mainFilter.remove(res);

                        if (this.TRACK_ALLOCATIONS) {
                            this.references.put(res, Thread.currentThread().getStackTrace());
                        }

                        return res;
                    }

                    return new byte[this.SIZES[i]];
                }
            }
        } else {
            byte[] res = null;
            for (byte[] cached : this.byteBuffer) {
                if (cached.length < minSize) {
                    continue;
                }
                if (res == null) {
                    res = cached;
                } else if (res.length > cached.length) {
                    res = cached;
                }
            }

            if (res != null) {
                this.byteBuffer.remove(res);
                this.mainFilter.remove(res);
                if (this.TRACK_ALLOCATIONS) {
                    this.references.put(res, Thread.currentThread().getStackTrace());
                }
                return res;
            }
        }

        return new byte[minSize];
    }
}
