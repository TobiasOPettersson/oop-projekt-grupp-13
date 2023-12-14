import Model.MainModel;
import View.GameView;

// TODO Javadoc comments

public class Application {
    private final double FPS = 60.0;
    MainModel model = new MainModel(); 
    GameView view = new GameView(model);
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Application().run();
    }

    /**
     * 
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
