package ru.itis.game.core.fields;

import ru.itis.game.core.GameEvent;

public enum ChanceCard {
    //todo cards
    ;

    private final GameEvent event;

    ChanceCard(GameEvent e){
        this.event = e;
    }

    public GameEvent getEvent() {
        return event;
    }
}
