package Model.Map;

/**
 * Class representing non-path tiles that towers can't be placed on, such as where the labels for money, wave etc are
 */
public class OccupiedTile extends ATile{

    /**
     * Constructor
     * @param x the Tiles x position in the matrix
     * @param y the Tiles y position in the matrix
     */
    public OccupiedTile(int x, int y){
        super(x, y, false);
    }
}
