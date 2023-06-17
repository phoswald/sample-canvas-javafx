package com.github.phoswald.sample.builders;

import java.nio.IntBuffer;

import javafx.scene.image.Image;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

public class MandelbrotImageFactory implements ImageFactory {
    
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLUE = 0xFF0000FF;

    private final double ptMinReal = -2.5;
    private final double ptMinImag = 2.5;
    private final double ptMaxReal = 2.5;
    private final double ptMaxImag = -2.5;
    private final int iterMax = 20;

    @Override
    public Image createImage(int width, int height) {
        int[] pixelArray = new int[width * height];
        PixelBuffer<IntBuffer> pixelBuffer = new PixelBuffer<>(
                width, height, 
                IntBuffer.wrap(pixelArray), 
                PixelFormat.getIntArgbPreInstance());
        WritableImage image = new WritableImage(pixelBuffer);
        double deltaX = (ptMaxReal - ptMinReal) / width;
        double deltaY = (ptMaxImag - ptMinImag) / height;
        for(int index = 0, y = 0; y < height; y++) {
            for(int x = 0; x < width; x++, index++) {
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
                    pixelArray[index] = BLACK;
                } else {
                    pixelArray[index] = (iterCur & 1) == 1 ? WHITE : BLUE;
                }
            }
        }
        return image;
    }
}
