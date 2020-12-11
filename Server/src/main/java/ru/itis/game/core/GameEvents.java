package ru.itis.game.core;

public enum GameEvents {
    ;

    private final GameEvent event;

    GameEvents(GameEvent e){
        this.event = e;
    }

    public GameEvent getEvent() {
        return event;
    }
}
