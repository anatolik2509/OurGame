package ru.itis.game.protocol;

import ru.itis.antonov.chat.utils.Message;
import ru.itis.antonov.chat.utils.Protocol;

import java.io.IOException;
import java.io.InputStream;

public class ProtocolInputStream extends InputStream {
    private InputStream inputStream;

    public ProtocolInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Message readMessage() throws IOException {
        int type = -1;
        int length;
        byte[] buffer;
        if ((type = inputStream.read()) == -1) {
            return null;
        }
        length = (inputStream.read() << 8) + inputStream.read();
        if (length > Protocol.MAX_MESSAGE_LENGTH) {
            Message incorrectMessage = new Message(Protocol.SEND_ERROR, new byte[0]);
            inputStream.skip(length);
            return incorrectMessage;
        }
        buffer = new byte[length];
        inputStream.read(buffer);
        return new Message((byte) type, buffer);
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return inputStream.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return inputStream.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return inputStream.skip(n);
    }

    @Override
    public int available() throws IOException {
        return inputStream.available();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    @Override
    public void mark(int readlimit) {
        inputStream.mark(readlimit);
    }

    @Override
    public void reset() throws IOException {
        inputStream.reset();
    }

    @Override
    public boolean markSupported() {
        return inputStream.markSupported();
    }
}