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
     * 
     * @return
     */
    public static int getWidth() {
        return WIDTH;
    }

    /**
     * 
     * @return
     */
    public static int getHeight() {
        return HEIGHT;
    }

    /**
     * 
     * @return
     */
    public static int getBottomPanel() {
        return BOTTOMPANEL;
    }

}
