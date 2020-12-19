package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

import java.util.List;

public class StationField extends PurchasableField{

    private final String name;

    //todo costs
    private static final int DEFAULT_COST = 200;
    private static final int DEFAULT_MORTGAGE_COST = 100;
    private static final int DEFAULT_UNMORTGAGE_COST = 110;

    public StationField(String name, GameSession session) {
        super(session);
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

    @Override
    public void stop(Player p) {
        if(p != getOwner() && getOwner() != null) {
            List<StationField> fields = session.getGameMap().stations();
            int n = 0;
            for (StationField f : fields) {
                if (f.getOwner() == getOwner()) {
                    n++;
                }
            }
            if(n == 1){
                p.takeAway(25);
                getOwner().receive(25);
            }
            if(n == 2){
                p.takeAway(50);
                getOwner().receive(50);
            }
            if(n == 3){
                p.takeAway(100);
                getOwner().receive(100);
            }
            if(n == 4){
                p.takeAway(200);
                getOwner().receive(200);
            }
        }
    }

    public String getName() {
        return name;
    }
}
