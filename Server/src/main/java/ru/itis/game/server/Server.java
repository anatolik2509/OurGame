package ru.itis.game.server;


import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;
import ru.itis.game.protocol.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Server {

    private List<Connection> connections;

    private List<Boolean> isReady;

    private GameSession gameSession;

    private ServerSocket server;

    private int state;

    public static final int STARTING = 1;
    public static final int GAME = 2;

    public Server() {
        try {
            server = new ServerSocket(Protocol.PORT);
            connections = new ArrayList<>();
            isReady = new ArrayList<>();
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
                isReady.add(false);
                c.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkReady(){
        if(connections.size() < 2){
            return false;
        }
        for(boolean b : isReady){
            if(!b){
                return false;
            }
        }
        return true;
    }

    public void startGame(){

    }

    public void initExit() {
        System.exit(0);
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void removeConnection(Connection c){
        int index = connections.indexOf(c);
        if(index != -1){
            connections.remove(c);
            isReady.remove(index);
        }
    }
}