package Model.Enemies;

import java.util.List;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A sub-class representing a BananaEnemy in the game.
 * This class extends the AEnemy abstract class.
 */
public class BananaEnemy extends AEnemy{
    public BananaEnemy(double y, List<Direction> directions) {
        super(40, y, 0.02, EnemyType.banana, directions, 4, 1);
    }
}
