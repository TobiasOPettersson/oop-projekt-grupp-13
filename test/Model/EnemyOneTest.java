package test.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;

import org.junit.Test;

import src.Model.EnemyDirection;
import src.Model.EnemyOne;
import src.Model.IMovable;
import src.Model.AEnemy;;

public class EnemyOneTest {

    @Test
    public void move_function_should_work_on_sub_types_of_IMovable() {
        IMovable enemy = new EnemyOne(0, 0, 1, 0, 0, null);
        enemy.move();
        assertEquals(1, enemy.getX());
    }

    @Test
    public void moving_an_enemy_in_the_right_direction_should_increase_its_x_coordinate() {
        AEnemy enemy = new EnemyOne(0, 0, 1, 0, 0, null);
        enemy.move();
        assertEquals(1, enemy.getX());
    }

    @Test
    public void moving_an_enemy_in_the_left_direction_should_decrease_its_x_coordinate() {
        AEnemy enemy = new EnemyOne(0, 0, 1, 0, 0, null);
        enemy.setDirection(EnemyDirection.LEFT);
        enemy.move();
        assertEquals(-1, enemy.getX());
    }

    @Test
    public void moving_an_enemy_in_the_up_direction_should_decrease_its_y_coordinate() {
        AEnemy enemy = new EnemyOne(0, 0, 1, 0, 0, null);
        enemy.setDirection(EnemyDirection.UP);
        enemy.move();
        assertEquals(-1, enemy.getY());
    }

    @Test
    public void moving_an_enemy_in_the_down_direction_should_increase_its_y_coordinate() {
        AEnemy enemy = new EnemyOne(0, 0, 1, 0, 0, null);
        enemy.setDirection(EnemyDirection.DOWN);
        enemy.move();
        assertEquals(1, enemy.getY());
    }

    @Test
    public void an_enemy_that_gets_to_a_tile_centerpoint_should_stop_moving() {
        AEnemy enemy = new EnemyOne(0, 20, 20, 0, 0, null);
        enemy.setDirection(EnemyDirection.RIGHT);
        Point centerPoint = new Point(20, 20);
        while(!enemy.isInTileCenter(centerPoint.getX(), centerPoint.getY())) {
            enemy.move();
        }
        assertEquals(20, enemy.getX());
    }
}
