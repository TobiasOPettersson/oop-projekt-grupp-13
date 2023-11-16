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
        enemy.setLastDirection(EnemyDirection.RIGHT);
        enemy.setNextDirection(EnemyDirection.RIGHT);

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

    @Test
    public void moving_an_enemy_in_the_up_direction_should_decrease_its_y_coordinate() {
        AEnemy enemy = new EnemyOne(0, 20, 0.34);
        Point tileCenterPoint = new Point(0,10);
        enemy.setTileCenterPointY(tileCenterPoint.getY());
        enemy.setLastDirection(EnemyDirection.UP);
        enemy.setNextDirection(EnemyDirection.UP);
        while (enemy.getY() > enemy.getTileCenterPointY()) {
            enemy.move();
        }
        assertEquals(10, Math.round(enemy.getY()));
    }

    @Test
    public void moving_an_enemy_in_the_down_direction_should_increase_its_y_coordinate() {
        AEnemy enemy = new EnemyOne(0, 10, 0.34);
        Point tileCenterPoint = new Point(0,20);
        enemy.setTileCenterPointY(tileCenterPoint.getY());
        enemy.setLastDirection(EnemyDirection.DOWN);
        enemy.setNextDirection(EnemyDirection.DOWN);
        while (enemy.getY() < enemy.getTileCenterPointY()) {
            enemy.move();
        }
        assertEquals(20, Math.round(enemy.getY()));
    }

    @Test
    public void next_tile_is_down() {
        AEnemy enemy = new EnemyOne(0.5, 0, 1);
        Point tileCenterPoint = new Point(1,0);
        enemy.setTileCenterPointX(tileCenterPoint.getX());
        enemy.setTileCenterPointY(tileCenterPoint.getY());
        enemy.setLastDirection(EnemyDirection.RIGHT);
        enemy.setNextDirection(EnemyDirection.DOWN);

        enemy.move();
        assertEquals(0.5, enemy.getY());
    }

    @Test
    public void next_tile_is_up() {
        AEnemy enemy = new EnemyOne(0.5, 10, 1);
        Point tileCenterPoint = new Point(1,10);
        enemy.setTileCenterPointX(tileCenterPoint.getX());
        enemy.setTileCenterPointY(tileCenterPoint.getY());
        enemy.setLastDirection(EnemyDirection.RIGHT);
        enemy.setNextDirection(EnemyDirection.UP);

        enemy.move();
        assertEquals(9.5, enemy.getY());
    }

    @Test
    public void next_tile_is_right() {
        AEnemy enemy = new EnemyOne(0, 1.5, 1);
        Point tileCenterPoint = new Point(0, 1);
        enemy.setTileCenterPointY(tileCenterPoint.getY());
        enemy.setTileCenterPointX(tileCenterPoint.getX());
        enemy.setLastDirection(EnemyDirection.UP);
        enemy.setNextDirection(EnemyDirection.RIGHT);

        enemy.move();
        assertEquals(0.5, enemy.getX()); 
    }

    @Test
    public void next_tile_is_left() {
        AEnemy enemy = new EnemyOne(1, 1.5, 1);
        Point tileCenterPoint = new Point(1, 2);
        enemy.setTileCenterPointY(tileCenterPoint.getY());
        enemy.setTileCenterPointX(tileCenterPoint.getX());
        enemy.setLastDirection(EnemyDirection.DOWN);
        enemy.setNextDirection(EnemyDirection.LEFT);

        enemy.move();
        assertEquals(0.5, enemy.getX());
    }

}
