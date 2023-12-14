package Model.Enemies;

import java.util.List;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A sub-class representing a CheeseEnemy in the game.
 * This class extends the AEnemy abstract class.
 */
public class CheeseEnemy extends AEnemy {
    /**
     * Constructor of a cheese enemy.
     * @param y             the y-position of the enemy as a grid-index, i.e. not the
     *                      y-position of the sprite in view
     * @param directions    is the list of directions in which the enemy will follow
     */
    public CheeseEnemy(double y, List<Direction> directions) {
        super(40, y, 0.01, EnemyType.cheese, directions, 1, 1);
    }  
}
