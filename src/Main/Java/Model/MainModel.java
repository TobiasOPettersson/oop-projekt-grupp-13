package Model;

import java.util.ArrayList;
import java.util.List;

import Controller.ITowerObserver;

public class MainModel implements ITowerObserver{
    private AMap map;
    private List<AEnemy> enemies = new ArrayList<AEnemy>();
    private Player player;
    private boolean alive;
    private boolean activeWave;
    private double tileOffset = 0.5;

    public MainModel(){
        this.map = new MapOne();
        // Temp Wave thing. Spawns three enemies.
        for (int i = 0; i <= 2; i++){
            this.enemies.add(new EnemyOne(this.map.getStartPosition() + tileOffset, 0.02, this.map.getPathDirections()));
        }
        //this.enemies.add(new EnemyOne(this.map.getStartPosition(), 1, this.map.getPathDirections()));
        this.player = new Player(10, 200);
        this.alive = true;
        this.activeWave = false;
    }

    public void run(){
        if(!activeWave){
            return;
        }

        AEnemy enemyToRemove = null;
        for (AEnemy enemy : enemies){
            enemy.move();
            if (enemy.getX() > map.getMapSizeX()) {
                player.takeDamage(enemy.getDamage());
                enemyToRemove = enemy;
            }
        }

        if(enemyToRemove != null){
            enemies.remove(enemyToRemove);
        }

        if(enemies.get(enemies.size()-1).getDirectionsSize() < map.getPathDirections().size()-1){
            enemies.add(new EnemyOne(map.getStartPosition(), 0.02, this.map.getPathDirections()));
        }


        for (ATower tower : map.getTowers()){
            if(tower.triggerCooldown()){
                if (tower instanceof AttackTower){
                    AEnemy target = tower.findFirstTarget(enemies);
                    if(target != null){
                        ((AttackTower)tower).attack(target);
                        if (target.getHealth() <= 0) {
                            player.addMoney(target.getMoney());
                            enemies.remove(target);
                        }
                    }
                }
            }
        }
        this.alive = alive();
        this.activeWave = activeWave();
    }

    private boolean alive(){
        if (player.getHealth() <= 0) return false;
        return true;
    }

    private boolean activeWave(){
        if (enemies.isEmpty()) return false;
        return true;
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

    @Override
    public void createTower(int x, int y, TowerType type){
        map.createTower(x, y, type);
    }

    @Override
    public void upgradeTower(int x, int y, int upgradeLvl) {
        map.upgradeTower(x, y, upgradeLvl);
    }

    public List<AEnemy> getEnemies() {
        return enemies;
    }

    public void play(){
        activeWave = true;
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