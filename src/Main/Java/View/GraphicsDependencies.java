package View;

// TODO Javadoc comments

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

    /**
     * Frame Width
     * 
     * @return
     */
    public static int getWidth() {
        return WIDTH;
    }

    /**
     * Frame Height
     * 
     * @return
     */
    public static int getHeight() {
        return HEIGHT;
    }

    /**
     * Y coordinates of TowerPanel
     * @return
     */
    public static int getBottomPanel() {
        return BOTTOMPANEL;
    }

    /**
     * Size of sprites
     * @return
     */
    public static int getSpriteSize() {
        return SPRITESIZE;
    }

}
