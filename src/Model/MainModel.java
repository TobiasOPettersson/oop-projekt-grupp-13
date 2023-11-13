package src.Model;

public class MainModel {
    
    // These enums are temporary
    public enum TowerType{
        Archer
    }

    public enum EnemyType{
        EnemyOne
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
}