package src.Model;

import java.util.List;
import java.util.ArrayList;

public class AMap {
    private int listSize = 9;
    private List<ATower> towers = new ArrayList<ATower>();
    private ATile grid[][] = new ATile[listSize][listSize];
    private int startPosition = 4;
    private int[][] pathGrid = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {1, 2, 3, 4, 5, 6, 7, 8, 9},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0},};
    private List<EnemyDirection> pathDirections = new ArrayList<EnemyDirection>();

    public AMap() {
        createGrid();
        setStartPosition();
    }

    private void createPathDirections(PathTile start) {
        PathTile next;
        while (start.getNext() != null) {
            next = start.getNext();
            if (start.getX() == next.getX() - 1){
                pathDirections.add(EnemyDirection.RIGHT);
                start = next;
                next = next.getNext();
                continue;
            } 
            else if (start.getX() == next.getX() + 1) {
                pathDirections.add(EnemyDirection.LEFT);
                start = next;
                next = next.getNext();
                continue;
            }
            else if (start.getY() == next.getY() - 1) {
                pathDirections.add(EnemyDirection.DOWN);
                start = next;
                next = next.getNext();
                continue;
            }
            else if (start.getY() == next.getY() + 1) {
                pathDirections.add(EnemyDirection.UP);
                start = next;
                next = next.getNext();
                continue;
            }
        }
    }

    private void createGrid() {
        int tempX = listSize - 1;
        int tempY = 0;
        int tempValue = 0;
        for(int i = 0 ; i < listSize ; i++){
            System.out.println(pathGrid[i][listSize - 1]);
            if (pathGrid[i][listSize - 1] > tempValue){
                tempValue = pathGrid[i][listSize - 1];
                tempY = i;
            } 
        }
        System.out.println("PathTile at: " + tempX + " " + tempY);
        grid[tempY][tempX] = new PathTile(tempX, tempY, null);

        System.out.println("Temp value: " + tempValue);
        while (tempValue > 1) {
            ATile nextTile = grid[tempY][tempX];
            if (tempX - 1 >= 0 && pathGrid[tempY][tempX - 1] == tempValue - 1) {
                tempValue--;
                tempX--;
            } else if (tempX + 1 < listSize - 1 && pathGrid[tempY][tempX + 1] == tempValue - 1) {
                tempValue--;
                tempX++;
            } else if (tempY - 1 >= 0 && pathGrid[tempY - 1][tempX] == tempValue - 1) {
                tempValue--;
                tempY--;
            } else if (tempY + 1 < listSize - 1 && pathGrid[tempY + 1][tempX] == tempValue - 1) {
                tempValue--;
                tempY++;
            }
            System.out.println("Test");
            // Setting the next tile in the grid
            if (nextTile instanceof PathTile) {
                PathTile pt = (PathTile) nextTile;
                grid[tempY][tempX] = new PathTile(tempX, tempY, pt);
                System.out.println("PathTile at: " + tempX + " " + tempY);
            }
        }

        createPathDirections((PathTile)grid[tempY][tempX]);

        // Filling the rest of the grid with TowerTiles
        for (int x = 0; x < listSize; x++) {
            for (int y = 0; y < listSize; y++) {
                if (!(grid[y][x] instanceof PathTile)) {
                    grid[y][x] = new TowerTile(x, y, true);
                    System.out.println("TowerTile at: " + x + " " + y);
                }
            }
        }
    }

    public void createTower(int x, int y, String type){
        ATower tower = null;
        switch (type){
            case "Archer": 
                tower = new Archer(x, y);
                break;
            default: 
                System.out.println("Tower type given is not implemented");
                break;
        }
        towers.add(tower);
        tower.place(x, y);
        grid[y][x].setPlaceable(false);
    }

    public ATile getTile(int x, int y){
        return grid[y][x];
    }

    public List<EnemyDirection> getPathDirections(){
        return new ArrayList<>(this.pathDirections);
    }

    public int getStartPosition(){
        return this.startPosition;
    }

    private void setStartPosition(){
        for(int i = 0 ; i < listSize ; i++){
            if (pathGrid[i][0] == 1) this.startPosition = i;
        }
    }
}