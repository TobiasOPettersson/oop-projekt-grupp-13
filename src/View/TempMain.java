package src.View;

import src.View.GameView;

import java.util.ArrayList;

import src.Model.MainModel;

public class TempMain {
    // Frame rate
    private final double FPS = 60.0;
    // Update rate, will e done in model later
    private final double UPS = 60.0;
    double speedMul = 0.34;
    int deltaX = 0;
    int deltaY = 0;
    double enemyX = 0;
    double enemyY = 240;

    // MainModel model = new MainModel();
    GameView view = new GameView(this);

    public static void main(String[] args) {
        new TempMain().run();
    }

    public Position moveEnemy(ArrayList<DirChange> dirArray) {
        if(dirArray.size() > 1){
        DirChange currentDir = dirArray.get(0);
        DirChange nextDir = dirArray.get(1);
        String direction = currentDir.getDir();
        double nextX = nextDir.getX();
        double nextY = nextDir.getY();

        switch (direction) {
            case ">":
                deltaY = 0;
                deltaX = 1;
                if (enemyX >= nextX && dirArray.size() > 1) {
                    dirArray.remove(0);
                } else {
                    enemyX += deltaX * speedMul;
                    return new Position(enemyX, enemyY);
                }
                break;
            case "^":
                deltaY = 1;
                deltaX = 0;
                if (enemyY <= nextY && dirArray.size() > 1) {
                    dirArray.remove(0);
                } else {
                    enemyY -= deltaY * speedMul;
                    return new Position(enemyX, enemyY);
                }
                break;
            case "v":
                deltaY = 1;
                deltaX = 0;
                if (enemyY >= nextY && dirArray.size() > 1) {
                    dirArray.remove(0);
                } else {
                    enemyY += deltaY * speedMul;
                    return new Position(enemyX, enemyY);
                }
        }
    }
        return new Position(enemyX, enemyY);
    }

    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS;

        long lastFrame = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        int frames = 0;

        while (true) {
            // Rendering loop
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                view.update();
                // frames++;
            }

            // Do this every second
            /*
             * if (System.currentTimeMillis() - lastTimeCheck >= 1_000) {
             * System.out.println("FPS: " + frames);
             * frames = 0;
             * }
             */
        }

    }
}
