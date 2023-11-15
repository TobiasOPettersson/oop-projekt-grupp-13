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
            { "g", "g", "g", "g", "g", "g", "g", "g", "g", "g" },
            { "g", "w", "w", "g", "g", "g", "g", "g", "g", "g" },
            { "g", "w", "w", "g", "^>", ">", ">v", "g", "g", "g" },
            { "g", "g", "g", "g", "^", "g", "v", "g", "g", "g" },
            { "g", "g", "g", "g", "^", "w", "v", "g", "g", "g" },
            { ">", ">", ">", ">", ">^", "w", "v", "g", "g", "g" },
            { "g", "g", "g", "g", "g", "w", "v>", ">", ">", ">" },
            { "g", "g", "g", "g", "w", "w", "g", "g", "g", "g" },
            { "g", "g", "g", "g", "w", "w", "g", "g", "g", "g" },
            { "g", "g", "g", "g", "w", "w", "g", "g", "g", "g" },
    };

    // Constructor
    public DrawPanel(MainModel model, BufferedImage image) {
        this.image = image;
        loadSprites();
    }

    // Create Sprite sheet
    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(image.getSubimage(x * 32, y * 32, 32, 32));
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

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                switch (map[j][i]) {
                    case "g":
                        g.drawImage(sprites.get(grass), i * 32, j * 32, null);
                        break;
                    case "w":
                        g.drawImage(sprites.get(water), i * 32, j * 32, null);
                        break;
                    case ">":
                        g.drawImage(sprites.get(path2), i * 32, j * 32, null);
                        break;
                    case ">^":
                        g.drawImage(SpriteRotator.rotateSprite(sprites.get(path1), 180), i * 32, j * 32, null);
                        break;
                    case "^":
                        g.drawImage(SpriteRotator.rotateSprite(sprites.get(path2), 90), i * 32, j * 32, null);
                        break;
                    case "v":
                        g.drawImage(SpriteRotator.rotateSprite(sprites.get(path2), 90), i * 32, j * 32, null);
                        break;
                    case "^>":
                        g.drawImage(sprites.get(path1), i * 32, j * 32, null);
                        break;
                    case ">v":
                        g.drawImage(SpriteRotator.rotateSprite(sprites.get(path1), 90), i * 32, j * 32, null);
                        break;
                    case "v>":
                        g.drawImage(SpriteRotator.rotateSprite(sprites.get(path1), -90), i * 32, j * 32, null);
                        break;
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        // BufferedImage RSprite = SpriteRotator.rotateSprite90Degree(sprites.get(8));

    }
}
