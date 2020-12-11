package ru.itis.game.core.fields;

import ru.itis.game.core.Player;

public class UtilityField extends PurchasableField{

    private final String name;

    //todo costs
    private static final int DEFAULT_COST = 200;
    private static final int DEFAULT_MORTGAGE_COST = 200;
    private static final int DEFAULT_UNMORTGAGE_COST = 200;

    public UtilityField(String name) {
        this.name = name;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public int getMortgageCost() {
        return 0;
    }

    @Override
    public int getUnmortgageCost() {
        return 0;
    }

    @Override
    public void stop(Player p) {
        //todo
    }
}
