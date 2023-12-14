package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Model.MainModel;
import Model.Enums.Direction;
import Model.Map.ATile;

// TODO ta bort att DrawMap har en pathDirections och mapGrid och använd getters istället, onödiga pilar
public class DrawMap {
    protected WorldSpriteManager worldSpriteManager = new WorldSpriteManager();
    private final int SPRITESIZE = GraphicsDependencies.getSpriteSize();
    private ArrayList<BufferedImage> pathSprites = new ArrayList<>();

    public DrawMap(List<Direction> pathDirections) {
        createPathSprites(pathDirections);

    }

    /**
     * Draws the map to the screen according to the blueprint
     * 
     * @param g Graphics
     */
    protected void drawTerrain(Graphics g, ATile mapGrid[][], int gridHeight, int gridWidth) {
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                switch (mapGrid[j][i].getTerrain()) {
                    case Plains:
                        g.drawImage(worldSpriteManager.getTileSprite(), i * SPRITESIZE, j * SPRITESIZE, null);
                        break;
                    case Water:
                        g.drawImage(worldSpriteManager.getTileSprite(), i * SPRITESIZE, j * SPRITESIZE, null);
                        break;
                    case Forrest:
                        g.drawImage(worldSpriteManager.getTileSprite(), i * SPRITESIZE, j * SPRITESIZE, null);
                        break;
                    case Mountains:
                        g.drawImage(worldSpriteManager.getTileSprite(), i * SPRITESIZE, j * SPRITESIZE, null);
                        break;
                    case Kitchen:
                        g.drawImage(worldSpriteManager.getTileSprite(), i * SPRITESIZE, j * SPRITESIZE, null);
                        break;
                    default:
                        g.drawImage(worldSpriteManager.getTileSprite(), i * SPRITESIZE, j * SPRITESIZE, null);
                        break;
                }
            }
        }
    }

    /**
     * Creates an array of sprites oriented the correct way and in the correct order
     * according
     * to the pathDirection array
     */
    private void createPathSprites(List<Direction> pathDirections) {
        for (int i = 1; i < pathDirections.size(); i++) {
            pathSprites.add(
                    worldSpriteManager.getPathTurn(pathDirections.get(i - 1), pathDirections.get(i)));
        }
    }

    /**
     * Draw enemy path to the screen
     * Uses the array pathsprites which has all the path sprites
     * in the correct orrientation and correct order from left side of screen to
     * right side
     * 
     * @param g
     */
    protected void drawPath(Graphics g, int[][] pathGrid, int gridWidth, int gridHeight) {
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                if (pathGrid[j][i] != 0) {
                    g.drawImage(pathSprites.get(pathGrid[j][i] - 1), i * SPRITESIZE, j * SPRITESIZE, null);
                }
            }
        }
    }

    /**
     * Draw start position for enemy path
     * 
     * @param g Graphics object
     */
    protected void drawStartPosition(Graphics g, int pathStartTile) {
        Color enemyColor = new Color(0, 200, 39, 80);
        int rectX = 0;
        int rectY = (pathStartTile * SPRITESIZE) - SPRITESIZE;
        int width = SPRITESIZE;
        int height = SPRITESIZE * 3;
        drawRect(rectX, rectY, width, height, enemyColor, g);
    }

    /**
     * Draw end position for enemy path
     * 
     * @param g Graphics object
     */
    protected void drawEndPosition(Graphics g, int mapSizeX, int pathEndTile) {
        Color homeColor = new Color(200, 0, 0, 80);
        int rectY = (pathEndTile * SPRITESIZE) - SPRITESIZE;
        int rectX = (mapSizeX * SPRITESIZE) - SPRITESIZE;
        int width = SPRITESIZE;
        int height = SPRITESIZE * 3;
        drawRect(rectX, rectY, width, height, homeColor, g);
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

}
