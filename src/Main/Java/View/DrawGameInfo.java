package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import Model.MainModel;

public class DrawGameInfo {
    private final int SPRITESIZE = GraphicsDependencies.getSpriteSize();
    private final int FRAMEWIDTH = GraphicsDependencies.getWidth();
    private final int FRAMEHEIGHT = GraphicsDependencies.getHeight();

    private MainModel model;
    

    public DrawGameInfo(MainModel model) {
        this.model = model;
    }

    /**
     * Draw inportant game inforation on the screen
     * 
     * @param g
     */
    public void draw(Graphics g) {
        drawInfoBackground(g);
        drawPlayerHealth(g);
        drawPlayerMoney(g);
        drawWaveNumber(g);
        drawEndScreen(g);

    }

    /**
     * Draw how much health the player has on the screen
     * 
     * @param g
     */
    private void drawPlayerHealth(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Health: " + model.getPlayerHealth(), 0, SPRITESIZE / 2 + 6);
    }

    /**
     * Draw how much money the player currently has
     * 
     * @param g
     */
    private void drawPlayerMoney(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Money: " + model.getPlayerMoney(), 0, SPRITESIZE + SPRITESIZE / 2 + 6);
    }

    /**
     * Draw how many waves are done and how many there are left
     * 
     * @param g
     */
    private void drawWaveNumber(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Round: " + model.getCurrentWaveNumber() + "/" + model.getMaxNumberofWaves(),
                model.getMapSizeX() * SPRITESIZE - SPRITESIZE * 3, SPRITESIZE / 2 + 6);
    }

    /**
     * Draw rectangle
     * 
     * @param x      X value
     * @param y      y value
     * @param width  width of rectange
     * @param height height of rectange
     * @param color  color of rectangle
     * @param g      Graphics object
     */
    private void drawRect(int X, int Y, int width, int height, Color color, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillRect(X, Y, width, height);
    }

    /**
     * Draw end screen
     * Draw You Lost if the player dies
     * Draw You Won if the player winns
     * 
     * @param g
     */
    private void drawEndScreen(Graphics g) {
        if (!model.getAlive()) {
            drawLostScreeen(g);
        }
        if (!model.getActiveWave() && model.allWavesDead() && model.getAlive()) {
            drawWonScreeen(g);
        }
    }

    /**
     * Draw winner screen
     * 
     * @param g
     */
    private void drawWonScreeen(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        drawCenteredText(g, "YOU WON!");
    }

    /**
     * Draw looser screen
     * 
     * @param g
     */
    private void drawLostScreeen(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        drawCenteredText(g, "YOU LOST!");
    }

    /**
     * Helper method to draw end text in the middle of the screen
     * 
     * @param g
     * @param text
     */
    private void drawCenteredText(Graphics g, String text) {
        int messageWidth = g.getFontMetrics().stringWidth(text);
        int x = (FRAMEWIDTH - messageWidth) / 2;
        int y = FRAMEHEIGHT / 2;
        g.drawString(text, x, y);
    }

    /**
     * Draw background for Health, coins and waves
     * 
     * @param g Graphics object
     */
    private void drawInfoBackground(Graphics g) {
        Color background = new Color(108, 108, 236, 220);
        // Rectangle 1 Health and Coins
        int rect1X = 0;
        int rect1Y = 0;
        int width1 = SPRITESIZE * 3;
        int height1 = SPRITESIZE * 2;
        drawRect(rect1X, rect1Y, width1, height1, background, g);

        // Rectangle 2 Waves
        int rect2X = (model.getMapSizeX() - 3) * SPRITESIZE;
        int rect2Y = 0;
        int width2 = SPRITESIZE * 3;
        int height2 = SPRITESIZE;
        drawRect(rect2X, rect2Y, width2, height2, background, g);
    }
}
