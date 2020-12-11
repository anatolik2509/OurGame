package ru.itis.game.core.fields;

import ru.itis.game.core.Player;

public class RandomEventsField extends MapField{

    private final int type;

    public static final int CHANCE = 0;
    public static final int COMMUNITY_CHEST = 1;

    public RandomEventsField(int type) {
        this.type = type;
    }

    @Override
    public void stop(Player p) {
        //todo
    }

    public int getType() {
        return type;
    }
}
