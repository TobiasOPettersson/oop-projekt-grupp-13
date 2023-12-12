package Model.Enemies;

import java.util.List;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A sub-class representing a CheeseEnemy in the game.
 * This class extends the AEnemy abstract class.
 */
public class CheeseEnemy extends AEnemy {
    public CheeseEnemy(double y, List<Direction> directions) {
        super(40, y, 0.01, EnemyType.cheese, directions, 3, 1);
    }  
}
