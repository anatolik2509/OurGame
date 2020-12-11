package ru.itis.game.protocol;

import java.io.IOException;
import java.io.InputStream;

public class ProtocolInputStream extends InputStream {
    private InputStream inputStream;

    public ProtocolInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Message readMessage(){
        //todo
        //тут надо реализовать логику чтения сообщения из inputStream
        //я от вас ожидаю,что вы помните декораторы))
        return null;
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
