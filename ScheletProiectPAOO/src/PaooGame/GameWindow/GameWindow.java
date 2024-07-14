package PaooGame.GameWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Implementeaza notiunea de fereastra a jocului.
 * Membrul wndFrame este un obiect de tip JFrame care va avea utilitatea unei
 * ferestre grafice si totodata si cea a unui container (toate elementele
 * grafice vor fi continute de fereastra).
 */
public class GameWindow {
    /** Fereastra principala a jocului. */
    private JFrame wndFrame;
    /** Titlul ferestrei. */
    private String wndTitle;
    /** Latimea ferestrei in pixeli. */
    private int wndWidth;
    /** Inaltimea ferestrei in pixeli. */
    private int wndHeight;
    /** "Panza/tablou" in care se poate desena. */
    private Canvas canvas;

    /**
     * Constructorul cu parametri al clasei GameWindow.
     * Retine proprietatile ferestrei (titlu, latime, inaltime)
     * in variabilele membre deoarece vor fi necesare pe parcursul jocului.
     * Crearea obiectului va trebui urmata de crearea ferestrei propriu-zise
     * prin apelul metodei BuildGameWindow().
     *
     * @param title  Titlul ferestrei.
     * @param width  Latimea ferestrei in pixeli.
     * @param height Inaltimea ferestrei in pixeli.
     */
    public GameWindow(String title, int width, int height) {
        wndTitle = title;
        wndWidth = width;
        wndHeight = height;
        wndFrame = null;
    }

    /**
     * Construieste/creaza fereastra si seteaza toate proprietatile necesare:
     * dimensiuni, pozitionare in centrul ecranului, operatia de inchidere,
     * invalideaza redimensionarea ferestrei, afiseaza fereastra.
     */
    public void BuildGameWindow() {
        if (wndFrame != null) {
            return;
        }
        wndFrame = new JFrame(wndTitle);
        wndFrame.setSize(wndWidth, wndHeight);
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wndFrame.setResizable(false);
        wndFrame.setLocationRelativeTo(null);
        wndFrame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));
        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));
        wndFrame.add(canvas);
        wndFrame.pack();
    }

    /** Returneaza latimea ferestrei. */
    public int GetWndWidth() {
        return wndWidth;
    }

    /** Returneaza inaltimea ferestrei. */
    public int GetWndHeight() {
        return wndHeight;
    }

    /** Returneaza referinta catre canvas-ul din fereastra pe care se poate desena. */
    public Canvas GetCanvas() {
        return canvas;
    }

    public Component GetWnd() {
        return wndFrame;
    }
}
