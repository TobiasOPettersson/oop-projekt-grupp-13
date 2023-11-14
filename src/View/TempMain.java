package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import Model.MainModel;


public class TempMain {
    public static void main(String[] args){
        MainModel model = new MainModel();
        GameView view = new GameView(model);
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