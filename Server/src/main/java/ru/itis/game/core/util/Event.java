package ru.itis.game.core.util;

import ru.itis.game.core.Player;

import java.nio.ByteBuffer;

public class Event {
    private Player target;
    private int eventType;
    private byte[] value;

    public Event(Player target, int eventType, byte[] value) {
        this.target = target;
        this.eventType = eventType;
        this.value = value;
    }

    public Event(Player target, int eventType, int value) {
        this.target = target;
        this.eventType = eventType;
        this.value = new byte[4];
        ByteBuffer.allocate(4).putInt(value).get(this.value);
    }

    public Event(Player target, int eventType, String value) {
        this.target = target;
        this.eventType = eventType;
        this.value = value.getBytes();
    }

    public Event(Player target, int eventType) {
        this.target = target;
        this.eventType = eventType;
    }

    public Player getTarget() {
        return target;
    }

    public int getEventType() {
        return eventType;
    }

    public byte[] getValue() {
        return value;
    }
}
