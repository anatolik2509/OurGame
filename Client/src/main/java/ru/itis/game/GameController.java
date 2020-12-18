package ru.itis.game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GameController {

    private static GameController instance;

    public static GameController getInstance() {
        return instance;
    }

    @FXML
    private GridPane gameMap;

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label moneyLabel;

    @FXML
    private Button buttonDice;

    @FXML
    private void initialize() {
        instance = this;
    }

    @FXML
    private void onDiceClick(MouseEvent event) {
    }

    public void changeBalance(int balance) {
        this.moneyLabel.setText(Integer.toString(balance));
    }
}
