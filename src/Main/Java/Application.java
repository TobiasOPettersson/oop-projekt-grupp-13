import Model.MainModel;
import View.GameView;

/**
 * The class that initializes the model and view and runs the game
 */
public class Application {
    private final double FPS = 60.0;
    MainModel model = new MainModel(); 
    GameView view = new GameView(model);
    
    /**
     * Main loop
     * @param args
     */
    public static void main(String[] args) {
        new Application().run();
    }

    /**
     * Calls the models run-method that updates to the model state each frame.
     */
    private void run() {
        double timePerFrame = 1_000_000_000.0 / FPS;
        long lastFrame = System.nanoTime();
        model.addObserver(view);

        while (true) {
            // Rendering loop
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                model.run();
            }
        }
    }
}
