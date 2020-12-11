package ru.itis.game.core;

import ru.itis.game.core.fields.MapField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {
    private final List<MapField> mapFields;
    private final Map<Player, Integer> playersPositions;

    public GameMap(List<Player> players){
        mapFields = new ArrayList<>();
        playersPositions = new HashMap<>();
        for(Player p : players){
            playersPositions.put(p, 0);
        }
        //todo fields
    }

    public int getPlayerPosition(Player p){
        return playersPositions.get(p);
    }

    public MapField field(int i){
        return mapFields.get(i);
    }
}
