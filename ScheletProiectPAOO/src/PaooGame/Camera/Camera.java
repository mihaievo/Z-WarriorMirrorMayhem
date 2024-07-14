package PaooGame.Camera;

/**
 * Clasa care implementeaza functionalitatea camerei in joc.
 * Camerele sunt utilizate pentru a controla vizualizarea jocului pe ecran.
 */
public class Camera {
    private int xOffset;  // Coordonata x a camerei
    // yOffset nu va fi folosit, deoarece jocul este de tip side-scroller
    private int yOffset;  // Coordonata y a camerei

    private static final int xViewSize = 1280; //px  // Latimea vizibila a camerei in pixeli
    private static final int yViewSize = 720; //px   // Inaltimea vizibila a camerei in pixeli

    /**
     * Constructor pentru clasa Camera.
     * Initializeaza offset-ul camerei la (0, 0).
     */
    public Camera() {
        xOffset = 0;
        yOffset = 0;
    }

    /**
     * Seteaza pozitia camerei la coordonatele date.
     * @param xOffset Coordonata x la care se va poziționa camera.
     * @param yOffset Coordonata y la care se va poziționa camera.
     */
    public void SetPosition(final int xOffset, final int yOffset) {
        if(xOffset >= 0 && xOffset <= 4 * xViewSize && yOffset <= 0) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }
    }

    /**
     * Actualizeaza pozitia camerei pe baza pozitiei obiectului focalizat.
     * @param focusObjectXPos Pozitia pe axa x a obiectului focalizat.
     */
    public void Update(int focusObjectXPos) {
        int cameraWidth = this.getxViewSize();
        int halfCameraWidth = cameraWidth / 2;

        // Calculeaza pozitia x a camerei pentru a centra pe jucator
        int targetCameraX = focusObjectXPos - halfCameraWidth;

        // Seteaza pozitia camerei pentru a pastra centrarea pe jucator
        this.SetPosition(targetCameraX + 64, this.getyOffset());  // Presupunand ca 64 este marimea dorita a obiectului focalizat
    }

    /**
     * Returneaza coordonata x a camerei.
     * @return Coordonata x a camerei.
     */
    public int getxOffset() {
        return xOffset;
    }

    /**
     * Returneaza coordonata y a camerei.
     * @return Coordonata y a camerei.
     */
    public int getyOffset() {
        return yOffset;
    }

    /**
     * Returneaza latimea vizibila a camerei (pe axa X).
     * @return Latimea vizibila a camerei.
     */
    public int getxViewSize() {
        return xViewSize;
    }

    /**
     * Returneaza inaltimea vizibila a camerei (pe axa Y).
     * @return Inaltimea vizibila a camerei.
     */
    public int getyViewSize() {
        return yViewSize;
    }
}
