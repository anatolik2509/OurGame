package ru.itis.game.core.util;

import java.util.ArrayList;
import java.util.List;

public class GameEventManager {
    private List<GameListener> gameListenerList;

    public GameEventManager() {
        gameListenerList = new ArrayList<>();
    }

    public void addGameListener(GameListener gameListener) {
        gameListenerList.add(gameListener);
    }

    public void activate(Event event) {
        for (GameListener listener: gameListenerList) {
            listener.activate(event);
        }
    }
}
