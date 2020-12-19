package ru.itis.game.core.fields;

import ru.itis.game.client.Connection;
import java.util.List;

public class StationField extends PurchasableField{

    private final String name;

    //todo costs
    private static final int DEFAULT_COST = 200;
    private static final int DEFAULT_MORTGAGE_COST = 200;
    private static final int DEFAULT_UNMORTGAGE_COST = 200;

    public StationField(String name) {
        this.name = name;
    }


    @Override
    public int getCost() {
        return DEFAULT_COST;
    }

    @Override
    public int getMortgageCost() {
        return DEFAULT_MORTGAGE_COST;
    }

    @Override
    public int getUnmortgageCost() {
        return DEFAULT_UNMORTGAGE_COST;
    }

    public String getName() {
        return name;
    }
}
