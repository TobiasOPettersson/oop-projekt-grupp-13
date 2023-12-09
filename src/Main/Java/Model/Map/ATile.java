package Model.Map;

import Model.Enums.TileTerrain;

public class ATile{
    public int x;
    public int y;
    public TileTerrain terrain;
    public boolean placeable;

    /**
     * TODO Javadoc comment
     * @param x
     * @param y
     * @param placeable
     */
    public ATile(int x, int y, boolean placeable){
        this.x = x;
        this.y = y;
        this.terrain = TileTerrain.Plains;
        this.placeable = placeable;
    }

    //----------------------------Getter and setters----------------------//

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TileTerrain getTerrain(){
        return terrain;
    }

    public void setTerrain(TileTerrain changeTerrain){
        this.terrain = changeTerrain;
    }

    public boolean getPlaceable() {
        return placeable;
    }

    public void setPlaceable(boolean change) {
        this.placeable = change;
    }
}
