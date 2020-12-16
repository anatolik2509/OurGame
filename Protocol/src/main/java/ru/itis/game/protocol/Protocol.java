package ru.itis.game.protocol;

public enum Protocol {
    SEND_ERROR(0),
    START_OF_TURN(1),
    END_OF_TURN(2),
    PURCHASE(3),
    SALE(4),
    LAY_DOWN(5),
    BUY_BACK(6),
    GO_TO_JAIL(7),
    CHANCE(8),
    PUBLIC_TREASURY(9),
    BALANCE_CHANGE(10);

    public static final int MAX_MESSAGE_LENGTH = 255;

    private final int messageNum;

    public int getMessageNum() {
        return messageNum;
    }

    Protocol(int messageNum) {
        this.messageNum = messageNum;
    }
}
