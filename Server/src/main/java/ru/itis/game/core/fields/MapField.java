package ru.itis.game.core.fields;

import ru.itis.game.core.Player;

public abstract class MapField {
    //invokes when player stops on this field
    public abstract void stop(Player p);
}
