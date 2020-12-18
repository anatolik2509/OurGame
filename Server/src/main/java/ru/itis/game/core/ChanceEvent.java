package ru.itis.game.core;

import ru.itis.game.core.fields.PurchasableField;
import ru.itis.game.core.fields.StreetField;
import ru.itis.game.core.util.Event;
import ru.itis.game.protocol.Protocol;

import java.util.List;

public enum ChanceEvent {
    GO_TO_GUEST_COMPLEX((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 39, true);
        s.initEvent(new Event(target, Protocol.CHANCE, 1));
    }),
    GO_TO_START((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 0, true);
        s.initEvent(new Event(target, Protocol.CHANCE, 2));
    }),
    PRISON_RELEASE((s, target) -> {
        target.addRelease();
        s.initEvent(new Event(target, Protocol.CHANCE, 3));
    }),
    STREET_REPAIR((s, target) -> {
        List<PurchasableField> domain = target.getDomain();
        int sum = 0;
        StreetField streetField;
        for(PurchasableField f : domain){
            if(f instanceof StreetField){
                streetField = (StreetField)f;
                if(streetField.getLevel() < 5){
                    sum += 40 * streetField.getLevel();
                }
                if(streetField.getLevel() == 5){
                    sum += 115;
                }
            }
        }
        target.takeAway(sum);
        s.initEvent(new Event(target, Protocol.CHANCE, 4));
    }),
    GO_TO_RESTAURANT((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 24, true);
        s.initEvent(new Event(target, Protocol.CHANCE, 5));
    }),
    SPEED_FINE((s, target) -> {
        target.takeAway(15);
        s.initEvent(new Event(target, Protocol.CHANCE, 6));
    }),
    DEPT_REPAYMENT((s, target) -> {
        target.receive(150);
        s.initEvent(new Event(target, Protocol.CHANCE, 7));
    }),
    ARREST((s, target) -> {
        s.getGameMap().arrestPlayer(target);
        s.initEvent(new Event(target, Protocol.CHANCE, 8));
    }),
    CAPITAL_REPAIR((s, target) -> {
        List<PurchasableField> domain = target.getDomain();
        int sum = 0;
        StreetField streetField;
        for(PurchasableField f : domain){
            if(f instanceof StreetField){
                streetField = (StreetField)f;
                if(streetField.getLevel() < 5){
                    sum += 25 * streetField.getLevel();
                }
                if(streetField.getLevel() == 5){
                    sum += 100;
                }
            }
        }
        target.takeAway(sum);
        s.initEvent(new Event(target, Protocol.CHANCE, 9));
    }),
    GO_TO_NORTH_S_P((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 15, true);
        s.initEvent(new Event(target, Protocol.CHANCE, 10));
    }),
    DIVIDENTS((s, target) -> {
        target.receive(50);
        s.initEvent(new Event(target, Protocol.CHANCE, 11));
    }),
    DRUNK_DRIVE((s, target) -> {
        target.takeAway(20);
        s.initEvent(new Event(target, Protocol.CHANCE, 12));
    }),
    DRIVE_COURSES((s, target) -> {
        target.takeAway(150);
        s.initEvent(new Event(target, Protocol.CHANCE, 13));
    }),
    CHECKMATES((s, target) -> {
        target.receive(100);
        s.initEvent(new Event(target, Protocol.CHANCE, 14));
    }),
    GO_TO_AQUAPARK((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 6, true);
        s.initEvent(new Event(target, Protocol.CHANCE, 15));
    }),
    MOVE_BACK((s, target) -> {
        s.getGameMap().movePlayer(target,-3);
        s.initEvent(new Event(target, Protocol.CHANCE, 16));
    });

    private final GameEvent event;

    ChanceEvent(GameEvent e){
        this.event = e;
    }

    public GameEvent getEvent() {
        return event;
    }
}
