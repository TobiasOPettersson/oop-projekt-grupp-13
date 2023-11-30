package Model.Map;

import Model.Towers.ATower;

public class TowerTile extends ATile {

    private ATower tower;

    public TowerTile(int x, int y, boolean placeable) {
        super(x, y, placeable);
    }

    public ATower getTower(){
        return tower;
    }
    
    public void setTower(ATower tower) {
        this.tower = tower;
    }
}
