package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/** Abstractizeaza notiunea de dala de tip sol/pamant.
 */
public class SoilTile extends Tile
{
    /** Constructorul de initializare al clasei

        @param id Id-ul dalei util in desenarea hartii.
     */
    public SoilTile(int id)
    {
        super(selectAsset(id), id);
    }

    private static BufferedImage selectAsset(int id) {
        switch (id) {
            case 0:
                return Assets.lv1ground;
            case 20:
                return Assets.lv2ground;
            case 30:
                return Assets.lv3ground;
            // Add more cases for other grass tile variations if needed
            default:
                // Handle default case (e.g., return a default asset or throw an exception)
                return null;
        }
    }

    // nu avem nevoie de solid, gravitatia va avea coliziune doar cu grassTile
}
