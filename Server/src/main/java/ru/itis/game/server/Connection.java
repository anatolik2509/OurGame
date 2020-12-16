
package ru.itis.game.server;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.PlayerController;
import ru.itis.game.protocol.Action;
import ru.itis.game.protocol.ProtocolInputStream;
import ru.itis.game.protocol.ProtocolOutputStream;

import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
    private final Socket socket;
    private PlayerController playerController;
    private final Server server;
    private GameSession gameSession;

    public Connection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            ProtocolInputStream inputStream = new ProtocolInputStream(socket.getInputStream());
            ProtocolOutputStream outputStream = new ProtocolOutputStream((socket.getOutputStream()));
            Action action;
            //TODO connection
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
