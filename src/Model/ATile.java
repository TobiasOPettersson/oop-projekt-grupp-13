package src.Model;

public class ATile implements IPositionable{
    public int x;
    public int y;

    public ATile(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
