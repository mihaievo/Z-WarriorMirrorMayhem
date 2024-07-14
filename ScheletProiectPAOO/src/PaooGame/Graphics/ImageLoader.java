package PaooGame.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

/** Clasa ce contine o metoda statica pentru incarcarea unei imagini in memorie.
 */
public class ImageLoader
{
    /** Incarca o imagine intr-un obiect BufferedImage si returneaza o referinta catre acesta.

        @param path Calea relativa pentru localizarea fisierul imagine.
     */
    public static BufferedImage LoadImage(String path)
    {
        try
        {
            return ImageIO.read(ImageLoader.class.getResource(path));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }}

