package Model;

import java.util.ArrayList;
import java.util.List;

import Controller.Interfaces.ITowerObserver;
import Model.Enemies.AEnemy;
import Model.Enemies.EnemyOne;
import Model.Enums.Direction;
import Model.Interfaces.ITargetable;
import Model.Map.AMap;
import Model.Map.ATile;
import Model.Map.MapOne;
import Model.Player.Player;
import Model.Towers.ATower;
import Model.Towers.AttackTower;
import Model.Towers.TowerType;

public class MainModel implements ITowerObserver{
    private AMap map;
    private List<AEnemy> enemies = new ArrayList<AEnemy>();
    private Player player;
    private boolean alive;
    private boolean activeWave;

    public MainModel(){
        this.map = new MapOne();
        // Temp Wave thing. Spawns three enemies.
        for (int i = 0; i <= 10; i++){
            this.enemies.add(new EnemyOne(map.getStartPosition(), 0.02, map.getPathDirections()));
        }
        //this.enemies.add(new EnemyOne(this.map.getStartPosition(), 1, this.map.getPathDirections()));
        this.player = new Player(10, 200);
        this.alive = true;
        this.activeWave = false;
    }

    public void run(){
        if(activeWave){
        AEnemy enemyToRemove = null;
        for (AEnemy enemy : enemies){
            enemy.move();
            if (enemy.getX() > map.getMapSizeX()) {
                player.takeDamage(enemy.getDamage());
                enemyToRemove = enemy;
            }
        }

        // Removal is outside the for-loop so that the list size doesnt chance while inside the for-loop
        if(enemyToRemove != null){
            enemies.remove(enemyToRemove);
        }

        // Spawn a new enemy if the last enemy has walked one tile
        //if(enemies.isEmpty() || enemies.get(enemies.size()-1).getDirectionsSize() < map.getPathDirections().size()-2){
        //    enemies.add(new EnemyOne(map.getStartPosition(), 0.02, this.map.getPathDirections()));
        //}


        for (ATower tower : map.getTowers()){
            if(!tower.isOnCooldown()){
                if (tower instanceof AttackTower){
                    List<AEnemy> targets = tower.findEnemiesInRange(enemies);
                    if(targets != null){
                        System.out.println(targets.size());
                        for(AEnemy target : targets){
                            ((AttackTower)tower).attack(target);
                            if (target.getHealth() <= 0) {
                                player.addMoney(target.getMoney());
                                enemies.remove(target);
                            }
                        }
                    }
                }
            } else{
                tower.decrementCooldown();
            }
        }

        this.alive = alive();
        //this.activeWave = activeWave(); Commented since it doesnt check if the wave is finished, only if there are no enemies currently on the panel/ in the list
        }
    }

    @Override
    public void createTower(int x, int y, TowerType type){
        map.createTower(x, y, type);
    }

    @Override
    public void upgradeTower(int x, int y, int upgradeLvl) {
        map.upgradeTower(x, y, upgradeLvl);
    }

    public void play(){
        activeWave = true;
    }


    public List<ITargetable> convertEnemiesToTargetables(){
        List<ITargetable> targetables = new ArrayList<>();
        for (AEnemy enemy : enemies) {
            targetables.add(enemy);
        }
        return targetables;
    }

    public List<ITargetable> convertTowersToTargetables(){
        List<ITargetable> targetables = new ArrayList<>();
        for (ATower tower : map.getTowers()) {
            targetables.add(tower);
        }
        return targetables;
    }

    private boolean alive(){
        return player.getHealth() > 0;
    }

    private boolean activeWave(){
        return enemies.isEmpty();
    }

    public boolean getAlive(){
        return this.alive;
    }

    public boolean getActiveWave(){
        return this.activeWave;
    }

   public ATile [][] getTileGrid(){
        return map.getTileGrid();
    }

    public List<Direction> getPathDirections(){
        return map.getPathDirections();
    }
    public int getStartPosition(){
        return map.getStartPosition();
    }
    public int[][] getPathGrid(){
        return map.getPathGrid();
    }
    public AMap getMap(){
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
    public List<AEnemy> getEnemyArray(){
        return this.enemies;
    }
    public List<ATower> getTowers(){
        return this.map.getTowers();
    }
}