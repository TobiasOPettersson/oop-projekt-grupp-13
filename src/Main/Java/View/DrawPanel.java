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

public class DrawPanel extends JPanel {
    private GameView gameView;
    private BufferedImage image;
    private Map<TowerType, BufferedImage> towerImageMap;
    private MainModel model;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    // public ArrayList<DirNode> dirChangeArray = new ArrayList<>(); // Temp map
    private ATile mapGrid[][];
    private List<Direction> pathDirections;
    private int gridWidth;
    private int gridHeight;
    private ArrayList<BufferedImage> pathSprites = new ArrayList<>();
    private int[][] pathGrid;
    private int[] selectedTile = new int[] { -1, -1 };
    private int[] hoveredTile = new int[] { -1, -1 };
    private int animationIndex = 0;
    private int animationTick = 0;

    // Constructor
    public DrawPanel(GameView gameView, MainModel model, BufferedImage image,
            Map<TowerType, BufferedImage> towerImageMap) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                handleTileClick(mEvent.getX(), mEvent.getY());
            }
            /*
             * @Override
             * public void mouseExited(MouseEvent mEvent) {
             * hoveredTile = new int[]{-1, -1};
             * }
             */
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
        // made some changes here
        // set the layout to null to get absolut psotio
        setLayout(null);
        PlayButtonController playButton = new PlayButtonController(model);
        playButton.setBounds(836, 384, 96, 96);
        add(playButton);
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
    }

    private void createPathSprites() { // Behöver fixas, Mycket redundant kod
        // pathTurn = sprites.get(8)
        int pathTurn = 8;
        // pathStraight = sprites.get(9)
        int pathStraight = 9;
        Direction previous = this.pathDirections.get(0);
        Direction current;
        for (int i = 1; i < this.pathDirections.size(); i++) {
            current = this.pathDirections.get(i);
            if (previous == Direction.RIGHT && current == Direction.RIGHT) {
                pathSprites.add(sprites.get(pathStraight)); // >
            } else if (previous == Direction.RIGHT && current == Direction.DOWN) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), 90)); // >v
            } else if (previous == Direction.RIGHT && current == Direction.UP) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), 180)); // >^
            } else if (previous == Direction.DOWN && current == Direction.DOWN) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathStraight), 90)); // v
            } else if (previous == Direction.DOWN && current == Direction.LEFT) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), 180)); // v<
            } else if (previous == Direction.DOWN && current == Direction.RIGHT) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), -90)); // v>
            } else if (previous == Direction.LEFT && current == Direction.LEFT) {
                pathSprites.add(sprites.get(pathStraight)); // <
            } else if (previous == Direction.LEFT && current == Direction.UP) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathTurn), -90)); // <^
            } else if (previous == Direction.LEFT && current == Direction.DOWN) {
                pathSprites.add(sprites.get(pathTurn)); // <v
            } else if (previous == Direction.UP && current == Direction.UP) {
                pathSprites.add(SpriteHelper.rotateSprite(sprites.get(pathStraight), 90)); // ^
            } else if (previous == Direction.UP && current == Direction.RIGHT) {
                pathSprites.add(sprites.get(pathTurn)); // ^>
            } else if (previous == Direction.UP && current == Direction.LEFT) {
                SpriteHelper.rotateSprite(sprites.get(pathTurn), 90); // ^<
            }
            previous = current;
        }
    }

    private void drawMap(Graphics g) {
        drawTerrain(g);
        drawPath(g);
        drawTowers(g);
        drawEnemies(g);
        drawHoveredTile(g);
    }

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
            int x = (int) (enemy.getX() * spriteSize) - offset;
            int y = (int) (enemy.getY() * spriteSize) - offset;
            if (!enemy.isStaggered()) {
                g.drawImage(sprites.get(28), x, y, null);
                drawEnemyHP(g, spriteSize, enemy, x, y);
            }
            // Add method that gets the correct sprite for enemies according to
            // animationIndex.
        }
    }

    private void drawEnemyHP(Graphics g, int spriteSize, AEnemy enemy, int x, int y) {
        if ((double) (enemy.getHealth()/enemy.getMaxHealth()) > 0.75) {
            g.setColor(Color.GREEN);
        }
        else if (((double) (enemy.getHealth()/enemy.getMaxHealth()) <= 0.75) && ((double) (enemy.getHealth()/enemy.getMaxHealth()) > 0.5)) {
            g.setColor(Color.YELLOW);
        }
        else if (((double) (enemy.getHealth()/enemy.getMaxHealth()) <= 0.5) && ((double) (enemy.getHealth()/enemy.getMaxHealth()) > 0.25)) {
            g.setColor(Color.ORANGE);
        }
        else {
            g.setColor(Color.RED);
        }
        g.drawLine(x, y+spriteSize, (int) (x + (spriteSize * enemy.getHealth())/enemy.getMaxHealth()), y+spriteSize);
    }

    private void drawTowers(Graphics g) {
        for (ATower tower : model.getMap().getTowers()) {
            BufferedImage towerImage = towerImageMap.get(tower.getTowerType());
            if (tower.getTargetPosition() != null) {
                Point2D.Double enemyCenterPoint = tower.getTargetPosition();
                double angleBInRadians = Math.atan2(tower.getY() + 0.5 - enemyCenterPoint.getY(),
                        tower.getX() + 0.5 - enemyCenterPoint.getX());
                double angle = Math.toDegrees(angleBInRadians);
                towerImage = SpriteHelper.rotateSprite(towerImage, (int) (angle) + 270);
            }
            g.drawImage(towerImage, (int) tower.getX() * 48, (int) tower.getY() * 48, null);

            Graphics2D g2 = (Graphics2D) g;
            g.setColor(Color.black);
            int rangeCircleX = (int) ((tower.getX() - tower.getRange()));
            int rangeCircleY = (int) ((tower.getY() - tower.getRange()));
            int rangeCircleD = (int) (tower.getRange() * 2 * 48);
            g2.drawOval(rangeCircleX * 48, rangeCircleY * 48, rangeCircleD + 48, rangeCircleD + 48);
        }
    }

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

    // Draws the map to the screen according to the blueprint
    private void drawTerrain(Graphics g) {
        // Grass = sprites.get(18)
        int plains = 18;
        // Water = sprites.get(60)
        int water = 60;
        // size of sprites, originalsize * scale = 32*1.5 = 48
        int sSize = 48;

        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                switch (mapGrid[j][i].getTerrain()) {
                    case Plains:
                        g.drawImage(sprites.get(plains), i * sSize, j * sSize, null);
                        break;
                    case Water:
                        g.drawImage(sprites.get(water), i * sSize, j * sSize, null);
                        break;
                }
            }
        }

    }

    public void update() {
        updateAnimation();
        repaint();
    }

    public int walk = 0;
    int c = 1;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);

        // Testing
        /*
         * if (c == 1)
         * for (DirChange item : dirChangeArray) {
         * System.out.println("x: " + item.getX() + ", y: " + item.getY() + ", dir: " +
         * item.getDir());
         * c = 0;
         * }
         */
        drawEnemies(g);
        drawTowers(g);
        drawSelectedTile(g);
    }

}
