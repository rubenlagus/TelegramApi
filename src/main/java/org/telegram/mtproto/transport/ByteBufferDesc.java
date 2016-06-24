/*
 * This is the source code of Telegram for Android v. 1.3.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2014.
 */
package org.telegram.mtproto.transport;

import org.telegram.mtproto.log.Logger;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferDesc {
    public ByteBuffer buffer;
    private boolean justCalc;
    private int len;

    public ByteBufferDesc(int size) {
        this.buffer = ByteBuffer.allocateDirect(size);
        this.buffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public ByteBufferDesc(boolean calculate) {
        this.justCalc = calculate;
    }

    public ByteBufferDesc(byte[] bytes) {
        this.buffer = ByteBuffer.wrap(bytes);
        this.buffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public int position() {
        return this.buffer.position();
    }

    public void position(int position) {
        this.buffer.position(position);
    }

    public int capacity() {
        return this.buffer.capacity();
    }

    public int limit() {
        return this.buffer.limit();
    }

    public void limit(int limit) {
        this.buffer.limit(limit);
    }

    public void put(ByteBuffer buff) {
        this.buffer.put(buff);
    }

    public void rewind() {
        this.buffer.rewind();
    }

    public void compact() {
        this.buffer.compact();
    }

    public boolean hasRemaining() {
        return this.buffer.hasRemaining();
    }

    public void writeInt32(int x) {
        try {
            if (this.justCalc) {
                this.len += 4;
            } else {
                this.buffer.putInt(x);
            }
        } catch(Exception e) {
            Logger.w("tmessages", "write int32 error");
        }
    }

    public void writeInt64(long x) {
        try {
            if (this.justCalc) {
                this.len += 8;
            } else {
                this.buffer.putLong(x);
            }
        } catch(Exception e) {
            Logger.w("tmessages", "write int64 error");
        }
    }

    public void writeBool(boolean value) {
        if (this.justCalc) {
            this.len += 4;
        } else {
            if (value) {
                writeInt32(0x997275b5);
            } else {
                writeInt32(0xbc799737);
            }
        }
    }

    public void writeRaw(byte[] b) {
        try {
            if (this.justCalc) {
                this.len += b.length;
            } else {
                this.buffer.put(b);
            }
        } catch (Exception e) {
            Logger.w("tmessages", "write raw error");
        }
    }

    public void writeRaw(byte[] b, int offset, int count) {
        try {
            if (this.justCalc) {
                this.len += count;
            } else {
                this.buffer.put(b, offset, count);
            }
        } catch (Exception e) {
            Logger.w("tmessages", "write raw error");
        }
    }

    public void writeByte(int i) {
        writeByte((byte)i);
    }

    public void writeByte(byte b) {
        try {
            if (this.justCalc) {
                this.len += 1;
            } else {
                this.buffer.put(b);
            }
        } catch (Exception e) {
            Logger.w("tmessages", "write byte error");
        }
    }

    public void writeString(String s) {
        try {
            writeByteArray(s.getBytes("UTF-8"));
        } catch(Exception e) {
            Logger.w("tmessages", "write string error");
        }
    }

    public void writeByteArray(byte[] b, int offset, int count) {
        try {
            if(count <= 253) {
                if (this.justCalc) {
                    this.len += 1;
                } else {
                    this.buffer.put((byte) count);
                }
            } else {
                if (this.justCalc) {
                    this.len += 4;
                } else {
                    this.buffer.put((byte) 254);
                    this.buffer.put((byte) count);
                    this.buffer.put((byte) (count >> 8));
                    this.buffer.put((byte) (count >> 16));
                }
            }
            if (this.justCalc) {
                this.len += count;
            } else {
                this.buffer.put(b, offset, count);
            }
            int i = (count <= 253) ? 1 : 4;
            while (((count + i) % 4) != 0) {
                if (this.justCalc) {
                    this.len += 1;
                } else {
                    this.buffer.put((byte) 0);
                }
                i++;
            }
        } catch (Exception e) {
            Logger.w("tmessages", "write byte array error");
        }
    }

    public void writeByteArray(byte[] b) {
        try {
            if (b.length <= 253) {
                if (this.justCalc) {
                    this.len += 1;
                } else {
                    this.buffer.put((byte) b.length);
                }
            } else {
                if (this.justCalc) {
                    this.len += 4;
                } else {
                    this.buffer.put((byte) 254);
                    this.buffer.put((byte) b.length);
                    this.buffer.put((byte) (b.length >> 8));
                    this.buffer.put((byte) (b.length >> 16));
                }
            }
            if (!this.justCalc) {
                this.buffer.put(b);
            } else {
                this.len += b.length;
            }
            int i = (b.length <= 253) ? 1 : 4;
            while(((b.length + i) % 4) != 0) {
                if (this.justCalc) {
                    this.len += 1;
                } else {
                    this.buffer.put((byte) 0);
                }
                i++;
            }
        } catch (Exception e) {
            Logger.w("tmessages", "write byte array error");
        }
    }

    public void writeDouble(double d) {
        try {
            writeInt64(Double.doubleToRawLongBits(d));
        } catch(Exception e) {
            Logger.w("tmessages", "write double error");
        }
    }

    public void writeByteBuffer(ByteBufferDesc b) {
        try {
            final int l = b.limit();
            if (l <= 253) {
                if (this.justCalc) {
                    this.len += 1;
                } else {
                    this.buffer.put((byte) l);
                }
            } else {
                if (this.justCalc) {
                    this.len += 4;
                } else {
                    this.buffer.put((byte) 254);
                    this.buffer.put((byte) l);
                    this.buffer.put((byte) (l >> 8));
                    this.buffer.put((byte) (l >> 16));
                }
            }
            if (this.justCalc) {
                this.len += l;
            } else {
                b.rewind();
                this.buffer.put(b.buffer);
            }
            int i = (l <= 253) ? 1 : 4;
            while(((l + i) % 4) != 0) {
                if (this.justCalc) {
                    this.len += 1;
                } else {
                    this.buffer.put((byte) 0);
                }
                i++;
            }
        } catch (Exception e) {
            Logger.e("tmessages", e);
        }
    }

    public void writeRaw(ByteBufferDesc b) {
        if (this.justCalc) {
            this.len += b.limit();
        } else {
            b.rewind();
            this.buffer.put(b.buffer);
        }
    }

    public int getIntFromByte(byte b) {
        return (b >= 0) ? b : (((int) b) + 256);
    }

    public int length() {
        if (!this.justCalc) {
            return this.buffer.position();
        }
        return this.len;
    }

    public void skip(int count) {
        if (count == 0) {
            return;
        }
        if (this.justCalc) {
            this.len += count;
        } else {
            this.buffer.position(this.buffer.position() + count);
        }
    }

    public int getPosition() {
        return this.buffer.position();
    }

    public int readInt32(boolean exception) {
        try {
            return this.buffer.getInt();
        } catch (Exception e) {
            if (exception) {
                throw new RuntimeException("read int32 error", e);
            } else {
                Logger.w("tmessages", "read int32 error");
            }
        }
        return 0;
    }

    public boolean readBool(boolean exception) {
        int consructor = readInt32(exception);
        if (consructor == 0x997275b5) {
            return true;
        }
        if (consructor == 0xbc799737) {
            return false;
        }
        if (exception) {
            throw new RuntimeException("Not bool value!");
        }
        Logger.w("tmessages", "Not bool value!");
        return false;
    }

    public long readInt64(boolean exception) {
        try {
            return this.buffer.getLong();
        } catch (Exception e) {
            if (exception) {
                throw new RuntimeException("read int64 error", e);
            } else {
                Logger.w("tmessages", "read int64 error");
            }
        }
        return 0;
    }

    public void readRaw(byte[] b, boolean exception) {
        try {
            this.buffer.get(b);
        } catch (Exception e) {
            if (exception) {
                throw new RuntimeException("read raw error", e);
            } else {
                Logger.w("tmessages", "read raw error");
            }
        }
    }

    public byte[] readData(int count, boolean exception) {
        final byte[] arr = new byte[count];
        readRaw(arr, exception);
        return arr;
    }

    public String readString(boolean exception) {
        try {
            int sl = 1;
            int l = getIntFromByte(this.buffer.get());
            if(l >= 254) {
                l = getIntFromByte(this.buffer.get()) | (getIntFromByte(this.buffer.get()) << 8) | (getIntFromByte(this.buffer.get()) << 16);
                sl = 4;
            }
            final byte[] b = new byte[l];
            this.buffer.get(b);
            int i = sl;
            while(((l + i) % 4) != 0) {
                this.buffer.get();
                i++;
            }
            return new String(b, "UTF-8");
        } catch (Exception e) {
            if (exception) {
                throw new RuntimeException("read string error", e);
            } else {
                Logger.w("tmessages", "read string error");
            }
        }
        return null;
    }

    public byte[] readByteArray(boolean exception) {
        try {
            int sl = 1;
            int l = getIntFromByte(this.buffer.get());
            if (l >= 254) {
                l = getIntFromByte(this.buffer.get()) | (getIntFromByte(this.buffer.get()) << 8) | (getIntFromByte(this.buffer.get()) << 16);
                sl = 4;
            }
            final byte[] b = new byte[l];
            this.buffer.get(b);
            int i = sl;
            while(((l + i) % 4) != 0) {
                this.buffer.get();
                i++;
            }
            return b;
        } catch (Exception e) {
            if (exception) {
                throw new RuntimeException("read byte array error", e);
            } else {
                Logger.w("tmessages", "read byte array error");
            }
        }
        return null;
    }

    public ByteBufferDesc readByteBuffer(boolean exception) {
        try {
            int sl = 1;
            int l = getIntFromByte(this.buffer.get());
            if (l >= 254) {
                l = getIntFromByte(this.buffer.get()) | (getIntFromByte(this.buffer.get()) << 8) | (getIntFromByte(this.buffer.get()) << 16);
                sl = 4;
            }
            final ByteBufferDesc b = BuffersStorage.getInstance().getFreeBuffer(l);
            if (b != null) {
                final int old = this.buffer.limit();
                this.buffer.limit(this.buffer.position() + l);
                b.buffer.put(this.buffer);
                this.buffer.limit(old);
                b.buffer.position(0);
            }
            int i = sl;
            while(((l + i) % 4) != 0) {
                this.buffer.get();
                i++;
            }
            return b;
        } catch (Exception e) {
            if (exception) {
                throw new RuntimeException("read byte array error", e);
            } else {
                Logger.w("tmessages", "read byte array error");
            }
        }
        return null;
    }

    public double readDouble(boolean exception) {
        try {
            return Double.longBitsToDouble(readInt64(exception));
        } catch(Exception e) {
            if (exception) {
                throw new RuntimeException("read double error", e);
            } else {
                Logger.w("tmessages", "read double error");
            }
        }
        return 0;
    }
}
