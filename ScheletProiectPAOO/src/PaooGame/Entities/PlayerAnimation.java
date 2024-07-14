package PaooGame.Entities;

/**
 * Enumerare ce contine toate tipurile posibile de animatii pentru jucator.
 * Acestea includ animatii precum stand (IDLE), alergare (RUN), lovire (PUNCH),
 * uppercut, incarcare fireball, lovitura de picior (KICK), super lovitura de picior (SUPER_KICK),
 * blocare (BLOCK), lansare fireball, super pumn (SUPER_PUNCH), eschivare (DODGE),
 * lovitura primita (TAKE_HIT), special, ridicare si aruncare (PICK_UP_AND_THROW),
 * infrant (DEFEATED), etc.
 */
public enum PlayerAnimation {
    IDLE,
    RUN,
    PUNCH,
    UPPERCUT,
    CHARGE_FIREBALL,
    KICK,
    SUPER_KICK,
    BLOCK,
    LAUNCH_FIREBALL,
    SUPER_PUNCH,
    DODGE,
    TAKE_HIT,
    SPECIAL,
    PICK_UP_AND_THROW,
    DEFEATED
}
