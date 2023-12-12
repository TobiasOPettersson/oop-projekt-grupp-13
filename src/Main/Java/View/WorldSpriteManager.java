package View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import Model.Enums.Direction;
import Model.Enums.TileTerrain;


import javax.imageio.ImageIO;


public class WorldSpriteManager {
    private HashMap<TileTerrain, BufferedImage> worldSprites;

    public WorldSpriteManager(){
        worldSprites = new HashMap<>();
        importTowerImg();
    }

    public BufferedImage[] getWorldSprites(TileTerrain TileTerrain) {
        BufferedImage sprite = worldSprites.get(TileTerrain);
        BufferedImage[] spriteArray = new BufferedImage[4];
        for(int i = 0; i < 3; i++){
            spriteArray[i] = sprite.getSubimage(i * 48, 0, 48, 48);
        }
        return spriteArray;
    }

    private void importTowerImg() {
        InputStream isKitchenTiles = this.getClass().getResourceAsStream("resView/world/tiles.png");
        InputStream isHeartImage = this.getClass().getResourceAsStream("resView/world/heart.png");

        try {
            worldSprites.put(TileTerrain.Kitchen, ImageIO.read(isKitchenTiles));           
        } catch (IOException e) {
            System.out.println("Couldn't find image file");
            e.printStackTrace();
        }
    }

    public BufferedImage getPathTurn(Direction from, Direction to){
        int pathStraight = 1;
        int pathTurn = 2;

            if (from == Direction.RIGHT && to == Direction.RIGHT
                    || from == Direction.LEFT && to == Direction.LEFT) {
                return this.getWorldSprites(TileTerrain.Kitchen)[pathStraight]; // > || <
            } else if (from == Direction.RIGHT && to == Direction.DOWN
                    || from == Direction.UP && to == Direction.LEFT) {
                return SpriteHelper.rotateSprite(this.getWorldSprites(TileTerrain.Kitchen)[pathTurn], -90); // >v || ^<
            } else if (from == Direction.RIGHT && to == Direction.UP
                    || from == Direction.DOWN && to == Direction.LEFT) {
                return this.getWorldSprites(TileTerrain.Kitchen)[pathTurn]; // >^ || v<
            } else if (from == Direction.DOWN && to == Direction.DOWN
                    || from == Direction.UP && to == Direction.UP) {
                return SpriteHelper.rotateSprite(this.getWorldSprites(TileTerrain.Kitchen)[pathStraight], 90); // v || ^
            } else if (from == Direction.DOWN && to == Direction.RIGHT
                    || from == Direction.LEFT && to == Direction.UP) {
                return SpriteHelper.rotateSprite(this.getWorldSprites(TileTerrain.Kitchen)[pathTurn], 90); // v> || <^
            } else if (from == Direction.LEFT && to == Direction.DOWN
                    || from == Direction.UP && to == Direction.RIGHT) {
                return SpriteHelper.rotateSprite(this.getWorldSprites(TileTerrain.Kitchen)[pathTurn], 180); // <v || ^>
            }
        return null;
    }

    public BufferedImage getTileSprite(){
        return this.getWorldSprites(TileTerrain.Kitchen)[0];
    }
}
