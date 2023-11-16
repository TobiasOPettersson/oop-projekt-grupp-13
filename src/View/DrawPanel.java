package src.View;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;

public class DrawPanel extends JPanel {
    private BufferedImage image;
    private TempMain model;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    public ArrayList<DirChange> dirChangeArray = new ArrayList<>();

    // Constructor
    public DrawPanel(TempMain model, BufferedImage image) {
        this.image = image;
        this.model = model;
        loadSprites();
        setPath(10, 20, 48);
        update();
    }

    // Map blueprint
    // "g" = Grass = sprites.get(18)
    // "w" = Water = sprites.get(60)
    // ">" = Path2 = sprites.get(9)
    // ">^" = Path1 * 180 degree = sprites.get(8)
    // ">v" = Path1 * 90 degree = sprites.get(8)
    // "^>" = Path1 = sprites.get(8)
    // "v>" = Path1 * (-)90 degree = sprites.get(8)
    // "^" = Path2 * 90 degree = sprites.get(9)
    // "v" = Path2 * 90 degree = sprites.get(9)

    private String[][] map = {
            { "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "w", "w", "w", "w", "w" },
            { "g", "w", "w", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "w", "w", "w", "w", "g" },
            { "g", "w", "w", "g", "^>", ">", ">v", "g", "g", "g", "g", "g", "g", "g", "w", "w", "w", "w", "g", "g" },
            { "g", "g", "g", "g", "^", "g", "v", "g", "g", "g", "g", "g", "g", "g", "^>", ">", ">v", "g", "g", "g" },
            { "g", "g", "g", "g", "^", "w", "v", "g", "g", "g", "g", "g", "g", "g", "^", "w", "v", "g", "g", "g" },
            { ">", ">", ">", ">", ">^", "w", "v", "g", "g", "g", "g", "g", "g", "g", "^", "g", "v", "g", "g", "g" },
            { "g", "g", "g", "g", "g", "w", "v>", ">", ">", ">", ">", ">", ">", ">", ">^", "g", "v>", ">", ">", ">" },
            { "g", "g", "g", "g", "w", "w", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g" },
            { "g", "g", "g", "g", "w", "w", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g" },
            { "g", "g", "g", "g", "w", "w", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g" },
    };

    private void setPath(int heightOfMap, int widthOfMap, int spriteSize) {
        setStartPos(heightOfMap, spriteSize);
        setPathPos(heightOfMap, 20, spriteSize);
        setEndPos(heightOfMap, widthOfMap, spriteSize);
        // setSuperEndPos(heightOfMap, widthOfMap);

    }

    private void setStartPos(int heightOfMap, int spriteSize) {
        for (int i = 0; i < heightOfMap; i++) {
            if (map[i][0].equals(">")) {
                dirChangeArray.add(new DirChange(0, i * spriteSize, ">"));
            }
        }
    }

    private void setEndPos(int heightOfMap, int widthOfMap, int spriteSize) {
        for (int i = 0; i < heightOfMap; i++) {
            if (map[i][widthOfMap - 1].equals(">")) {
                dirChangeArray.add(new DirChange(widthOfMap * spriteSize + 10, i * spriteSize, ">"));
            }
        }
    }

    /*
     * private void setSuperEndPos(int heightOfMap, int widthOfMap) {
     * dirChangeArray.add(new DirChange(widthOfMap+10, heightOfMap/2, ">"));
     * }
     */

    private void setPathPos(int heightOfMap, int widthOfMap, int spriteSize) {
        for (int i = 0; i < widthOfMap; i++) {
            for (int j = 0; j < heightOfMap; j++) {
                DirChange prevChange = dirChangeArray.get(dirChangeArray.size() - 1);
                switch (prevChange.getDir()) {
                    case ">":
                        if (map[j][i].equals(">^")) {
                            dirChangeArray.add(new DirChange(i * spriteSize, j * spriteSize, "^"));
                        } else if (map[j][i].equals(">v")) {
                            dirChangeArray.add(new DirChange(i * spriteSize, j * spriteSize, "v"));
                        }
                        break;
                    case "^":
                        if (map[j][i].equals(">")) {
                            dirChangeArray.add(new DirChange(i * spriteSize, j * spriteSize, ">"));
                        }
                        break;
                    case "v":
                        if (map[j][i].equals("v>")) {
                            dirChangeArray.add(new DirChange(i * spriteSize, j * spriteSize, ">"));
                        }
                }
            }
        }
    }

    // Create Sprite sheet by taking subimages from image and then scale them
    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                // Get the subimage that is 32x32 and scale it before putting it in the array of
                // sprites
                sprites.add(SpriteHelper.scaleSprite(image.getSubimage(x * 32, y * 32, 32, 32), 1.5));
            }
        }
    }

    // Draws the map to the screen according to the blueprint
    private void drawMap(Graphics g) {
        // Grass = sprites.get(18)
        int grass = 18;
        // Water = sprites.get(60)
        int water = 60;
        // path1 = sprites.get(8)
        int path1 = 8;
        // path2 = sprites.get(9)
        int path2 = 9;
        // size of sprites, originalsize * scale = 32*1.5 = 48
        int sSize = 48;

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 20; i++) {
                switch (map[j][i]) {
                    case "g":
                        g.drawImage(sprites.get(grass), i * sSize, j * sSize, null);
                        break;
                    case "w":
                        g.drawImage(sprites.get(water), i * sSize, j * sSize, null);
                        break;
                    case ">":
                        g.drawImage(sprites.get(path2), i * sSize, j * sSize, null);
                        break;
                    case ">^":
                        g.drawImage(SpriteHelper.rotateSprite(sprites.get(path1), 180), i * sSize, j * sSize, null);
                        break;
                    case "^":
                        g.drawImage(SpriteHelper.rotateSprite(sprites.get(path2), 90), i * sSize, j * sSize, null);
                        break;
                    case "v":
                        g.drawImage(SpriteHelper.rotateSprite(sprites.get(path2), 90), i * sSize, j * sSize, null);
                        break;
                    case "^>":
                        g.drawImage(sprites.get(path1), i * sSize, j * sSize, null);
                        break;
                    case ">v":
                        g.drawImage(SpriteHelper.rotateSprite(sprites.get(path1), 90), i * sSize, j * sSize, null);
                        break;
                    case "v>":
                        g.drawImage(SpriteHelper.rotateSprite(sprites.get(path1), -90), i * sSize, j * sSize, null);
                        break;
                }
            }
        }
    }

    void drawEnemy(Graphics g) {

        Position position = model.moveEnemy(dirChangeArray);
        g.drawImage(sprites.get(28), (int) position.getX(), (int) position.getY(), null);

    }

    public void update() {

        repaint();
    }

    public int walk = 0;
    int c = 1;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);

        // Testing
        /*
         * if (c == 1)
         * for (DirChange item : dirChangeArray) {
         * System.out.println("x: " + item.getX() + ", y: " + item.getY() + ", dir: " +
         * item.getDir());
         * c = 0;
         * }
         */
        drawEnemy(g);

    }
}
