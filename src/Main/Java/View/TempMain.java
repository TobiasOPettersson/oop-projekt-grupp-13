package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import Model.MainModel;

import View.GameView;

import java.util.ArrayList;

import Model.MainModel;

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
        // This if is there to ensure that nextNode won't trigger an index out of bounds
        // when the array only has 1 element
        if (dirNodeArray.size() > 1) {
            DirNode currentNode = dirNodeArray.get(0);
            DirNode nextNode = dirNodeArray.get(1);
            String currentDirection = currentNode.getDir();
            // nextX and nextY are the coordinates for the next node, the node the enemies
            // are walking towards
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

/*
TrainCanvas enemy = new TrainCanvas();
view.getContentPane().add(enemy);
Teststuff for *enemyInRange*
class TrainCanvas extends JComponent {
    private int lastX = 0;

    public TrainCanvas() {
        Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    repaint(); 
                    double distance = Math.sqrt(Math.pow(600 - lastX, 2) + Math.pow(440 - (getHeight()/2 + 10), 2));
                    if (distance <= 100){
                        System.out.println("Enemy in range when at (" + lastX + ", " + (getHeight()/2 + 10) + ")");
                    }
                    try {Thread.sleep(10);} catch (Exception ex) {}
                }
            }
        });
        animationThread.start();
    }

    public void paintComponent(Graphics g) {
        Graphics2D gg = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        int trainW = 20;
        int trainH = 20;
        int trainSpeed = 3;

        int x = lastX + trainSpeed;

        if (x > w + trainW) {
            x = -trainW;
        }

        gg.setColor(Color.BLACK);
        gg.fillRect(x, h/2 + trainH, trainW, trainH);

        lastX = x;

        gg.setColor(Color.blue);
        gg.fillOval(500,340,200,200);
    }
}*/