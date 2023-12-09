package View;

import javax.swing.JPanel;

import Controller.PlayButtonController;
import Model.MainModel;
import Model.Enemies.AEnemy;
import Model.Enums.Direction;
import Model.Enums.TowerType;
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

public class DrawPanel extends JPanel implements ICreateTowerObserver {
    private GameView gameView;
    private BufferedImage image;
    private MainModel model;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private ATile mapGrid[][];
    private List<Direction> pathDirections;
    private int gridWidth;
    private int gridHeight;
    private ArrayList<BufferedImage> pathSprites = new ArrayList<>();
    private int[][] pathGrid;
    private int[] selectedTile = new int[] { -1, -1 };
    private int[] hoveredTile = new int[] { -1, -1 };
    private TowerType towerTypeToPlace = null;
    private boolean isPlacingTower = false;

    // Constructor
    public DrawPanel(GameView gameView, MainModel model, BufferedImage image,
            Map<TowerType, BufferedImage> towerImageMap) {
        this.gameView = gameView;
        this.image = image;
        this.model = model;
        this.pathDirections = this.model.getPathDirections();
        this.mapGrid = this.model.getTileGrid();
        this.gridWidth = this.model.getMapSizeX();
        this.gridHeight = this.model.getMapSizeY();
        this.pathGrid = this.model.getPathGrid();
        // made some changes here
        // set the layout to null to get absolut psotio
        setLayout(null);
        // PlayButtonController playButton = new PlayButtonController(model);
        // playButton.setBounds(836, 384, 96, 96);
        // add(playButton);
        loadSprites();
        update();
        createPathSprites();
        addMouseListeners();
    }

    private void handleTileClick(int x, int y) {
        for (int i = 0; i < gridWidth; i++) {
            if (x > 48 * i && x < 48 * (i + 1)) {
                for (int j = 0; j < gridHeight; j++) {
                    if (y > 48 * j && y < 48 * (j + 1)) {
                        if (model.getMap().getTile(i, j) instanceof TowerTile) {
                            selectedTile[0] = i;
                            selectedTile[1] = j;
                            gameView.openWidgit(i, j);
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * Create Sprite sheet by taking subimages from image and then scale them
     */
    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                // Get the subimage that is 32x32 and scale it before putting it in the array of
                // sprites
                sprites.add(SpriteHelper.scaleSprite(image.getSubimage(x * 32, y * 32, 32, 32), 1.5));
            }
        }
    }

    /*
     * Creates an array of sprites oriented the correct way and in the correct order
     * according
     * to the pathDirection array
     */
    private void createPathSprites() {
        // pathTurn = sprites.get(8)
        int pathTurn = 8;
        // pathStraight = sprites.get(9)
        int pathStraight = 9;
        Direction previous = this.pathDirections.get(0);
        Direction current;
        for (int i = 1; i < this.pathDirections.size(); i++) {
            current = this.pathDirections.get(i);
            if (previous == Direction.RIGHT && current == Direction.RIGHT
                    || previous == Direction.LEFT && current == Direction.LEFT) {
                pathSprites.add(sprites.get(pathStraight)); // > || <
            } else if (previous == Direction.RIGHT && current == Direction.DOWN
                    || previous == Direction.UP && current == Direction.LEFT) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), 90)); // >v || ^<
            } else if (previous == Direction.RIGHT && current == Direction.UP
                    || previous == Direction.DOWN && current == Direction.LEFT) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), 180)); // >^ || v<
            } else if (previous == Direction.DOWN && current == Direction.DOWN
                    || previous == Direction.UP && current == Direction.UP) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathStraight), 90)); // v || ^
            } else if (previous == Direction.DOWN && current == Direction.RIGHT
                    || previous == Direction.LEFT && current == Direction.UP) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), -90)); // v> || <^
            } else if (previous == Direction.LEFT && current == Direction.DOWN
                    || previous == Direction.UP && current == Direction.RIGHT) {
                pathSprites.add(sprites.get(pathTurn)); // <v || ^>
            }
            previous = current;
        }
    }

    //----------------------------Draw & paint methods-----------------------//

    /**
     * TODO javadoc comment
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawEnemies(g);
        drawTowers(g);
        drawSelectedTile(g);
        drawEndScreen(g);
        drawVisibleGrid(g);
 
        if(getTowerAtMousePos() != null){
            drawHoveredTowerRange(g, getTowerAtMousePos());
        }
        
        if(isPlacingTower){
            drawTowerAtMousePos(g);
        }
    }

    /**
     * TODO Javadoc comment, duplicate methodcalls in paintComponents() and here 
     * @param g
     */
    private void drawMap(Graphics g) {
        drawTerrain(g);
        drawPath(g);
        drawHoveredTile(g);
    }

    /**
     * Draws a square border around the tile the player has clicked on
     * 
     * @param g Graphics
     */
    void drawSelectedTile(Graphics g) {
        if (selectedTile.length > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(1));
            g2.drawRect(selectedTile[0] * 48, selectedTile[1] * 48, 48, 48);
            g2.setStroke(g2.getStroke());
        }
    }

    /**
     * Draws a square border around the tile the player is hovering over
     * 
     * @param g Graphics
     */
    void drawHoveredTile(Graphics g) {
        if (selectedTile.length > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.yellow);
            g2.drawRect(hoveredTile[0] * 48, hoveredTile[1] * 48, 48, 48);
            g2.setStroke(g2.getStroke());
        }
    }

    // TODO Javadoc comment
    /**
     * 
     * @param g
     */
    void drawEnemies(Graphics g) {

        for (AEnemy enemy : model.getEnemies()) {
            enemy.paint(g);
        }
    }

    

     /*
      * draw towers
      */
    private void drawTowers(Graphics g) {
        for (ATower tower : model.getTowers()) {
            tower.paint(g);
        }
    }

    // TODO Javadoc comment
    /**
     * 
     * @param g
     * @param spriteSize
     * @param enemy
     * @param x
     * @param y
     */
    private void drawEnemyHP(Graphics g, int spriteSize, AEnemy enemy, int x, int y) {
        double percentOfHP = enemy.getHealth() / enemy.getMaxHealth();
        if (percentOfHP > 0.75) {
            g.setColor(Color.GREEN);
        } else if ((percentOfHP <= 0.75) && (percentOfHP > 0.5)) {
            g.setColor(Color.YELLOW);
        } else if ((percentOfHP <= 0.5) && ((percentOfHP) > 0.25)) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.RED);
        }
        g.drawLine(x, y + spriteSize, (int) (x + (spriteSize * percentOfHP)), y + spriteSize);
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
        BufferedImage towerImage = towerImageMap.get(towerTypeToPlace); 
        g.drawImage(towerImage, hoveredTile[0] * 48, hoveredTile[1] * 48, null);
        drawTowerRange(g, hoveredTile[0], hoveredTile[1], 1);
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
        int rangeCircleD = (int) (range * 2 * 48);
        g2.drawOval(rangeCircleX * 48, rangeCircleY * 48, rangeCircleD + 48, rangeCircleD + 48);
    }

    /**
     * TODO javadoc comment
     * @param g
     */
    private void drawPath(Graphics g) {
        int spriteSize = 48;
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                if (pathGrid[j][i] != 0) {
                    g.drawImage(pathSprites.get(pathGrid[j][i] - 1), i * spriteSize, j * spriteSize, null);
                }
            }
        }
    }

    /**
     * Draws the map to the screen according to the blueprint
     * 
     * @param g Graphics
     */
    private void drawTerrain(Graphics g) {
        // Grass = sprites.get(18)
        int plains = 18;
        // Water = sprites.get(60)
        int water = 60;

        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                switch (mapGrid[j][i].getTerrain()) {
                    case Plains:
                        g.drawImage(sprites.get(plains), i * SPRITE_SIZE, j * SPRITE_SIZE, null);
                        break;
                    case Water:
                        g.drawImage(sprites.get(water), i * SPRITE_SIZE, j * SPRITE_SIZE, null);
                        break;
                    case Forrest:
                        break;
                    case Mountains:
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /*
     * Draw a grid on the whole map
     */
    private void drawVisibleGrid(Graphics g) {
        for (int i = 0; i < gridWidth; i++) {
            g.setColor(new Color(77, 77, 77));
            g.drawLine(i * SPRITE_SIZE, 0, i * SPRITE_SIZE, gridHeight * SPRITE_SIZE);
        }
        for (int j = 0; j < gridHeight; j++) {
            g.drawLine(0, j * SPRITE_SIZE, gridWidth * SPRITE_SIZE, j * SPRITE_SIZE);
        }
    }

    /*
     * Draw a border around the selected tile to emphasize that it is the selected
     * tile
     */
    private void drawTileBorder(Graphics g) {
        for (int j = 0; j < gridWidth; j++) {
            g.drawLine(0, j * SPRITE_SIZE, gridWidth, j * SPRITE_SIZE);
        }
    }

    private void drawEndScreen(Graphics g) {
        if (!model.getAlive()) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            drawCenteredText(g, "YOU LOST!");
        }
        if (!model.getActiveWave() && model.allWavesDead() && model.getAlive()) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            drawCenteredText(g, "YOU WON!");
        }
    }

    private void drawCenteredText(Graphics g, String text) {
        int messageWidth = g.getFontMetrics().stringWidth(text);
        int x = (getWidth() - messageWidth) / 2;
        int y = getHeight() / 2;
        g.drawString(text, x, y);
    }

    //----------------------------Other methods--------------------------//

    /**
     * TODO javadoc comment
     */
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
                    gameView.openCreateWidgit();
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
            if (isPlacingTower) {
                model.createTower(hoveredTile[0], hoveredTile[1], towerTypeToPlace);
            } else if (getTowerAtMousePos() != null) {
                selectedTile[0] = hoveredTile[0];
                selectedTile[1] = hoveredTile[1];
                gameView.openUpgradeWidgit(hoveredTile[0], hoveredTile[1], getTowerAtMousePos().getTowerType(),
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
            if (x > 48 * i && x < 48 * (i + 1)) {
                for (int j = 0; j < gridHeight; j++) {
                    if (y > 48 * j && y < 48 * (j + 1)) {
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

    // TODO reduce method chaining
    /**
     * Gets the tower object at the current mouse position
     * 
     * @return Tower at mouse position or null if the tile doesn't have a tower
     */
    private ATower getTowerAtMousePos() {
        if (hoveredTile[0] > -1 && hoveredTile[1] > -1) {
            if (isHoveredTileTowerTile()) {
                return ((TowerTile) model.getMap().getTile(hoveredTile[0], hoveredTile[1])).getTower();
            }
        }
        return null;
    }

    // TODO reduce method chaining?
    private boolean isHoveredTileTowerTile() {
        return model.getMap().getTile(hoveredTile[0], hoveredTile[1]) instanceof TowerTile;
    }
}
