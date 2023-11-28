package View;

public class GraphicsDependencies {
    // Window size
    private static final int WIDTH = 960;
    private static final int HEIGHT = 800;
    // Frame rate for View
    private static final int FRAMERATE = 20;
    // Y where tower controller should be created
    private static final int BOTTOMPANEL = 190; // Kanske inte ska användas
    // sprite size in pixels
    private static final int SPRITESIZE = 48;
    

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getBottomPanel() {
        return BOTTOMPANEL;
    }

}
