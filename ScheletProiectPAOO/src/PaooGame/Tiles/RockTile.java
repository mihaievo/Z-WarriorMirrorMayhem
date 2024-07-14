package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/** Abstractizeaza notiunea de dala de tip munte sau piatra.
 */
public class RockTile extends Tile {

    /** Constructorul de initializare al clasei

       @param id Id-ul dalei util in desenarea hartii.
    */
    public RockTile(int id)
    {
        super(selectAsset(id), id);
    }

        /** Metoda care selecteaza asset-ul corespunzator in functie de id.
         *  @param id Id-ul dalei pentru care se selecteaza asset-ul.
         *  @return Asset-ul corespunzator id-ului.
         */
        private static BufferedImage selectAsset(int id) {
        switch(id) {
            case 8:
                return Assets.lv1StoneHStraight;
            case 9:
                return Assets.lv1StoneVStraightL;
            case 10:
                return Assets.lv1StoneVStraightR;
            case 11:
                return Assets.lv1StoneOCR;
            case 12:
                return Assets.lv1StoneOCL;
            case 13:
                return Assets.lv1StoneICR;
            case 14:
                return Assets.lv1StoneICL;
            case 24:
                return Assets.lv2StoneVStraightL;
            case 25:
                return Assets.lv2StoneVStraightR;
            case 31:
                return Assets.lv3StoneHStraight;
            case 32:
                return Assets.lv3StoneOCL;
            case 33:
                return Assets.lv3StoneOCR;
            case 34:
                return Assets.lv3StoneVStraightL;
            case 35:
                return Assets.lv3StoneVStraightR;
            case 36:
                return Assets.lv3StoneToDirt;
            default:
                // Handle default case (e.g., return a default asset or throw an exception)
                return null;
        }
    }

    /** Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid() { return true; }
}
