package ru.itis.game.core;

public interface GameEvent {
    void invoke(GameSession s, Player target);
}
