package ru.itis.game.core.fields;

import ru.itis.game.core.Player;

public abstract class PurchasableField extends MapField {

    private Player owner;

    private boolean isMortgaged;

    public void purchase(Player p){
        //todo purchase
    }

    public void mortgage(Player p){
        //todo mortgage
    }

    public void unmortgage(Player p){
        //todo
    }

    public abstract int getCost();

    public abstract int getMortgageCost();

    public abstract int getUnmortgageCost();

    public Player getOwner() {
        return owner;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }
}
