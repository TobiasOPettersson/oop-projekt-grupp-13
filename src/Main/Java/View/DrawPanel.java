package View;

import javax.swing.JPanel;
import Model.MainModel;
import Model.Enums.TowerType;
import Model.Interfaces.IObservable;
import Model.Map.TowerTile;
import Model.Towers.ATower;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class DrawPanel extends JPanel implements ICreateTowerObserver, IObservable {
    private MainModel model;
    private DrawEnemies drawEnemies;
    private DrawMap drawMap;
    private DrawTowers drawTowers;
    private DrawGameInfo drawGameInfo;
    private final int SPRITESIZE = GraphicsDependencies.getSpriteSize();
    private int[] selectedTile = new int[] { -1, -1 };
    private int[] hoveredTile = new int[] { -1, -1 };
    private TowerType towerTypeToPlace = null;
    private boolean isPlacingTower = false;
    private int gridWidth;
    private int gridHeight;

    // Constructor
    public DrawPanel(MainModel model) {
        this.model = model;
        this.drawEnemies = new DrawEnemies();
        this.drawMap = new DrawMap(model.getPathDirections());
        this.drawGameInfo = new DrawGameInfo(model);
        this.drawTowers = new DrawTowers();
        setLayout(null);
        update();
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
        drawMap(g);
        drawEnemies.draw(g, model.getEnemies());
        drawTowers(g);
        drawGameInfo.draw(g);
        drawSelectedTile(g);
        drawHoveredTile(g);

    }

    private void drawMap(Graphics g) {
        drawMap.drawTerrain(g, model.getTileGrid(), model.getMapSizeY(), model.getMapSizeX());
        drawMap.drawPath(g, model.getPathGrid(), model.getMapSizeX(), model.getMapSizeY());
        drawMap.drawStartPosition(g, model.getStartPosition());
        drawMap.drawEndPosition(g, model.getMapSizeX(), model.getEndPosition());
    }

    private void drawTowers(Graphics g) {
        drawTowers.drawTowers(g, model.getTowers());
        if (getTowerAtMousePos() != null) {
            drawTowers.drawHoveredTowerRange(g, getTowerAtMousePos());
        }
        if (isPlacingTower) {
            drawTowers.drawTowerAtMousePos(g, hoveredTile, towerTypeToPlace);
        }
    }

    /**
     * Draws a square border around the tile the player has clicked on
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

    // ----------------------------Other methods--------------------------//

    /**
     * Refreshes visual elements
     */
    @Override
    public void update() {
        repaint();
    }

    // -----------------------Related to mouse events--------------------//

    /**
     * Saves which tile the player is hovering over in the hoveredTile variable
     * @param x Mouse x-pos
     * @param y Mouse y-pos
     */
    protected void hoverOverTile(int x, int y) {
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
     * @return Tower at mouse position or null if the tile doesn't have a tower
     */
    protected ATower getTowerAtMousePos() {
        if (hoveredTile[0] > -1 && hoveredTile[1] > -1) {
            if (isHoveredTileTowerTile()) {
                return model.getTowerOnTile(((TowerTile) model.getTile(hoveredTile[0], hoveredTile[1])));
            }
        }
        return null;
    }

    /**
     * Checks whether the hovered tile is a TowerTile
     * @return If the hovered tile is a TowerTile
     */
    protected boolean isHoveredTileTowerTile() {
        return model.tileIsTowerTile(hoveredTile[0], hoveredTile[1]);
    }

    // ----------------------------Getters & setters--------------------------//

    protected void setSelectedTile() {
        selectedTile[0] = hoveredTile[0];
        selectedTile[1] = hoveredTile[1];
    }

    protected int[] getHoveredTile() {
        return hoveredTile;
    }

    protected TowerType getTowerTypeToPlace() {
        return towerTypeToPlace;
    }

    protected boolean isPlacingTower() {
        return isPlacingTower;
    }

    protected boolean setPlacingTower(boolean bool) {
        return isPlacingTower = bool;
    }
}