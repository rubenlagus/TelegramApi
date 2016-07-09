package org.telegram.bot.kernel.engine;

/**
 * Created by Hendrik Hofstadt on 08.03.14.
 */

import org.telegram.bot.services.BotLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.zip.CRC32;

import static org.telegram.tl.StreamingUtils.readBytes;
import static org.telegram.tl.StreamingUtils.readInt;
import static org.telegram.tl.StreamingUtils.readLong;
import static org.telegram.tl.StreamingUtils.writeByteArray;
import static org.telegram.tl.StreamingUtils.writeInt;
import static org.telegram.tl.StreamingUtils.writeLong;

/**
 * Created: 23.08.13 1:46
 */
@SuppressWarnings("ALL")
class SafeFileWriter {
    private static final String LOGTAG = "SAFEFILEWRITER";
    private final Random random = new Random();
    private final String fileName;

    public SafeFileWriter(String fileName) {
        this.fileName = fileName;
        String TAG = "SafeFileWriter#" + hashCode();
    }

    private File getFile() {
        return new File(fileName);
    }

    private File getTempFile() {
        return new File("random_" + random.nextLong() + ".tmp");
    }

    public synchronized void saveData(byte[] data) {
        File file = getFile();
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            writeInt(data.length, os);
            writeByteArray(data, os);
            CRC32 crc32 = new CRC32();
            crc32.update(data);
            writeLong(crc32.getValue(), os);
            os.flush();
            os.getFD().sync();
            os.close();
            os = null;
            file.renameTo(getFile());
        } catch (IOException e) {
            BotLogger.error(LOGTAG, e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        }
    }

    public synchronized byte[] loadData() {
        File file = getFile();
        if (!file.exists())
            return null;

        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            int len = readInt(is);
            byte[] res = readBytes(len, is);
            CRC32 crc32 = new CRC32();
            crc32.update(res);
            long crc = readLong(is);
            if (crc32.getValue() != crc) {
                return null;
            }
            return res;
        } catch (IOException e) {
            BotLogger.error(LOGTAG, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        }
        return null;
    }
}