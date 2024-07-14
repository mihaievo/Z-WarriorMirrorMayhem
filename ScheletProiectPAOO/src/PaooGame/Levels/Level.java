package PaooGame.Levels;

import PaooGame.Camera.Camera;
import PaooGame.Entities.Enemy;
import PaooGame.Graphics.Assets;
import PaooGame.LevelBackgrounds.LevelBackground;
import PaooGame.Tiles.Tile;

import PaooGame.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Level este responsabilă de gestionarea informațiilor și desenarea unui nivel din joc.
 * Acesta conține date despre dalele nivelului, fundalul și funcții pentru încărcare și desenare.
 */
public class Level {
    private static LevelBackground background; // Fundalul nivelului
    private static Tile[] tileset; // Setul de dale disponibile în nivel

    private static int[][] tileData; // Matricea care reține tipurile de dale din nivel

    public static final int HTILE_COUNT = 400; // Numărul de dale pe orizontală în nivel
    public static final int VTILE_COUNT = 45; // Numărul de dale pe verticală în nivel

    private static int id; // ID-ul nivelului

    private static boolean wasLoaded; // Flag pentru a verifica dacă datele nivelului au fost încărcate

    private List<Enemy> enemies;

    /**
     * Constructor pentru clasa Level.
     * @param id ID-ul nivelului.
     */
    public Level(int id) {
        this.id = id;
        wasLoaded = false;
        InitLevel();
        enemies = new ArrayList<Enemy>();
    }

    public void selectLevel(int id) {
        enemies = Game.getEnemyList();
        for (Enemy enemy : enemies) {
            if(enemy != null)
                enemy.despawn();
        }
        this.id = id;
        wasLoaded = false;
        InitLevel();
        respawnEnemies();
    }

    public List<Enemy> getEnemiesAndPositions() {
        enemies.add(new Enemy(Tile.PlayerIdle0, 100.0, 0.5, 2, 1, 10, 26 * 16, false));
        enemies.add(new Enemy(Tile.PlayerIdle0, 100.0, 0.5, 2, 1, 1200, 26 * 16, false));
        enemies.add(new Enemy(Tile.PlayerIdle0, 100.0, 0.5, 2, 1, 240, 26 * 16, false));
        enemies.add(new Enemy(Tile.PlayerIdle0, 100.0, 0.5, 2, 1, 450, 26 * 16, false));
        enemies.add(new Enemy(Tile.PlayerIdle0, 100.0, 0.5, 2, 1, 720, 26 * 16, false));
        enemies.add(new Enemy(Tile.PlayerIdle0, 100.0, 0.5, 2, 1, 560, 26 * 16, false));
        enemies.add(new Enemy(Tile.PlayerIdle0, 100.0, 0.5, 2, 1, 4300, 26 * 16, false));
        enemies.add(new Enemy(Tile.PlayerIdle0, 100.0, 0.5, 2, 1, 2700, 26 * 16, false));
        enemies.add(new Enemy(Tile.PlayerIdle0, 100.0, 0.5, 2, 1, 1120, 26 * 16, false));
        enemies.add(new Enemy(Tile.PlayerIdle0, 100.0, 0.5, 2, 1, 6200, 26 * 16, false));
        respawnEnemies();
        return enemies;
    }

    public void respawnEnemies() {
        int minDistance = 100; // Minimum distance between enemies on the x-axis
        int startX = 0; // Starting x position for the first enemy

        for (Enemy enemy : enemies) {
            // Calculate the new X position ensuring spacing
            int newX = startX + (int) (Math.random() * minDistance * 10);
            int newY = 0; // new Y position
            enemy.respawn(newX, newY);

            // Update the startX for the next enemy
            startX = newX + minDistance;
        }
    }


    /**
     * Inițializează nivelul, încărcând datele acestuia dacă nu au fost încărcate.
     */
    private void InitLevel() {
        if(!wasLoaded) {
            //System.err.println("RELOADING LEVEL");
            BufferedImage bg = null;
            switch (id) {
                case 1:
                    bg = Assets.lv1Background;
                    break;
                case 2:
                    bg = Assets.lv2Background;
                    break;
                case 3:
                    bg = Assets.lv3Background;
                    break;
                default:
                    break;
            }
            background = new LevelBackground(bg);
            wasLoaded = LoadTileData();
            this.tileset = new Tile[]{
                    Tile.Lv1Ground,
                    Tile.Lv1GrassHStraight,
                    Tile.Lv1GrassVStraightL,
                    Tile.Lv1GrassVStraightR,
                    Tile.Lv1GrassOCR,
                    Tile.LV1GrassOCL,
                    Tile.Lv1GrassICR,
                    Tile.Lv1GrassICL,
                    Tile.Lv1StoneHStraight,
                    Tile.Lv1StoneVStraightL,
                    Tile.Lv1StoneVStraightR,
                    Tile.Lv1StoneOCR,
                    Tile.Lv1StoneOCL,
                    Tile.Lv1StoneICR,
                    Tile.Lv1StoneICL,
                    Tile.Lv1PlatformUCL,
                    Tile.Lv1PlatformUCR,
                    Tile.Lv1PlatformBCL,
                    Tile.Lv1PlatformBCR,
                    Tile.Lv2Ground,
                    Tile.Lv2GrassHStraight,
                    Tile.Lv2GrassOCL,
                    Tile.Lv2GrassOCR,
                    Tile.Lv2StoneVStraightL,
                    Tile.Lv2StoneVStraightR,
                    Tile.Lv2PavementHStraight,
                    Tile.Lv3Ground,
                    Tile.Lv3StoneHStraight,
                    Tile.Lv3StoneOCL,
                    Tile.Lv3StoneOCR,
                    Tile.Lv3StoneVStraightL,
                    Tile.Lv3StoneVStraightR,
                    Tile.Lv3StoneToDirt,
                    Tile.Lv3DirtHStraight,
                    Tile.Lv3DirtToStone,
                    Tile.Lv3GrassToDirt,
                    Tile.Lv3DirtToGrass,
                    Tile.Lv3GrassLU,
                    Tile.Lv3GrassLUU,
                    Tile.Lv3GrassHStraight,
                    Tile.Lv3GrassUUR,
                    Tile.Lv3GrassUR,
                    Tile.Lv3PlatformHStraight,
                    Tile.Lv3PlatformConnector,
                    Tile.Lv3PlatformBase
                    };
            }
    }

    /**
     * Încarcă datele de dale din fișierul binar corespunzător nivelului.
     * @return true dacă datele au fost încărcate cu succes, false altfel.
     */
    private boolean LoadTileData() {
        String mapPath;
        switch (id) {
            case 1:
                mapPath = "res/maps/map1.bin";
                break;
            case 2:
                mapPath = "res/maps/map2.bin";
                break;
            case 3:
                mapPath = "res/maps/map3.bin";
                break;
            default:
                System.err.println("Invalid map id.");
                return false;
        }
        tileData = new int[VTILE_COUNT][HTILE_COUNT];
        int tileIndex = 0;
        int xPos;
        int yPos;

        try (FileInputStream inputStream = new FileInputStream(mapPath)) {
            int data;

            while ((data = inputStream.read()) != -1) {
                // Determină poziția (xPos, yPos) pe baza indexului dalei
                xPos = tileIndex % HTILE_COUNT;
                yPos = tileIndex / HTILE_COUNT;
                tileData[yPos][xPos] = data;

                if (yPos >= VTILE_COUNT) {
                    break; // Ieși din buclă dacă toate rândurile au fost procesate
                }
                tileIndex++; // Incrementarea indexului pentru următoarea dală
            }
        } catch (FileNotFoundException e) {
            System.err.println("Map not found.");
            return false;
        } catch (IOException e) {
            System.err.println("Cannot read binary map file.");
            return false;
        }
        return true;
    }
    /**
     * Desenează nivelul pe ecran.
     * @param g Contextul grafic pe care se realizează desenarea.
     * @param camera Camera folosită pentru vizualizarea nivelului.
     */
    public void DrawLevel(Graphics g, Camera camera) {
        // Calculate the tile indices visible in the camera viewport
        int cameraX = camera.getxOffset();
        int cameraY = camera.getyOffset();
        int cameraWidth = camera.getxViewSize();
        int cameraHeight = camera.getyViewSize();

        // Calculate the range of tiles to draw within the camera viewport
        int startX = Math.max(0, cameraX / Tile.TILE_WIDTH);  // Starting column index
        int endX = Math.min(HTILE_COUNT, (cameraX + cameraWidth) / Tile.TILE_WIDTH + 1);  // Ending column index (+1 for inclusive)
        int startY = Math.max(0, cameraY / Tile.TILE_HEIGHT);  // Starting row index
        int endY = Math.min(VTILE_COUNT, (cameraY + cameraHeight) / Tile.TILE_HEIGHT + 1);  // Ending row index (+1 for inclusive)

        // Draw the visible portion of the level
        background.draw(g, 0, 0);

        for (int yPos = startY; yPos < endY; ++yPos) {
            for (int xPos = startX; xPos < endX; ++xPos) {
                int data = tileData[yPos][xPos];
                // Calculate the drawing position relative to the camera
                int drawX = xPos * Tile.TILE_WIDTH - cameraX;
                int drawY = yPos * Tile.TILE_HEIGHT - cameraY;
                // Draw the tile based on the data read
                switch(id) {
                    case 1:
                    switch (data) {
                        case 254:
                            tileset[0].Draw(g, drawX, drawY);
                            break;
                        case 10:
                            tileset[8].Draw(g, drawX, drawY);
                            break;
                        case 11:
                            tileset[12].Draw(g, drawX, drawY);
                            break;
                        case 12:
                            tileset[11].Draw(g, drawX, drawY);
                            break;
                        case 13:
                            tileset[10].Draw(g, drawX, drawY);
                            break;
                        case 14:
                            tileset[9].Draw(g, drawX, drawY);
                            break;
                        case 15:
                            tileset[13].Draw(g, drawX, drawY);
                            break;
                        case 16:
                            tileset[14].Draw(g, drawX, drawY);
                            break;
                        case 20:
                            tileset[1].Draw(g, drawX, drawY);
                            break;
                        case 21:
                            tileset[5].Draw(g, drawX, drawY);
                            break;
                        case 22:
                            tileset[4].Draw(g, drawX, drawY);
                            break;
                        case 23:
                            tileset[3].Draw(g, drawX, drawY);
                            break;
                        case 24:
                            tileset[2].Draw(g, drawX, drawY);
                            break;
                        case 25:
                            tileset[6].Draw(g, drawX, drawY);
                            break;
                        case 26:
                            tileset[7].Draw(g, drawX, drawY);
                            break;
                        case 30:
                            tileset[15].Draw(g, drawX, drawY);
                            break;
                        case 31:
                            tileset[16].Draw(g, drawX, drawY);
                            break;
                        case 32:
                            tileset[18].Draw(g, drawX, drawY);
                            break;
                        case 33:
                            tileset[17].Draw(g, drawX, drawY);
                            break;
                        default:
                            // Handle unknown tile types or skip drawing
                            break;
                    }
                    break;
                    case 2:
                        switch (data) {
                            case 20:
                                tileset[19].Draw(g, drawX, drawY);
                                break;
                            case 21:
                                tileset[20].Draw(g, drawX, drawY);
                                break;
                            case 22:
                                tileset[21].Draw(g, drawX, drawY);
                                break;
                            case 23:
                                tileset[22].Draw(g, drawX, drawY);
                                break;
                            case 24:
                                tileset[23].Draw(g, drawX, drawY);
                                break;
                            case 25:
                                tileset[24].Draw(g, drawX, drawY);
                                break;
                            case 26:
                                tileset[25].Draw(g, drawX, drawY);
                                break;
                        }
                        break;
                    case 3:
                        switch(data) {
                            case 30:
                                tileset[26].Draw(g, drawX, drawY);
                                break;
                            case 31:
                                tileset[27].Draw(g, drawX, drawY);
                                break;
                            case 32:
                                tileset[28].Draw(g, drawX, drawY);
                                break;
                            case 33:
                                tileset[29].Draw(g, drawX, drawY);
                                break;
                            case 34:
                                tileset[30].Draw(g, drawX, drawY);
                                break;
                            case 35:
                                tileset[31].Draw(g, drawX, drawY);
                                break;
                            case 36:
                                tileset[32].Draw(g, drawX, drawY);
                                break;
                            case 37:
                                tileset[33].Draw(g, drawX, drawY);
                                break;
                            case 38:
                                tileset[34].Draw(g, drawX, drawY);
                                break;
                            case 39:
                                tileset[35].Draw(g, drawX, drawY);
                                break;
                            case 40:
                                tileset[33].Draw(g, drawX, drawY);
                                break;
                            case 41:
                                tileset[36].Draw(g, drawX, drawY);
                                break;
                            case 42:
                                tileset[37].Draw(g, drawX, drawY);
                                break;
                            case 43:
                                tileset[38].Draw(g, drawX, drawY);
                                break;
                            case 44:
                                tileset[39].Draw(g, drawX, drawY);
                                break;
                            case 45:
                                tileset[40].Draw(g, drawX, drawY);
                                break;
                            case 46:
                                tileset[41].Draw(g, drawX, drawY);
                                break;
                            case 47:
                                tileset[42].Draw(g, drawX, drawY);
                                break;
                            case 48:
                                tileset[43].Draw(g, drawX, drawY);
                                break;
                            case 49:
                                tileset[44].Draw(g, drawX, drawY);
                                break;
                            case 50:
                                tileset[44].Draw(g, drawX, drawY);
                                break;
                        }
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Returnează tipul dalei de la o anumită poziție în matrice.
     * @param tileX Coordonata x a dalei în matrice.
     * @param tileY Coordonata y a dalei în matrice.
     * @return Dala corespunzătoare poziției specificate.
     */
    public static Tile getTileAtPosition(int tileX, int tileY) {
        if (tileX >= 0 && tileX < HTILE_COUNT && tileY >= 0 && tileY < VTILE_COUNT) {
            int tileDataValue = tileData[tileY][tileX];
            // Map the tileDataValue to a corresponding Tile type using switch-case
            switch(id) {
                case 1:
                    switch (tileDataValue) {
                        case 254:
                            return tileset[0];
                        case 10:
                            return tileset[8];
                        case 11:
                            return tileset[12];
                        case 12:
                            return tileset[11];
                        case 13:
                            return tileset[10];
                        case 14:
                            return tileset[9];
                        case 15:
                            return tileset[13];
                        case 16:
                            return tileset[14];
                        case 20:
                            return tileset[1];
                        case 21:
                            return tileset[5];
                        case 22:
                            return tileset[4];
                        case 23:
                            return tileset[3];
                        case 24:
                            return tileset[2];
                        case 25:
                            return tileset[6];
                        case 26:
                            return tileset[7];
                        case 30:
                            return tileset[15];
                        case 31:
                            return tileset[16];
                        case 32:
                            return tileset[18];
                        case 33:
                            return tileset[17];
                        default:
                            return null; // Return a default tile
                    }
                case 2:
                    switch (tileDataValue) {
                        case 20:
                            return tileset[19];
                        case 21:
                            return tileset[20];
                        case 22:
                            return tileset[21];
                        case 23:
                            return tileset[22];
                        case 24:
                            return tileset[23];
                        case 25:
                            return tileset[24];
                        case 26:
                            return tileset[25];
                        default:
                            return null;
                    }
                case 3:
                    switch (tileDataValue) {
                        case 30:
                            return tileset[26];
                        case 31:
                            return tileset[27];
                        case 32:
                            return tileset[28];
                        case 33:
                            return tileset[29];
                        case 34:
                            return tileset[30];
                        case 35:
                            return tileset[31];
                        case 36:
                            return tileset[32];
                        case 37:
                            return tileset[33];
                        case 38:
                            return tileset[34];
                        case 39:
                            return tileset[35];
                        case 40:
                            return tileset[36];
                        case 41:
                            return tileset[37];
                        case 42:
                            return tileset[38];
                        case 43:
                            return tileset[39];
                        case 44:
                            return tileset[40];
                        case 45:
                            return tileset[41];
                        case 46:
                            return tileset[42];
                        case 47:
                            return tileset[43];
                        case 48:
                            return tileset[43];
                        case 49:
                            return tileset[44];
                    }
                default:
                    break;
            }
        }
        return null; // No valid tile found.
    }

    /**
     * Verifică dacă nivelul a fost încărcat.
     * @return true dacă datele nivelului au fost încărcate, false altfel.
     */
    public boolean checkLevelLoaded() {
        return wasLoaded;
    }

    /**
     * Verifică dacă există o dala solidă sub o anumită zonă.
     * @param startX Coordonata x a începutului zonei.
     * @param startY Coordonata y a începutului zonei.
     * @param width Lățimea zonei.
     * @param height Înălțimea zonei.
     * @return true dacă există o dala solidă sub zona specificată, false altfel.
     */
    public boolean isCollisionTileUnderRange(int startX, int startY, int width, int height) {
        // Calculate the tile coordinates (column and row) based on pixel coordinates
        int startTileX = startX / Tile.TILE_WIDTH;                 // Starting column index
        int startTileY = startY / Tile.TILE_HEIGHT;                // Starting row index
        int endTileX = (startX + width) / Tile.TILE_WIDTH;         // Ending column index
        int endTileY = (startY + height) / Tile.TILE_HEIGHT;       // Ending row index

        // Iterate through the range of tiles and check if any represent a collision tile
        for (int tileY = startTileY; tileY <= endTileY; tileY++) {
            for (int tileX = startTileX; tileX <= endTileX; tileX++) {
                Tile tile = getTileAtPosition(tileX, tileY);
                if (tile != null && tile.IsSolid()) {
                    return true;  // Found a solid collision tile within the specified range
                }
            }
        }

        return false;  // No solid collision tile found within the specified range
    }

    /**
     * Verifică dacă există o dala solidă la dreapta unei anumite zone.
     * @param startX Coordonata x a începutului zonei.
     * @param startY Coordonata y a începutului zonei.
     * @param width Lățimea zonei.
     * @param height Înălțimea zonei.
     * @return true dacă există o dala solidă la dreapta zonei specificate, false altfel.
     */
    public static boolean isTileRightOfRange(int startX, int startY, int width, int height) {
        // Calculate the tile coordinates (column) of the right boundary based on pixel coordinates
        int endTileX = (startX + width) / Tile.TILE_WIDTH;         // Ending column index

        // Iterate through the tiles to the right of the specified range
        for (int tileX = endTileX + 1; tileX < endTileX + 2; tileX++) {  // Check the next tile column
            for (int tileY = startY / Tile.TILE_HEIGHT; tileY <= (startY + height) / Tile.TILE_HEIGHT; tileY++) {
                Tile tile = getTileAtPosition(tileX, tileY);
                if (tile != null && tile.IsSolid()) {
                    return true;  // Found a solid collision tile to the right of the specified range
                }
            }
        }

        return false;  // No solid collision tile found to the right of the specified range
    }

    /**
     * Verifică dacă există o dala solidă la stânga unei anumite zone.
     * @param startX Coordonata x a începutului zonei.
     * @param startY Coordonata y a începutului zonei.
     * @param height Înălțimea zonei.
     * @return true dacă există o dala solidă la stânga zonei specificate, false altfel.
     */
    public static boolean isTileLeftOfRange(int startX, int startY, int height) {
        // Calculate the tile coordinates (column) of the left boundary based on pixel coordinates
        int startTileX = startX / Tile.TILE_WIDTH;                 // Starting column index

        // Iterate through the tiles to the left of the specified range
        for (int tileX = startTileX - 1; tileX >= startTileX - 1; tileX--) {  // Check the previous tile column
            for (int tileY = startY / Tile.TILE_HEIGHT; tileY <= (startY + height) / Tile.TILE_HEIGHT; tileY++) {
                Tile tile = getTileAtPosition(tileX, tileY);
                if (tile != null && tile.IsSolid()) {
                    return true;  // Found a solid collision tile to the left of the specified range
                }
            }
        }

        return false;  // No solid collision tile found to the left of the specified range
    }

}