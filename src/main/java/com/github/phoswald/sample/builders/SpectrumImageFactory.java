package com.github.phoswald.sample.builders;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SpectrumImageFactory implements ImageFactory {

    @Override
    public Image createImage(int width, int height) {
        WritableImage image = new WritableImage(width, height);
        PixelWriter writer = image.getPixelWriter();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                writer.setColor(x, y, Color.rgb(x & 0xFF, y & 0xFF, (x + y) & 0xFF));
            }
        }
        return image;
    }
}
