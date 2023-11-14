package src.Model;

import java.util.List;

import src.Model.TileAvailability;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AMap {
    public List<ATower> towers = new ArrayList<ATower>();
    public List<AEnemy> enemies = new ArrayList<AEnemy>();
    public TileAvailability grid[][] = new TileAvailability[8][8];
    
    public AMap(){
        createGrid();
    }

    public void createGrid(){
        for(TileAvailability[] row : grid){
            Arrays.fill(row, TileAvailability.Free);
        }
        System.out.println(grid);
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
    }
}