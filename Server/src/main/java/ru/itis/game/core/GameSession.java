package ru.itis.game.core;

import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private GameMap gameMap;

    public static final int MAX_PLAYERS = 6;
    public static final int MIN_PLAYERS = 2;

    private final List<Player> players;

    private int turn;

    private int dice1;
    private int dice2;

    public GameSession(){
        turn = 0;
        players = new ArrayList<>();
    }

    public PlayerController addPlayer(Player p){
        if(players.size() == MAX_PLAYERS){
            return null;
        }
        else {
            players.add(p);
            return new PlayerController(p, this);
        }
    }

    //todo game process

    public List<Player> getPlayers() {
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
}
