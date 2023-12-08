package View;

public class DirNode {
    private double x;
    private double y;
    private String dir;

    /**
     * TODO Javadoc comment
     * @param x
     * @param y
     * @param dir
     */
    public DirNode(double x, double y, String dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    //----------------------------Getters and setters--------------------------// 

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
