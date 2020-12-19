package ru.itis.game.core.fields;

import ru.itis.game.client.Connection;
import ru.itis.game.core.Player;


public abstract class PurchasableField extends MapField {

    private Player owner;

    private boolean isMortgaged;

    public void purchase(Player p){

    }

    public void mortgage(){
        isMortgaged = true;
    }

    public void unmortgage(){
        isMortgaged = false;
    }

    public abstract int getCost();

    public abstract int getMortgageCost();

    public abstract int getUnmortgageCost();

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner.removeField(this);
        this.owner = owner;
        this.owner.addField(this);
    }
    public boolean isMortgaged() {
        return isMortgaged;
    }
}
