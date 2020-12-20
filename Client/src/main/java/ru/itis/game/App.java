package ru.itis.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.itis.game.client.Connection;
import ru.itis.game.protocol.Protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("ready"));
        this.stage = stage;
        this.stage.setMinWidth(1000);
        this.stage.setMinHeight(680);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws UnknownHostException {
        Connection connection = new Connection(InetAddress.getLocalHost(), Protocol.PORT);
        launch();
    }

}