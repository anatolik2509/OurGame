package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

public class StartField extends MapField {
    public StartField(GameSession session) {
        super(session);
    }

    @Override
    public void stop(Player p) {

    }
}
