package PaooGame;

import PaooGame.Camera.Camera;
import PaooGame.Entities.Direction;
import PaooGame.Entities.Enemy;
import PaooGame.Entities.Player;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Levels.Level;
import PaooGame.Physics.Gravity;
import PaooGame.Tiles.Tile;
import PaooGame.Utilities.LeaderboardDB;
import PaooGame.Utilities.SavegameDB;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.security.Key;
import java.util.List;

/** @implNote Clasa principală a întregului proiect. Implementează Game - Loop (Update -> Draw)

 *               ------------
 *               |           |
 *               |     ------------
 *   60 ori/s     |     |  Update  |  -->{ actualizează variabile, stări, poziții ale elementelor grafice etc.
 *       =       |     ------------
 *    16.7 ms    |           |
 *               |     ------------
 *               |     |   Draw   |  -->{ desenează totul pe ecran
 *               |     ------------
 *               |           |
 *               -------------
 *   Implementează interfața Runnable:

 *       public interface Runnable {
 *           public void run();
 *       }
 *
 *   Interfața este utilizată pentru a crea un nou fir de execuție având ca argument clasa Game.
 *   Clasa Game trebuie să aibă definită metoda "public void run()", metoda ce va fi apelată
 *    în noul thread (fir de execuție). Mai multe explicații veți primi la curs.

 *   În mod obișnuit, această clasă trebuie să conțină următoarele:
 *       - public Game();            //constructor
 *       - private void init();      //metoda privată de inițializare
 *       - private void update();    //metoda privată de actualizare a elementelor jocului
 *       - private void draw();      //metoda privată de desenare a tablei de joc
 *       - public run();             //metoda publică ce va fi apelată de noul fir de execuție
 *       - public synchronized void start(); //metoda publică de pornire a jocului
 *       - public synchronized void stop()   //metoda publică de oprire a jocului
 */
public class Game implements Runnable, KeyListener
{
    /** Fereastra în care se va desena tabla jocului:*/
    private GameWindow      wnd;
    /** Flag ce starea firului de execuție.:*/
    private boolean         runState;
    /** Referință către thread-ul de update și draw al ferestrei:*/
    private Thread          gameThread;
    /** Referință către un mecanism cu care se organizează memoria complexă pentru un canvas:*/
    private BufferStrategy  bs;

    /** Referință către un context grafic:*/
    private Graphics        g;

    public Level levelManager;

    private int currentLevel;

    private static Player mainPlayer;

    private static java.util.List<Enemy> enemies;

    private LeaderboardDB leaderboardDBManager;
    private SavegameDB savegameDBManager;

    // Camera
    private static Camera camera; // Poziția X a camerei (offset orizontal)

    private Gravity gravity;


    /**
     Constructor de inițializare al clasei Game.

     Acest constructor primește ca parametri titlul ferestrei, lățimea și înălțimea
     acesteia având în vedere că fereastra va fi construită/creată în cadrul clasei Game.

     @param title Titlul ferestrei.
     @param width Lățimea ferestrei în pixeli.
     @param height Înălțimea ferestrei în pixeli.
     */
    public Game(String title, int width, int height)
    {
        wnd = new GameWindow(title, width, height);
        runState = false;
    }

    public static List<Enemy> getEnemyList() {
        return enemies;
    }

    /**Metoda construiește fereastra jocului, initializează aseturile, listenerul de tastatură etc.

     Fereastra jocului va fi construită prin apelul funcției BuildGameWindow();
     Sunt construite elementele grafice (assets): dale, player, elemente active și pasive.

     */
    private void InitGame()
    {
        camera = new Camera();
        wnd.BuildGameWindow();
        Assets.Init();
        currentLevel = 1;
        levelManager = new Level(currentLevel);
        mainPlayer = new Player(Tile.PlayerIdle0, 100.0, 30.0, 2, 1, 10, 26 * 16, false);
        enemies = levelManager.getEnemiesAndPositions();
        gravity = new Gravity(levelManager);
        leaderboardDBManager = new LeaderboardDB();
        savegameDBManager = new SavegameDB(this);
        // Adaugă clasa Game ca KeyListener pentru canvas
        wnd.GetCanvas().addKeyListener(this);
    }

    /** Functia ce va rula în thread-ul creat.

     Această funcție va actualiza starea jocului și va redesena tabla de joc (va actualiza fereastra grafică)
     */
    public void run()
    {
        InitGame();
        long oldTime = System.nanoTime();
        long curentTime;

        final int framesPerSecond   = 60;
        final double frameTime      = 1000000000d / framesPerSecond;
        while (runState)
        {
            curentTime = System.nanoTime();
            if((curentTime - oldTime) > frameTime)
            {
                Update();
                Draw();
                oldTime = curentTime;
            }
        }

    }

    /** Crează și pornește firul separat de execuție (thread).

     Metoda trebuie să fie declarată synchronized pentru ca apelul acesteia să fie semaforizat.
     */
    public synchronized void StartGame()
    {
        if(!runState)
        {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
        else
        {
            System.err.println("[START_ERR] : Jocul este deja pornit.");
            return;
        }
    }

    /** Oprește execuția thread-ului.

     Metoda trebuie să fie declarată synchronized pentru ca apelul acesteia să fie semaforizat.
     */
    public synchronized void StopGame()
    {
        if(runState == true)
        {
            runState = false;
            try
            {
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            System.err.println("[STOP_ERR] : Jocul este deja oprit sau nu a fost pornit.");
            return;
        }
    }

    /** Actualizează starea elementelor din joc.

     Metoda este declarată privată deoarece trebuie apelată doar în metoda run()
     */
    private void Update()
    {
        if(levelManager.checkLevelLoaded()) {
            gravity.applyGravity(mainPlayer);
            for(Enemy e : enemies) {
                if(e.isActive())
                    gravity.applyGravity(e);
            }
        }

        // Actualizează mișcarea și fizica jucătorului
        mainPlayer.update();

        for(Enemy e : enemies) {
                    //System.err.println("Enemy is dead. " + enemies.indexOf(e));
            e.update();
        }

        // Actualizează poziția camerei pe baza mișcării jucătorului
        camera.Update(mainPlayer.getxPos());

        // Resetarea jucătorului la începutul nivelului dacă depășește limitele hărții
        if(mainPlayer.getyPos() > Level.VTILE_COUNT * 16) {
           mainPlayer.setHealth(0);
           mainPlayer.setDead(true);
        }

        if(mainPlayer.getxPos() > Level.HTILE_COUNT * 16) {
            mainPlayer.addLevelPassBonus();
            if(currentLevel == 3) {
                // Display message box asking for player's name for leaderboard input
                String playerName = JOptionPane.showInputDialog(wnd.GetWnd(), "Congratulations! You won the game.\nPlease enter your name for the leaderboard:");
                leaderboardDBManager.insert(playerName, mainPlayer.getScore());
                // Ask if the player wants to play again
                int option = JOptionPane.showConfirmDialog(wnd.GetWnd(), "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    resetPlayer();
                } else {
                    System.exit(0);
                }
            }
            else {
                proceedToNextLevel();
            }
        }
    }

    /** Desenează elementele grafice în fereastra corespunzător stărilor actualizate ale elementelor.

     Metoda este declarată privată deoarece trebuie apelată doar în metoda run()
     */
    private void Draw() {
        bs = wnd.GetCanvas().getBufferStrategy();
        if(bs == null) {
            try {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        // Desenează fundalul și nivelul cu offset-ul camerei
        levelManager.DrawLevel(g, camera);
        // Calculează poziția jucătorului relativ la cameră
        int playerDrawX = mainPlayer.getxPos() - camera.getxOffset();
        int playerDrawY = mainPlayer.getyPos(); // Presupunând că poziția Y este absolută

        // Desenează jucătorul relativ la cameră
        mainPlayer.draw(g, playerDrawX, playerDrawY);

        if(levelManager.checkLevelLoaded()) {
            for(Enemy e : enemies) {
                int enemyDrawX = e.getxPos() - camera.getxOffset();
                int enemyDrawY = e.getyPos();
                e.draw(g, enemyDrawX, enemyDrawY);
            }
        }

        // Zonă de coliziune actuală a jucătorului: x: offset 24 de la marginea stângă, dimensiune 12
        //                                       y: offset 0 de la partea de sus, dimensiune 48
        //                                       => coliziunea va apărea la [(24, 0), 12 x 48] a dalei jucătorului
        // DEBUG: COLIZIUNE JUCĂTOR CU SOLUL
        if(Main.IS_DEBUG) {
            // Desenam marginile imaginii
            //g.setColor(Color.GREEN);
            //g.drawRect(playerDrawX, playerDrawY, 64, 64);
            // Desenam coliziunea
            g.setColor(Color.BLUE);
            g.drawRect(playerDrawX + Player.COLLISION_XOFFSET, playerDrawY, Player.COLLISION_WIDTH, Player.COLLISION_HEIGHT);
            for(Enemy e: enemies) {
                int enemyDrawX = e.getxPos() - camera.getxOffset();
                int enemyDrawY = e.getyPos();
                g.drawRect(enemyDrawX + Player.COLLISION_XOFFSET, enemyDrawY, Player.COLLISION_WIDTH, Player.COLLISION_HEIGHT);
            }
            // Desenam zona de atac
            g.setColor(Color.RED);
            g.drawRect(playerDrawX + Player.COLLISION_XOFFSET + Player.COLLISION_WIDTH, playerDrawY, 2, Player.COLLISION_HEIGHT);
            g.drawRect(playerDrawX + Player.COLLISION_XOFFSET - 2, playerDrawY, 2, Player.COLLISION_HEIGHT);
            for(Enemy e: enemies) {
                int enemyDrawX = e.getxPos() - camera.getxOffset();
                int enemyDrawY = e.getyPos();
                g.drawRect(enemyDrawX + Player.COLLISION_XOFFSET + Player.COLLISION_WIDTH, enemyDrawY, 2, Player.COLLISION_HEIGHT);
                g.drawRect(enemyDrawX + Player.COLLISION_XOFFSET - 2, enemyDrawY, 2, Player.COLLISION_HEIGHT);
            }

        }

        g.setColor(Color.WHITE);
        g.drawString("Health: " + mainPlayer.getHealth(), 10, 20);
        g.drawString("Score: " + mainPlayer.getScore(), 10, 40);

        if(mainPlayer.isDead()) g.drawString("GAME OVER! YOU DIED. Press F1 to restart.", 480, 360);

        // Afișează strategia de bufferizare
        bs.show();

        // Eliberează contextul grafic
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int playerSpeed = 2; // Ajustați această valoare în funcție de viteza de deplasare a jucătorului

        if (e.getKeyCode() == KeyEvent.VK_D) {
            // Verifică dacă există dale de coliziune la dreapta poziției curente a jucătorului
            if (!levelManager.isTileRightOfRange(mainPlayer.getxPos() + Player.COLLISION_XOFFSET, mainPlayer.getyPos() - 16, Player.COLLISION_WIDTH - 16, Player.COLLISION_HEIGHT)) {
                // Deplasează jucătorul spre dreapta
                mainPlayer.move(Direction.RIGHT, playerSpeed);
                mainPlayer.setDirection(Direction.RIGHT);
                mainPlayer.setVelocityX(1.0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            // Verifică dacă există dale de coliziune la stânga poziției curente a jucătorului
            if (!levelManager.isTileLeftOfRange(mainPlayer.getxPos() + Player.COLLISION_XOFFSET + 16, mainPlayer.getyPos() - 16, Player.COLLISION_HEIGHT)) {
                // Deplasează jucătorul spre stânga
                mainPlayer.move(Direction.LEFT, playerSpeed);
                mainPlayer.setDirection(Direction.LEFT);
                mainPlayer.setVelocityX(-1.0);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_W) {
            if(mainPlayer.isGrounded()) {
                mainPlayer.jump();
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_Q) {
            mainPlayer.punch();
        }
        else if (e.getKeyCode() == KeyEvent.VK_F1) {
            resetPlayer();
            camera.SetPosition(0, 0);
        }
        //add debug to change level
        else if (e.getKeyCode() == KeyEvent.VK_F11)
        {
            if(currentLevel > 1) {
                levelManager.selectLevel(--currentLevel);
                mainPlayer.setxPos(10);
                mainPlayer.setyPos(16 * 16);
                mainPlayer.setHealth(100);
                camera.SetPosition(0, 0);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_F12) {
            proceedToNextLevel();
        }
        else if (e.getKeyCode() == KeyEvent.VK_F2) {
            savegameDBManager.saveGame(mainPlayer.getxPos(), mainPlayer.getyPos(), mainPlayer.getHealth(), mainPlayer.getScore(), currentLevel);
        }
        else if (e.getKeyCode() == KeyEvent.VK_F3) {
            String inputDialog = JOptionPane.showInputDialog(wnd.GetWnd(), "Load game\nPlease enter the desired savegame ID to load:");
            if(inputDialog != null)
                savegameDBManager.loadGame(Integer.parseInt(inputDialog));
        }
        else if (e.getKeyCode() == KeyEvent.VK_F4) {
            JOptionPane.showMessageDialog(wnd.GetWnd(), "Player Name          |      Score       \n------------------------------------------------\n "+ leaderboardDBManager.selectAll(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
        }

        // Actualizează poziția camerei pe baza mișcării jucătorului - necesar!
        // Acest lucru este deja calculat în Update(), însă trebuie să actualizăm camera
        // după fiecare mișcare a jucătorului, pentru a-l menține centrat.
        camera.Update(mainPlayer.getxPos());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
            mainPlayer.setVelocityX(0);
            mainPlayer.update();
        }
    }

    public void resetPlayer() {
        mainPlayer.setDead(false);
        mainPlayer.setHealth(100);
        mainPlayer.setxPos(10);
        mainPlayer.setyPos(16 * 16);
        currentLevel = 1;
        levelManager.selectLevel(currentLevel);
        levelManager.respawnEnemies();
        mainPlayer.resetScore();
        camera.SetPosition(0, 0);
    }

    public void proceedToNextLevel() {
        if(currentLevel < 3) {
            levelManager.selectLevel(++currentLevel);
            mainPlayer.setxPos(10);
            mainPlayer.setyPos(16 * 16);
            mainPlayer.setHealth(100);
            camera.SetPosition(0, 0);
            levelManager.respawnEnemies();
        }
    }

    public static Player getPlayer() { return mainPlayer; }

    public void setEnemyList(List<Enemy> loadedEnemies) {
        enemies = loadedEnemies;
    }
}
