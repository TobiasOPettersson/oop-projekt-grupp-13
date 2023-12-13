package Model.Map;

import Model.Towers.ATower;

public class TowerTile extends ATile {

    private ATower tower;

    /**
     * TODO Javadoc comment
     * @param x the Tiles x position in the matrix
     * @param y the Tiles y position in the matrix
     * @param placeable if it's possible to place a tower on this tile
     */
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
