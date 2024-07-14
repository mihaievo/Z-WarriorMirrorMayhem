package PaooGame.LevelBackgrounds;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelBackground {

    protected BufferedImage img;

    /**
     * Constructor pentru a inițializa fundalul nivelului cu o imagine specifică.
     *
     * @param img Imaginea de fundal pentru nivel.
     */
    public LevelBackground(BufferedImage img) {
        this.img = img;
    }

    /**
     * Desenează fundalul pe contextul grafic.
     *
     * @param g Grafica pe care se va desena.
     * @param x Coordonata x unde trebuie desenat fundalul.
     * @param y Coordonata y unde trebuie desenat fundalul.
     */
    public void draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, null);
    }
}
