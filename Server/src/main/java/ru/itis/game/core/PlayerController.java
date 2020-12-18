package ru.itis.game.core;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;
import ru.itis.game.core.fields.MapField;
import ru.itis.game.core.fields.PurchasableField;
import ru.itis.game.core.fields.StreetField;

public class PlayerController {
    private Player player;
    private GameSession session;

    public PlayerController(Player player, GameSession session) {
        this.player = player;
        this.session = session;
    }

    public void rollDices(){
        if(player != session.playersTurn()) return;
        session.rollDices();
    }

    public void exit(){
        if(player != session.playersTurn()) return;
        session.removePlayer(player);
    }

    public void endTurn(){
        if(player != session.playersTurn()) return;
        if(player.getBalance() < 0) return;
        session.getGameMap().notifyAll();
    }

    public void usePrisonRelease(){
        if(player != session.playersTurn()) return;
        player.useRelease();
    }

    public void purchaseField(){
        if(player != session.playersTurn()) return;
        session.getGameMap().purchaseField(player);
    }

    public void mortgageField(MapField f){
        if(player != session.playersTurn()) return;
        if(f instanceof PurchasableField){
            ((PurchasableField) f).mortgage(player);
        }
    }

    public void unmortgageField(MapField f){
        if(player != session.playersTurn()) return;
        if(f instanceof PurchasableField){
            ((PurchasableField) f).mortgage(player);
        }
    }

    public void build(MapField f){
        if(f instanceof StreetField){
            ((StreetField) f).build(player);
        }
    }

    public void remove(MapField f){
        if(f instanceof StreetField){
            ((StreetField) f).remove(player);
        }
    }

    public void sendOffer(Player to){

    }

    //todo
}
