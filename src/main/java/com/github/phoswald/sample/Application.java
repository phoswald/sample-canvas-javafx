package com.github.phoswald.sample;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("sample-javafx is starting");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("/fxml/application.fxml"));
        stage.setResizable(false);
        stage.setTitle("sample-canvas-javafx");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
