package src.View;

import src.View.GameView;

import java.util.ArrayList;

import src.Model.MainModel;

public class TempMain {
    // Frame rate
    private final double FPS = 60.0;
    // speed är hur många pixlar fienden flyttar sig per update
    double speed = 1.0;
    double enemyX = 0;
    double enemyY = 240;

    // MainModel model = new MainModel();
    GameView view = new GameView(this);

    public static void main(String[] args) {
        new TempMain().run();
    }

    public Position moveEnemy(ArrayList<DirNode> dirNodeArray) {
        // This if is there to ensure that nextNode won't trigger an index out of bounds when the array only has 1 element
        if(dirNodeArray.size() > 1){
        DirNode currentNode = dirNodeArray.get(0);
        DirNode nextNode = dirNodeArray.get(1);
        String currentDirection = currentNode.getDir();
        // nextX and nextY are the coordinates for the next node, the node the enemies are walking towards
        double nextX = nextNode.getX();
        double nextY = nextNode.getY();

        switch (currentDirection) {
            case ">":
                if (enemyX >= nextX) {
                    dirNodeArray.remove(0);
                } else {
                    enemyX += speed;
                    return new Position(enemyX, enemyY);
                }
                break;
            case "^":
                if (enemyY <= nextY) {
                    dirNodeArray.remove(0);
                } else {
                    enemyY -= speed;
                    return new Position(enemyX, enemyY);
                }
                break;
            case "v":
                if (enemyY >= nextY) {
                    dirNodeArray.remove(0);
                } else {
                    enemyY += speed;
                    return new Position(enemyX, enemyY);
                }
        }
    }
        return new Position(enemyX, enemyY);
    }

    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS;
        long lastFrame = System.nanoTime();

        while (true) {
            // Rendering loop
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                view.update();
            }
        }

    }
}
