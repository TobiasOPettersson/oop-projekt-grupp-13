package Model.Enemies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

/**
 * A class representing an enemy factory which creates all instances of enemies.
 */
public class EnemyFactory {
    private int startPosition;
    private List<Direction> pathDirection = new ArrayList<Direction>();

    /**
     * Constructor
     * @param startPosition get the position of the first Tile
     * @param pathDirection get the list with directions of the path
     */
    public EnemyFactory(int startPosition, List<Direction> pathDirection){
        this.startPosition = startPosition;
        this.pathDirection = pathDirection;
    }

    /**
     * Creates a queue of AEnemy objects from a queue of EnemyTypes
     * @param currentWaveType a queue of EnemyTypes for the current wave
     * @return a queue of AEnemy for the current wave
     */
    public Queue<AEnemy> createCurrentWave(Queue<EnemyType> currentWaveType){
        Queue<AEnemy> currentWave = new LinkedList<AEnemy>(); 
        currentWave.clear();
        EnemyType currentEnemyType;
        while (currentWaveType.isEmpty() == false) {
            currentEnemyType = currentWaveType.poll();
            switch (currentEnemyType) {
                case banana:
                    currentWave.add(new BananaEnemy(startPosition, pathDirection));
                    break;

                case tomato:
                    currentWave.add(new TomatoEnemy(startPosition, pathDirection));
                    break;

                case cheese:
                    currentWave.add(new CheeseEnemy(startPosition, pathDirection));
                    break;

                case chicken:
                    currentWave.add(new ChickenEnemy(startPosition, pathDirection));
                    break;
            
                default:
                    break;
            } 
        }
        return currentWave;
    }
}
