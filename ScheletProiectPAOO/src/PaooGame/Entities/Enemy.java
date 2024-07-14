package PaooGame.Entities;

import PaooGame.Game;
import PaooGame.Levels.Level;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Enemy extends Player {
    private boolean active; // Flag to track if the enemy is active
    private long lastAttackTime; // Time of the last attack
    private final long attackCooldown = 1000; // Cooldown period in milliseconds (1 second)

    public Enemy(Tile tile, double health, double damage, int speed, int jumpStrength, int xPos, int yPos, boolean isUnmovableEntity) {
        super(tile, health, damage, speed, jumpStrength, xPos, yPos, isUnmovableEntity);
        this.active = true; // Set the enemy as active initially
        this.lastAttackTime = 0; // Initialize the last attack time
    }

    public void followPlayer() {
        if (isDead()) return; // Don't follow the player if dead

            int playerX = Game.getPlayer().getxPos();
            int playerY = Game.getPlayer().getyPos();
            int enemyX = getxPos();
            int enemyY = getyPos();

            int distanceX = Math.abs(playerX - enemyX);
            int distanceY = Math.abs(playerY - enemyY);
            int followRange = 100; // 100-tile range for following the player
            int attackRange = 10; // Range within which the enemy attacks the player

            if (distanceX <= followRange && distanceY <= followRange) {
                // Determine direction based on player's position
                if (playerX < enemyX) {
                    setDirection(Direction.LEFT);
                    if (distanceX > attackRange) {
                        if (!Level.isTileLeftOfRange(getxPos() + Player.COLLISION_XOFFSET + 16, getyPos() - 16, Player.COLLISION_HEIGHT)) {
                            move(Direction.LEFT, 2);
                            setVelocityX(-1.0);
                        }
                    } else {
                        attackPlayer();
                    }
                } else if (playerX > enemyX) {
                    setDirection(Direction.RIGHT);
                    if (distanceX > attackRange) {
                        if (!Level.isTileRightOfRange(getxPos() + Player.COLLISION_XOFFSET, getyPos() - 16, Player.COLLISION_WIDTH - 16, Player.COLLISION_HEIGHT)) {
                            move(Direction.RIGHT, 2);
                            setVelocityX(1.0);
                        }
                    } else {
                        attackPlayer();
                    }
                }
            } else {
                // If out of range, just face the player
                if (playerX < enemyX) {
                    setDirection(Direction.LEFT);
                } else {
                    setDirection(Direction.RIGHT);
                }
            }
    }

    public void attackPlayer() {
        if (isDead()) return; // Can't attack if dead
        // Check if close enough to attack
        if (Math.abs(getxPos() - Game.getPlayer().getxPos()) < Player.COLLISION_WIDTH) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastAttackTime >= attackCooldown) {
                punch();
                lastAttackTime = currentTime; // Update the last attack time
            }
            if (this.isPunching && this.currentFrame == this.punchFrames.length - 1) {
                // Inflict damage to the player
                Game.getPlayer().applyDamage(getDamage());
            }
        }
    }


    @Override
    public void update() {
        if (!active) {
            return; // If the enemy is not active, do not update
        }
        if (getyPos() > 720 || getHealth() <= 0) {
            despawn();
        }
        super.update();
        followPlayer();
    }

    public void despawn() {
        this.active = false; // Set the enemy as inactive
        setxPos(-100);
    }

    public void respawn(int newX, int newY) {
        setxPos(newX); // Update position
        setyPos(newY);
        this.active = true; // Set the enemy as active
        setHealth(100); // Reset health on respawn
        setDead(false); // Ensure the enemy is not dead
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
        g.setColor(Color.GREEN);
        g.drawString("Health: " + getHealth(), x, y - 10); // Draw health above the enemy
    }

    public void applyDamage(double damage) {
        if (!isDead()) {
            setHealth(getHealth() - damage);
            if (getHealth() <= 0) {
                this.setDead(true);
            }
        }
    }
}
