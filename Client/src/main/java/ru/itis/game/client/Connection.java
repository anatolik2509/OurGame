package ru.itis.game.client;

import ru.itis.game.core.GameMap;
import ru.itis.game.core.Player;
import ru.itis.game.protocol.Action;
import ru.itis.game.protocol.ProtocolInputStream;
import ru.itis.game.protocol.ProtocolOutputStream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    private static Connection instance;
    private Socket socket;
    private ProtocolOutputStream outputStream;
    private ProtocolInputStream inputStream;
    private int state;
    private List<Player> players;
    private GameMap gameMap;
    private boolean isReady;
    private int clientPLayerId;
    public static final int STARTING = 1;
    public static final int GAME = 2;

    public GameMap getGameMap(){
        return gameMap;
    }

    public static Connection getInstance() {
        return instance;
    }
    public Connection(InetAddress address, int port) {
        this.instance = this;
        System.out.println(instance);
        isReady = false;
        players = new ArrayList<>();
        try {
            state = STARTING;
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

    public void startGame(){
        gameMap = new GameMap((Player[]) players.toArray(), this, clientPLayerId);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public Player getPlayerById(int id){
        for(Player p : players) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void closeConnection(){
        try {
            socket.close();
        } catch (IOException e) {
            //ignore
        }
    }

    public void removePlayer(Player p){
        players.remove(p);
        getGameMap().removePlayer(p);
    }

    public void setClientPLayerId(int clientPLayerId) {
        this.clientPLayerId = clientPLayerId;
    }
}
