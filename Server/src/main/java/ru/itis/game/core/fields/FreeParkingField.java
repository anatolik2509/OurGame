package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

public class FreeParkingField extends MapField{
    public FreeParkingField(GameSession session) {
        super(session);
    }

    @Override
    public void stop(Player p) {

    }
}
