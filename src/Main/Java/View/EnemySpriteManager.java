package View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;

import Model.Enums.EnemyType;

/**
 * Classthat manages enemy sprites
 */
public class EnemySpriteManager {
    private HashMap<EnemyType, BufferedImage> enemySprites;

    /**
     * Constructor
     */
    public EnemySpriteManager(){
        enemySprites = new HashMap<>();
        importEnemyImg();
    }

    /**
    /**
     * Gets an enemys sprites and splices them into an array containing their frame by frame animation
     * @param enemyType     The type of the enemy
     * @return              An array containing all sprites of the enemys animation
     */
    public BufferedImage[] getEnemySprites(EnemyType enemyType) {
        BufferedImage sprite = enemySprites.get(enemyType);
        BufferedImage[] spriteArray = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            spriteArray[i] = sprite.getSubimage(i * 48, 0, 48, 48);
        }
        return spriteArray;
    }

    /**
     * Imports enemy sprites
     */
    private void importEnemyImg() {
        InputStream isTomatoEnemy = this.getClass().getResourceAsStream("resView/enemies/tomatoEnemy.png");
        InputStream isBananaEnemy = this.getClass().getResourceAsStream("resView/enemies/bananaEnemy.png");
        InputStream isCheeseEnemy = this.getClass().getResourceAsStream("resView/enemies/cheeseEnemy.png");
        InputStream isChickenEnemy = this.getClass().getResourceAsStream("resView/enemies/chickenEnemy.png");
        
        try {
            enemySprites.put(EnemyType.tomato, ImageIO.read(isTomatoEnemy));
            enemySprites.put(EnemyType.banana, ImageIO.read(isBananaEnemy));
            enemySprites.put(EnemyType.cheese, ImageIO.read(isCheeseEnemy));
            enemySprites.put(EnemyType.chicken, ImageIO.read(isChickenEnemy));
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
