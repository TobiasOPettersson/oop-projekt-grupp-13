package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import Model.Enemies.AEnemy;

/**
 * Class containing methods that draw the enemies on the map
 */
public class DrawEnemies {
    private final int SPRITESIZE = GraphicsDependencies.getSpriteSize();
    protected EnemySpriteManager enemySpriteManager = new EnemySpriteManager();
    private BufferedImage[] enemySprites;

    public DrawEnemies() {
    }

    /**
     * Method for drawing everything to do with enemies
     * @param g         Graphics object
     * @param enemies   List of live enemies
     */
    public void draw (Graphics g, List<AEnemy> enemies){
        drawEnemies(g, enemies);
    }

    /**
     * Draw enemies
     * @param g       Graphics object
     * @param enemies List of live enemies
     */
    private void drawEnemies(Graphics g, List<AEnemy> enemies) {
        for (AEnemy enemy : enemies) {
            int offset = SPRITESIZE / 2; // Sprite size / 2
            this.enemySprites = enemySpriteManager.getEnemySprites(enemy.getEnemyType());

            if (!enemy.getIsStaggered()) {
                int x = (int) (enemy.getX() * SPRITESIZE) - offset;
                int y = (int) (enemy.getY() * SPRITESIZE) - offset;
                g.drawImage(enemySprites[enemy.getAnimationIndex()], x, y, null);
                drawEnemyHP(g, enemy, x, y);
            }
        }
    }

    /**
     * Draw enemy Health points as a bar in the bottom of the enemy sprite
     * Color depends on amount of health.
     * Green = 100% health,
     * Yellow = 75% health
     * Orange = 50% health
     * red = 25% health
     * 
     * @param g     Graphics object
     * @param enemy
     * @param x
     * @param y
     */
    private void drawEnemyHP(Graphics g, AEnemy enemy, int x, int y) {
        double percentOfHP = enemy.getHealth() / enemy.getMaxHealth();
        if (percentOfHP > 0.75) {
            g.setColor(Color.GREEN);
        } else if ((percentOfHP <= 0.75) && (percentOfHP > 0.5)) {
            g.setColor(Color.YELLOW);
        } else if ((percentOfHP <= 0.5) && (percentOfHP > 0.25)) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.RED);
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(x, y + SPRITESIZE + 2, (int) (x + (SPRITESIZE * percentOfHP)), y + SPRITESIZE + 2);
    }

}