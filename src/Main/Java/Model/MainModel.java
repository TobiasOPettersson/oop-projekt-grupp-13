package Model;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
    private AMap map;
    private List<AEnemy> enemies = new ArrayList<AEnemy>();
    private Player player;
    private boolean alive;
    private boolean activeWave;

    public MainModel(){
        this.map = new MapOne();
        this.enemies.add(new EnemyOne(this.map.getStartPosition(), 1, this.map.getPathDirections()));
        this.player = new Player(10, 200);
        this.alive = true;
        this.activeWave = false;
    }
    
    // These enums are temporary
    public enum TowerType{
        Archer
    }

    public enum EnemyType{
        EnemyOne
    }

    public void run(){
        for (AEnemy enemy : this.enemies){
            enemy.move();
            if (enemy.getX() > map.getMapSize()) {
                player.takeDamage(enemy.getDamage());
                enemies.remove(enemy);
            }
        }

        for (ATower tower : this.map.getTower()){
            tower.changeCooldown();
            if (tower instanceof AttackTower){
                AEnemy target = tower.findFirstTarget(enemies);
                if(target != null){
                    target.doDamage(((AttackTower)tower).getDamage());
                    if (target.getHealth() <= 0) {
                        player.addMoney(target.getMoney());
                        enemies.remove(target);
                    } 
                }
            }
        }
        this.alive = alive();
        this.activeWave = activeWave();
    }

    public void createTower(int x, int y, TowerType type){
        ATower tower = null;
        switch (type){
            case Archer: 
                tower = new Archer(x, y);
                break;
            default: 
                System.out.println("Tower type given is not implemented");
                break;
        }
        //towers.add(tower);
        tower.place(x, y);
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
}