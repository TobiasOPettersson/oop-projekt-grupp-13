package View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Model.Enums.TowerType;

/**
 * A class that manages tower sprites
 */
public class TowerSpriteManager {
    private HashMap<TowerType, BufferedImage> towerSprites;

    /**
     * Constructor
     */
    public TowerSpriteManager(){
        towerSprites = new HashMap<>();
        importTowerImg();
    }

    /**
     * Gets the tower sprites and splices them into an array containing their frame by frame animation
     * An exception to freezer since it desn't have an animation
     * @param towerType     The type of the tower
     * @return              An array containing all sprites of the towers animation
     */
    public BufferedImage[] getTowerSprites(TowerType towerType) {
        if(towerType == TowerType.freezer){
            return new BufferedImage[]{towerSprites.get(towerType)};
        }
        BufferedImage sprite = towerSprites.get(towerType);
        BufferedImage[] spriteArray = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            spriteArray[i] = sprite.getSubimage(i * 48, 0, 48, 48);
        }
        return spriteArray;
    }

    /**
     * Imports tower sprites
     */
    private void importTowerImg() {
        InputStream isKnifeTower = this.getClass().getResourceAsStream("resView/towers/knifeTower.png");
        InputStream isMalletTower = this.getClass().getResourceAsStream("resView/towers/malletTower.png");
        InputStream isBlowtorchTower = this.getClass().getResourceAsStream("resView/towers/blowtorchTower.png");
        InputStream isSlicerTower = this.getClass().getResourceAsStream("resView/towers/slicerTower.png");
        InputStream isFreezerTower = this.getClass().getResourceAsStream("resView/towers/fridgeTower.png");

        try {
            towerSprites.put(TowerType.knife, ImageIO.read(isKnifeTower));
            towerSprites.put(TowerType.mallet, ImageIO.read(isMalletTower));
            towerSprites.put(TowerType.blowtorch, ImageIO.read(isBlowtorchTower));
            towerSprites.put(TowerType.slicer, ImageIO.read(isSlicerTower));
            towerSprites.put(TowerType.freezer, ImageIO.read(isFreezerTower));
        } catch (IOException e) {
            System.out.println("Couldn't find image file");
            e.printStackTrace();
        }
    }
}
