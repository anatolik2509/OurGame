package ru.itis.game.core;

import ru.itis.game.core.util.Event;
import ru.itis.game.protocol.Protocol;

import java.util.List;

public enum CommunityChestEvent {
    BANK_ERROR((s, target) -> {
        target.receive(150);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 1));
    }),
    STONKS((s, target) -> {
        target.receive(25);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 2));
    }),
    STONKS_2((s, target) -> {
        target.receive(25);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 2));
    }),
    PRISON_RELEASE((s, target) -> {
        target.addRelease();
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 3));
    }),
    BEQUEST((s, target) -> {
        target.receive(100);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 4));
    }),
    TAX_CANCEL((s, target) -> {
        target.receive(25);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 5));
    }),
    ARREST((s, target) -> {
        s.getGameMap().arrestPlayer(target);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 6));
    }),
    INSURANCE((s, target) -> {
        target.takeAway(50);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 7));
    }),
    BEAUTY_CONCURS((s, target) -> {
        target.receive(10);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 8));
    }),
    BONDS((s, target) -> {
        target.receive(50);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 9));
    }),
    MEDIC_BILL((s, target) -> {
        target.takeAway(100);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 10));
    }),
    GO_TO_GUEST_COMPLEX((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 1, false);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 11));
    }),
    RENT((s, target) -> {
        target.receive(100);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 12));
    }),
    BIRTHDAY((s, target) -> {
        Player[] players = s.getPlayers();
        target.receive(s.playersOnline() * 10);
        for(Player p : players){
            if(p != null) {
                p.takeAway(10);
            }
        }
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 13));
    }),
    FINE((s, target) -> {
        target.takeAway(10);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 14));
    }),
    DOCTOR_BILL((s, target) -> {
        target.takeAway(50);
        s.initEvent(new Event(target, Protocol.PUBLIC_TREASURY, 15));
    });

    private final GameEvent event;

    CommunityChestEvent(GameEvent e){
        this.event = e;
    }

    public GameEvent getEvent() {
        return event;
    }
}
