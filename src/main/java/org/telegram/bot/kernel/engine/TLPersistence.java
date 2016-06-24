package org.telegram.bot.kernel.engine;

/**
 * Created by Hendrik Hofstadt on 08.03.14.
 */

import org.telegram.bot.kernel.engine.storage.TLDcInfo;
import org.telegram.bot.kernel.engine.storage.TLKey;
import org.telegram.bot.kernel.engine.storage.TLLastKnownSalt;
import org.telegram.bot.kernel.engine.storage.TLOldSession;
import org.telegram.bot.kernel.engine.storage.TLStorage;
import org.telegram.bot.services.BotLogger;
import org.telegram.tl.TLContext;
import org.telegram.tl.TLObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 09.11.13
 * Time: 0:29
 */
public class TLPersistence<T extends TLObject> extends TLContext {

    private static final String LOGTAG = "KernelPersistence";
    private Class<T> destClass;
    private T obj;
    private SafeFileWriter writer;

    TLPersistence() {
        super();
    }

    TLPersistence(String fileName, Class<T> destClass) {
        long start = System.currentTimeMillis();
        writer = new SafeFileWriter(fileName);
        byte[] data = writer.loadData();

        BotLogger.warning(LOGTAG, "Loaded state in " + (System.currentTimeMillis() - start) + " ms");
        if (data != null) {
            try {
                ByteArrayInputStream stream = new ByteArrayInputStream(data);
                obj = (T) this.deserializeMessage(stream);
            } catch (IOException e) {
                BotLogger.warning(LOGTAG, e);
            }
        }

        if (obj == null) {
            try {
                obj = destClass.newInstance();
            } catch (Exception e1) {
                throw new RuntimeException("Unable to instantiate default settings");
            }
        }

        afterLoaded();
    }

    public void init() {
        registerClass(TLDcInfo.CLASS_ID, TLDcInfo.class);
        registerClass(TLKey.CLASS_ID, TLKey.class);
        registerClass(TLLastKnownSalt.CLASS_ID, TLLastKnownSalt.class);
        registerClass(TLOldSession.CLASS_ID, TLOldSession.class);
        registerClass(TLStorage.CLASS_ID, TLStorage.class);
    }

    void afterLoaded() {

    }

    void write() {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            obj.serialize(stream);
            stream.close();
            writer.saveData(stream.toByteArray());
        } catch (IOException e) {
            BotLogger.warning(LOGTAG, e);
        }
    }

    public T getObj() {
        return obj;
    }

}
