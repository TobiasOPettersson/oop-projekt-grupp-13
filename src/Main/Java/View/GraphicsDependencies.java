package View;

/**
 * A class containing global variables
 */
public class GraphicsDependencies {
    // Window size
    private static final int WIDTH = 960;
    private static final int HEIGHT = 800;
    
    // Y where tower controller should be created
    private static final int BOTTOMPANEL = 190; // Kanske inte ska användas
    
    // Sprite size in pixels
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

    public static int getSpriteSize() {
        return SPRITESIZE;
    }

}
