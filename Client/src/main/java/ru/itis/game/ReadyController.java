package ru.itis.game;

import java.io.IOException;
import javafx.fxml.FXML;

public class ReadyController {

    @FXML
    private void readyClick() throws IOException {
        App.setRoot("game");
    }
}