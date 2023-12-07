package Model.Enemies;

import java.util.List;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A sub-class representing EnemyOne in the game.
 * This class extends the AEnemy abstract class.
 */
public class EnemyOne extends AEnemy{
    
    public EnemyOne(double y, double speed, List<Direction> directions) {
        super(10, y, speed, EnemyType.tomato, directions, 1, 1);
    }
}
