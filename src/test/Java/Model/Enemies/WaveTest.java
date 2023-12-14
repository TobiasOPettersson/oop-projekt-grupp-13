package Model.Enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import Model.Enums.Direction;
import Model.Enums.EnemyType;
import Model.Map.AMap;
import Model.Map.MapOne;
import Model.Enemies.TomatoEnemy;
import Model.Enemies.Wave;
import Model.Enemies.AEnemy;
import Model.Interfaces.IAttackable;

public class WaveTest {
    AMap map = new MapOne();
    public int startPosition = map.getStartPosition();
    public List<Direction> pathDirections = map.getPathDirections();

   /*  @Test
    public void testStartWave() throws Exception{
        Wave wave = new Wave(startPosition, pathDirections);

        // Test incrementing currentWaveNumber
        int initialWaveNumber = wave.getCurrentWaveNumber();
        wave.startWave();
        assertEquals(initialWaveNumber + 1, wave.getCurrentWaveNumber());
    }

    @Test
    public void testWavesIsEmpty() throws Exception {
        Wave wave = new Wave(startPosition, pathDirections);

        // Test on non-empty waves
        assertFalse(wave.wavesIsEmpty());

        // Test on empty waves
        while (!wave.wavesIsEmpty()) {
            wave.startWave();
        }
        assertTrue(wave.wavesIsEmpty());
    } */

    @Test
    public void testUpdateSpawnRate() {
        Wave wave = new Wave(startPosition, pathDirections);

        // Test decreasing spawn rate
        int initialSpawnRate = wave.getSpawnRate();
        wave.updateSpawnRate();
        assertEquals(initialSpawnRate - 1, wave.getSpawnRate());

        // Test spawn rate doesn't go below zero
        while (wave.getSpawnRate() > 0) {
            wave.updateSpawnRate();
        }
        wave.updateSpawnRate();
        assertEquals(0, wave.getSpawnRate());
    }

    @Test
    public void testCheckIfSpawnable() {
        Wave wave = new Wave(startPosition, pathDirections);

        // Test returning true when spawn rate is zero
        while (wave.getSpawnRate() > 0) {
            wave.updateSpawnRate();
        }
        assertTrue(wave.checkIfSpawnable());
    }
}
