package PaooGame.Tiles;

import PaooGame.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

/** Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 200; // 19 tiles in nivel 1, 200 e placeholder pentru ca vom avea si entitati
    public static Tile[] tiles          = new Tile[NO_TILES];
    public static Tile Lv1Ground        = new SoilTile(0);
    public static Tile Lv1GrassHStraight  = new GrassTile(1);
    public static Tile Lv1GrassVStraightL = new GrassTile(2);
    public static Tile Lv1GrassVStraightR = new GrassTile(3);
    public static Tile Lv1GrassOCR = new GrassTile(4);
    public static Tile LV1GrassOCL = new GrassTile(5);
    public static Tile Lv1GrassICR = new GrassTile(6);
    public static Tile Lv1GrassICL = new GrassTile(7);
    public static Tile Lv1StoneHStraight = new RockTile(8);
    public static Tile Lv1StoneVStraightL = new RockTile(9);
    public static Tile Lv1StoneVStraightR = new RockTile(10);
    public static Tile Lv1StoneOCR = new RockTile(11);
    public static Tile Lv1StoneOCL = new RockTile(12);
    public static Tile Lv1StoneICR = new RockTile(13);
    public static Tile Lv1StoneICL = new RockTile(14);
    public static Tile Lv1PlatformUCL = new PlatformTile(15);
    public static Tile  Lv1PlatformUCR = new PlatformTile(16);
    public static Tile  Lv1PlatformBCL = new PlatformTile(17);
    public static Tile  Lv1PlatformBCR = new PlatformTile(18);

    // de la 20 inainte vom avea tiles pt map2
    public static Tile Lv2Ground = new SoilTile(20);
    public static Tile Lv2GrassOCL = new GrassTile(22);
    public static Tile Lv2GrassOCR = new GrassTile(23);
    public static Tile Lv2GrassHStraight = new GrassTile(21);
    public static Tile Lv2StoneVStraightL = new RockTile(24);
    public static Tile Lv2StoneVStraightR = new RockTile(25);
    public static Tile Lv2PavementHStraight = new PlatformTile(26);

    // de la 30 inainte vom avea tiles pt map3
    public static Tile Lv3Ground = new SoilTile(30);
    public static Tile Lv3StoneOCL = new RockTile(32);
    public static Tile Lv3StoneHStraight = new RockTile(31);
    public static Tile Lv3StoneOCR = new RockTile(33);
    public static Tile Lv3StoneVStraightL = new RockTile(34);
    public static Tile Lv3StoneVStraightR = new RockTile(35);
    public static Tile Lv3StoneToDirt = new RockTile(36);
    public static Tile Lv3DirtHStraight = new GrassTile(37);
    public static Tile Lv3DirtToStone = new GrassTile(38);
    public static Tile Lv3GrassToDirt = new GrassTile(39);
    public static Tile Lv3DirtToGrass = new GrassTile(40);
    public static Tile Lv3GrassLU = new GrassTile(41);
    public static Tile Lv3GrassLUU = new GrassTile(42);
    public static Tile Lv3GrassHStraight = new GrassTile(43);
    public static Tile Lv3GrassUUR = new GrassTile(44);
    public static Tile Lv3GrassUR = new GrassTile(45);
    public static Tile Lv3PlatformBase = new PlatformTile(46);
    public static Tile Lv3PlatformConnector = new PlatformTile(47);
    public static Tile Lv3PlatformHStraight = new PlatformTile(48);

    public static Tile PlayerIdle0 = new CharacterTile(100);
    public static Tile PlayerIdle1 = new CharacterTile(101);
    public static Tile PlayerIdle2 = new CharacterTile(102);
    public static Tile PlayerIdle3 = new CharacterTile(103);
    public static Tile PlayerIdle4 = new CharacterTile(104);
    public static Tile PlayerIdle5 = new CharacterTile(105);

    public static Tile PlayerWalk0 = new CharacterTile(110);
    public static Tile PlayerWalk1 = new CharacterTile(111);
    public static Tile PlayerWalk2 = new CharacterTile(112);
    public static Tile PlayerWalk3 = new CharacterTile(113);
    public static Tile PlayerWalk4 = new CharacterTile(114);

    public static Tile PlayerPunch0 = new CharacterTile(120);
    public static Tile PlayerPunch1 = new CharacterTile(121);
    public static Tile PlayerPunch2 = new CharacterTile(122);
    public static Tile PlayerPunch3 = new CharacterTile(123);
    public static Tile PlayerPunch4 = new CharacterTile(124);
    public static Tile PlayerPunch5 = new CharacterTile(125);
    public static Tile PlayerPunch6 = new CharacterTile(126);

    public static Tile PlayerDeath0 = new CharacterTile(130);
    public static  Tile PlayerDeath1 = new CharacterTile(131);

    public static final int TILE_WIDTH  = 16;
    public static final int TILE_HEIGHT = 16;

    protected BufferedImage img;
    protected final int id;

    /** Constructorul aferent clasei.

        @param image Imaginea corespunzatoare dalei.
        @param idd Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    /** Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /** Deseneaza in fereastra dala.

        @param g Contextul grafic in care sa se realizeze desenarea
        @param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        @param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
        if(Main.IS_DEBUG && this.IsSolid()) {
            g.setColor(Color.BLUE);
            g.drawRect(x, y, TILE_WIDTH, TILE_HEIGHT);
        }
    }

    /** Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    public BufferedImage getImage() { return img; }

    /** Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}
