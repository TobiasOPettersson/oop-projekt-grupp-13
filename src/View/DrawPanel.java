package src.View;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;

import src.Model.MainModel;

public class DrawPanel extends JPanel {
    private BufferedImage image;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    // Map blueprint
    // "g" = Grass = sprites.get(18)
    // "w" = Water = sprites.get(60)
    // "p1" = Path1 = sprites.get(8)
    // "p2" = Path2 = sprites.get(9)
    private String[][] map = {
            { "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "w", "w", "w", "w", "w" },
            { "g", "w", "w", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "w", "w", "w", "w", "g" },
            { "g", "w", "w", "g", "^>", ">", ">v", "g", "g", "g", "g", "g", "g", "g", "w", "w", "w", "w", "g", "g" },
            { "g", "g", "g", "g", "^", "g", "v", "g", "g", "g", "g", "g", "g", "g", "^>", ">", ">v", "g", "g", "g" },
            { "g", "g", "g", "g", "^", "w", "v", "g", "g", "g", "g", "g", "g", "g", "^", "w", "v", "g", "g", "g" },
            { ">", ">", ">", ">", ">^", "w", "v", "g", "g", "g", "g", "g", "g", "g", "^", "g", "v", "g", "g", "g"  },
            { "g", "g", "g", "g", "g", "w", "v>", ">", ">", ">", ">", ">", ">", ">", ">^", "g", "v>", ">", ">", ">"  },
            { "g", "g", "g", "g", "w", "w", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g"  },
            { "g", "g", "g", "g", "w", "w", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g"  },
            { "g", "g", "g", "g", "w", "w", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g"  },
    };

    // Constructor
    public DrawPanel(MainModel model, BufferedImage image) {
        this.image = image;
        loadSprites();

        update();
    }

    // Create Sprite sheet by taking subimages from image and then scale them
    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                // Get the subimage that is 32x32 and scale it before putting it in the array of sprites
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
    void drawEnemy(Graphics g, int walk){
        int x = walk;
        int y = 235;

        g.drawImage(sprites.get(28), x, y, null);

    }

    public void update(){
        repaint();
    } 
    public int walk = 0;
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        walk += 5; 
        drawEnemy(g,walk);
        // BufferedImage RSprite = SpriteRotator.rotateSprite90Degree(sprites.get(8));

    }
}
