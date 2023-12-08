package Model.Map;

public class PathTile extends ATile {
    PathTile next;
    
    /**
     * TODO Javadoc comment
     * @param x
     * @param y
     * @param next
     */
    public PathTile(int x, int y, PathTile next){
        super(x, y, false);
        this.next = next;
    }

    public PathTile getNext(){
        return this.next;
    }
}
