package Model.Map;

import java.util.List;

import Model.Enums.Direction;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;
import Model.Player.Player;
import Model.Towers.ATower;
import Model.Towers.BlowtorchTower;
import Model.Towers.FreezerTower;
import Model.Towers.KnifeTower;
import Model.Towers.MalletTower;
import Model.Towers.SlicerTower;

import java.util.ArrayList;

public class AMap{
    private final int MAP_HEIGHT = 10;
    private final int MAP_WIDTH = 20;
    private List<ATower> towers = new ArrayList<ATower>();
    private ATile grid[][] = new ATile[MAP_HEIGHT][MAP_WIDTH];
    private int startPosition;
    private int[][] pathGrid;
    private List<Direction> pathDirections = new ArrayList<Direction>();
    private Player player;

    public AMap(int[][] pathGrid) {
        this.pathGrid = pathGrid;
        createPathGrid();
        fillOccupiedTile();
        fillGridTowerTile();
        setStartPosition();
    }


    //----------------------------Constructor methods----------------------//

    /*
     * Create the instances of every pathTile on the grid
     * Then fill the pathDirections with the directions of the path
     */
    private void createPathGrid() {
        int tempX = this.MAP_WIDTH - 1;
        int tempY = 0;
        int tempValue = 0;
        for(int i = 0 ; i < this.MAP_HEIGHT ; i++){
            if (this.pathGrid[i][tempX] > tempValue){
                tempValue = this.pathGrid[i][tempX];
                tempY = i;
            }
        }
        
        this.grid[tempY][tempX] = new PathTile(tempX, tempY, null);

       
        while (tempValue > 1) {
            ATile nextTile = this.grid[tempY][tempX];
            if (tempX - 1 >= 0 && pathGrid[tempY][tempX - 1] == tempValue - 1) {
                tempX--;
            } else if (tempX + 1 < this.MAP_WIDTH - 1 && this.pathGrid[tempY][tempX + 1] == tempValue - 1) {
                tempX++;
            } else if (tempY - 1 >= 0 && this.pathGrid[tempY - 1][tempX] == tempValue - 1) {
                tempY--;
            } else if (tempY + 1 < this.MAP_HEIGHT - 1 && this.pathGrid[tempY + 1][tempX] == tempValue - 1) {
                tempY++;
            }
            tempValue--;

            // Setting the next tile in the grid
            nextTileInGrid(nextTile, tempX, tempY);
        }
        createPathDirections((PathTile)grid[tempY][tempX]);
    }
   
    /*
     * Fill the rest of the grid that isn't pathTile with towerTile
     */
    private void fillGridTowerTile(){
        for (int x = 0; x < this.MAP_WIDTH; x++) {
            for (int y = 0; y < this.MAP_HEIGHT; y++) {
                if (!(this.grid[y][x] instanceof PathTile || this.grid[y][x] instanceof OccupiedTile)) {
                    this.grid[y][x] = new TowerTile(x, y, true);
                }
            }
        }

    }

    /*
     * Find the starting position of the path
     */
    private void setStartPosition() {
        for (int i = 0; i < this.MAP_HEIGHT; i++) {
            if (this.pathGrid[i][0] == 1)
                this.startPosition = i;
        }
    }

    /*
     * Place the tiles where the player can't place towers and isn't a path
     */
    private void fillOccupiedTile(){
        this.grid[this.MAP_HEIGHT-1][this.MAP_WIDTH-1] = new OccupiedTile(MAP_WIDTH-1, MAP_HEIGHT-1);
        this.grid[this.MAP_HEIGHT-2][this.MAP_WIDTH-1] = new OccupiedTile(MAP_WIDTH-2, MAP_HEIGHT-1);
        this.grid[this.MAP_HEIGHT-2][this.MAP_WIDTH-2] = new OccupiedTile(MAP_WIDTH-2, MAP_HEIGHT-2);
        this.grid[this.MAP_HEIGHT-1][this.MAP_WIDTH-2] = new OccupiedTile(MAP_WIDTH-1, MAP_HEIGHT-2);
    }


    //----------------------------Constructor helper methods----------------------// 

    /*
     * Create the next pathTile and have it point at the previous pathTile
     */
    private void nextTileInGrid(ATile nextTile, int tempX, int tempY){
        if (nextTile instanceof PathTile) {
            PathTile pt = (PathTile) nextTile;
            this.grid[tempY][tempX] = new PathTile(tempX, tempY, pt);
        }
    }

    /*
     * Fill the pathDirections with the directions for the path
     */
    private void createPathDirections(PathTile start) {
        PathTile next;
        this.pathDirections.add(Direction.RIGHT);
        while (start.getNext() != null) {
            next = start.getNext();
            if (start.getX() == next.getX() - 1) {
                addPathDirections(Direction.RIGHT);
            } else if (start.getX() == next.getX() + 1) {
                addPathDirections(Direction.LEFT);
            } else if (start.getY() == next.getY() - 1) {
                addPathDirections(Direction.DOWN);
            } else if (start.getY() == next.getY() + 1) {
                addPathDirections(Direction.UP);
            }
            start = next;
            next = next.getNext();
        }
        this.pathDirections.add(Direction.RIGHT);
    }

    /*
     * Add the direction to pathDirection
     */
    private void addPathDirections(Direction direction){
        this.pathDirections.add(direction);
    }

    //----------------------------Changes from Controller----------------------//

    /*
     * Create a new tower on the grid
     */
    public void createTower(int x, int y, TowerType type) throws Exception{
        if(((TowerTile)getTile(x, y)).placeable){
            ATower tower = null;
            switch (type){
                case knife: 
                    tower = new KnifeTower(x, y);
                    break;
                case mallet: 
                    tower = new MalletTower(x, y);
                    break;
                case blowtorch: 
                    tower = new BlowtorchTower(x, y);
                    break;
                case slicer: 
                    tower = new SlicerTower(x, y);
                    break;
                case freezer:
                    tower = new FreezerTower(x, y);
                    break;
                default:
                    System.out.println("Tower type given is not implemented");
                    break;
            }
            player.subtractMoney(tower.getCost());
            this.towers.add(tower);
            getTile(x, y).setPlaceable(false);
            ((TowerTile)getTile(x, y)).setTower(tower);
        }
    }

    public void upgradeTower(int x, int y, Upgrade upgrade) {
        if(!getTowerOnTile(x, y).getUpgrades().contains(upgrade)){
            getTowerOnTile(x, y).upgrade(upgrade);
        }
    }


    //----------------------------Public Getter and Setters----------------------//

    public ATile getTile(int x, int y){
        return grid[y][x];
    }

    public ATower getTowerOnTile(int x, int y){
        return ((TowerTile)getTile(x, y)).getTower();
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
    public void setPlayer(Player player){
        this.player = player;
    }
}