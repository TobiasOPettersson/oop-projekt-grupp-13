package Model.Map;

public class OccupiedTile extends ATile{

    /**
     * A Tile where you can't place anything on. Such as where the labels for money, wave etc are
     * @param x the Tiles x position in the matrix
     * @param y the Tiles y position in the matrix
     */
    public OccupiedTile(int x, int y){
        super(x, y, false);
    }
}
