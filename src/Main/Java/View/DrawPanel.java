package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Model.MainModel;
import Model.Enemies.AEnemy;
import Model.Enums.Direction;
import Model.Enums.TowerType;
import Model.Interfaces.IObservable;
import Model.Map.ATile;
import Model.Map.TowerTile;
import Model.Towers.ATower;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class DrawPanel extends JPanel implements ICreateTowerObserver, IObservable {
    private MainModel model;
    private DrawEnemies drawEnemies = new DrawEnemies();
    private DrawMap drawMap = new DrawMap(model);
    private DrawGameInfo drawGameInfo = new DrawGameInfo(model);

    private int[] selectedTile = new int[] { -1, -1 };
    private int[] hoveredTile = new int[] { -1, -1 };
    private TowerType towerTypeToPlace = null;
    private boolean isPlacingTower = false;
    protected TowerSpriteManager towerSpriteManager = new TowerSpriteManager();

    private BufferedImage[] towerSprites;

    private final int SPRITESIZE = GraphicsDependencies.getSpriteSize();
    private int gridWidth;
    private int gridHeight;

    // Constructor
    public DrawPanel(GameView gameView, MainModel model) {
        this.model = model;
        setLayout(null);
        update();
        addMouseListeners();
        this.gridWidth = model.getMapSizeX();
        this.gridHeight = model.getMapSizeY();
    }

    // ----------------------------Draw & paint methods-----------------------//

    /**
     * TODO javadoc comment
     * 
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTowers(g);
        drawSelectedTile(g);
        if (getTowerAtMousePos() != null) {
            drawHoveredTowerRange(g, getTowerAtMousePos());
        }

        if (isPlacingTower) {
            drawTowerAtMousePos(g);
        }
        drawEnemies.draw(g, model.getEnemies());
        drawMap.draw(g);
        drawGameInfo.draw(g);
        drawHoveredTile(g);
    }

    /**
     * Draws a square border around the tile the player has clicked on
     * 
     * @param g Graphics object
     */
    void drawSelectedTile(Graphics g) {
        if (selectedTile.length > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(1));
            g2.drawRect(selectedTile[0] * SPRITESIZE, selectedTile[1] * SPRITESIZE, SPRITESIZE, SPRITESIZE);
            g2.setStroke(g2.getStroke());
        }
    }

    /**
     * Draws a square border around the tile the player is hovering over
     * 
     * @param g Graphics object
     */
    void drawHoveredTile(Graphics g) {
        if (selectedTile.length > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.yellow);
            g2.drawRect(hoveredTile[0] * SPRITESIZE, hoveredTile[1] * SPRITESIZE, SPRITESIZE, SPRITESIZE);
            g2.setStroke(g2.getStroke());
        }
    }

    // TODO Javadoc comment

    /**
     * Draw Towers on the map
     * 
     * @param g
     */
    private void drawTowers(Graphics g) {
        for (ATower tower : model.getTowers()) {
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
     * 
     * @param tower      The tower that is attacking the enemy
     * @param towerImage The original tower image
     * @return The rotated tower image
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
     * 
     * @param g Graphics
     */
    private void drawTowerAtMousePos(Graphics g) {
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
     * 
     * @param g               Graphics
     * @param towerAtMousePos The tower the player is hovering over
     */
    private void drawHoveredTowerRange(Graphics g, ATower towerAtMousePos) {
        drawTowerRange(g, towerAtMousePos.getX(), towerAtMousePos.getY(), towerAtMousePos.getRange());
    }

    /**
     * Draws a circle around the tower representing its range
     * 
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

    // ----------------------------Other methods--------------------------//

    /**
     * TODO javadoc comment
     */
    @Override
    public void update() {
        repaint();
    }

    /** -------- Related to mouse events -------- */

    /**
     * Adds mouselisteners to DrawPanel
     * MouseClicked left: Exits placing towers
     * MouseClicked right: Creates a tower at the mouse position
     * MouseMoved: Saves which tile the player is hovering over in the hoveredTile
     * variable
     */
    private void addMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                if (mEvent.getButton() == MouseEvent.BUTTON3) {
                    isPlacingTower = false;
                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(DrawPanel.this);
                    ((GameView) parentFrame).openCreateWidgit();
                } else {
                    try {
                        handleTileClick();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent mEvent) {
                // if(!model.activeWave()){
                hoverOverTile(mEvent.getX(), mEvent.getY());
                // }
            }
        });
    }

    /**
     * Creates a tower at the mouse position if the player is placing towers
     * OR
     * Opens the upgrade widget for the tower at the mouse position
     */
    private void handleTileClick() throws Exception {
        if (isHoveredTileTowerTile()) {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(DrawPanel.this);
            if (isPlacingTower) {
                model.createTower(hoveredTile[0], hoveredTile[1], towerTypeToPlace);
                ((GameView) parentFrame).addNewUpgradeWidget(towerTypeToPlace, hoveredTile[0], hoveredTile[1]);
            } else if (getTowerAtMousePos() != null) {
                selectedTile[0] = hoveredTile[0];
                selectedTile[1] = hoveredTile[1];
                ((GameView) parentFrame).openUpgradeWidgit(hoveredTile[0], hoveredTile[1],
                        getTowerAtMousePos().getTowerType(),
                        getTowerAtMousePos().getUpgrades());
            }
        }
    }

    /**
     * Saves which tile the player is hovering over in the hoveredTile variable
     * 
     * @param x Mouse x-pos
     * @param y Mouse y-pos
     */
    private void hoverOverTile(int x, int y) {
        for (int i = 0; i < gridWidth; i++) {
            if (x > SPRITESIZE * i && x < SPRITESIZE * (i + 1)) {
                for (int j = 0; j < gridHeight; j++) {
                    if (y > SPRITESIZE * j && y < SPRITESIZE * (j + 1)) {
                        hoveredTile[0] = i;
                        hoveredTile[1] = j;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Called when the player clicks on a widget button in "create mode"
     * Saves which type of tower they want to create in towerTypeToPlace
     */
    @Override
    public void selectTowerToCreate(TowerType type) {
        towerTypeToPlace = type;
        isPlacingTower = true;
    }

    /**
     * Gets the tower object at the current mouse position
     * 
     * @return Tower at mouse position or null if the tile doesn't have a tower
     */
    private ATower getTowerAtMousePos() {
        if (hoveredTile[0] > -1 && hoveredTile[1] > -1) {
            if (isHoveredTileTowerTile()) {
                return model.getTowerOnTile(((TowerTile) model.getTile(hoveredTile[0], hoveredTile[1])));
            }
        }
        return null;
    }

    /**
     * Checks whether the hovered tile is a TowerTile
     * 
     * @return If the hovered tile is a TowerTile
     */
    private boolean isHoveredTileTowerTile() {
        return model.tileIsTowerTile(hoveredTile[0], hoveredTile[1]);
    }
}
