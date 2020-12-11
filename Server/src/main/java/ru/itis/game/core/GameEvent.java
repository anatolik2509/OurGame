package ru.itis.game.core;

import ru.itis.game.core.util.Event;

public interface GameEvent {
    Event invoke(GameSession s, Player target);
}
