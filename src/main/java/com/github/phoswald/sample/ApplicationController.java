package com.github.phoswald.sample;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.phoswald.sample.builders.ImageFactory;
import com.github.phoswald.sample.builders.MandelbrotImageFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

public class ApplicationController implements Initializable {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ImageFactory imageBuilder = new MandelbrotImageFactory(); 

    @FXML
    private Button clearButton;

    @FXML
    private Button drawButton;

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearButton.setOnAction(this::onClear);
        drawButton.setOnAction(this::onDraw);
    }

    private void onClear(ActionEvent event) {
        logger.info("Clearing.");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void onDraw(ActionEvent event) {
        logger.info("Drawing...");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(imageBuilder.createImage(width, height), 0, 0, width, height);
        logger.info("Drawing complete.");
    }
}
