package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.Graphics.Assets;

/** Abstractizeaza notiunea de dala de tip caracter joc. */
public class CharacterTile extends Tile {

    /** Constructorul de initializare al clasei.
     *  @param id Id-ul dalei util in desenarea hartii.
     */
    public CharacterTile(int id) {
        super(selectAsset(id), id); // Call to super constructor must be the first statement
    }

    /** Metoda care selecteaza asset-ul corespunzator in functie de id.
     *  @param id Id-ul dalei pentru care se selecteaza asset-ul.
     *  @return Asset-ul corespunzator id-ului.
     */
    private static BufferedImage selectAsset(int id) {
        switch (id) {
            case 100:
                return Assets.playerIdle0;
            case 101:
                return Assets.playerIdle1;
            case 102:
                return Assets.playerIdle2;
            case 103:
                return Assets.playerIdle3;
            case 104:
                return Assets.playerIdle4;
            case 105:
                return Assets.playerIdle5;
            case 110:
                return Assets.playerWalk0;
            case 111:
                return Assets.playerWalk1;
            case 112:
                return Assets.playerWalk2;
            case 113:
                return Assets.playerWalk3;
            case 114:
                return Assets.playerWalk4;
            case 120:
                return Assets.playerPunch0;
            case 121:
                return Assets.playerPunch1;
            case 122:
                return Assets.playerPunch2;
            case 123:
                return Assets.playerPunch3;
            case 124:
                return Assets.playerPunch4;
            case 125:
                return Assets.playerPunch5;
            case 126:
                return Assets.playerPunch6;
            case 130:
                return Assets.playerDeath0;
            case 131:
                return Assets.playerDeath1;
            default:
                // Handle default case (e.g., return a default asset or throw an exception)
                return null;
        }
    }
    /** Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune. */
    @Override
    public boolean IsSolid() {
        return true;
    }
}
