package com.booleanuk.simpleapi.models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import static java.lang.Math.min;

public class ImageManager {
    private int minLength;
    private int minHeight;
    private int minWidth;
    private int[] rawColors;
    private byte[] processedColors;
    private Image imageA;
    private Image imageB;

    public ImageManager(Image imageA, Image imageB) {
        this.imageA = imageA;
        this.imageB = imageB;
        this.minLength = min(imageA.getPixels().length, imageB.getPixels().length);
        this.minHeight = min(imageA.getHeight(), imageB.getHeight());
        this.minWidth = min(imageA.getWidth(), imageB.getWidth());
        this.rawColors = new int[minLength];
        this.processedColors = new byte[rawColors.length / 3];
    }

    //This method processes the colors to be in the correct format because .setRGB requires it
    public void processColors() {
        for (int i = 0; i < processedColors.length; i++) {
            int blue = rawColors[i * 3] & 0xFF;
            int green = rawColors[i * 3 + 1] & 0xFF;
            int red = rawColors[i * 3 + 2] & 0xFF;
            processedColors[i] = (byte) ((red << 16) | (green << 8) | blue);
        }
    }

    public int handleSignedInts(int number) {
        if (number < 0) {
            return number + 256;
        }
        return number;
    }

    //get average value of each RGB value of the two images
    public void getAverage() {
        for (int i = 0; i < minLength; i++) {
            int value = (int) ((handleSignedInts(imageA.getPixels()[i]) + handleSignedInts(imageB.getPixels()[i])) * 0.5);
            rawColors[i] = value;
        }

    }

    public Image makeImage() {
        this.getAverage();
        this.processColors();

        //This should probably be done on the frontend
        //BufferedImage avgImage = new BufferedImage(minWidth, minHeight, BufferedImage.TYPE_INT_RGB);
        //avgImage.setRGB(0, 0, minWidth, minHeight, processedColors, 0, minWidth);
        System.out.println("Image created!");
        return new Image(minHeight, minWidth, processedColors);
    }

   public void writeImage(BufferedImage image) {
        try {
            ImageIO.write(image, "PNG", new java.io.File("averaged_image.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

}
