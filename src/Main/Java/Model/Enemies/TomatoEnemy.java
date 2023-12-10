package Model.Enemies;

import java.util.List;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A sub-class representing a TomatoEnemy in the game.
 * This class extends the AEnemy abstract class.
 */
public class TomatoEnemy extends AEnemy{
    public TomatoEnemy(double y, List<Direction> directions) {
        super(10, y, 0.05, EnemyType.tomato, directions, 1, 1);
    }
}
