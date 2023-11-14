package Model;

import java.util.List;

import Model.TileAvailability;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AMap {
    public List<ATower> towers = new ArrayList<ATower>();
    public List<AEnemy> enemies = new ArrayList<AEnemy>();
    public TileAvailability grid[][] = new TileAvailability[8][8];

    public AMap() {
        createGrid();
    }

    public void createGrid() {
        for (TileAvailability[] row : grid) {
            Arrays.fill(row, TileAvailability.Free);
        }
        System.out.println(grid);
    }

    public void addTower(ATower tower) {
        // towers.add(tower);
        //
        int towerX = tower.getX();
        int towerY = tower.getY();

        grid[towerX][towerY].setOccupied(true);
        towers.add(tower);

        // occupied... x, y värde på tower..
    }

}