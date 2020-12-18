package ru.itis.game.client;

import ru.itis.game.core.GameMap;
import ru.itis.game.protocol.Action;
import ru.itis.game.protocol.ProtocolInputStream;
import ru.itis.game.protocol.ProtocolOutputStream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private ProtocolOutputStream outputStream;
    private ProtocolInputStream inputStream;

    public GameMap getGameMap(){
        return null;
    }

    public Connection(InetAddress address, int port) {
        try {
            this.socket = new Socket(address, port);
            outputStream = new ProtocolOutputStream(socket.getOutputStream());
            inputStream = new ProtocolInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAction(Action action) throws IOException {
        outputStream.writeAction(action);
    }

}
