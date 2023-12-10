package Model.Enemies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Model.Enums.Direction;
import Model.Enums.EnemyType;

public class WaveFactory {
    private Queue<EnemyType> currentWaveType = new LinkedList<EnemyType>();
    private Queue<AEnemy> currentWave = new LinkedList<AEnemy>(); 
    private int startPosition;
    private List<Direction> pathDirection = new ArrayList<Direction>();

    public WaveFactory(int startPosition, List<Direction> pathDirection){
        this.startPosition = startPosition;
        this.pathDirection = pathDirection;
    }

    public Queue<AEnemy> createCurrentWave(Queue<EnemyType> currentWaveType){
        this.currentWaveType = currentWaveType;
        this.currentWave.clear();
        EnemyType currentEnemyType;
        while (this.currentWaveType.isEmpty() == false) {
            currentEnemyType = this.currentWaveType.poll();
            if (currentEnemyType == EnemyType.tomato) this.currentWave.add(new TomatoEnemy(this.startPosition, this.pathDirection));
            if (currentEnemyType == EnemyType.banana) this.currentWave.add(new BananaEnemy(this.startPosition, this.pathDirection));
            //if statements for every enemytype
        }

        return this.currentWave;
    }
}
