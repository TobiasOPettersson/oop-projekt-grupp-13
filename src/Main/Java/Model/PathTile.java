package Model;

public class PathTile extends ATile {
    PathTile next;
    
    public PathTile(int x, int y, PathTile next){
        super(x, y, false);
        this.next = next;
    }

    public PathTile getNext(){
        return this.next;
    }
}
