package src.Model;

public class ATile{
    public int x;
    public int y;
    public TileTerrain terrain;

    public ATile(int x, int y){
        this.x = x;
        this.y = y;
        this.terrain = TileTerrain.Plains;
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
}
