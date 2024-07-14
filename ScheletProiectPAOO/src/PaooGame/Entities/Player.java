package PaooGame.Entities;

import PaooGame.Tiles.Tile;
import PaooGame.Game;

import java.awt.*;

/**
 * Clasa care defineste jucatorul din joc, extinzand clasa abstracta Entity.
 * Jucatorul are proprietati precum viteza orizontala (velocityX), viteza verticala (velocityY),
 * starea de saritura (isJumping), starea de a fi pe sol (isGrounded) si directia in care se uita (direction).
 * Aceasta clasa contine metode pentru saritura, actualizare, desenare si obtinerea animatiilor curente.
 */
public class Player extends Entity {
    private double velocityX;  // Viteza orizontala
    private double velocityY;  // Viteza verticala
    private boolean isAttacking;
    protected boolean isPunching;
    private boolean isJumping;
    private boolean isGrounded;

    private boolean isDead;
    private Direction direction; // Directia in care se uita jucatorul

    private Tile[] idleFrames;
    private Tile[] walkFrames;
    private Tile[] jumpFrames;
    protected Tile[] punchFrames;
    private Tile[] deathFrames;

    protected int currentFrame;
    private int frameDelayCounter;
    private int frameDelayIdle;
    private int frameDelayWalk;
    private int frameDelayJump;
    private int frameDelayPunch;
    private int frameDelayDeath;

    private int score;

    public static final int COLLISION_XOFFSET = 20;
    public static final int COLLISION_YOFFSET = 0;
    public static final int COLLISION_WIDTH = 20;
    public static final int COLLISION_HEIGHT = 52;

    public Player(Tile tile, double health, double damage, int speed, int jumpStrength,
                  int xPos, int yPos, boolean isUnmovableEntity) {
        super(tile, health, damage, speed, jumpStrength, xPos, yPos, isUnmovableEntity);
        this.score = 0;
        this.velocityX = 0.0;
        this.velocityY = 0.0;
        this.isAttacking = false;
        this.isJumping = false;
        this.isGrounded = false;
        this.isPunching = false;
        this.isDead = false;
        this.direction = Direction.RIGHT; // Directia implicita este dreapta

        this.idleFrames = new Tile[]{Tile.PlayerIdle0, Tile.PlayerIdle4};
        this.walkFrames = new Tile[]{Tile.PlayerWalk2, Tile.PlayerWalk3};
        this.jumpFrames = new Tile[]{Tile.PlayerWalk3, Tile.PlayerWalk2};
        this.punchFrames = new Tile[]{Tile.PlayerPunch0, Tile.PlayerPunch1, Tile.PlayerPunch2,
                Tile.PlayerPunch3, Tile.PlayerPunch4, Tile.PlayerPunch5, Tile.PlayerPunch6};
        this.deathFrames = new Tile[]{Tile.PlayerDeath0, Tile.PlayerDeath1};

        this.currentFrame = 0;
        this.frameDelayCounter = 0;
        this.frameDelayIdle = 60;
        this.frameDelayWalk = 5;
        this.frameDelayJump = 5;
        this.frameDelayPunch = 5;
        this.frameDelayDeath = 30;
    }

    /**
     * Metoda pentru saritura jucatorului.
     * Jucatorul sare doar daca se afla pe sol (isGrounded == true).
     * In functie de directia in care se uita jucatorul, se calculeaza viteza de saritura.
     */
    public void jump() {
        if (!isDead && isGrounded) {
            double jumpSpeed = 12.0;
            double jumpAngle = direction == Direction.RIGHT ? Math.PI / 4 : 3 * Math.PI / 4;

            double jumpVelocityX = jumpSpeed * Math.cos(jumpAngle);
            double jumpVelocityY = -jumpSpeed * Math.sin(jumpAngle);

            setVelocityX(jumpVelocityX);
            setVelocityY(jumpVelocityY);

            setyPos(getyPos() - (int) jumpSpeed);

            isJumping = true;
        }
    }


    public void punch() {
        if (!isDead && !isAttacking && !isPunching) {
            isAttacking = true;
            isPunching = true;
            currentFrame = 0;
            int enemyToAttack = getIndexOfEnemyInRange(Game.getEnemyList());
            if(enemyToAttack >= 0) {
                if(Game.getEnemyList().get(enemyToAttack) != this) {
                    Game.getEnemyList().get(enemyToAttack).applyDamage(this.getDamage());
                    score += (int) getDamage();
                }
            }
        }
    }

    /**
     * Metoda de actualizare a starii jucatorului.
     * Se realizeaza actualizarea cadrelor de animatie in functie de starea jucatorului.
     * Daca jucatorul nu este pe sol, se realizeaza miscarea pe axele x si y in functie de viteza.
     * Daca este pe sol, viteza orizontala se seteaza la 0.
     */
    @Override
    public void update() {
        if (isDead) {
            // Play death animation and stay in the last frame
            if (currentFrame < deathFrames.length - 1) {
                frameDelayCounter++;
                if (frameDelayCounter >= frameDelayDeath) {
                    frameDelayCounter = 0;
                    currentFrame++;
                    setTile(deathFrames[currentFrame]);
                }
            }
            return;
        }
        frameDelayCounter++;
        if (frameDelayCounter >= getCurrentFrameDelay()) {
            frameDelayCounter = 0;
            if (isPunching) {
                currentFrame++;
                if (currentFrame >= punchFrames.length) {
                    currentFrame = 0;
                    isPunching = false;
                    isAttacking = false; // Reset attacking state
                }
                setTile(punchFrames[currentFrame]);
            } else {
                currentFrame = (currentFrame + 1) % getCurrentAnimation().length;
                setTile(getCurrentAnimation()[currentFrame]);
            }
        }

        if (!isGrounded) {
            setxPos((int) (getxPos() + getVelocityX()));
            setyPos((int) (getyPos() + getVelocityY()));
        } else {
            setVelocityX(0.0);
        }
    }

    /**
     * Metoda pentru desenarea jucatorului la coordonatele specifice.
     * Se determina latimea si inaltimea imaginii in functie de dala curenta.
     * Daca jucatorul se uita la stanga, imaginea este oglindita orizontal.
     *
     * @param g Contextul grafic pe care se deseneaza jucatorul.
     * @param x Coordonata x de desenare a jucatorului.
     * @param y Coordonata y de desenare a jucatorului.
     */
    public void draw(Graphics g, int x, int y) {
        int width = 64; // Latimea imaginii jucatorului (FIXATA)
        int height = 64; // Inaltimea imaginii jucatorului (FIXATA)

        if (direction == Direction.LEFT) {
            g.drawImage(tile.getImage(), x + width, y, -width, height, null);
        } else {
            g.drawImage(tile.getImage(), x, y, width, height, null);
        }
    }

    private Tile[] getCurrentAnimation() {
        if (isDead) {
            return deathFrames;
        } else if (isJumping()) {
            return jumpFrames;
        } else if (Math.abs(getVelocityX()) > 0.1) {
            return walkFrames;
        } else if (isAttacking) {
            return punchFrames;
        } else {
            return idleFrames;
        }
    }

    private int getCurrentFrameDelay() {
        if (isDead) {
            return frameDelayDeath;
        } else if (isJumping()) {
            return frameDelayJump;
        } else if (Math.abs(getVelocityX()) > 0.1) {
            return frameDelayWalk;
        } else if (isAttacking) {
            return frameDelayPunch;
        } else {
            return frameDelayIdle;
        }
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isGrounded() {
        return isGrounded;
    }

    public void setGrounded(boolean grounded) {
        isGrounded = grounded;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
        if (dead) {
            currentFrame = 0; // Start death animation from the beginning
            frameDelayCounter = 0;
        }
    }

    public void applyDamage(double damage) {
        if (!isDead) {
            setHealth(getHealth() - damage);
            if (getHealth() <= 0) {
                setDead(true);
            }
        }
    }

    public int getIndexOfEnemyInRange(java.util.List<Enemy> enemies) {
        for (Enemy e : enemies)
            if (Math.abs(getxPos() - e.getxPos()) < COLLISION_WIDTH)
                return enemies.indexOf(e);
        return -1;
    }

    public int getScore() {
        return score;
    }

    public void resetScore() { score = 0; }

    public void addLevelPassBonus() { score += (int) getHealth(); score += 100; }

    public void setScore(int score) { this.score = score; }
}
