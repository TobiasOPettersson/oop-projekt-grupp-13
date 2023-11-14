package test.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;

import org.junit.Test;

import src.Model.EnemyDirection;
import src.Model.EnemyOne;
import src.Model.AEnemy;;

public class EnemyOneTest {

    @Test
    public void moving_an_enemy_in_the_right_direction_should_increase_its_x_coordinate() {
        AEnemy enemy = new EnemyOne(0, 0, 0.34);
        Point tileCenterPoint = new Point(20,0);
        enemy.setTileCenterPointX(tileCenterPoint.getX());

        while (enemy.getX() < enemy.getTileCenterPointX()) {
            enemy.move();
        }
        assertEquals(20, Math.round(enemy.getX()));
    }

    @Test
    public void moving_an_enemy_in_the_left_direction_should_decrease_its_x_coordinate() {
        AEnemy enemy = new EnemyOne(20, 0, 0.34);
        Point tileCenterPoint = new Point(10,0);
        enemy.setTileCenterPointX(tileCenterPoint.getX());
        enemy.setLastDirection(EnemyDirection.LEFT);
        enemy.setNextDirection(EnemyDirection.LEFT);
        while (enemy.getX() > enemy.getTileCenterPointX()) {
            enemy.move();
        }
        assertEquals(10, Math.round(enemy.getX()));
    }
}
