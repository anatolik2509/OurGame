package ru.itis.game.protocol;

import java.io.IOException;
import java.io.OutputStream;

public class ProtocolOutputStream extends OutputStream {
    OutputStream outputStream;

    public ProtocolOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendMessage(Message m){
        //todo
        //тут надо реализовать логику отправки сообщения в outputStream
        //я от вас ожидаю,что вы помните декораторы))
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
