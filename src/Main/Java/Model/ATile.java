package Model;

public class ATile{
    public int x;
    public int y;
    public TileTerrain terrain;
    public boolean placeable;

    public ATile(int x, int y, boolean placeable){
        this.x = x;
        this.y = y;
        this.terrain = TileTerrain.Plains;
        this.placeable = placeable;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public TileTerrain getTerrain(){
        return this.terrain;
    }

    public void setTerrain(TileTerrain changeTerrain){
        this.terrain = changeTerrain;
    }

    public boolean getPlaceable() {
        return this.placeable;
    }

    public void setPlaceable(boolean change) {
        this.placeable = change;
    }
}
