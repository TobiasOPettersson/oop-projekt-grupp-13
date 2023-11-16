package src.View;

public class DirChange {
    private double x;
    private double y;
    private String dir;

    public DirChange(double x, double y, String dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getDir() {
        return dir;
    }
}
