package Model.Enemies;

import java.util.List;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A sub-class representing a ChickenEnemy in the game.
 * This class extends the AEnemy abstract class.
 */
public class ChickenEnemy extends AEnemy {
    public ChickenEnemy(double y, List<Direction> directions) {
        super(40, y, 0.001, EnemyType.chicken, directions, 5, 1);
    }
}
