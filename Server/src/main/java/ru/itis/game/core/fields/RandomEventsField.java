package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

public class RandomEventsField extends MapField{

    private final int type;

    public static final int CHANCE = 0;
    public static final int COMMUNITY_CHEST = 1;

    public RandomEventsField(int type, GameSession session) {
        super(session);
        this.type = type;
    }

    @Override
    public void stop(Player p) {
        if(type == CHANCE){
            session.getGameMap().chance(p);
        }
        if(type == COMMUNITY_CHEST){
            session.getGameMap().communityChest(p);
        }
    }

    public int getType() {
        return type;
    }
}
