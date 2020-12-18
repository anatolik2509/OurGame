package ru.itis.game.core;

import ru.itis.game.core.util.Event;
import ru.itis.game.protocol.Protocol;
import ru.itis.game.server.Server;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Random;

public class GameSession {
    private final GameMap gameMap;

    private Server server;
    public static final int MAX_PLAYERS = 6;
    public static final int MIN_PLAYERS = 2;

    private final Player[] players;
    private int cursor;

    private int turn;

    private Player current;
    private boolean canRoll;

    private boolean exit;

    private int dice1;
    private int dice2;

    private Random random;

    private List<Offer> offers;

    public GameSession(Server server){
        this.server = server;
        turn = 0;
        players = new Player[6];
        cursor = 0;
        exit = false;
        gameMap = new GameMap(players, this);
        random = new Random();
    }


    public PlayerController addPlayer(Player p){
        if(cursor == MAX_PLAYERS){
            return null;
        }
        else {
            players[cursor] = p;
            p.setSession(this);
            return new PlayerController(p, this);
        }
    }

    public void start(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(players.length * 8);
        for (Player player : players) {
            if (player == null) {
                byteBuffer.putInt(-1).putInt(-1);
            } else {
                byteBuffer.putInt(player.getId()).putInt(player.getCharacter());
            }
        }
        byte[] bytes = new byte[players.length * 8];
        byteBuffer.get(bytes);
        initEvent(new Event(null, Protocol.GAME_STARTED, bytes));
        current = players[0];
        cursor = 0;
        while (!exit){
            initEvent(new Event(current, Protocol.START_OF_TURN));
            canRoll = true;
            try {
                synchronized (gameMap) {
                    gameMap.wait();
                }
            } catch (InterruptedException e){
                //ignore
            }
            initEvent(new Event(current, Protocol.END_OF_TURN));
            nextPlayer();
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public int getTurn() {
        return turn;
    }

    public int getDiceSum(){
        return dice1 + dice2;
    }

    public void removePlayer(Player p) {
        for(int i = 0; i < 6; i++){
            if(players[i] == p){
                players[i] = null;
            }
        }
        gameMap.removePlayer(p);
    }

    public void initEvent(Event e){
        server.initEvent(e);
    }

    public Player playersTurn(){
        return current;
    }

    public void nextPlayer(){
        turn++;
        int iter = 0;
        while (players[turn % players.length] != null){
            turn++;
            iter++;
            if(iter > 12){
                exit = true;
                break;
            }
        }
        current = players[turn];
    }

    public Server getServer() {
        return server;
    }

    public int playersOnline(){
        int sum = 0;
        for(Player p : players){
            if(p != null){
                sum++;
            }
        }
        return sum;
    }

    public void rollDices(){
        dice1 = random.nextInt(6) + 1;
        dice2 = random.nextInt(6) + 1;
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(dice1);
        buffer.putInt(dice2);
        byte[] bytes = new byte[8];
        buffer.get(bytes);
        initEvent(new Event(null, Protocol.DICES_RESPONSE, bytes));
        if(current.isArrested()){
            if(dice1 == dice2){
                current.setArrested(false);
                current.setArrestTurns(0);
            }
            else {
                canRoll = false;
                current.increaseArrestedTurns();
                if(current.getArrestTurns() == 3){
                    current.setArrested(false);
                    current.setArrestTurns(0);
                }
            }
        }
        else {
            gameMap.movePlayer(current, getDiceSum());
            if(dice1 != dice2){
                canRoll = false;
            }
        }
    }

    public boolean isCanRoll() {
        return canRoll;
    }

    public void setCanRoll(boolean canRoll) {
        this.canRoll = canRoll;
    }
}
