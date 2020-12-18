package ru.itis.game.server;

import ru.itis.game.core.GameSession;
import ru.itis.game.core.Player;
import ru.itis.game.core.PlayerController;
import ru.itis.game.core.util.Event;
import ru.itis.game.protocol.Action;
import ru.itis.game.protocol.ProtocolInputStream;
import ru.itis.game.protocol.ProtocolOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

import static ru.itis.game.protocol.Protocol.*;

public class Connection extends Thread {
    private final Socket socket;
    private final Server server;
    private Player player;
    private PlayerController playerController;
    private GameSession gameSession;
    private ProtocolInputStream inputStream;
    private ProtocolOutputStream outputStream;

    public Connection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        try {
            inputStream = new ProtocolInputStream(socket.getInputStream());
            outputStream = new ProtocolOutputStream((socket.getOutputStream()));
        } catch (IOException e){
            server.removeConnection(this);
        }
        server.addGameListener(event -> {
            try {
                byte[] bytes = new byte[event.getValue().length + 4];
                ByteBuffer byteBuffer = ByteBuffer.allocate(event.getValue().length + 4);
                byteBuffer.putInt(event.getTarget() == null ? 0:event.getTarget().getId());
                byteBuffer.put(event.getValue());
                byteBuffer.get(bytes);  
                outputStream.writeAction(new Action(event.getEventType(), bytes));
            } catch (IOException e) {
                server.removeConnection(this);
            }
        });
    }

    @Override
    public void run() {
        try {
            Action action;
            while ((action = inputStream.readAction()) != null){
                switch (action.getType()){
                    case READY :{
                        if(server.getState() != Server.STARTING){
                            outputStream.writeAction(new Action(SEND_ERROR));
                        }
                        else {
                            if(player.getCharacter() == -1){
                                outputStream.writeAction(new Action(NOT_CHOSEN_CHARACTER));
                            }
                            else {
                                server.setReady(this, true);
                                server.initEvent(new Event(player, READY_RESPONSE));
                                server.startGame();
                            }
                        }
                        break;
                    }
                    case CANCEL_READY: {
                        if (server.getState() != Server.STARTING) {
                            outputStream.writeAction(new Action(SEND_ERROR));
                        } else {
                            server.setReady(this, false);
                            server.initEvent(new Event(player, CANCEL_READY_RESPONSE));
                        }
                        break;
                    }
                    case SET_CHARACTER:{
                        if(server.getState() != Server.STARTING){
                            outputStream.writeAction(new Action(SEND_ERROR));
                        }
                        else {
                            ByteBuffer buffer = ByteBuffer.wrap(action.getData());
                            int character = buffer.getInt();
                            if (server.isCharacterOccupied(character)) {
                                outputStream.writeAction(new Action(CHARACTER_IS_OCCUPIED));
                            }
                            else {
                                player.setCharacter(character);
                                server.initEvent(new Event(player, SET_CHARACTER, character));
                            }
                        }
                        break;
                    }
                    case SET_NICKNAME:{
                        if(server.getState() != Server.STARTING){
                            outputStream.writeAction(new Action(SEND_ERROR));
                        }
                        else {
                            player.setNickName(action.getDataAsString());
                            server.initEvent(new Event(player, SET_NICKNAME_RESPONSE, player.getNickName()));
                        }
                    }
                    case REMOVE_PLAYER:{
                        server.removeConnection(this);
                        if(playerController != null){
                            playerController.exit();
                        }
                        socket.close();
                        break;
                    }
                    case ROLL_DICES:{
                        if(server.getState() != Server.GAME){
                            outputStream.writeAction(new Action(SEND_ERROR));
                        }
                        else {
                            playerController.rollDices();
                        }
                    }
                    case PURCHASE:{
                        if(server.getState() != Server.GAME){
                            outputStream.writeAction(new Action(SEND_ERROR));
                        }
                        playerController.purchaseField();
                    }
                    case LAY_DOWN:{
                        if(server.getState() != Server.GAME){
                            outputStream.writeAction(new Action(SEND_ERROR));
                        }
                        ByteBuffer buffer = ByteBuffer.wrap(action.getData());
                        int field = buffer.getInt();
                        playerController.mortgageField(gameSession.getGameMap().field(field));
                    }
                    case BUY_BACK:{
                        if(server.getState() != Server.GAME){
                            outputStream.writeAction(new Action(SEND_ERROR));
                        }
                        ByteBuffer buffer = ByteBuffer.wrap(action.getData());
                        int field = buffer.getInt();
                        playerController.unmortgageField(gameSession.getGameMap().field(field));
                    }
                    case BUILD:{
                        if(server.getState() != Server.GAME){
                            outputStream.writeAction(new Action(SEND_ERROR));
                        }
                        ByteBuffer buffer = ByteBuffer.wrap(action.getData());
                        int field = buffer.getInt();
                        playerController.build(gameSession.getGameMap().field(field));
                    }
                    case REMOVE:{
                        if(server.getState() != Server.GAME){
                            outputStream.writeAction(new Action(SEND_ERROR));
                        }
                        ByteBuffer buffer = ByteBuffer.wrap(action.getData());
                        int field = buffer.getInt();
                        playerController.remove(gameSession.getGameMap().field(field));
                    }
                }
            }
        } catch (IOException e) {
            server.removeConnection(this);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public void sendAction(Action a) throws IOException {
        outputStream.writeAction(a);
    }
}
