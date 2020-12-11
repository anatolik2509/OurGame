package ru.itis.game.core.fields;

import ru.itis.game.core.GameEvent;

public enum CommunityChestCard {
    //todo cards
    ;

    private final GameEvent event;

    CommunityChestCard(GameEvent e){
        this.event = e;
    }

    public GameEvent getEvent() {
        return event;
    }
}
