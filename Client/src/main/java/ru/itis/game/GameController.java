package ru.itis.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Callback;
import javafx.util.Duration;
import ru.itis.game.client.Connection;
import ru.itis.game.core.GameMap;
import ru.itis.game.core.Player;
import ru.itis.game.core.fields.PurchasableField;
import ru.itis.game.core.fields.StationField;
import ru.itis.game.core.fields.StreetField;
import ru.itis.game.core.fields.UtilityField;
import ru.itis.game.protocol.Action;
import ru.itis.game.protocol.Protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {

    private static GameController instance;
    private Connection connection;
    private Player currentPlayer;
    private Map<Player, PlayerIcon> playerToPlayerIcon;
    private Map<Player, Integer> playerToPlayerPosition;
    private GameMap gameMapCore;

    public static GameController getInstance() {
        return instance;
    }

    public void setGameMapCore(GameMap gameMapCore) {
        this.gameMapCore = gameMapCore;
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
    private FlowPane playersList;

    @FXML
    private ListView<String> systemListView;

    @FXML
    private TableView<PropItem> clientPropList;

    @FXML
    private TableColumn<PropItem, String> clientPropColors;

    @FXML
    private TableColumn<PropItem, String> clientPropTitles;

    @FXML
    private TableColumn<PropItem, Integer> clientPropRents;

    @FXML
    private TableColumn<PropItem, Integer> clientPropPrices;

    @FXML
    private void initialize() {
        instance = this;
//        connection = Connection.getInstance();
//        gameMapCore = connection.getGameMap();
//        currentPlayer = gameMapCore.getClientPlayer();
//        fillPlayers(connection.getPlayers());
//        addPlayersToMap(connection.getPlayers());
//        setNickname(gameMapCore.getClientPlayer().getNickName());
//        changeBalance(gameMapCore.getClientPlayer().getBalance());

        clientPropColors.setCellValueFactory(new PropertyValueFactory<>("color"));
        clientPropTitles.setCellValueFactory(new PropertyValueFactory<>("title"));
        clientPropRents.setCellValueFactory(new PropertyValueFactory<>("rent"));
        clientPropPrices.setCellValueFactory(new PropertyValueFactory<>("price"));

        List<Player> players = new ArrayList<>();
        Player p1 = new Player(1, 1, "Adel");
        Player p2 = new Player(2, 2, "Ruslan");
        Player p3 = new Player(3, 3, "Tolya");
        players.add(p1);
        players.add(p2);
        players.add(p3);

        fillPlayers(players);
        addPlayersToMap(players);

        int dice1 = 5;
        int dice2 = 6;
        addSystemInfo(String.format("Вам выпало... %d и %d. Вы двигаетесь на %d.", dice1, dice2, dice1 + dice2));
        addSystemInfo(String.format("Ход игрока %s", p2.getNickName()));

        addClientProp(new StreetField(StreetField.Street.ARBAT_STREET));
        addClientProp(new StreetField(StreetField.Street.TVERSKAYA_STREET));

    }

    @FXML
    private void onDiceClick(MouseEvent event) {

        try {
            connection.writeAction(new Action(Protocol.ROLL_DICES));
        } catch (IOException e) {
            nicknameLabel.setText("Не получилось");
            System.exit(-1);
        }
    }

    public void changeBalance(int balance) {
        moneyLabel.setText(Integer.toString(balance));
    }

    public void setNickname(String nickname) {
        nicknameLabel.setText(nickname);
    }

    public void movePlayer(Player p, int steps) {
        PlayerIcon currentPlayerIcon = playerToPlayerIcon.get(p);
        int currentPlayerPosition = playerToPlayerPosition.get(p);

        Timeline timeline = new Timeline();
        AtomicInteger i = new AtomicInteger();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(750), event -> {
            if (i.get() == steps) {
                timeline.stop();
            } else {
                gameMap.getChildren().remove(currentPlayerIcon);
                int[] gridCoords = calculateGridCoords(currentPlayerPosition + i.get() + 1);
                gameMap.add(currentPlayerIcon, gridCoords[0], gridCoords[1]);
                i.getAndIncrement();
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(steps);
        timeline.play();

        playerToPlayerPosition.put(p, currentPlayerPosition + steps);
    }

    public void goTo(Player p, int position) {
        PlayerIcon currentPlayerIcon = playerToPlayerIcon.get(p);

        gameMap.getChildren().remove(currentPlayerIcon);
        int[] coords = calculateGridCoords(position);
        gameMap.add(currentPlayerIcon, coords[0], coords[1]);

        playerToPlayerPosition.put(p, position);
    }

    public void addSystemInfo(String msg) {
        systemListView.getItems().add(msg);
    }

    public void addClientProp(PurchasableField pf) {

        PropItem propItem;

        if (pf instanceof StreetField) {
            StreetField sf = (StreetField) pf;
            propItem = new PropItem(getColorByCode(sf.getColor()), sf.getStreet().getName(), sf.getStreet().getRent()[sf.getLevel()], sf.getStreet().getCost());
            System.out.println(propItem.toString());
        } else if (pf instanceof StationField) {
            StationField sf = (StationField) pf;
            propItem = new PropItem(getColorByCode(-1), sf.getName(), 50, sf.getCost());
        } else {
            UtilityField uf = (UtilityField) pf;
            propItem = new PropItem(getColorByCode(-1), uf.getName(), 0, uf.getCost());
        }

        clientPropList.getItems().add(propItem);

    }

    private void fillPlayers(List<Player> players) {
        playersList.setVgap(10);
        playersList.setHgap(20);

        for (Player p : players) {
            playersList.getChildren().add(new PlayerPane(p));
        }
    }

    private void addPlayersToMap(List<Player> players) {

        playerToPlayerIcon = new HashMap<>();
        playerToPlayerPosition = new HashMap<>();

        for(Player p : players) {
            PlayerIcon icon = new PlayerIcon(p);
            playerToPlayerIcon.put(p, icon);
            playerToPlayerPosition.put(p, 0);
            gameMap.add(icon ,10 , 10);
        }
    }

    private int[] calculateGridCoords(int position) {

        if (position < 10) {
            return new int[]{10 - position % 10, 10};
        } else if (position < 20) {
            return new int[]{0, 10 - position % 10};
        } else if (position < 30) {
            return new int[]{position % 10, 0};
        } else {
            return new int[]{10, position % 10};
        }

    }

    public class PropItem {
        private String color;
        private String title;
        private int rent;
        private int price;

        @Override
        public String toString() {
            return "PropItem{" +
                    "color=" + color +
                    ", title='" + title + '\'' +
                    ", rent=" + rent +
                    ", price=" + price +
                    '}';
        }

        public PropItem(String color, String title, int rent, int price) {
            this.color = color;
            this.title = title;
            this.rent = rent;
            this.price = price;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getRent() {
            return rent;
        }

        public void setRent(int rent) {
            this.rent = rent;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    private String getColorByCode(int code) {
        switch (code) {
            case -1:
                return "white";
            case 0:
                return "brown";
            case 1:
                return "yellow";
            case 2:
                return "green";
            case 3:
                return "gray";
            case 4:
                return "red";
            case 5:
                return "pink";
            case 6:
                return "blue";
            case 7:
                return "cyan";
            default:
                return "white";
        }
    }

    private class PlayerIcon extends ImageView {

        public PlayerIcon(Player p) {
            setImage(new Image("/images/player" + (p.getCharacter() - 1) + ".png"));
            setFitWidth(35);
            setFitHeight(35);
        }

    }

    private class PlayerPane extends VBox {

        public PlayerPane(Player p) {
            Button button = new Button();
            Label label = new Label();
            label.setAlignment(Pos.CENTER);

            button.setStyle(new StringBuilder().append("-fx-background-image: url(/images/player").
                                                append(p.getCharacter() - 1).
                                                append(".png); -fx-pref-width: 70px; -fx-pref-height: 70px; -fx-background-size: cover;").toString());
            label.setText(p.getNickName());

            getChildren().addAll(button, label);
        }

    }
}
