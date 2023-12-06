package View;

import javax.swing.JPanel;

import Controller.PlayButtonController;
import Model.MainModel;
import Model.Enemies.AEnemy;
import Model.Enums.Direction;
import Model.Enums.TowerType;
import Model.Interfaces.ITargetable;
import Model.Map.ATile;
import Model.Map.TowerTile;
import Model.Towers.ATower;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class DrawPanel extends JPanel{
    private GameView gameView;
    private BufferedImage image;
    private Map<TowerType, BufferedImage> towerImageMap;
    private MainModel model;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private ATile mapGrid[][];
    private List<Direction> pathDirections;
    private int gridWidth;
    private int gridHeight;
    private ArrayList<BufferedImage> pathSprites = new ArrayList<>();
    private int[][] pathGrid;
    
    private BufferedImage[] knifeTowerSprites = new BufferedImage[4];
    private BufferedImage[] malletTowerSprites = new BufferedImage[4];
    private BufferedImage[] blowtorchTowerSprites = new BufferedImage[4];
    private BufferedImage[] slicerTowerSprites = new BufferedImage[4];
    private final int SPRITE_SIZE = 48;

    /*
     * Constructor
     */
    //public DrawPanel(GameView gameView, MainModel model, BufferedImage image, BufferedImage imageKnife) {
    private int[] selectedTile = new int[]{-1, -1};
    private int[] hoveredTile = new int[]{-1, -1};
    private int animationIndex = 0;
    private int animationTick = 0;

    // Constructor
    public DrawPanel(GameView gameView, MainModel model, BufferedImage image, Map<TowerType, BufferedImage> towerImageMap) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                handleTileClick(mEvent.getX(), mEvent.getY());
            }
            /*@Override
            public void mouseExited(MouseEvent mEvent) {
                hoveredTile = new int[]{-1, -1};
            } */      
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent mEvent) {
                hoverOverTile(mEvent.getX(), mEvent.getY());
            }
        });
        this.gameView = gameView;
        this.image = image;
        this.towerImageMap = towerImageMap;
        this.model = model;
        this.pathDirections = this.model.getPathDirections();
        this.mapGrid = this.model.getTileGrid();
        this.gridWidth = this.model.getMapSizeX();
        this.gridHeight = this.model.getMapSizeY();
        this.pathGrid = this.model.getPathGrid();
        setLayout(new BorderLayout());
        add(new PlayButtonController(model), BorderLayout.PAGE_END);
        loadSprites();
        update();
        createPathSprites();
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

    private void updateAnimation() {
        animationTick++;
        if (animationTick >= 20) {
            animationTick = 0;
            updateAnimationIndex();
        }
    }

    private void updateAnimationIndex() {
        animationIndex++;
        if (animationIndex >= 4) {
            animationIndex = 0;
        }
    }

    // Create Sprite sheet by taking subimages from image and then scale them
    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                // Get the subimage that is 32x32 and scale it before putting it in the array of
                // sprites
                sprites.add(SpriteHelper.scaleSprite(image.getSubimage(x * 32, y * 32, 32, 32), 1.5));
            }
        }
        for (int i = 0; i < 4; i++){
            knifeTowerSprites[i] = (towerImageMap.get(TowerType.knife).getSubimage(i * 48, 0, 48, 48));
        }
        for (int i = 0; i < 4; i++){
            malletTowerSprites[i] = (towerImageMap.get(TowerType.mallet).getSubimage(i * 48, 0, 48, 48));
        }
        for (int i = 0; i < 4; i++){
            blowtorchTowerSprites[i] = (towerImageMap.get(TowerType.blowtorch).getSubimage(i * 48, 0, 48, 48));
        }
        for (int i = 0; i < 4; i++){
            slicerTowerSprites[i] = (towerImageMap.get(TowerType.slicer).getSubimage(i * 48, 0, 48, 48));
        }
    }

    /*
     * Creates an array of sprites oriented the correct way and in the correct order according
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
            if (previous == Direction.RIGHT && current == Direction.RIGHT || previous == Direction.LEFT && current == Direction.LEFT) {
                pathSprites.add(sprites.get(pathStraight)); // > || <
            } else if (previous == Direction.RIGHT && current == Direction.DOWN || previous == Direction.UP && current == Direction.LEFT) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), 90)); // >v || ^<
            } else if (previous == Direction.RIGHT && current == Direction.UP || previous == Direction.DOWN && current == Direction.LEFT) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), 180)); // >^ || v<
            } else if (previous == Direction.DOWN && current == Direction.DOWN || previous == Direction.UP && current == Direction.UP) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathStraight), 90)); // v || ^
            } else if (previous == Direction.DOWN && current == Direction.RIGHT || previous == Direction.LEFT && current == Direction.UP) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), -90)); // v> || <^
            } else if (previous == Direction.LEFT && current == Direction.DOWN || previous == Direction.UP && current == Direction.RIGHT) {
                pathSprites.add(sprites.get(pathTurn)); // <v || ^>
            }
            previous = current;
        }
    }

    private void drawMap(Graphics g) {
        drawTerrain(g);
        drawPath(g);
        drawHoveredTile(g);
    }

    /*
     * 
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

    void drawHoveredTile(Graphics g) {
        if (selectedTile.length > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.yellow);
            g2.drawRect(hoveredTile[0] * 48, hoveredTile[1] * 48, 48, 48);
            g2.setStroke(g2.getStroke());
        }
    }

    void drawEnemies(Graphics g) {
        // offset half of sprite size so the calculated position of enemy will be same
        // position as center of enemysprite
        int offset = 24; // Sprite size / 2
        int spriteSize = 48;
        
        for (AEnemy enemy : model.getEnemies()) {
            if(!enemy.isStaggered()){
                int x = (int) (enemy.getX() * spriteSize) - offset;
                int y = (int) (enemy.getY() * spriteSize) - offset;
                g.drawImage(sprites.get(28), x, y, null);
            }
            //System.out.println("Enemy X: " + enemy.getX() + ", Y: " + enemy.getY()); // DEL
            // Add method that gets the correct sprite for enemies according to
            // animationIndex.
        }
    }

    /*
     * Use the towerArray to draw towers 
     */

     /*private void drawTowers(Graphics g) {
        for (ATower tower : model.getMap().getTowers()) { // Tror att detta är det bättre sättet att göra det på då kan alla enemies och towers ha sina egna animation timers utan att view ska behöva gå in i alla objekt och läsa av.
            tower.paint(g);
        }
    }*/
    private void drawTowers(Graphics g) {
        TowerType towerType;
        for (ATower tower : model.getMap().getTowers()) {
            // TowerSprite: Knife
            // System.out.println("Enemy X: " + enemy.getX() + ", Y: " + enemy.getY()); //
            // DEL
          //g.drawImage(knifeSprites[animationIndex], (int) (tower.getX() * 48) - 24, (int) tower.getY() * 48, null); // implement animations
            //g.drawImage(towerImageMap.get(tower.getTowerType()), (int) tower.getX()*48, (int) tower.getY()*48, null); // Need to make it so tower.getTowerType will accept an animation index
            towerType = tower.getTowerType();
            switch (towerType) {
                case knife:
                    g.drawImage(knifeTowerSprites[tower.getAnimationIndex()], (int) (tower.getX() * 48) - 24, (int) tower.getY() * 48, null);
                    break;
            
                case mallet:
                    g.drawImage(malletTowerSprites[tower.getAnimationIndex()], (int) (tower.getX() * 48) - 24, (int) tower.getY() * 48, null);
                    break;

                case blowtorch: 
                    g.drawImage(blowtorchTowerSprites[tower.getAnimationIndex()], (int) (tower.getX() * 48) - 24, (int) tower.getY() * 48, null);
                    break;

                case slicer:
                    g.drawImage(slicerTowerSprites[tower.getAnimationIndex()], (int) (tower.getX() * 48) - 24, (int) tower.getY() * 48, null);
                    break;
            }
            //BufferedImage towerImage = towerImageMap.get(tower.getTowerType());
            if(tower.getTargetPosition() != null){
                Point2D.Double enemyCenterPoint = tower.getTargetPosition();
                double angleBInRadians = Math.atan2(tower.getY()+0.5 - enemyCenterPoint.getY(), tower.getX()+0.5 - enemyCenterPoint.getX());
                double angle = Math.toDegrees(angleBInRadians);
                //towerImage = SpriteHelper.rotateSprite(towerImage, (int)(angle)+270);
            }
            //g.drawImage(towerImage, (int) tower.getX()*48, (int) tower.getY()*48, null);
            
            /*Graphics2D g2 = (Graphics2D) g;
            g.setColor(Color.black);
            int rangeCircleX = (int)((tower.getX()-tower.getRange()));
            int rangeCircleY = (int)((tower.getY()-tower.getRange()));
            int rangeCircleD = (int)(tower.getRange()*2*48);
            g2.drawOval(rangeCircleX*48, rangeCircleY*48, rangeCircleD+48, rangeCircleD+48);*/
        }
    }

    /*
     * Use the pathGrid matrix to draw pathsprites from the pathsprite array
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
    /*
    * Draws the map to the screen according to the mapGrid
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
                g.drawLine(i * SPRITE_SIZE, 0, i * SPRITE_SIZE, gridHeight*SPRITE_SIZE);
            }
            for (int j = 0; j < gridHeight; j++) {
                g.drawLine(0, j * SPRITE_SIZE, gridWidth*SPRITE_SIZE, j * SPRITE_SIZE);
            }
    }

    /*
     * Draw a border around the selected tile to emphasize that it is the selected tile
     */
    private void drawTileBorder(Graphics g) {
            for (int j = 0; j < gridWidth; j++) {
                g.drawLine(0, j * SPRITE_SIZE, gridWidth, j * SPRITE_SIZE);
            }
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawEnemies(g);
        drawTowers(g);
        drawSelectedTile(g);
        drawVisibleGrid(g);

    }

}
