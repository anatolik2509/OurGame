package ru.itis.game.protocol;

public class Protocol {
    public static final byte SEND_ERROR = 0;
    public static final byte START_OF_TURN = 1;
    public static final byte END_OF_TURN = 2;
    public static final byte PURCHASE = 3;
    public static final byte SALE = 4;
    public static final byte LAY_DOWN = 5;
    public static final byte BUY_BACK = 6;
    public static final byte GO_TO_PRISON = 7;
    public static final byte CHANCE = 8;
    public static final byte PUBLIC_TREASURY = 9;
    public static final byte BALANCE_CHANGE = 10;
    public static final byte READY = 11;
    public static final byte CANCEL_READY = 12;
    public static final byte SET_CHARACTER = 13;
    public static final byte REMOVE_PLAYER = 14;
    public static final byte SET_NICKNAME = 15;
    public static final byte NOT_CHOSEN_CHARACTER = 16;
    public static final byte CHARACTER_IS_OCCUPIED = 17;
    public static final byte READY_RESPONSE = 18;
    public static final byte CANCEL_READY_RESPONSE = 19;
    public static final byte SET_NICKNAME_RESPONSE = 20;
    public static final byte ADD_PLAYER = 21;
    public static final byte GAME_STARTED = 22;
    public static final byte ROLL_DICES = 23;
    public static final byte DICES_RESPONSE = 24;
    public static final byte SEND_OFFER = 25;
    public static final byte OFFER_RESPONSE = 26;
    public static final byte USE_PRISON_RELEASE = 27;
    public static final byte PRISON_RELEASE = 28;
    public static final byte MOVE = 29;
    public static final byte GO_TO = 30;
    public static final byte RENT = 31;
    public static final byte BUILD = 32;
    public static final byte REMOVE = 33;
    public static final byte SET_OWNER = 34;
    public static final byte BUILD_RESPONSE = 35;
    public static final byte REMOVE_RESPONSE = 36;
    public static final byte LAY_DOWN_RESPONSE = 37;
    public static final byte BUY_BACK_RESPONSE = 38;
    public static final byte NEXT_TURN = 39;
    public static final byte ADD_PRISON_RELEASE = 40;
    public static final byte ADD_ID = 41;

    public static final int MAX_ACTION_LENGTH = 255;
    public static final int PORT = 6666;
}
