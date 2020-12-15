package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

public class TaxField extends MapField{
    private final int type;

    public static final int INCOME = 0;
    public static final int LUXURY = 1;

    public TaxField(int type, GameSession session) {
        super(session);
        this.type = type;
    }

    @Override
    public void stop(Player p) {
        if(type == INCOME){
            p.takeAway(200);
        }
        if(type == LUXURY){
            p.takeAway(100);
        }
    }

    public int getType() {
        return type;
    }
}
