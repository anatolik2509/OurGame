package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

public class PrisonField extends MapField{

    public PrisonField(GameSession session) {
        super(session);
    }

    @Override
    public void stop(Player p) {
        //nothing
    }
}
