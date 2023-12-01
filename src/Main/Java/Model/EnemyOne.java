package Model;

import java.util.List;

/**
 * A sub-class representing EnemyOne in the game.
 * This class extends the AEnemy abstract class.
 */
public class EnemyOne extends AEnemy{
    
    public EnemyOne(double y, double speed, List<Direction> directions) {
        super(1, y, speed, EnemyType.tomato, directions, 1, 1);
    }
}
