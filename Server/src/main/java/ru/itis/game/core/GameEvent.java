package ru.itis.game.core;

import ru.itis.game.core.util.Event;

public interface GameEvent {

    void invoke(GameSession s, Player target);
}
