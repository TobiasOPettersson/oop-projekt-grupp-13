package View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Model.Enums.TowerType;

public class TowerSpriteManager {
    private HashMap<TowerType, BufferedImage> towerSprites;

    public TowerSpriteManager(){
        towerSprites = new HashMap<>();
        importTowerImg();
    }

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
