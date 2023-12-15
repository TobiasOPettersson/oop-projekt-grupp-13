package Model.Enemies;

import java.util.List;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A sub-class representing a TomatoEnemy in the game.
 * This class extends the AEnemy abstract class.
 */
public class TomatoEnemy extends AEnemy{
    /**
     * Constructor of a tomato enemy.
     * @param y             the y-position of the enemy as a grid-index, i.e. not the y-position of the sprite in view
     * @param directions    is the list of directions in which the enemy will follow
     */
    public TomatoEnemy(double y, List<Direction> directions) {
        super(15, y, 0.05, EnemyType.tomato, directions, 1, 1);
    }
}
