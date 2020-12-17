package ru.itis.game.client;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Client extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private WebView webView;
    private WebEngine webEngine;
    private Label status;
    private HBox tools;
    private Button back;
    private Scene webPage;
    private Scene historyPage;

    @Override
    public void start(Stage stage) throws Exception {
        webView = new WebView();
        status = new Label();
        webEngine = webView.getEngine();

        tools = new HBox();

        webEngine.load("https://yandex.ru");

        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                status.setText(newState.name());
                if (newState == Worker.State.SUCCEEDED) {
                    //stage.setTitle(webEngine.getLocation());
                    stage.setTitle(webEngine.getTitle());
                }
            }
        });

        VBox root = new VBox();
        root.getChildren().add(webView);
        root.getChildren().add(status);

        // Create the Scene
        Scene scene = new Scene(root);
        // Add  the Scene to the Stage
        stage.setScene(scene);
        // Display the Stage
        stage.show();
    }
}
