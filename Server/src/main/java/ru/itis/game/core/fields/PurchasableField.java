package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;
import ru.itis.game.core.util.Event;
import ru.itis.game.protocol.Protocol;


public abstract class PurchasableField extends MapField {

    private Player owner;

    private boolean isMortgaged;

    public PurchasableField(GameSession session) {
        super(session);
    }

    public void purchase(Player p){
        if(owner != null){
            return;
        }
        if(p.pay(getCost())){
            p.addField(this);
        }
        session.initEvent(new Event(p, Protocol.SET_OWNER, session.getGameMap().fieldIndex(this)));
    }

    public void mortgage(Player p){
        if (p != owner){
            return;
        }
        if(!isMortgaged){
            isMortgaged = true;
            p.receive(getMortgageCost());
        }
        session.initEvent(new Event(p, Protocol.LAY_DOWN_RESPONSE, session.getGameMap().fieldIndex(this)));
    }

    public void unmortgage(Player p){
        if (p != owner){
            return;
        }
        if(isMortgaged){
            if(p.pay(getUnmortgageCost())){
                isMortgaged = false;
            }
        }
        session.initEvent(new Event(p, Protocol.BUY_BACK_RESPONSE, session.getGameMap().fieldIndex(this)));
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
