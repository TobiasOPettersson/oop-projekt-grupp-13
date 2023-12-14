package Model.Enemies;

import java.util.List;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A sub-class representing a BananaEnemy in the game.
 * This class extends the AEnemy abstract class.
 */
public class BananaEnemy extends AEnemy{
    /**
     * Constructor of a banana enemy.
     * @param y             the y-position of the enemy as a grid-index, i.e. not the
     *                      y-position of the sprite in view
     * @param directions    is the list of directions in which the enemy will follow
     */
    public BananaEnemy(double y, List<Direction> directions) {
        super(40, y, 0.015, EnemyType.banana, directions, 1, 1);
    }
}