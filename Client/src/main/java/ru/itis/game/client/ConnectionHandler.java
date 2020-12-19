package ru.itis.game.client;

import ru.itis.game.core.Player;
import ru.itis.game.core.fields.MapField;
import ru.itis.game.core.fields.PurchasableField;
import ru.itis.game.core.fields.StreetField;
import ru.itis.game.protocol.Action;
import ru.itis.game.protocol.ProtocolInputStream;

import java.io.IOException;
import java.nio.ByteBuffer;

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
                    case READY_RESPONSE: {
                        connection.setReady(true);
                        break;
                    }
                    case CANCEL_READY_RESPONSE: {
                        connection.setReady(false);
                        break;
                    }
                    case SET_NICKNAME_RESPONSE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        byte[] nameBytes = new byte[action.getData().length - 4];
                        byteBuffer.get(nameBytes);
                        String name = new String(nameBytes);
                        connection.getPlayerById(id).setNickName(name);
                        break;
                    }
                    case SET_CHARACTER: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        int character = byteBuffer.getInt();
                        connection.getPlayerById(id).setCharacter(character);
                        break;
                    }
                    case NOT_CHOSEN_CHARACTER: {
                        //todo create modal window
                    }
                    case CHARACTER_IS_OCCUPIED: {
                        //todo create modal window
                    }
                    case ADD_PLAYER: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        connection.getPlayers().add(new Player(byteBuffer.getInt()));
                        break;
                    }
                    case GAME_STARTED: {
                        connection.startGame();
                        break;
                    }
                    case GO_TO_PRISON: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        connection.getGameMap().getPlayerById(id).setArrested(true);
                        break;
                    }
                    case PUBLIC_TREASURY: {
                        //todo
                    }
                    case CHANCE: {
                        //todo
                    }
                    case BALANCE_CHANGE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        Player p = connection.getGameMap().getPlayerById(id);
                        int money = byteBuffer.getInt();
                        p.setBalance(money);
                        break;
                    }
                    case REMOVE_PLAYER: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        connection.removePlayer(connection.getPlayerById(id));
                        break;
                    }
                    case DICES_RESPONSE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        connection.getGameMap().setDice1(byteBuffer.getInt());
                        connection.getGameMap().setDice2(byteBuffer.getInt());
                        break;
                    }
                    case PRISON_RELEASE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        connection.getGameMap().getPlayerById(id).setArrested(false);
                        break;
                    }
                    case USE_PRISON_RELEASE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        connection.getGameMap().getPlayerById(id).useRelease();
                        break;
                    }
                    case MOVE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        Player p = connection.getGameMap().getPlayerById(id);
                        connection.getGameMap().movePlayer(p,byteBuffer.getInt());
                        break;
                    }
                    case GO_TO: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        Player p = connection.getGameMap().getPlayerById(id);
                        connection.getGameMap().moveDirectlyPlayer(p,byteBuffer.getInt());
                        break;
                    }
                    case RENT: {
                        break;
                    }
                    case SET_OWNER: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        Player p = connection.getGameMap().getPlayerById(id);
                        int index = byteBuffer.getInt();
                        MapField f = connection.getGameMap().field(index);
                        if(f instanceof PurchasableField){
                            ((PurchasableField) f).setOwner(p);
                        }
                        break;
                    }
                    case BUILD_RESPONSE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int index = byteBuffer.getInt();
                        MapField f = connection.getGameMap().field(index);
                        if(f instanceof StreetField){
                            ((StreetField) f).build();
                        }
                        break;
                    }
                    case REMOVE_RESPONSE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int index = byteBuffer.getInt();
                        MapField f = connection.getGameMap().field(index);
                        if(f instanceof StreetField){
                            ((StreetField) f).remove();
                        }
                        break;
                    }
                    case LAY_DOWN_RESPONSE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int index = byteBuffer.getInt();
                        MapField f = connection.getGameMap().field(index);
                        if(f instanceof PurchasableField){
                            ((PurchasableField) f).mortgage();
                        }
                        break;
                    }
                    case BUY_BACK_RESPONSE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int index = byteBuffer.getInt();
                        MapField f = connection.getGameMap().field(index);
                        if(f instanceof StreetField){
                            ((StreetField) f).unmortgage();
                        }
                        break;
                    }
                    case ADD_PRISON_RELEASE: {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(action.getData());
                        int id = byteBuffer.getInt();
                        Player p = connection.getGameMap().getPlayerById(id);
                        p.addRelease();
                        break;
                    }
                    case NEXT_TURN: {
                        //todo
                    }
                }
            }
        } catch (IOException e) {
            connection.closeConnection();
        }
    }
}
