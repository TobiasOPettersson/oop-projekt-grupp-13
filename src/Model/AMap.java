package src.Model;

import java.util.List;

import src.Model.TileAvailability;

import java.util.ArrayList;

public class AMap {
    public List<ATower> towers = new ArrayList<ATower>();
    public List<AEnemy> enemies = new ArrayList<AEnemy>();
    public TileAvailability grid[][] = new TileAvailability[8][8];
    
    public AMap(){
        createGrid();
    }

    public void createGrid(){
        int temp1 = grid.length;
        int temp2 = grid[0].length;
        for(int i = 0 ; i < temp1; i++){
            for(int j = 0 ; i < temp2; j++){
                grid[j][i] = TileAvailability.Free;
            }
        }
        System.out.println(grid);
    }
}