package PaooGame.Physics;

import PaooGame.Entities.Player;
import PaooGame.Levels.Level;

import javax.swing.*;

/**
 * Clasa care gestionează gravitația și aplicarea ei asupra jucătorului în joc.
 */
public class Gravity {
    private static final double GRAVITY_ACCELERATION = 3.0;  // Accelerarea datorată gravitației
    private static final double MAX_FALL_SPEED = 5.0;       // Viteza maximă de cădere (viteza terminală)

    private Level level; // Referință către nivelul jocului pentru detectarea coliziunilor

    /**
     * Constructor pentru clasa Gravity.
     * @param level Referință către nivelul jocului pentru detectarea coliziunilor.
     */
    public Gravity(Level level) {
        this.level = level;
    }

    /**
     * Aplică gravitația asupra vitezei verticale a jucătorului.
     * @param player Jucătorul asupra căruia se aplică gravitația.
     */
    public void applyGravity(Player player) {
        if (!isOnGround(player, player.getyPos())) {
            // Aplică gravitația asupra vitezei verticale a jucătorului
            double velocityY = player.getVelocityY() + GRAVITY_ACCELERATION;

            // Limitează viteza maximă de cădere (viteza terminală)
            if (velocityY > MAX_FALL_SPEED) {
                velocityY = MAX_FALL_SPEED;
            }

            // Actualizează viteza verticală a jucătorului
            player.setVelocityY(velocityY);

            // Actualizează poziția verticală a jucătorului pe baza vitezei verticale
            int newYPos = (int) (player.getyPos() + velocityY);

            // Actualizează poziția Y a jucătorului
            player.setyPos(newYPos);

            // Verifică dacă noua poziție a jucătorului este pe sol (coliziune cu dalele de sol)
            if (isOnGround(player, newYPos)) {
                player.setGrounded(true);
            } else {
                player.setGrounded(false);
            }
        } else {
            // Jucătorul este pe sol
            player.setGrounded(true);
            player.setJumping(false);
            player.setVelocityY(0); // Resetare viteză verticală când este pe sol
        }
    }

    /**
     * Verifică dacă jucătorul este pe sol (coliziune cu dalele de sol).
     * @param player Jucătorul de verificat.
     * @param yPos Noua poziție Y a jucătorului după aplicarea gravitației.
     * @return true dacă jucătorul este pe sol, false altfel.
     */
    private boolean isOnGround(Player player, int yPos) {
        // Calculează intervalul pentru coliziunea cu solul pe baza dimensiunilor jucătorului
        int collisionWidth = Player.COLLISION_WIDTH;
        int collisionHeight = Player.COLLISION_HEIGHT;

        // Verifică coliziunea cu dalele de sol sub picioarele jucătorului
        return level.isCollisionTileUnderRange(player.getxPos() + Player.COLLISION_XOFFSET,
                yPos + Player.COLLISION_YOFFSET,
                collisionWidth, collisionHeight);
    }
}
