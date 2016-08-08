package org.telegram.tl;

import org.telegram.mtproto.log.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Helper class for writing and reading data for tl (de-)serialization.
 *
 * @author Ruben Bermudez
 */
public class StreamingUtils {

    /**
     * Writing byte to stream
     *
     * @param v      value
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeByte(int v, OutputStream stream) throws IOException {
        stream.write(v);
    }

    /**
     * Writing byte to stream
     *
     * @param v      value
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeByte(byte v, OutputStream stream) throws IOException {
        stream.write(v);
    }

    /**
     * Writing int to stream
     *
     * @param v      value
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeInt(int v, OutputStream stream) throws IOException {
        writeByte((byte) (v & 0xFF), stream);
        writeByte((byte) ((v >> 8) & 0xFF), stream);
        writeByte((byte) ((v >> 16) & 0xFF), stream);
        writeByte((byte) ((v >> 24) & 0xFF), stream);
    }

    /**
     * Writing long to stream
     *
     * @param v      value
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeLong(long v, OutputStream stream) throws IOException {
        writeByte((byte) (v & 0xFF), stream);
        writeByte((byte) ((v >> 8) & 0xFF), stream);
        writeByte((byte) ((v >> 16) & 0xFF), stream);
        writeByte((byte) ((v >> 24) & 0xFF), stream);

        writeByte((byte) ((v >> 32) & 0xFF), stream);
        writeByte((byte) ((v >> 40) & 0xFF), stream);
        writeByte((byte) ((v >> 48) & 0xFF), stream);
        writeByte((byte) ((v >> 56) & 0xFF), stream);
    }

    /**
     * Writing double to stream
     *
     * @param v      value
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeDouble(double v, OutputStream stream) throws IOException {
        writeLong(Double.doubleToLongBits(v), stream);
    }

    /**
     * Writing byte array to stream
     *
     * @param data   data
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeByteArray(byte[] data, OutputStream stream) throws IOException {
        stream.write(data);
    }

    /**
     * Writing byte array to stream
     *
     * @param data   data
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeByteArray(byte[] data, int offset, int len, OutputStream stream) throws IOException {
        stream.write(data, offset, len);
    }

    /**
     * Writing tl-bool value
     *
     * @param v      value
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeTLBool(boolean v, OutputStream stream) throws IOException {
        if (v) {
            writeInt(TLBoolTrue.CLASS_ID, stream);
        } else {
            writeInt(TLBoolFalse.CLASS_ID, stream);
        }
    }

    /**
     * Writing tl-string value
     *
     * @param v      value
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeTLString(String v, OutputStream stream) throws IOException {
        writeTLBytes(v.getBytes(), stream);
    }

    /**
     * Writing tl-bytes value
     *
     * @param v      value
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeTLBytes(byte[] v, OutputStream stream) throws IOException {
        int startOffset = 1;
        if (v.length >= 254) {
            startOffset = 4;
            writeByte(254, stream);
            writeByte(v.length & 0xFF, stream);
            writeByte((v.length >> 8) & 0xFF, stream);
            writeByte((v.length >> 16) & 0xFF, stream);
        } else {
            writeByte(v.length, stream);
        }

        writeByteArray(v, stream);

        int offset = (v.length + startOffset) % 4;
        if (offset != 0) {
            int offsetCount = 4 - offset;
            writeByteArray(new byte[offsetCount], stream);
        }
    }

    /**
     * Writing tl-bytes value
     *
     * @param v      value
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeTLBytes(TLBytes v, OutputStream stream) throws IOException {
        int startOffset = 1;
        if (v.getLength() >= 254) {
            startOffset = 4;
            writeByte(254, stream);
            writeByte(v.getLength() & 0xFF, stream);
            writeByte((v.getLength() >> 8) & 0xFF, stream);
            writeByte((v.getLength() >> 16) & 0xFF, stream);
        } else {
            writeByte(v.getLength(), stream);
        }

        writeByteArray(v.getData(), v.getOffset(), v.getLength(), stream);

        int offset = (v.getLength() + startOffset) % 4;
        if (offset != 0) {
            int offsetCount = 4 - offset;
            writeByteArray(new byte[offsetCount], stream);
        }
    }

    /**
     * Writing tl-object to stream
     *
     * @param v      tl-object
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeTLObject(TLObject v, OutputStream stream) throws IOException {
        v.serialize(stream);
    }

    /**
     * Writing tl-method to stream. Same as writeTLObject, but used for pretty code
     *
     * @param v      tl-method
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeTLMethod(TLMethod v, OutputStream stream) throws IOException {
        writeTLObject(v, stream);
    }

    /**
     * Writing tl-vector to stream
     *
     * @param v      tl-vector
     * @param stream destination stream
     * @throws IOException
     */
    public static void writeTLVector(TLVector v, OutputStream stream) throws IOException {
        writeTLObject(v, stream);
    }

    /**
     * Reading int from stream
     *
     * @param stream source stream
     * @return value
     * @throws IOException reading exception
     */
    public static int ºreadInt(InputStream stream) throws IOException {
        int a = stream.read();
        if (a < 0) {
            throw new IOException();
        }
        int b = stream.read();
        if (b < 0) {
            throw new IOException();
        }
        int c = stream.read();
        if (c < 0) {
            throw new IOException();
        }
        int d = stream.read();
        if (d < 0) {
            throw new IOException();
        }

        return a + (b << 8) + (c << 16) + (d << 24);
    }

    /**
     * Reading uint from stream
     *
     * @param stream source stream
     * @return value
     * @throws IOException reading exception
     */
    public static long readUInt(InputStream stream) throws IOException {
        long a = stream.read();
        if (a < 0) {
            throw new IOException();
        }
        long b = stream.read();
        if (b < 0) {
            throw new IOException();
        }
        long c = stream.read();
        if (c < 0) {
            throw new IOException();
        }
        long d = stream.read();
        if (d < 0) {
            throw new IOException();
        }

        return a + (b << 8) + (c << 16) + (d << 24);
    }

    /**
     * Reading long from stream
     *
     * @param stream source stream
     * @return value
     * @throws IOException reading exception
     */
    public static long readLong(InputStream stream) throws IOException {
        long a = readUInt(stream);
        long b = readUInt(stream);

        return a + (b << 32);
    }

    /**
     * Reading double from stream
     *
     * @param stream source stream
     * @return value
     * @throws IOException reading exception
     */
    public static double readDouble(InputStream stream) throws IOException {
        return Double.longBitsToDouble(readLong(stream));
    }

    /**
     * Reading tl-string from stream
     *
     * @param stream source stream
     * @return value
     * @throws IOException reading exception
     */
    public static String readTLString(InputStream stream) throws IOException {
        return new String(readTLBytes(stream));
    }

    /**
     * Reading tl-object from stream
     *
     * @param stream  source stream
     * @param context tl-context
     * @return tl-object
     * @throws IOException reading exception
     */
    public static TLObject readTLObject(InputStream stream, TLContext context) throws IOException {
        return context.deserializeMessage(stream);
    }

    public static <T extends TLObject> T readTLObject(InputStream stream, TLContext context, Class<T> type) throws IOException {
        return (T) context.deserializeMessage(stream);
    }

    /**
     * Reading tl-method from stream. Same as readTLObject, used for pretty code.
     *
     * @param stream  source stream
     * @param context tl-method
     * @return tl-method
     * @throws IOException reading exception
     */
    public static TLMethod readTLMethod(InputStream stream, TLContext context) throws IOException {
        return (TLMethod) context.deserializeMessage(stream);
    }

    /**
     * Reading bytes from stream
     *
     * @param count  bytes count
     * @param stream source stream
     * @return readed bytes
     * @throws IOException reading exception
     */
    public static byte[] readBytes(int count, InputStream stream) throws IOException {
        byte[] res = new byte[count];
        int offset = 0;
        while (offset < res.length) {
            int readed = stream.read(res, offset, res.length - offset);
            if (readed > 0) {
                offset += readed;
            } else if (readed < 0) {
                throw new IOException();
            } else {
                Thread.yield();
            }
        }
        return res;
    }

    /**
     * Reading bytes from stream
     *
     * @param count  bytes count
     * @param stream source stream
     * @return readed bytes
     * @throws IOException reading exception
     */
    //public static void skipBytes(int count, InputStream stream) throws IOException {
    //    stream.skip(count);
    //}

    public static void skipBytes(int count, InputStream stream) throws IOException {
        byte[] bytes = new byte[count];
        stream.read(bytes);
        Logger.d("SKIPED", "Skypped " + count + " bytes: " + bytes);
        //stream.skip(count);
    }


    /**
     * Reading bytes from stream
     *
     * @param count  bytes count
     * @param stream source stream
     * @return readed bytes
     * @throws IOException reading exception
     */
    public static void readBytes(byte[] buffer, int offset, int count, InputStream stream) throws IOException {
        int woffset = 0;
        while (woffset < count) {
            int readed = stream.read(buffer, woffset + offset, count - woffset);
            if (readed > 0) {
                woffset += readed;
            } else if (readed < 0) {
                throw new IOException();
            } else {
                Thread.yield();
            }
        }
    }

    /**
     * Reading tl-bytes from stream
     *
     * @param stream source stream
     * @return readed bytes
     * @throws IOException reading exception
     */
    public static byte[] readTLBytes(InputStream stream) throws IOException {
        int count = stream.read();
        int startOffset = 1;
        if (count >= 254) {
            count = stream.read() + (stream.read() << 8) + (stream.read() << 16);
            startOffset = 4;
        }

        byte[] raw = readBytes(count, stream);
        int offset = (count + startOffset) % 4;
        if (offset != 0) {
            int offsetCount = 4 - offset;
            skipBytes(offsetCount, stream);
        }

        return raw;
    }

    /**
     * Reading tl-bytes from stream with manual allocation
     *
     * @param stream  source stream
     * @param context tl-context
     * @return readed bytes
     * @throws IOException reading exception
     */
    public static TLBytes readTLBytes(InputStream stream, TLContext context) throws IOException {
        int count = stream.read();
        int startOffset = 1;
        if (count >= 254) {
            count = stream.read() + (stream.read() << 8) + (stream.read() << 16);
            startOffset = 4;
        }
        TLBytes res = context.allocateBytes(count);
        readBytes(res.getData(), res.getOffset(), res.getLength(), stream);

        int offset = (count + startOffset) % 4;
        if (offset != 0) {
            int offsetCount = 4 - offset;
            skipBytes(offsetCount, stream);
        }
        return res;
    }

    /**
     * Reading tl-vector from stream
     *
     * @param stream  source stream
     * @param context tl-context
     * @return tl-vector
     * @throws IOException reading exception
     */
    public static TLVector readTLVector(InputStream stream, TLContext context) throws IOException {
        return context.deserializeVector(stream);
    }

    /**
     * Reading tl-vector from stream
     *
     * @param stream  source stream
     * @param context tl-context
     * @return tl-vector
     * @throws IOException reading exception
     */
    public static <T> TLVector<T> readTLVector(InputStream stream, TLContext context, Class<T> vectorType) throws IOException {
        return (TLVector<T>) context.deserializeVector(stream);
    }

    /**
     * Reading tl-vector of integers from stream
     *
     * @param stream  source stream
     * @param context tl-context
     * @return tl-vector of integers
     * @throws IOException reading exception
     */
    public static TLIntVector readTLIntVector(InputStream stream, TLContext context) throws IOException {
        return context.deserializeIntVector(stream);
    }

    /**
     * Reading tl-vector of longs from stream
     *
     * @param stream  source stream
     * @param context tl-context
     * @return tl-vector of longs
     * @throws IOException reading exception
     */
    public static TLLongVector readTLLongVector(InputStream stream, TLContext context) throws IOException {
        return context.deserializeLongVector(stream);
    }

    /**
     * Reading tl-vector of strings from stream
     *
     * @param stream  source stream
     * @param context tl-context
     * @return tl-vector of strings
     * @throws IOException reading exception
     */
    public static TLStringVector readTLStringVector(InputStream stream, TLContext context) throws IOException {
        return context.deserializeStringVector(stream);
    }

    /**
     * Reading tl-bool from stream
     *
     * @param stream source stream
     * @return bool
     * @throws IOException reading exception
     */
    public static boolean readTLBool(InputStream stream) throws IOException {
        int v = readInt(stream);
        if (v == TLBoolTrue.CLASS_ID) {
            return true;
        } else if (v == TLBoolFalse.CLASS_ID) {
            return false;
        } else
            throw new DeserializeException("Not bool value: " + Integer.toHexString(v));
    }

    /**
     * Converting int to bytes
     *
     * @param value source integer
     * @return bytes of integer
     */
    public static byte[] intToBytes(int value) {
        return new byte[]{
                (byte) (value & 0xFF),
                (byte) ((value >> 8) & 0xFF),
                (byte) ((value >> 16) & 0xFF),
                (byte) ((value >> 24) & 0xFF)};
    }

    /**
     * Converting long to bytes
     *
     * @param value source long
     * @return bytes of long
     */
    public static byte[] longToBytes(long value) {
        return new byte[]{
                (byte) (value & 0xFF),
                (byte) ((value >> 8) & 0xFF),
                (byte) ((value >> 16) & 0xFF),
                (byte) ((value >> 24) & 0xFF),
                (byte) ((value >> 32) & 0xFF),
                (byte) ((value >> 40) & 0xFF),
                (byte) ((value >> 48) & 0xFF),
                (byte) ((value >> 56) & 0xFF)};
    }

    /**
     * Reading int from bytes array
     *
     * @param src source bytes
     * @return int value
     */
    public static int readInt(byte[] src) {
        return readInt(src, 0);
    }

    /**
     * Reading int from bytes array
     *
     * @param src    source bytes
     * @param offset offset in array
     * @return int value
     */
    public static int readInt(byte[] src, int offset) {
        int a = src[offset + 0] & 0xFF;
        int b = src[offset + 1] & 0xFF;
        int c = src[offset + 2] & 0xFF;
        int d = src[offset + 3] & 0xFF;

        return a + (b << 8) + (c << 16) + (d << 24);
    }

    public static int readInt(InputStream stream) throws IOException {
        int i = 0;
        for (int j = 0; j < 4; j++) {
            i |= (stream.read() << (j * 8));
        }
        return i;
    }

    /**
     * Reading uint from bytes array
     *
     * @param src source bytes
     * @return uint value
     */
    public static long readUInt(byte[] src) {
        return readUInt(src, 0);
    }

    /**
     * Reading uint from bytes array
     *
     * @param src    source bytes
     * @param offset offset in array
     * @return uint value
     */
    public static long readUInt(byte[] src, int offset) {
        long a = src[offset + 0] & 0xFF;
        long b = src[offset + 1] & 0xFF;
        long c = src[offset + 2] & 0xFF;
        long d = src[offset + 3] & 0xFF;

        return a + (b << 8) + (c << 16) + (d << 24);
    }

    /**
     * Reading long value from bytes array
     *
     * @param src    source bytes
     * @param offset offset in array
     * @return long value
     */
    public static long readLong(byte[] src, int offset) {
        long a = readUInt(src, offset);
        long b = readUInt(src, offset + 4);

        return (a & 0xFFFFFFFF) + ((b & 0xFFFFFFFF) << 32);
    }
}
