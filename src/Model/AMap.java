package src.Model;

import java.util.List;

import src.Model.TileAvailability;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AMap {
    public List<ATower> towers = new ArrayList<ATower>();
    public TileAvailability grid[][] = new TileAvailability[8][8];
    
    public AMap(){
        createGrid();
    }

    /*
     * Takes in the vertecies of the path
     * The two list need to be of equal length of type int
     */
    public void createGrid(int[] verteciesX, int[] verteciesY){
        for(TileAvailability[] row : grid){
            Arrays.fill(row, TileAvailability.Free);
        }
    }
}