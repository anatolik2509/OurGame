package ru.itis.game.protocol;

import java.io.IOException;
import java.io.OutputStream;

public class ProtocolOutputStream extends OutputStream {
    private OutputStream outputStream;

    public ProtocolOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeAction(Action action) throws IOException {
        byte type = action.getType();
        if (action.getContentLength() > Protocol.MAX_ACTION_LENGTH) {
            return;
        }
        byte[] data = action.getData();
        int length = data.length;
        outputStream.write(type);
        outputStream.write(length >> 8);
        outputStream.write(length);
        outputStream.write(data);
        outputStream.flush();
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }
}
