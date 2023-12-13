package Model.Enemies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

public class EnemyFactory {
    private Queue<AEnemy> currentWave = new LinkedList<AEnemy>(); 
    private int startPosition;
    private List<Direction> pathDirection = new ArrayList<Direction>();

    /**
     * 
     * @param startPosition get the position of the first Tile
     * @param pathDirection get the list with directions of the path
     */
    public EnemyFactory(int startPosition, List<Direction> pathDirection){
        this.startPosition = startPosition;
        this.pathDirection = pathDirection;
    }

    /**
     * 
     * @param currentWaveType a queue of EnemyTypes for the current wave
     * @return a queue of AEnemy for the current wave
     */
    public Queue<AEnemy> createCurrentWave(Queue<EnemyType> currentWaveType){
        currentWave.clear();
        EnemyType currentEnemyType;
        while (currentWaveType.isEmpty() == false) {
            currentEnemyType = currentWaveType.poll();
            if (currentEnemyType == EnemyType.tomato) currentWave.add(new TomatoEnemy(startPosition, pathDirection));
            if (currentEnemyType == EnemyType.banana) currentWave.add(new BananaEnemy(startPosition, pathDirection));
            if (currentEnemyType == EnemyType.cheese) currentWave.add(new CheeseEnemy(startPosition, pathDirection));
            if (currentEnemyType == EnemyType.chicken) currentWave.add(new ChickenEnemy(startPosition, pathDirection));
        }
        return currentWave;
    }
}
