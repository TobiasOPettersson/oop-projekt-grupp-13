package Model;

public class TowerTile extends ATile {
    boolean occupied;

    public TowerTile(int x, int y, boolean occupied) {
        super(x, y);
        this.occupied = occupied;
    }

    public boolean getOccupied() {
        return this.occupied;
    }

    public void setOccupied(boolean change) {
        this.occupied = change;
    }
}
