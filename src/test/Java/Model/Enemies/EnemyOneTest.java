package Model.Enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import Model.Enums.Direction;
import Model.Enemies.EnemyOne;
import Model.Enemies.AEnemy;;

public class EnemyOneTest {

    @Test
    public void next_tile_is_left() {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        directions.add(Direction.RIGHT); // (3.5, 3.5)
        directions.add(Direction.UP);
        directions.add(Direction.RIGHT);
        directions.add(Direction.DOWN);
        directions.add(Direction.RIGHT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.DOWN);
        directions.add(Direction.DOWN);
        AEnemy enemy = new EnemyOne(1, 0.3, directions);
        // 1.5,1.5 2.5,1.5 -----> move(1.5,1) move()

        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();

        assertEquals(3.5, enemy.getX());
        assertEquals(3.5, enemy.getY());
    }

}
