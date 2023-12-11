package Model.Enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Enums.Condition;
import Model.Enums.Direction;
import Model.Enums.EnemyType;
import Model.Interfaces.IMovable;
import Model.Interfaces.ITargetable;
import View.SpriteHelper;
import View.EnemySpriteManager;
import View.GraphicsDependencies;

// TODO seperate methods into groups if possible (with for example //----------------------------Getter and setters----------------------//)

/**
 * An abstract class representing an enemy in the game.
 * This class implements the IMovable interface.
 */
public abstract class AEnemy implements IMovable, ITargetable {
    private double health; //health points
    private double maxHealth;
    private double x, y; //position
    private double speed; //movement speed of an enemy
    private double maxSpeed;
    private EnemyType type; // The type of enemy
    private List<Direction> directions; // List of directions for the enemy to follow when moving
    private int damage;
    private int moneyBag;
    private double tileOffset = 0.5;
    private boolean isStaggered = false;
    private HashMap<Condition, Integer> conditions;
    private int animationTick = 0;
    private int animationIndex = 0;
    private EnemySpriteManager spriteManager;
    private BufferedImage[] enemySprites;
    private final int SPRITESIZE = GraphicsDependencies.getSpriteSize();
    
    /**
     * TODO Javadoc comment
     * @param health
     * @param y
     * @param speed
     * @param type
     * @param directions
     * @param damage
     * @param moneyBag
     */
    public AEnemy(double health, double y, double speed, EnemyType type, List<Direction> directions, int damage, int moneyBag) {
        this.health = health;
        this.maxHealth = health;
        this.x = 0;
        this.y = y + tileOffset;
        this.speed = speed;
        this.maxSpeed = speed;
        this.type = type;
        this.directions = new ArrayList<>(directions);
        this.damage = damage;
        this.moneyBag = moneyBag;
        initConditionMap();
        this.spriteManager = new EnemySpriteManager();
        this.enemySprites = spriteManager.getEnemySprites(type);
    }
/**
     * TODO Javadoc comment
     */
    private void initConditionMap() {

        conditions = new HashMap<Condition, Integer>();
        conditions.put(Condition.chilled, 0);
        conditions.put(Condition.superChilled, 0);
        conditions.put(Condition.onFire, 0);
    }

     /**
      * TODO comment parameter and return
      * Computes the horizontal vector.
      * Returns the horizontal vector depending on an enemy's direciton. 
      * @param dir
      * @return
      */
    private int horizontalVector(Direction dir) {
        return ((dir == Direction.RIGHT) ? 1 : 0) - ((dir == Direction.LEFT) ? 1 : 0);
    }
    
    /**
     * 
     * Computes the vertical vector.
     * Returns the vertical vector depending on an enemy's direciton.
     * @param dir
     * @return
     */
    private int verticalVector(Direction dir) {
        return ((dir == Direction.DOWN) ? 1 : 0) - ((dir == Direction.UP) ? 1 : 0);
    }

    public void updateAnimationTick() {
        animationTick++;
        if (animationTick >= 10) {
            animationTick = 0;
            incrementAnimationIndex();
        }
    }

    /*
     * Updates animationIndex
     */
    public void incrementAnimationIndex() {
        animationIndex++;
        if (animationIndex >= enemySprites.length) {
            animationIndex = 0;
        }
    }

    public void resetAnimation() {
        if (animationIndex != 0) {
            animationIndex++;
            if (animationIndex >= enemySprites.length) {
                animationIndex = 0;
            }
        }
    }

    /*
     * Paint: How to paint an enemy
     */
    public void paint(Graphics g) {
        // offset half of sprite size so the calculated position of enemy will be same
        // position as center of enemysprite
        int spriteSize = 48;
        int offset = spriteSize / 2; // Sprite size / 2

        int x = (int) (this.x * spriteSize) - offset;
        int y = (int) (this.y * spriteSize) - offset;
        drawEnemyHP(g, x, y);
        if (!this.isStaggered()) {
            g.drawImage(enemySprites[animationIndex], x, y, null);
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
    private void drawEnemyHP(Graphics g, int x, int y) {
        int yPositionOffset = 3;
        double percentOfHP = this.getHealth() / this.getMaxHealth();
        if (percentOfHP > 0.75) {
            g.setColor(Color.GREEN);
        } else if ((percentOfHP <= 0.75) && (percentOfHP > 0.5)) {
            g.setColor(Color.YELLOW);
        } else if ((percentOfHP <= 0.5) && ((percentOfHP) > 0.25)) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.RED);
        }
        g.drawLine(x, y + SPRITESIZE - yPositionOffset, (int) (x + (SPRITESIZE * percentOfHP)), y + SPRITESIZE - yPositionOffset);
    }

    /*
     * Computes the closest tile centerpoint x- or y-corodinate.
     * h must be -1, 0 or 1. Throws IllegalArgumentException if not.
     * Else return the closest coordinate depending on the enemy's position
     * and current vector.
     * @param coordinate
     * @param vector
     * @return
     */
    private double tileCenterPoint(double coordinate, double vector) {
        if (vector != -1 && vector != 0 && vector != 1) {
            throw new IllegalArgumentException("The vector can only be -1, 0, or 1");
        }

        if (vector != 0) {
            double number;
            double offset = vector / 2;

            if (coordinate == Math.floor(coordinate) + this.tileOffset) {
                number = Math.round(coordinate + offset);
            } else {
                number = Math.round(coordinate);
            }

            return number + offset;
        }

        double number = Math.floor(coordinate);
        return number + 0.5;
    }
    
    /**
     * 
     * Return the closest tile centerpoint x-coordinate.
     * @param h
     * @return
     */
    private double tileCenterPointX(double h) {
        return tileCenterPoint(this.x, h);
    }
    
    /**
     * 
     * Return the closest tile centerpoint y-coordinate.
     * @param v
     * @return
     */
    private double tileCenterPointY(double v) {
        return tileCenterPoint(this.y, v);
    }

    /**
     * 
     * Computes if the enemy's next position is passed or on a tile centerpoint.
     * Returns true if the position is passed or on a centerpoint, else false. 
     * @param nextX
     * @param nextY
     * @param currentDir
     * @param h
     * @param v
     * @return
     */
    private boolean nextPosIsPassedCenterPoint(double nextX, double nextY, Direction currentDir, double h, double v) {
        if ((nextX >= tileCenterPointX(h) && currentDir == Direction.RIGHT)
                || (nextX <= tileCenterPointX(h) && currentDir == Direction.LEFT)) {
            return true;
        }
        if ((nextY <= tileCenterPointY(v) && currentDir == Direction.UP)
                || (nextY >= tileCenterPointY(v) && currentDir == Direction.DOWN)) {
            return true;
        }
        return false;
    }

    /**
     * 
     * Computes if the enemy's current position is passed or on a tile centerpoint.
     * Returns true if the position is passed or on a centerpoint, else false.
     * @param centerPointX
     * @param centerPointY
     * @param h
     * @param v
     * @return
     */
    private boolean currentPosIsPassedCenterPoint(double centerPointX, double centerPointY, double h, double v) {
        return ((Math.signum(this.y - centerPointY) == v) || (this.y == centerPointY))
                && (Math.signum(this.x - centerPointX) == h || (this.x == centerPointX));
    }

    /**
     * 
     * Calculate the next enemy position coordinates in one axis and updates them.
     * @param h
     * @param v
     */
    private void oneAxisMove(double h, double v) {
        if (h != 0 && v != 0) {
            throw new IllegalArgumentException("The enemy can only walk in one direction");
        }
        this.x += h * speed;
        this.y += v * speed;
    }

    /**
     * 
     * Updates the enemy's coordinates to the closest tile centerpoint coordinates.
     * @param centerPointX
     * @param centerPointY
     */
    private void forceEnemyToCenterPoint(double centerPointX, double centerPointY) {
        this.x = centerPointX;
        this.y = centerPointY;
    }

    /**
     * 
     * If there is only one direction for the enemy left to move, move it
     * in that direction until it has reached its closest tile centerpoint.
     * @param h
     * @param v
     */
    private void finalDirectionMove(double h, double v) {
        if (directions.size() == 1) {
            double centerPointX = tileCenterPointX(h);
            double centerPointY = tileCenterPointY(v);
            oneAxisMove(h, v);
            if (currentPosIsPassedCenterPoint(centerPointX, centerPointY, h, v)) {
                forceEnemyToCenterPoint(centerPointX, centerPointY);
                directions.remove(0);
            }
        }
    }

    /**
     * 
     * Calculate the next enemy position coordinates after a turn and updates them.
     * @param h
     * @param v
     * @param hNext
     * @param vNext
     * @param nextX
     * @param nextY
     */
    private void turningMove(double h, double v, double hNext, double vNext, double nextX, double nextY) {
        double nextXPos = tileCenterPointX(h) + Math.abs((nextY) - tileCenterPointY(v)) * hNext;
        double nextYPos = tileCenterPointY(v) + Math.abs((nextX) - tileCenterPointX(h)) * vNext;
        this.x = nextXPos;
        this.y = nextYPos;
    }

    /**
     * Moves the enemy along a predefined path of directions, adjusting its position
     * based on the current and next direction. Handles both continuous movement
     * in the same direction and turning at designated points.
     */
    private void moveEnemy() {
        Direction currentDir = directions.get(0);

        double h = horizontalVector(currentDir);
        double v = verticalVector(currentDir);

        finalDirectionMove(h, v);

        if (directions.size() > 1) {
            Direction nextDir = directions.get(1);

            double hNext = horizontalVector(nextDir);
            double vNext = verticalVector(nextDir);

            double nextX = this.x + h * speed;
            double nextY = this.y + v * speed;

            if (nextPosIsPassedCenterPoint(nextX, nextY, currentDir, h, v)) {
                if (currentDir == nextDir) {
                    oneAxisMove(h, v);
                } else {
                    turningMove(h, v, hNext, vNext, nextX, nextY);
                }
                directions.remove(0);
            } else {
                oneAxisMove(h, v);
            }
        }
        speed = maxSpeed;
    }

    /*
     * Move an enemy if the direction list isn't empty.
     */
    @Override
    public void move() {
        if (directions.size() > 0) {
            moveEnemy();
        }
    }

    //----------------------------Condition methods----------------------//

    /**
     * Triggers any conditions the enemy has (If its duration in the conditions map is more that 0)
     */
    public void triggerConditions(){
        if(conditions.get(Condition.chilled) > 0){
            speed = maxSpeed*0.5;
        } else if(conditions.get(Condition.superChilled) > 0){
            speed = maxSpeed*0.2;
        } else{
            speed = maxSpeed;
        }
        if(conditions.get(Condition.onFire) > 0){
            health -= 1;
        }
        decrementConditionDurations();
    }

    /**
     * Decrements condition duration it it's larger than 0
     */
    public void decrementConditionDurations(){
        for (Map.Entry<Condition, Integer> entry : conditions.entrySet()) {
            if (entry.getValue() > 0) {
                conditions.put(entry.getKey(), entry.getValue() - 1);
            }
        }
    }

    //----------------------------Other methods----------------------//

    /*
     * Updates the enemy health depending on the damage it takes
     */
    public void takeDamage(int damage){
        if (damage >= 0) {
            this.health -= damage;
        }
        
    }

    //----------------------------Getter and setters----------------------//

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public int getDamage() {
        return this.damage;
    }

    public boolean isChilled() {
        return conditions.get(Condition.chilled) > 0 || conditions.get(Condition.superChilled) > 0;
    }

    public void setCondition(Condition condition, int duration) {
        if (conditions.get(condition) < duration) {
            conditions.put(condition, duration);
        }
    }

    public EnemyType getType() {
        return type;
    }

    public void setStaggered(boolean bool) {
        isStaggered = bool;
    }

    public boolean isStaggered() {
        return isStaggered;
    }

    public double getHealth(){
        return this.health;
    }

    public double getMaxHealth(){
        return this.maxHealth;
    }

    public int getMoney() {
        return this.moneyBag;
    }

    public int getDirectionsSize() {
        return directions.size();
    }

    public EnemyType getEnemyType() {
        return this.type;
    }
}