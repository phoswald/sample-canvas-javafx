package com.github.phoswald.sample.builders;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class MandelbrotImageFactory implements ImageFactory {

    private final double ptMinReal = -2.5;
    private final double ptMinImag = 2.5;
    private final double ptMaxReal = 2.5;
    private final double ptMaxImag = -2.5;
    private final int iterMax = 20;

    @Override
    public Image createImage(int width, int height) {
        WritableImage image = new WritableImage(width, height);
        PixelWriter writer = image.getPixelWriter();
        double deltaX = (ptMaxReal - ptMinReal) / width;
        double deltaY = (ptMaxImag - ptMinImag) / height;
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                double iterReal = 0.0;
                double iterImag = 0.0;
                double ptReal = ptMinReal + x * deltaX;
                double ptImag = ptMinImag + y * deltaY;
                int iterCur = 0;
                while(iterReal * iterReal + iterImag * iterImag <= 4.0 && iterCur < iterMax) {
                    double iterNextReal = iterReal * iterReal - iterImag * iterImag + ptReal;
                    double iterNextImag = 2 * iterReal * iterImag + ptImag;
                    iterReal = iterNextReal;
                    iterImag = iterNextImag;
                    iterCur++;
                }
                if(iterCur == iterMax) {
                    writer.setColor(x, y, Color.BLACK);
                } else {
                    writer.setColor(x, y, (iterCur % 2 == 1) ? Color.WHITE : Color.BLUE);
                }
            }
        }
        return image;
    }
}
