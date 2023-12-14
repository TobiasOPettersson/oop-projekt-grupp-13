package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * A class that draws game info such as lives, money, the loss- and win screen
 */
public class DrawGameInfo {
    private final int SPRITESIZE = GraphicsDependencies.getSpriteSize();
    private final int FRAMEWIDTH = GraphicsDependencies.getWidth();
    private final int FRAMEHEIGHT = GraphicsDependencies.getHeight();
    
    /**
     * Constructor
     * @param model The main model
     */
    public DrawGameInfo() {
    }

    /**
     * Draw all game information on the screen
     * @param g Graphics
     */
    public void draw(Graphics g, int playerHealth, int playerMoney, int currentWaveNumber, int maxNumberOfWaves, int mapSizeX, boolean playerAlive, boolean activeWave, boolean allWavesDead) {
        drawInfoBackground(g, mapSizeX);
        drawPlayerHealth(g, playerHealth);
        drawPlayerMoney(g, playerMoney);
        drawWaveNumber(g, currentWaveNumber, maxNumberOfWaves, mapSizeX);
        drawEndScreen(g, playerAlive, activeWave, allWavesDead);
    }

    /**
     * Draw how much health the player has on the screen
     * @param g Graphics
     */
    private void drawPlayerHealth(Graphics g, int playerHealth) {
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Health: " + playerHealth, 0, SPRITESIZE / 2 + 6);
    }

    /**
     * Draw how much money the player currently has
     * @param g Graphics
     */
    private void drawPlayerMoney(Graphics g, int playerMoney) {
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Money: " + playerMoney, 0, SPRITESIZE + SPRITESIZE / 2 + 6);
    }

    /**
     * Draw how many waves are done and how many there are left
     * @param g Graphics
     */
    private void drawWaveNumber(Graphics g, int currentWaveNumber, int maxNumberOfWaves, int mapSizeX) {
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Round: " + currentWaveNumber + "/" + maxNumberOfWaves,
                mapSizeX * SPRITESIZE - SPRITESIZE * 3, SPRITESIZE / 2 + 6);
    }

    /**
     * Draw rectangle
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
     * Draw You Won if the player wins
     * @param g
     */
    private void drawEndScreen(Graphics g, boolean playerAlive, boolean activeWave, boolean allWavesDead) {
        if (!playerAlive) {
            drawLostScreeen(g);
        }
        if (!activeWave && allWavesDead && playerAlive) {
            drawWonScreeen(g);
        }
    }

    /**
     * Draw winner screen
     * @param g Graphics
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
     * @param g Graphics
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
     * @param g     Graphics
     * @param text  The text to draw
     */
    private void drawCenteredText(Graphics g, String text) {
        int messageWidth = g.getFontMetrics().stringWidth(text);
        int x = (FRAMEWIDTH - messageWidth) / 2;
        int y = FRAMEHEIGHT / 3;
        g.drawString(text, x, y);
    }

    /**
     * Draw background for Health, coins and waves
     * @param g Graphics object
     */
    private void drawInfoBackground(Graphics g, int mapSizeX) {
        Color background = new Color(108, 108, 236, 220);
        // Rectangle 1 Health and Coins
        int rect1X = 0;
        int rect1Y = 0;
        int width1 = SPRITESIZE * 3;
        int height1 = SPRITESIZE * 2;
        drawRect(rect1X, rect1Y, width1, height1, background, g);

        // Rectangle 2 Waves
        int rect2X = (mapSizeX - 3) * SPRITESIZE;
        int rect2Y = 0;
        int width2 = SPRITESIZE * 3;
        int height2 = SPRITESIZE;
        drawRect(rect2X, rect2Y, width2, height2, background, g);
    }
}
