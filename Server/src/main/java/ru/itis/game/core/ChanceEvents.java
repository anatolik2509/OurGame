package ru.itis.game.core;

import ru.itis.game.core.fields.MapField;
import ru.itis.game.core.fields.PurchasableField;
import ru.itis.game.core.fields.StreetField;

import java.util.List;

public enum ChanceEvents {
    GO_TO_GUEST_COMPLEX((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 39, true);
    }),
    GO_TO_START((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 0, true);
    }),
    PRISON_RELEASE((s, target) -> {
        target.addRelease();
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
    }),
    GO_TO_RESTAURANT((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 24, true);
    }),
    SPEED_FINE((s, target) -> {
        target.takeAway(15);
    }),
    DEPT_REPAYMENT((s, target) -> {
        target.receive(150);
    }),
    ARREST((s, target) -> {
        s.getGameMap().arrestPlayer(target);
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
    }),
    GO_TO_NORTH_S_P((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 15, true);
    }),
    DIVIDENTS((s, target) -> {
        target.receive(50);
    }),
    DRUNK_DRIVE((s, target) -> {
        target.takeAway(20);
    }),
    DRIVE_COURSES((s, target) -> {
        target.takeAway(150);
    }),
    CHECKMATES((s, target) -> {
        target.receive(100);
    }),
    GO_TO_AQUAPARK((s, target) -> {
        s.getGameMap().moveDirectlyPlayer(target, 6, true);
    }),
    MOVE_BACK((s, target) -> {
        s.getGameMap().movePlayer(target,-3);
    });

    private final GameEvent event;

    ChanceEvents(GameEvent e){
        this.event = e;
    }

    public GameEvent getEvent() {
        return event;
    }
}
