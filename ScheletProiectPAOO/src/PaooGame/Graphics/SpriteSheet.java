package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/** Clasa retine o referinta catre o imagine formata din dale (sprite sheet).

    Metoda crop() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
    de la adresa (x * latimeDala, y * inaltimeDala).
 */
public class SpriteSheet
{
    private BufferedImage       spriteSheet;
    private static final int    tileWidth   = 128;
    private static final int    tileHeight  = 128;

    /** Constructor, initializeaza spriteSheet.

        @param buffImg Un obiect BufferedImage valid.
     */
    public SpriteSheet(BufferedImage buffImg)
    {
        spriteSheet = buffImg;
    }

    /** Returneaza un obiect BufferedImage ce contine o subimage (dala).

        Subimaginea este localizata avand ca referinta punctul din stanga sus.

        @param x numarul dalei din sprite sheet pe axa x.
        @param y numarul dalei din sprite sheet pe axa y.
     */
    public BufferedImage crop(int x, int y)
    {
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }
}
