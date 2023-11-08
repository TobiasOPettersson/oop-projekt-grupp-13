package src.Model;

import java.util.List;

import src.Model.TileAvailability.GridType;

import java.util.ArrayList;

public class AMap {
    public List<ATower> towers = new ArrayList<ATower>();
    public List<AEnemy> enemies = new ArrayList<AEnemy>();
    public GridType grid [][] = new GridType[10][8];

    public void createGrid(int[] positionX, int[] positionY){
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; i < grid[0].length ; j++){
                grid[j][i] = GridType.Free;
            }
        }
        System.out.println(grid);
    }
}