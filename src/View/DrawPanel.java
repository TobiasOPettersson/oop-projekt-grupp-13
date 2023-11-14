package src.View;

import javax.swing.JPanel;
import java.awt.Graphics;
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
        {"g", "g", "g", "g", "g", "g", "g", "g", "g", "g"},
        {"g", "w", "w", "g", "g", "g", "g", "g", "g", "g"},
        {"g", "w", "w", "g", "p1","p2","p1","g", "g", "g"},
        {"g", "g", "g", "g", "p2","g", "p2","g", "g", "g"},
        {"g", "g", "g", "g", "p2","w", "p2","g", "g", "g"},
        {"p2","p2","p2","p2","p1","w", "p2","g", "g", "g"},
        {"g", "g", "g", "g", "g", "w", "p1","p2","p2","p2"},
        {"g", "g", "g", "g", "w", "w", "g", "g", "g", "g"},
        {"g", "g", "g", "g", "w", "w", "g", "g", "g", "g"},
        {"g", "g", "g", "g", "w", "w", "g", "g", "g", "g"},
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
                if(map[j][i].equals("g")){
                    g.drawImage(sprites.get(grass), i*32, j*32, null);
                }else if(map[j][i].equals("w")){
                    g.drawImage(sprites.get(water), i*32, j*32, null);
                }else if(map[j][i].equals("p1")){
                    g.drawImage(sprites.get(path1), i*32, j*32, null);
                }else if(map[j][i].equals("p2")){
                    g.drawImage(sprites.get(path2), i*32, j*32, null);
                }
            }
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);

    }
}
