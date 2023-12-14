package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import Model.Enums.TowerType;
import Model.Towers.ATower;

/**
 * A class containing methods that draw towers
 */
public class DrawTowers {
    protected TowerSpriteManager towerSpriteManager = new TowerSpriteManager();
    private BufferedImage[] towerSprites;
    private final int SPRITESIZE = GraphicsDependencies.getSpriteSize();

    public DrawTowers(){
    }

    /**
     * Draw Towers on the map
     * @param g Graphics
     */
    protected void drawTowers(Graphics g, List<ATower> towers) {
        for (ATower tower : towers) {
            this.towerSprites = towerSpriteManager.getTowerSprites(tower.getTowerType());
            BufferedImage towerImage = null;
            if (tower.getTowerType() != TowerType.freezer) {
                towerImage = towerSprites[tower.getAnimationIndex()];
                if (tower.getTargetPosition() != null) {
                    towerImage = rotateTowerTowardTarget(tower, towerImage);
                }
            } else {
                towerImage = towerSprites[0];
            }
            g.drawImage(towerImage, (int) tower.getX() * SPRITESIZE, (int) tower.getY() * SPRITESIZE, null);
        }
    }

    /**
     * Rotates the tower image toward the enemy its targeting
     * @param tower         The tower that is attacking the enemy
     * @param towerImage    The original tower image
     * @return              The rotated tower image
     */
    private BufferedImage rotateTowerTowardTarget(ATower tower, BufferedImage towerImage) {
        Point2D.Double enemyCenterPoint = tower.getTargetPosition();
        double angleBInRadians = Math.atan2(tower.getY() + 0.5 - enemyCenterPoint.getY(),
                tower.getX() + 0.5 - enemyCenterPoint.getX());
        double angle = Math.toDegrees(angleBInRadians);
        return SpriteHelper.rotateSprite(towerImage, (int) (angle) + 270);
    }

    /**
     * Draws the tower that the player wants to create at the mouse cursor
     * @param g Graphics
     */
    protected void drawTowerAtMousePos(Graphics g, int[] hoveredTile, TowerType towerTypeToPlace) {
        Map<TowerType, Integer> defaultRangeMap = Map.of(
                TowerType.knife, 1,
                TowerType.mallet, 1,
                TowerType.blowtorch, 3,
                TowerType.slicer, 1,
                TowerType.freezer, 1);

        BufferedImage[] towerImage = towerSpriteManager.getTowerSprites(towerTypeToPlace);
        g.drawImage(towerImage[0], hoveredTile[0] * SPRITESIZE, hoveredTile[1] * SPRITESIZE, null);
        drawTowerRange(g, hoveredTile[0], hoveredTile[1], defaultRangeMap.get(towerTypeToPlace));
    }

    /**
     * If the player is hovering over an already placed tower this draws its range
     * @param g               Graphics
     * @param towerAtMousePos The tower the player is hovering over
     */
    protected void drawHoveredTowerRange(Graphics g, ATower towerAtMousePos) {
        drawTowerRange(g, towerAtMousePos.getX(), towerAtMousePos.getY(), towerAtMousePos.getRange());
    }

    /**
     * Draws a circle around the tower representing its range
     * @param g     Graphics
     * @param x     Index x of the tile the tower is placed on
     * @param y     Index y of the tile the tower is placed on
     * @param range The towers range
     */
    private void drawTowerRange(Graphics g, double x, double y, double range) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.black);
        int rangeCircleX = (int) (x - range);
        int rangeCircleY = (int) (y - range);
        int rangeCircleD = (int) (range * 2 * SPRITESIZE);
        g2.drawOval(rangeCircleX * SPRITESIZE, rangeCircleY * SPRITESIZE, rangeCircleD + SPRITESIZE,
                rangeCircleD + SPRITESIZE);
    }
}
