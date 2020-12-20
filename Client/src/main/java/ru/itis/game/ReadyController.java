package ru.itis.game;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.itis.game.client.Connection;
import ru.itis.game.protocol.Action;
import ru.itis.game.protocol.Protocol;

public class ReadyController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField setCharacterField;

    @FXML
    private void readyClick() throws IOException {
        Connection.getInstance().writeAction(new Action(Protocol.READY));
    }

    @FXML
    public void enterName() {
        System.out.println("Enter name");
        try {
            Connection.getInstance().writeAction(new Action(Protocol.SET_NICKNAME,
                    nameField.getText().getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            System.out.println("Some error");
        }

    }

    @FXML
    public void setCharacter() {
        System.out.println("Enter char");
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(4);
            Connection.getInstance().writeAction(new Action(Protocol.SET_CHARACTER,
                    byteBuffer.putInt(Integer.parseInt(setCharacterField.getText())).array()));
        }
        catch (IOException e) {
            System.out.println("Some error");
        }
    }
}