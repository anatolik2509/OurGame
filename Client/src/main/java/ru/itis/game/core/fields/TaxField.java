package ru.itis.game.core.fields;

import ru.itis.game.client.Connection;

public class TaxField extends MapField{
    private final int type;

    public static final int INCOME = 0;
    public static final int LUXURY = 1;

    public TaxField(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
