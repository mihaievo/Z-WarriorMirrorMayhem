package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/**
 * Clasa incarca fiecare element grafic necesar jocului.
 * Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets {
    public static BufferedImage lv1Background;
    public static BufferedImage lv2Background;
    public static BufferedImage lv3Background;
    public static BufferedImage lv1ground;
    public static BufferedImage lv1GrassHStraight;
    public static BufferedImage lv1GrassVStraightL;
    public static BufferedImage lv1GrassVStraightR;
    public static BufferedImage lv1GrassOCR;
    public static BufferedImage lv1GrassOCL;
    public static BufferedImage lv1GrassICR;
    public static BufferedImage lv1GrassICL;
    public static BufferedImage lv1StoneHStraight;
    public static BufferedImage lv1StoneVStraightL;
    public static BufferedImage lv1StoneVStraightR;
    public static BufferedImage lv1StoneOCR;
    public static BufferedImage lv1StoneOCL;
    public static BufferedImage lv1StoneICR;
    public static BufferedImage lv1StoneICL;
    public static BufferedImage lv1PlatformUCL;
    public static BufferedImage lv1PlatformUCR;
    public static BufferedImage lv1PlatformBCL;
    public static BufferedImage lv1PlatformBCR;

    public static BufferedImage lv2ground;
    public static BufferedImage lv2GrassHStraight;

    public static BufferedImage lv2GrassOCL;
    public static BufferedImage lv2GrassOCR;
    public static BufferedImage lv2StoneVStraightL;
    public static BufferedImage lv2StoneVStraightR;
    public static BufferedImage lv2PavementHStraight;

    public static BufferedImage lv3ground;
    public static BufferedImage lv3StoneOCL;
    public static BufferedImage lv3StoneHStraight;
    public static BufferedImage lv3StoneOCR;
    public static BufferedImage lv3StoneVStraightL;
    public static BufferedImage lv3StoneVStraightR;
    public static BufferedImage lv3StoneToDirt;
    public static BufferedImage lv3DirtHStraight;
    public static BufferedImage lv3DirtToStone;
    public static BufferedImage lv3GrassToDirt;
    public static BufferedImage lv3DirtToGrass;
    public static BufferedImage lv3GrassLU;
    public static BufferedImage lv3GrassLUU;
    public static BufferedImage lv3GrassHStraight;
    public static BufferedImage lv3GrassUUR;
    public static BufferedImage lv3GrassUR;
    public static BufferedImage lv3PlatformBase;
    public static BufferedImage lv3PlatformConnector;
    public static BufferedImage lv3PlatformHStraight;

    public static BufferedImage playerIdle0;
    public static BufferedImage playerIdle1;
    public static BufferedImage playerIdle2;
    public static BufferedImage playerIdle3;
    public static BufferedImage playerIdle4;
    public static BufferedImage playerIdle5;
    public static BufferedImage playerWalk0;
    public static BufferedImage playerWalk1;
    public static BufferedImage playerWalk2;
    public static BufferedImage playerWalk3;
    public static BufferedImage playerWalk4;

    public static BufferedImage  playerPunch0;
    public static BufferedImage  playerPunch1;
    public static BufferedImage  playerPunch2;
    public static BufferedImage  playerPunch3;
    public static BufferedImage  playerPunch4;
    public static BufferedImage  playerPunch5;
    public static BufferedImage  playerPunch6;

    public static BufferedImage playerDeath0;
    public static BufferedImage playerDeath1;

    /**
     * Functia initializaza referintele catre elementele grafice utilizate.
     * Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
     * sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init() {
        // Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheetLv1 = new SpriteSheet(ImageLoader.LoadImage("/textures/map1/tileset.png"));
        SpriteSheet sheetLv2 = new SpriteSheet(ImageLoader.LoadImage("/textures/map2/tileset.png"));
        SpriteSheet sheetLv3 = new SpriteSheet(ImageLoader.LoadImage("/textures/map3/tileset.png"));
        SpriteSheet sheetMainPlayer = new SpriteSheet(ImageLoader.LoadImage("/textures/player/playersprites.png"));
        lv1Background = ImageLoader.LoadImage("/textures/map1/background.png");
        lv2Background = ImageLoader.LoadImage("/textures/map2/background.png");
        lv3Background = ImageLoader.LoadImage("/textures/map3/background.png");

        // Se obtin subimaginile corespunzatoare elementelor necesare.
        lv1ground = sheetLv1.crop(4, 2);
        lv1GrassHStraight = sheetLv1.crop(0, 0);
        lv1GrassVStraightL = sheetLv1.crop(3, 0);
        lv1GrassVStraightR = sheetLv1.crop(4, 0);
        lv1GrassOCR = sheetLv1.crop(2, 0);
        lv1GrassOCL = sheetLv1.crop(1, 0);
        lv1GrassICR = sheetLv1.crop(6, 0);
        lv1GrassICL = sheetLv1.crop(5, 0);
        lv1StoneHStraight = sheetLv1.crop(0, 1);
        lv1StoneVStraightL = sheetLv1.crop(4, 1);
        lv1StoneVStraightR = sheetLv1.crop(3, 1);
        lv1StoneOCR = sheetLv1.crop(1, 1);
        lv1StoneOCL = sheetLv1.crop(2, 1);
        lv1StoneICR = sheetLv1.crop(6, 1);
        lv1StoneICL = sheetLv1.crop(5, 1);
        lv1PlatformUCL = sheetLv1.crop(0, 2);
        lv1PlatformUCR = sheetLv1.crop(1, 2);
        lv1PlatformBCL = sheetLv1.crop(2, 2);
        lv1PlatformBCR = sheetLv1.crop(3, 2);

        lv2ground = sheetLv2.crop(6, 0);
        lv2GrassOCL = sheetLv2.crop(0, 0);
        lv2GrassOCR = sheetLv2.crop(2, 0);
        lv2GrassHStraight = sheetLv2.crop(1, 0);
        lv2StoneVStraightL = sheetLv2.crop(3, 0);
        lv2StoneVStraightR = sheetLv2.crop(5, 0);
        lv2PavementHStraight = sheetLv2.crop(4, 0);

        lv3ground = sheetLv3.crop(1, 1);
        lv3StoneOCL = sheetLv3.crop(0, 0);
        lv3StoneHStraight = sheetLv3.crop(1, 0);
        lv3StoneOCR = sheetLv3.crop(2, 0);
        lv3StoneVStraightL = sheetLv3.crop(0, 1);
        lv3StoneVStraightR = sheetLv3.crop(2, 1);
        lv3StoneToDirt = sheetLv3.crop(3,1);
        lv3DirtHStraight = sheetLv3.crop(4, 1);
        lv3DirtToStone = sheetLv3.crop(5, 1);
        lv3GrassToDirt = sheetLv3.crop(3, 0);
        lv3DirtToGrass = sheetLv3.crop(5, 0);
        lv3GrassLU = sheetLv3.crop(0, 4);
        lv3GrassLUU = sheetLv3.crop(1, 4);
        lv3GrassHStraight = sheetLv3.crop(2, 4);
        lv3GrassUUR = sheetLv3.crop(3, 4);
        lv3GrassUR = sheetLv3.crop(4, 4);
        lv3PlatformBase = sheetLv3.crop(1, 3);
        lv3PlatformConnector = sheetLv3.crop(1, 2);
        lv3PlatformHStraight = sheetLv3.crop(0, 2);

        playerIdle0 = sheetMainPlayer.crop(0, 0);
        playerIdle1 = sheetMainPlayer.crop(1, 0);
        playerIdle2 = sheetMainPlayer.crop(2, 0);
        playerIdle3 = sheetMainPlayer.crop(3, 0);
        playerIdle4 = sheetMainPlayer.crop(4, 0);
        playerIdle5 = sheetMainPlayer.crop(5, 0);

        playerWalk0 = sheetMainPlayer.crop(0, 1);
        playerWalk1 = sheetMainPlayer.crop(1, 1);
        playerWalk2 = sheetMainPlayer.crop(2, 1);
        playerWalk3 = sheetMainPlayer.crop(3, 1);
        playerWalk4 = sheetMainPlayer.crop(4, 1);

        playerPunch0 = sheetMainPlayer.crop(0,2);
        playerPunch1 = sheetMainPlayer.crop(1,2);
        playerPunch2 = sheetMainPlayer.crop(2,2);
        playerPunch3 = sheetMainPlayer.crop(3,2);
        playerPunch4 = sheetMainPlayer.crop(4,2);
        playerPunch5 = sheetMainPlayer.crop(5,2);
        playerPunch6 = sheetMainPlayer.crop(6,2);

        playerDeath0 = sheetMainPlayer.crop(0, 12);
        playerDeath1 = sheetMainPlayer.crop(1, 12);

    }
}
