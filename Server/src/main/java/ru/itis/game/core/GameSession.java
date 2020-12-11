package ru.itis.game.core;

import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private GameMap gameMap;

    public static final int MAX_PLAYERS = 6;
    public static final int MIN_PLAYERS = 2;

    private final List<Player> players;

    public GameSession(){
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

    public List<Player> getPlayers() {
        return players;
    }
}
