package com.github.phoswald.sample;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.phoswald.sample.builders.MandelbrotBuilder;
import com.github.phoswald.sample.builders.SpectrumBuilder;
import com.github.phoswald.sample.export.ImageExporter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ApplicationController implements Initializable {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ImageExporter imageExporter = new ImageExporter();
    private final MandelbrotBuilder mandelbrot = new MandelbrotBuilder(512, 512);

    @FXML
    private Button clearButton;

    @FXML
    private Button drawCanvasButton;

    @FXML
    private Button updateImageButton;

    @FXML
    private Canvas canvas;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearButton.setOnAction(this::onClear);
        drawCanvasButton.setOnAction(this::onDrawCanvas);
        updateImageButton.setOnAction(this::onUpdateImage);
        imageView.setImage(mandelbrot.getImage());
    }

    private void onClear(ActionEvent event) {
        logger.info("Clearing.");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void onDrawCanvas(ActionEvent event) {
        logger.info("Drawing canvas...");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();
        Image image = new SpectrumBuilder().createImage(width, height);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(image, 0, 0, width, height);
        logger.info("Drawing complete.");
        imageExporter.export(image);
    }

    private void onUpdateImage(ActionEvent event) {
        logger.info("Updating image...");
        mandelbrot.updateImage();
        logger.info("Updating complete.");
        imageExporter.export(mandelbrot.getImage());
    }
}
