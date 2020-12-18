package ru.itis.game.server;


import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;
import ru.itis.game.core.util.Event;
import ru.itis.game.core.util.GameEventManager;
import ru.itis.game.core.util.GameListener;
import ru.itis.game.protocol.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Server {

    private List<Connection> connections;

    private List<Boolean> isReady;

    private GameSession gameSession;

    private ServerSocket server;

    private int connectionNumber;

    private int state;

    public static final int STARTING = 1;
    public static final int GAME = 2;

    private GameEventManager manager;

    public Server() {
        connectionNumber = STARTING;
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
                createConnection(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createConnection(Socket socket){
        Connection c = new Connection(socket, this);
        connections.add(c);
        isReady.add(false);
        c.setPlayer(new Player(connectionNumber));
        c.start();
        initEvent(new Event(null, Protocol.ADD_PLAYER, connectionNumber));
        connectionNumber++;
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
        if(checkReady()){
            state = GAME;
            gameSession = new GameSession(this);
            for (Connection c : connections){
                c.setGameSession(gameSession);
                c.setPlayerController(gameSession.addPlayer(c.getPlayer()));
                //todo gameSession.addGameListener()
            }
            gameSession.start();
        }
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
        if(gameSession != null){
            gameSession.removePlayer(c.getPlayer());
        }
        initEvent(new Event(c.getPlayer(), Protocol.REMOVE_PLAYER, c.getPlayer().getId()));
    }

    public void setReady(Connection c, boolean ready){
        int index = connections.indexOf(c);
        if(index != -1){
            isReady.set(index, ready);
        }
    }

    public int getState() {
        return state;
    }

    public boolean isCharacterOccupied(int character){
        Player p;
        for (Connection c : connections){
            p = c.getPlayer();
            if(p.getCharacter() == character){
                return false;
            }
        }
        return true;
    }

    public void addGameListener(GameListener listener){
        manager.addGameListener(listener);
    }

    public void initEvent(Event e){
        manager.activate(e);
    }

    public Connection getByPlayer(Player p){
        for(Connection c : connections){
            if(c.getPlayer() == p){
                return c;
            }
        }
        return null;
    }
}