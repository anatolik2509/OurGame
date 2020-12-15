package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

public abstract class PurchasableField extends MapField {



    private Player owner;

    private boolean isMortgaged;

    public PurchasableField(GameSession session) {
        super(session);
    }

    public void purchase(Player p){
        if(p.pay(getCost())){
            p.addField(this);
        }
    }

    public void mortgage(Player p){
        if(!isMortgaged){
            isMortgaged = true;
            p.receive(getMortgageCost());
        }
    }

    public void unmortgage(Player p){
        if(isMortgaged){
            if(p.pay(getUnmortgageCost())){
                isMortgaged = false;
            }
        }
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
