package ru.itis.game.core.fields;

import ru.itis.game.client.Connection;
import ru.itis.game.core.Player;


public abstract class PurchasableField extends MapField {

    private Player owner;

    private boolean isMortgaged;

    public void purchase(Player p){

    }

    public void mortgage(Player p){

    }

    public void unmortgage(Player p){

    }

    public abstract int getCost();

    public abstract int getMortgageCost();

    public abstract int getUnmortgageCost();

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }
}
