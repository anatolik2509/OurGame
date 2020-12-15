package ru.itis.game.core.fields;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;

public abstract class MapField {
    protected final GameSession session;

    public MapField(GameSession session) {
        this.session = session;
    }

    //invokes when player stops on this field
    public abstract void stop(Player p);
}
