package Model.Enemies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Enums.Condition;
import Model.Enums.Direction;
import Model.Enums.EnemyType;
import Model.Interfaces.IMovable;
import Model.Interfaces.ITargetable;

/**
 * An abstract class representing an enemy in the game.
 * This class implements the IMovable and ITargatable interface.
 */
public abstract class AEnemy implements IMovable, ITargetable {
    private double health;
    private double maxHealth;
    private double x, y;
    private double speed;
    private double maxSpeed;
    private EnemyType enemyType;
    private List<Direction> directions;
    private int damage;
    private int moneyBag;
    private double tileOffset = 0.5;
    private boolean isStaggered = false;
    private HashMap<Condition, Integer> conditions;
    private int animationTick = 0;
    private int animationIndex = 0;
    
    /**
     * Constructor of abstract class AEnemy.
     * @param health        is the health points needed to kill an enemy
     * @param y             the y-position of the enemy as a grid-index, i.e. not the
     *                      y-position of the sprite in view
     * @param speed         is the movement speed of the enemy
     * @param type          is the type of the enemy
     * @param directions    is the list of directions in which the enemy will follow
     * @param damage        is the damage that the enemy can deal to a player
     * @param moneyBag      is the money which a player will gain if they kill the enemy
     */
    public AEnemy(double health, double y, double speed, EnemyType enemyType, List<Direction> directions, int damage, int moneyBag) {
        this.health = health;
        this.maxHealth = health;
        this.x = 0;
        this.y = y + tileOffset;
        this.speed = speed;
        this.maxSpeed = speed;
        this.enemyType = enemyType;
        this.directions = new ArrayList<>(directions);
        this.damage = damage;
        this.moneyBag = moneyBag;
        initConditionMap();
    }

     /**
      * Computes a horizontal unit vector.
      * @param dir is the enemy's current direction
      * @return the horizontal unit vector as an int depending on an enemy's direction.
      */
    private int horizontalVector(Direction dir) {
        return ((dir == Direction.RIGHT) ? 1 : 0) - ((dir == Direction.LEFT) ? 1 : 0);
    }
    
    /**
      * Computes a vertical unit vector.
      * @param dir is the enemy's current direction
      * @return the vertical unit vector as an int depending on an enemy's direction.
      */
    private int verticalVector(Direction dir) {
        return ((dir == Direction.DOWN) ? 1 : 0) - ((dir == Direction.UP) ? 1 : 0);
    }

    /**
     * Computes the closest tile centerpoint x- or y-corodinate.
     * vector must be -1, 0 or 1. Throws IllegalArgumentException if not.
     * Else return the closest coordinate depending on the enemy's position
     * and current vector.
     * @param coordinate the enemy position coordinate, can be x or y.
     * @param vector the unit vector for the current direction. Can be horizontal or vertical.
     * @return x- or y-coordinate depending on the arguments as a double.
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
     * @param h is the horizontal unit vector for the current direction
     * @return the closest tile centerpoint x-coordinate.
     */
    private double tileCenterPointX(double h) {
        return tileCenterPoint(this.x, h);
    }
    
    /**
     * @param v is the vertical unit vector for the current direction
     * @return the closest tile centerpoint y-coordinate.
     */
    private double tileCenterPointY(double v) {
        return tileCenterPoint(this.y, v);
    }

    /**
     * Computes if the enemy's next position is on or passed a tile centerpoint.
     * @param nextX is the next x-position
     * @param nextY is the next y-position
     * @param currentDir is the current direction in which an enemy is moving
     * @param h is the horizontal unit vector for the current direction
     * @param v is the vertical unit vector for the current direction
     * @return boolean for if the next position is on or passed a centerpoint. True if that's the case, else false.
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
     * Computes if the enemy's current position is on or passed a tile centerpoint.
     * @param centerPointX is the closest centerpoint x-coordinate
     * @param centerPointY is the closest centerpoint y-coordinate
     * @param h is the horizontal unit vector for the current direction
     * @param v is the vertical unit vector for the current direction
     * @return boolean for if the position is on or passed a centerpoint. True if that's the case, else false.
     */
    private boolean currentPosIsPassedCenterPoint(double centerPointX, double centerPointY, double h, double v) {
        return ((Math.signum(this.y - centerPointY) == v) || (this.y == centerPointY))
                && (Math.signum(this.x - centerPointX) == h || (this.x == centerPointX));
    }

    /**
     * Calculate the next enemy position coordinates in one axis and updates them.
     * @param h is the horizontal unit vector for the current direction
     * @param v is the vertical unit vector for the current direction
     */
    private void oneAxisMove(double h, double v) {
        if (h != 0 && v != 0) {
            throw new IllegalArgumentException("The enemy can only walk in one direction");
        }
        this.x += h * speed;
        this.y += v * speed;
    }

    /**
     * Updates the enemy's coordinates to the closest tile centerpoint coordinates.
     * @param centerPointX is the closest centerpoint x-coordinate
     * @param centerPointY is the closest centerpoint y-coordinate
     */
    private void forceEnemyToCenterPoint(double centerPointX, double centerPointY) {
        this.x = centerPointX;
        this.y = centerPointY;
    }

    /**
     * If there is only one direction for the enemy left to move, move it
     * in that direction until it has reached its closest tile centerpoint.
     * @param h is the horizontal unit vector for the current direction
     * @param v is the vertical unit vector for the current direction
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
     * Calculate the next enemy position coordinates after a turn and updates them.
     * @param h is the horizontal unit vector for the current direction
     * @param v is the vertical unit vector for the current direction
     * @param hNext is the horizontal unit vector for the next direction
     * @param vNext is the vertical unit vector for the next direction
     * @param nextX is the next x-position
     * @param nextY is the next y-position
     */
    private void turningMove(double h, double v, double hNext, double vNext, double nextX, double nextY) {
        double nextXPos = tileCenterPointX(h) + Math.abs((nextY) - tileCenterPointY(v)) * hNext;
        double nextYPos = tileCenterPointY(v) + Math.abs((nextX) - tileCenterPointX(h)) * vNext;
        this.x = nextXPos;
        this.y = nextYPos;
    }

    /**
     * Moves the enemy along a predefined path of directions, adjusting its position
     * based on the current and next direction. Handles both movement
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

    /**
     * Move an enemy if the direction list isn't empty.
     */
    @Override
    public void move() {
        if (directions.size() > 0) {
            moveEnemy();
        }
    }

    //----------------------------Animation----------------------//

    /*
     * Updates the animation tick count and checks if it's time to increment the animation index and reset tick count.
     */
    public void updateAnimationTick() {
        animationTick++;
        if (animationTick >= 10) {
            animationTick = 0;
            incrementAnimationIndex();
        }
    }

    /*
     * Increments the animation index and resets if it exceeds the number of sprites in the animation.
     */
    private void incrementAnimationIndex() {
        int spritesInAnimation = 4;
        animationIndex++;
        if (animationIndex >= spritesInAnimation) {
            animationIndex = 0;
        }
    }
    
    //----------------------------Condition methods----------------------//

    /**
     * Initializes the conditions for which an enemy can have.
     */
    private void initConditionMap() {
        conditions = new HashMap<Condition, Integer>();
        conditions.put(Condition.chilled, 0);
        conditions.put(Condition.superChilled, 0);
        conditions.put(Condition.onFire, 0);
    }

    /**
     * Triggers any conditions the enemy has (If its duration in the conditions map is more than 0)
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
     * Decrements condition duration if it's larger than 0
     */
    public void decrementConditionDurations(){
        for (Map.Entry<Condition, Integer> entry : conditions.entrySet()) {
            if (entry.getValue() > 0) {
                conditions.put(entry.getKey(), entry.getValue() - 1);
            }
        }
    }

    public void setCondition(Condition condition, int duration) {
        if (conditions.get(condition) < duration) {
            conditions.put(condition, duration);
        }
    }

    public boolean hasCondition(Condition condition){
        return conditions.get(condition) > 0;
    }

    public int getConditionDuration(Condition condition){
        return conditions.get(condition);
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

    public void setStaggered(boolean bool) {
        isStaggered = bool;
    }

    public boolean getIsStaggered() {
        return isStaggered;
    }

    public double getHealth(){
        return health;
    }

    public double getMaxHealth(){
        return maxHealth;
    }

    public int getMoney() {
        return moneyBag;
    }

    public int getDirectionsSize() {
        return directions.size();
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }
    public int getAnimationIndex(){
        return this.animationIndex;
    }
}