package org.telegram.mtproto.transport;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Different method to serialize and deserialize
 * @date 06 of February of 2016
 */
class BytesHelper {
    public static byte[] intToBytes(int value) {
        return new byte[]{
                (byte) (value & 0xFF),
                (byte) ((value >> 8) & 0xFF),
                (byte) ((value >> 16) & 0xFF),
                (byte) ((value >> 24) & 0xFF)};
    }

    public static void writeInt(int value, OutputStream stream) throws IOException {
        stream.write((byte) (value & 0xFF));
        stream.write((byte) ((value >> 8) & 0xFF));
        stream.write((byte) ((value >> 16) & 0xFF));
        stream.write((byte) ((value >> 24) & 0xFF));
    }

    public static void writeByte(int v, OutputStream stream) throws IOException {
        stream.write(v);
    }

    public static void writeByteArray(byte[] data, OutputStream stream) throws IOException {
        stream.write(data);
    }

}
