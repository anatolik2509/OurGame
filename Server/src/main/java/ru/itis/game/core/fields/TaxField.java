package ru.itis.game.core.fields;

import ru.itis.game.core.Player;

public class TaxField extends MapField{
    private final int type;

    public static final int INCOME = 0;
    public static final int LUXURY = 1;

    public TaxField(int type) {
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
