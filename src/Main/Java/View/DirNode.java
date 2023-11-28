package View;

public class DirNode {
    private double x;
    private double y;
    private String dir;

    public DirNode(double x, double y, String dir) {
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
