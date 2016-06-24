package org.telegram.api.engine;

import org.telegram.tl.TLContext;
import org.telegram.tl.TLGzipObject;
import org.telegram.tl.TLMethod;
import org.telegram.tl.TLObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import static org.telegram.tl.StreamingUtils.writeTLBytes;

/**
 * Created by Ruben Bermudez on 07.12.13.
 * @param <T>  the type parameter
 */
public class GzipRequest<T extends TLObject> extends TLMethod<T> {

    private static final int CLASS_ID = TLGzipObject.CLASS_ID;

    private TLMethod<T> method;

    /**
     * Instantiates a new Gzip request.
     *
     * @param method the method
     */
    public GzipRequest(TLMethod<T> method) {
        this.method = method;
    }

    @Override
    public T deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return this.method.deserializeResponse(stream, context);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        ByteArrayOutputStream resOutput = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(resOutput);
        this.method.serialize(gzipOutputStream);
        gzipOutputStream.flush();
        gzipOutputStream.close();
        byte[] body = resOutput.toByteArray();
        writeTLBytes(body, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        throw new IOException("Unsupported operation");
    }

    @Override
    public String toString() {
        return "gzip<" + this.method + ">";
    }
}
