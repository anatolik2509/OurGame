package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

public class ArrestField extends MapField{

    public ArrestField(GameSession session) {
        super(session);
    }

    @Override
    public void stop(Player p) {
        session.getGameMap().arrestPlayer(p);
    }
}
