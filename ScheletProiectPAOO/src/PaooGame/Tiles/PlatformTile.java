package PaooGame.Tiles;

import java.awt.image.BufferedImage;

import PaooGame.Graphics.Assets;

/** Abstractizeaza notiunea de dala de tip platforma. */
public class PlatformTile extends Tile {

    /** Constructorul de initializare al clasei.
     *  @param id Id-ul dalei util in desenarea hartii.
     */
    public PlatformTile(int id) {
        super(selectAsset(id), id); // Call to super constructor must be the first statement
    }

    /** Metoda care selecteaza asset-ul corespunzator in functie de id.
     *  @param id Id-ul dalei pentru care se selecteaza asset-ul.
     *  @return Asset-ul corespunzator id-ului.
     */
    private static BufferedImage selectAsset(int id) {
        switch (id) {
            case 15:
                return Assets.lv1PlatformUCL;
            case 16:
                return Assets.lv1PlatformUCR;
            case 17:
                return Assets.lv1PlatformBCL;
            case 18:
                return Assets.lv1PlatformBCR;
            case 26:
                return Assets.lv2PavementHStraight;
            case 46:
                return Assets.lv3PlatformBase;
            case 47:
                return Assets.lv3PlatformConnector;
            case 48:
                return Assets.lv3PlatformHStraight;
            // Add more cases for other platform tile variations if needed
            default:
                // Handle default case (e.g., return a default asset or throw an exception)
                return null;
        }
    }

    /** Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune. */
    @Override
    public boolean IsSolid() {
        return true; // Platform tiles are typically solid (used for collision)
    }
}