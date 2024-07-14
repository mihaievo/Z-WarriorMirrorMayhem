package PaooGame.Entities;

import PaooGame.Tiles.Tile;

/**
 * Clasa abstracta care defineste o entitate din joc.
 * O entitate are proprietati precum dale de tip Tile, viata, daune, pozitie (x, y),
 * viteza, putere de saritura si indicator daca este o entitate nemiscatoare.
 * Aceasta clasa contine constructorul pentru initializarea unei entitati si metode
 * pentru manipularea pozitiei, vietei, daunelor si actualizarea entitatii.
 */
public abstract class Entity {
    public Tile tile;
    private double health;
    private double damage;

    private int xPos;
    private int yPos;

    private int speed;

    private int jumpStrength;

    private boolean isUnmovableEntity;

    public Entity(Tile tile, double health, double damage, int speed, int jumpStrength,
                  int xPos, int yPos, boolean isUnmovableEntity) {
        this.tile = tile;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.jumpStrength = jumpStrength;
        this.xPos = xPos;
        this.yPos = yPos;
        this.isUnmovableEntity = isUnmovableEntity;
    }

    /**
     * Metoda pentru deplasarea entitatii intr-o anumita directie cu o viteza data.
     * Deplasarea nu se realizeaza daca entitatea este nemiscatoare.
     * @param direction Directia in care se deplaseaza entitatea (stanga sau dreapta).
     * @param playerSpeed Viteza cu care se deplaseaza entitatea.
     */
    public void move(Direction direction, int playerSpeed) {
        if (!isUnmovableEntity) {
            switch (direction) {
                case LEFT:
                    this.xPos -= playerSpeed;
                    break;
                case RIGHT:
                    this.xPos += playerSpeed;
                    break;
                default:
                    break;
            }
        }
    }

    public float getHealth() {
        return (float)health;
    }

    public double getDamage() {
        return damage;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    /**
     * Metoda abstracta ce trebuie implementata de subclase pentru a actualiza
     * comportamentul entitatii.
     */
    public abstract void update();

    /**
     * Metoda folosita pentru setarea dalei (tile) unei entitati.
     * Aceasta metoda va fi utilizata pentru animatii.
     * @param newTile Noua dala a entitatii.
     */
    public void setTile(Tile newTile) {
        this.tile = newTile;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setxPos(int x) {
        xPos = x;
    }

    public void setyPos(int y) {
        yPos = y;
    }
}
