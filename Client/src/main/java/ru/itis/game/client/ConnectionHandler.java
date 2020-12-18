package ru.itis.game.client;

import ru.itis.game.protocol.Action;
import ru.itis.game.protocol.ProtocolInputStream;

import java.io.IOException;

import static ru.itis.game.protocol.Protocol.*;

public class ConnectionHandler implements Runnable {
    private ProtocolInputStream inputStream;
    private Connection connection;

    public ConnectionHandler(Connection connection, ProtocolInputStream inputStream) {
        this.connection = connection;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        try {
            Action action;
            while ((action = inputStream.readAction()) != null) {
                switch (action.getType()) {
                    case GO_TO_PRISON: {

                    }
                    case PUBLIC_TREASURY: {

                    }
                    case BALANCE_CHANGE: {

                    }
                    case READY: {

                    }
                    case CANCEL_READY: {

                    }
                    case SET_CHARACTER: {

                    }
                    case REMOVE_PLAYER: {

                    }
                    case SET_NICKNAME: {

                    }
                    case NOT_CHOSEN_CHARACTER: {

                    }
                    case CHARACTER_IS_OCCUPIED: {

                    }
                    case READY_RESPONSE: {

                    }
                    case CANCEL_READY_RESPONSE: {

                    }
                    case ADD_PLAYER: {

                    }
                    case SET_NICKNAME_RESPONSE: {

                    }
                    case GAME_STARTED: {

                    }
                    case ROLL_DICES: {

                    }
                    case DICES_RESPONSE: {

                    }
                    case OFFER_RESPONSE: {

                    }
                    case SEND_OFFER: {

                    }
                    case PRISON_RELEASE: {

                    }
                    case MOVE: {

                    }
                    case GO_TO: {

                    }
                    case RENT: {

                    }
                    case BUILD: {

                    }
                    case  REMOVE: {

                    }
                    case SET_OWNER: {

                    }
                    case BUILD_RESPONSE: {

                    }
                    case REMOVE_RESPONSE: {

                    }
                    case LAY_DOWN_RESPONSE: {

                    }
                    case BUY_BACK_RESPONSE: {

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
