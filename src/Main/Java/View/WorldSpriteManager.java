package View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Model.Enums.TileTerrain;

public class WorldSpriteManager {
    private HashMap<TileTerrain, BufferedImage> worldSprites;

    public WorldSpriteManager(){
        worldSprites = new HashMap<>();
        importTowerImg();
    }

    public BufferedImage[] getWorldSprites(TileTerrain TileTerrain) {
        BufferedImage sprite = worldSprites.get(TileTerrain);
        BufferedImage[] spriteArray = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            spriteArray[i] = sprite.getSubimage(i * 48, 0, 48, 48);
        }
        return spriteArray;
    }

    private void importTowerImg() {
        InputStream isKitchenTiles = this.getClass().getResourceAsStream("res/Tiles.png");
        

        try {
            worldSprites.put(TileTerrain.Plains, ImageIO.read(isKitchenTiles));
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
