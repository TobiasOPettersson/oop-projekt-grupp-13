package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Queue;
import java.util.LinkedList;

import Model.Enemies.AEnemy;
import Model.Enemies.TomatoEnemy;
import Model.Enemies.Wave;
import Model.Enemies.EnemyFactory;
import Model.Enums.Direction;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;
import Model.Interfaces.IObservable;
import Model.Interfaces.ITargetable;
import Model.Interfaces.ITowerUpgradeObserver;
import Model.Enums.EnemyType;
import Model.Map.AMap;
import Model.Map.ATile;
import Model.Map.MapOne;
import Model.Player.Player;
import Model.Towers.ATower;
import Model.Towers.AttackTower;

public class MainModel implements ITowerUpgradeObserver {
    private AMap map;
    private List<AEnemy> enemies = new ArrayList<AEnemy>();
    private Queue<AEnemy> currentWaveEnemies = new LinkedList<AEnemy>();
    private Player player;
    private boolean alive;
    private boolean activeWave;
    private Wave allWaves;
    List<IObservable> observers = new ArrayList<IObservable>();

    /**
     * TODO Javadoc comment
     */
    public MainModel() {
        this.player = new Player(5, 3);
        this.map = new MapOne();
        this.allWaves = new Wave(this.map.getStartPosition(), this.map.getPathDirections());
        this.alive = true;
        this.activeWave = false;
    }

    /**
     * TODO Javadoc comment, refactor into seperate methods?
     */
    public void run() {
        if (activeWave) {
            System.out.println(enemies.size());
            AEnemy enemyToRemove = null;
            for (AEnemy enemy : enemies) {
                enemy.updateAnimationTick();
                enemy.triggerConditions();
                enemy.move();
                enemy.setStaggered(false);
                if (enemy.getX() > map.getMapSizeX()) {
                    player.takeDamage(enemy.getDamage());
                    enemyToRemove = enemy;
                }
            }

            // Removal is outside the for-loop so that the list size doesnt chance while
            // inside the for-loop
            if (enemyToRemove != null) {
                enemies.remove(enemyToRemove);
            }

            this.allWaves.updateSpawnRate();
            if (this.allWaves.checkIfSpawnable() && this.currentWaveEnemies.isEmpty() == false) {
                this.enemies.add(this.currentWaveEnemies.poll());
            }

            for (ATower tower : map.getTowers()) {
                tower.updateAnimationTick();
                if (!tower.isOnCooldown()) {
                    if (tower instanceof AttackTower) {
                        List<AEnemy> targets = tower.findEnemiesInRange(enemies);
                        if (targets != null) {
                            for (AEnemy target : targets) {
                                ((AttackTower) tower).attack(target);
                                if (target.getHealth() <= 0) {
                                    player.addMoney(target.getMoney());
                                    enemies.remove(target);
                                }
                            }
                        }
                    }
                } else {
                    tower.decrementCooldown();
                }
            }

            this.alive = alive();
            this.activeWave = activeWave(); // Commented since it doesnt check if the wave is finished, only if there
                                            // are no enemies currently on the panel/ in the list
        }
        notifyObservers();
    }

    //-----------------------Tower methods---------------------// 

    /**
     * Calls createTower() in map that creates a new instance of a tower
     * @param x Grid x-index of where the tower will be created
     * @param y Grid y-index of where the tower will be created
     * @param type Type of the tower
     * @throws Exception if the player doesn't have enough money to buy the tower
     */
    public void createTower(int x, int y, TowerType type) throws Exception {
        map.createTower(x, y, type);
    }

    @Override
    
    /**
     * Calls upgradeTower() in map that upgrades the tower at tile (x, y)
     * @param x The towers x-index on the grid
     * @param y The towers y-index on the grid
     * @param upgrade The type of upgrade that will be added
     */
    public void upgradeTower(int x, int y, Upgrade upgrade) {
        map.upgradeTower(x, y, upgrade);
    }

    //-----------------------Observer---------------------------//
    public void addObserver(IObservable observer) {
        observers.add(observer);
    }

    private void notifyObservers(){
        for (IObservable observer : observers) {
            observer.update();
        }
    }

    //-----------------------Other methods---------------------// 

    /**
     * Starts the wave
     */
    public void play() {
        System.out.println(this.currentWaveEnemies);
        if (canStartNewWave()) {
            this.currentWaveEnemies = this.allWaves.getWave();
            this.activeWave = true;
        }
    }

    private boolean canStartNewWave() {
        if (this.activeWave == false && this.alive == true && this.currentWaveEnemies.isEmpty() == true
                && this.enemies.isEmpty() == true)
            return true;
        return false;
    }
    private boolean alive(){
        return player.getHealth() > 0;
    }
   


    //-----------------------ITargetale convertion methods (Might use)---------------------// 

    public List<ITargetable> convertEnemiesToTargetables() {
        List<ITargetable> targetables = new ArrayList<>();
        for (AEnemy enemy : enemies) {
            targetables.add(enemy);
        }
        return targetables;
    }

    public List<ITargetable> convertTowersToTargetables() {
        List<ITargetable> targetables = new ArrayList<>();
        for (ATower tower : map.getTowers()) {
            targetables.add(tower);
        }
        return targetables;
    }

    //----------------------------Waves--------------------------// 

    /**
     * TODO Javadoc comment
     * @return
     */
    public boolean activeWave(){
        if (this.enemies.isEmpty() && this.currentWaveEnemies.isEmpty()) return false;
        return true;
    }

    //----------------------------Getters and setters--------------------------// 

    public boolean getAlive() {
        return this.alive;
    }

    public boolean getActiveWave() {
        return this.activeWave;
    }

    public ATile[][] getTileGrid() {
        return map.getTileGrid();
    }

    public List<Direction> getPathDirections() {
        return map.getPathDirections();
    }

    public int getStartPosition() {
        return map.getStartPosition();
    }

    public int getEndPosition() {
        return map.getEndPosition();
    }

    public int[][] getPathGrid() {
        return map.getPathGrid();
    }

    public AMap getMap() {
        return map;
    }

    public List<AEnemy> getEnemies() {
        return enemies;
    }

    public int getMapSizeX() {
        return map.getMapSizeX();
    }

    public int getMapSizeY() {
        return map.getMapSizeY();
    }

    public List<AEnemy> getEnemyArray() {
        return this.enemies;
    }

    public List<ATower> getTowers() {
        return this.map.getTowers();
    }

    /**
     * TODO REMOVE OR CHANGE
     */
    public Player getPlayer() {
        map.setPlayer(player);
        return player;
    }

    public int getPlayerMoney() {
        return player.getMoney();
    }

    public int getPlayerHealth() {
        return player.getHealth();
    }

    public boolean allWavesDead() {
        return this.allWaves.wavesIsEmpty();
    }

    public int getCurrentWaveNumber() {
        return this.allWaves.getCurrentWaveNumber();
    }

    public int getMaxNumberofWaves() {
        return this.allWaves.getMaxNumberOfWaves();
    }
}