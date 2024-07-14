package PaooGame.Tiles;

import java.awt.image.BufferedImage;

import PaooGame.Graphics.Assets;

/**
 * Abstractizeaza notiunea de dala de tip iarba.
 */
public class GrassTile extends Tile {

    /**
     * Constructorul de initializare al clasei.
     *
     * @param id Id-ul dalei util in desenarea hartii.
     */
    public GrassTile(int id) {
        super(selectAsset(id), id); // Call to super constructor must be the first statement
    }

    /**
     * Metoda care selecteaza asset-ul corespunzator in functie de id.
     *
     * @param id Id-ul dalei pentru care se selecteaza asset-ul.
     * @return Asset-ul corespunzator id-ului.
     */
    private static BufferedImage selectAsset(int id) {
        switch (id) {
            case 1:
                return Assets.lv1GrassHStraight;
            case 2:
                return Assets.lv1GrassVStraightL;
            case 3:
                return Assets.lv1GrassVStraightR;
            case 4:
                return Assets.lv1GrassOCR;
            case 5:
                return Assets.lv1GrassOCL;
            case 6:
                return Assets.lv1GrassICR;
            case 7:
                return Assets.lv1GrassICL;
            case 21:
                return Assets.lv2GrassHStraight;
            case 22:
                return Assets.lv2GrassOCL;
            case 23:
                return Assets.lv2GrassOCR;
            case 37:
                return Assets.lv3DirtHStraight;
            case 38:
                return Assets.lv3DirtToStone;
            case 39:
                return Assets.lv3DirtToGrass;
            case 40:
                return Assets.lv3GrassToDirt;
            case 41:
                return Assets.lv3GrassLU;
            case 42:
                return Assets.lv3GrassLUU;
            case 43:
                return Assets.lv3GrassHStraight;
            case 44:
                return Assets.lv3GrassUUR;
            case 45:
                return Assets.lv3GrassUR;
            // Add more cases for other grass tile variations if needed
            default:
                // Handle default case (e.g., return a default asset or throw an exception)
                return null;
        }
    }

    /**
     * Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid() {
        return true; // Grass tiles are typically not solid
    }
}
