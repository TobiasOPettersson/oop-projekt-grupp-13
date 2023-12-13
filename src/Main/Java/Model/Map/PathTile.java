package Model.Map;

public class PathTile extends ATile {
    PathTile next;
    
    /**
     * TODO Javadoc comment
     * @param x the Tiles x position in the matrix
     * @param y the Tiles y position in the matrix
     * @param next points towards the next PathTile
     */
    public PathTile(int x, int y, PathTile next){
        super(x, y, false);
        this.next = next;
    }

    public PathTile getNext(){
        return next;
    }
}
