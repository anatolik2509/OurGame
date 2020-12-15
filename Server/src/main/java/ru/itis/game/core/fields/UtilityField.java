package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

import java.util.List;

public class UtilityField extends PurchasableField{

    private final String name;

    //todo costs
    private static final int DEFAULT_COST = 200;
    private static final int DEFAULT_MORTGAGE_COST = 200;
    private static final int DEFAULT_UNMORTGAGE_COST = 200;

    public UtilityField(String name, GameSession session) {
        super(session);
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
        if(p != getOwner() && getOwner() != null) {
            List<UtilityField> fields = session.getGameMap().utilities();
            int n = 0;
            for (UtilityField f : fields) {
                if (f.getOwner() == getOwner()) {
                    n++;
                }
            }
            if(n == 1){
                p.takeAway(4 * session.getDiceSum());
                getOwner().receive(4 * session.getDiceSum());
            }
            if(n == 2){
                p.takeAway(10 * session.getDiceSum());
                getOwner().receive(10 * session.getDiceSum());
            }
        }
    }
}
