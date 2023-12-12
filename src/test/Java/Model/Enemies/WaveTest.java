package Model.Enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void testStartWave() {
        AMap map = new MapOne();
        Wave wave = new Wave(map.getStartPosition(), map.getPathDirections());

        // Test incrementing currentWaveNumber
        int initialWaveNumber = wave.getCurrentWaveNumber();
        wave.startWave();
        assertEquals(initialWaveNumber + 1, wave.getCurrentWaveNumber());
    }

    // @Test
    // public void testWavesIsEmpty() {
    //     Wave wave = new Wave(startPosition, pathDirections);

    //     // Test on non-empty waves
    //     assertFalse(wave.wavesIsEmpty());

    //     // Test on empty waves
    //     while (!wave.wavesIsEmpty()) {
    //         wave.startWave();
    //     }
    //     assertTrue(wave.wavesIsEmpty());
    // }

}
