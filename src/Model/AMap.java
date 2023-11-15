package src.Model;

import java.util.List;
import java.util.Queue;

import Model.TileAvailability;
import src.Model.TileTerrain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AMap {
    public List<ATower> towers = new ArrayList<ATower>();
    public ATile grid[][] = new ATile[9][9];
    public int startPosition = 4;
    public int[][] pathGrid = {{0, 0, 0, 0, 1, 0, 0, 0, 0},
                               {0, 0, 0, 0, 2, 0, 0, 0, 0},
                               {0, 0, 0, 0, 3, 0, 0, 0, 0},
                               {0, 0, 0, 0, 4, 0, 0, 0, 0},
                               {0, 0, 0, 0, 5, 0, 0, 0, 0},
                               {0, 0, 0, 0, 6, 0, 0, 0, 0},
                               {0, 0, 0, 0, 7, 0, 0, 0, 0},
                               {0, 0, 0, 0, 8, 0, 0, 0, 0},
                               {0, 0, 0, 0, 9, 0, 0, 0, 0},};

    public AMap() {
        createGrid();
    }

    public void createGrid() {
        /* 
        for (int i = 8 ; i >= 0 ; i--){
            if (i == 8) grid[8][4] = new PathTile(4, 8, null);
            else {
                ATile nextTile = grid[i + 1][4];
                if (nextTile instanceof PathTile){
                    PathTile pt = (PathTile) nextTile;
                    grid[i][4] = new PathTile(i, 4, pt);
                }
            }
        }
        */
        int tempX = 8;
        int tempY = 0;
        int tempValue = 0;
        for(int i = 0 ; i < 9 ; i++){
            System.out.println(pathGrid[8][i]);
            if (pathGrid[8][i] > tempValue){
                tempY = i;
            } 
        }
        System.out.println("PathTile at: " + tempX + " " + tempY);
        grid[tempX][tempY] = new PathTile(tempX, tempY, null);

        /*
        while (pathGrid[tempX][tempY] != 1) {
            if (tempX - 1 >= 0) {
                if(pathGrid[tempX - 1][tempY] == tempValue - 1){
                    ATile nextTile = grid[tempX][tempY];
                    tempValue--;
                    tempX--;
                    if (nextTile instanceof PathTile){
                        PathTile pt = (PathTile) nextTile;
                        grid[tempX][tempY] = new PathTile(tempX, tempY, pt);
                    }
                    continue;
                }
            }
    
            // Check right
            if (tempX + 1 < 8) {
                if(pathGrid[tempX + 1][tempY] == tempValue - 1){
                    ATile nextTile = grid[tempX][tempY];
                    tempValue--;
                    tempX++;
                    if (nextTile instanceof PathTile){
                        PathTile pt = (PathTile) nextTile;
                        grid[tempX][tempY] = new PathTile(tempX, tempY, pt);
                    }
                    continue;
                }
            }
    
            // Check above
            if (tempY - 1 >= 0) {
                if(pathGrid[tempX][tempY - 1] == tempValue - 1){
                    ATile nextTile = grid[tempX][tempY];
                    tempValue--;
                    tempY--;
                    if (nextTile instanceof PathTile){
                        PathTile pt = (PathTile) nextTile;
                        grid[tempX][tempY] = new PathTile(tempX, tempY, pt);
                    }
                    continue;
                }
            }
    
            // Check below
            if (tempY + 1 < 8) {
                if(pathGrid[tempX][tempY + 1] == tempValue - 1){
                    ATile nextTile = grid[tempX][tempY];
                    tempValue--;
                    tempY++;
                    if (nextTile instanceof PathTile){
                        PathTile pt = (PathTile) nextTile;
                        grid[tempX][tempY] = new PathTile(tempX, tempY, pt);
                    }
                    continue;
                }
            }
        }
        */

        while (tempValue > 1) {
            ATile nextTile = grid[tempX][tempY];
            if (tempX - 1 >= 0 && pathGrid[tempX - 1][tempY] == tempValue - 1) {
                tempValue--;
                tempX--;
            } else if (tempX + 1 < 8 && pathGrid[tempX + 1][tempY] == tempValue - 1) {
                tempValue--;
                tempX++;
            } else if (tempY - 1 >= 0 && pathGrid[tempX][tempY - 1] == tempValue - 1) {
                tempValue--;
                tempY--;
            } else if (tempY + 1 < 8 && pathGrid[tempX][tempY + 1] == tempValue - 1) {
                tempValue--;
                tempY++;
            }
        
            // Setting the next tile in the grid
            if (nextTile instanceof PathTile) {
                PathTile pt = (PathTile) nextTile;
                grid[tempX][tempY] = new PathTile(tempX, tempY, pt);
                System.out.println("PathTile at: " + tempX + " " + tempY);
            }
        }

        // Filling the rest of the grid with TowerTiles
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!(grid[i][j] instanceof PathTile)) {
                    grid[i][j] = new TowerTile(i, j, true);
                    System.out.println("TowerTile at: " + i + " " + j);
                }
            }
        }
    }

    public void addTower(ATower tower) {
        // towers.add(tower);
        //
        int towerX = tower.getX();
        int towerY = tower.getY();

        grid[towerX][towerY].setPlaceable(false);
        towers.add(tower);

        // occupied... x, y värde på tower..
    }

    public ATile getTile(int x, int y){
        return grid[x][y];
    }

}