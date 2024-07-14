package PaooGame;

public class Main
{
    public static final boolean IS_DEBUG = true;
    public static void main(String[] args)
    {
        Game paooGame = new Game("Z-Warrior: Mirror Mayhem", 1280, 720);
        paooGame.StartGame();
    }
}
