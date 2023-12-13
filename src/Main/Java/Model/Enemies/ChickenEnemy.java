package Model.Enemies;

import java.util.List;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A sub-class representing a ChickenEnemy in the game.
 * This class extends the AEnemy abstract class.
 */
public class ChickenEnemy extends AEnemy {
    /**
     * Constructor of a chicken enemy.
     * @param y             the y-position of the enemy as a grid-index, i.e. not the
     *                      y-position of the sprite in view
     * @param directions    is the list of directions in which the enemy will follow
     */
    public ChickenEnemy(double y, List<Direction> directions) {
        super(50, y, 0.001, EnemyType.chicken, directions, 2, 1);
    }
}
