package ru.itis.game.core;

import ru.itis.game.core.fields.PurchasableField;
import ru.itis.game.core.fields.StreetField;

import java.util.List;

public enum CommunityChestEvents {
    BANK_ERROR((s, target) -> {
        target.receive(150);
    }),
    STONKS((s, target) -> {
        target.receive(25);
    }),
    STONKS_2((s, target) -> {
        target.receive(25);
    }),
    PRISON_RELEASE((s, target) -> {
        target.addRelease();
    }),
    BEQUEST((s, target) -> {
        target.receive(100);
    }),
    TAX_CANCEL((s, target) -> {
        target.receive(25);
    }),
    ARREST((s, target) -> {
        s.getGameMap().arrestPlayer(target);
    }),
    INSURANCE((s, target) -> {
        target.takeAway(50);
    }),
    BEAUTY_CONCURS((s, target) -> {
        target.receive(10);
    }),
    BONDS((s, target) -> {
        target.receive(50);
    }),
    MEDIC_BILL((s, target) -> {
        target.takeAway(100);
    }),
    GO_TO_GUEST_COMPLEX((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 1, false);
    }),
    RENT((s, target) -> {
        target.receive(100);
    }),
    BIRTHDAY((s, target) -> {
        List<Player> players = s.getPlayers();
        target.receive(players.size() * 10);
        for(Player p : players){
            p.takeAway(10);
        }
    }),
    FINE((s, target) -> {
        target.takeAway(10);
    }),
    DOCTOR_BILL((s, target) -> {
        target.takeAway(50);
    });

    private final GameEvent event;

    CommunityChestEvents(GameEvent e){
        this.event = e;
    }

    public GameEvent getEvent() {
        return event;
    }
}
