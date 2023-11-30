package Model.Map;

import java.util.List;
import java.util.Map;

import Controller.Interfaces.ITowerObserver;
import Model.Enums.Direction;
import Model.Towers.ATower;
import Model.Towers.KnifeTower;
import Model.Towers.TowerType;

import java.awt.Point;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;

public class AMap{
    private final int MAP_HEIGHT = 10;
    private final int MAP_WIDTH = 20;
    private List<ATower> towers = new ArrayList<ATower>();
    private ATile grid[][] = new ATile[MAP_HEIGHT][MAP_WIDTH];
    private int startPosition;
    private int[][] pathGrid = {
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 8, 9, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 7, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 6, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 2, 3, 4, 5, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };

    private List<Direction> pathDirections = new ArrayList<Direction>();

    public AMap() {
        createGrid();
        setStartPosition();
    }

    private void createPathDirections(PathTile start) {
        PathTile next;
        pathDirections.add(Direction.RIGHT);
        while (start.getNext() != null) {
            next = start.getNext();
            if (start.getX() == next.getX() - 1) {
                pathDirections.add(Direction.RIGHT);
                start = next;
                next = next.getNext();
                continue;
            } else if (start.getX() == next.getX() + 1) {
                pathDirections.add(Direction.LEFT);
                start = next;
                next = next.getNext();
                continue;
            } else if (start.getY() == next.getY() - 1) {
                pathDirections.add(Direction.DOWN);
                start = next;
                next = next.getNext();
                continue;
            } else if (start.getY() == next.getY() + 1) {
                pathDirections.add(Direction.UP);
                start = next;
                next = next.getNext();
                continue;
            }
        }
        pathDirections.add(Direction.RIGHT);
        for (int i = 0; i < pathDirections.size(); i++) {
        }
    }



    private void createGrid() {
        int tempX = this.MAP_WIDTH - 1;
        int tempY = 0;
        int tempValue = 0;
        for(int i = 0 ; i < this.MAP_HEIGHT ; i++){
            if (pathGrid[i][tempX] > tempValue){
                tempValue = pathGrid[i][tempX];
                tempY = i;
            }
        }
        // System.out.println("PathTile at: " + tempX + " " + tempY); // DEL
        grid[tempY][tempX] = new PathTile(tempX, tempY, null);

        //System.out.println("Temp value: " + tempValue); // DEL
        while (tempValue > 1) {
            ATile nextTile = grid[tempY][tempX];
            if (tempX - 1 >= 0 && pathGrid[tempY][tempX - 1] == tempValue - 1) {
                tempValue--;
                tempX--;
            } else if (tempX + 1 < this.MAP_WIDTH - 1 && pathGrid[tempY][tempX + 1] == tempValue - 1) {
                tempValue--;
                tempX++;
            } else if (tempY - 1 >= 0 && pathGrid[tempY - 1][tempX] == tempValue - 1) {
                tempValue--;
                tempY--;
            } else if (tempY + 1 < this.MAP_HEIGHT - 1 && pathGrid[tempY + 1][tempX] == tempValue - 1) {
                tempValue--;
                tempY++;
            }

            // Setting the next tile in the grid
            if (nextTile instanceof PathTile) {
                PathTile pt = (PathTile) nextTile;
                grid[tempY][tempX] = new PathTile(tempX, tempY, pt);
                //System.out.println("PathTile at: " + tempX + " " + tempY); // DEL
            }
        }
        createPathDirections((PathTile)grid[tempY][tempX]);

        // Filling the rest of the grid with TowerTiles
        for (int x = 0; x < this.MAP_WIDTH; x++) {
            for (int y = 0; y < this.MAP_HEIGHT; y++) {
                if (!(grid[y][x] instanceof PathTile)) {
                    grid[y][x] = new TowerTile(x, y, true);
                    //System.out.println("TowerTile at: " + x + " " + y); // DEL
                }
            }
        }

        for (ATile[] aTiles : grid) {
            for (ATile aTile : aTiles) {
                // System.out.print((aTile instanceof PathTile) + " "); // DEL
            }
            // System.out.println(); // DEL
        }
    }

    public void createTower(int x, int y, TowerType type){
        if(((TowerTile)getTile(x, y)).placeable){
            ATower tower = null;
            switch (type){
                case knife: 
                    tower = new KnifeTower(x, y);
                    break;
                default:
                    System.out.println("Tower type given is not implemented");
                    break;
            }
            towers.add(tower);
            getTile(x, y).setPlaceable(false);
            ((TowerTile)getTile(x, y)).setTower(tower);
        }
    }

    public void upgradeTower(int x, int y, int upgradeLvl) {
        //getTile(x, y).getTower().upgrade(upgradeLvl);
    }

    public ATile getTile(int x, int y){
        return grid[y][x];
    }

    public ATile[][] getTileGrid() {
        return this.grid;
    }

    public List<Direction> getPathDirections() {
        return new ArrayList<>(this.pathDirections);
    }

    public int getStartPosition() {
        return this.startPosition;
    }

    private void setStartPosition() {
        for (int i = 0; i < this.MAP_HEIGHT; i++) {
            if (pathGrid[i][0] == 1)
                this.startPosition = i;
        }
    }

    public List<ATower> getTowers() {
        return this.towers;
    }

    public int getMapSizeX() {
        return this.MAP_WIDTH;
    }
    public int getMapSizeY() {
        return this.MAP_HEIGHT;
    }

    public int[][] getPathGrid() {
        return this.pathGrid;
    }

}