package ru.itis.game.server;


import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<Connection> connections;

    private List<Player> playerList;

    private GameSession gameSession;

    private ServerSocket server;

    public Server() {
        try {
            server = new ServerSocket(123);
            playerList = new ArrayList<>();
            connections = new ArrayList<>();
            gameSession = new GameSession();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void start() {
        while (true) {
            try {
                Socket s = server.accept();
                Connection c = new Connection(s, this);
                connections.add(c);
                c.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addUser(int character, String nickName) {
        if (character > 5) {
            return false;
        }
        for (Player player: playerList) {
            if (player.getCharacter() == character) {
                return false;
            }
        }
        playerList.add(new Player(character, gameSession, nickName));
        return true;
    }

    public void removeUser(int character) {
        playerList.removeIf(player -> player.getCharacter() == character);
    }
    public void initExit() {
        System.exit(0);
    }

    public List<Player> getNickNames() {
        return playerList;
    }

    public GameSession getGameSession() {
        return gameSession;
    }
}