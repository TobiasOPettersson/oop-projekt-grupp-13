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

public abstract class AMap{
    private final int MAP_HEIGHT = 10;
    private final int MAP_WIDTH = 20;
    private List<ATower> towers = new ArrayList<ATower>();
    private ATile grid[][] = new ATile[MAP_HEIGHT][MAP_WIDTH];
    private int startPosition;
    private int endPosition;
    private int[][] pathGrid;
    private List<Direction> pathDirections = new ArrayList<Direction>();
    private Player player;

    /**
     * The constructor of the AMap class
     * @param pathGrid Size X = 20 and Y = 10. Matrix made in subclass. Path made by ints 1 = start and highest number is the end.
     * Start is always on the left and End is always at the right
     */
    public AMap(int[][] pathGrid) {
        this.pathGrid = pathGrid;
        createPathGrid();
        fillOccupiedTile();
        fillGridTowerTile();
        setStartPosition();
        setEndPosition();
    }


    //----------------------------Constructor methods----------------------//

    /**
     * Create the instances of every pathTile on the grid.
     * Then fill the pathDirections with the directions of the path
     */
    private void createPathGrid() {
        int tempX = MAP_WIDTH - 1;
        int tempY = 0;
        int tempValue = 0;
        for(int i = 0 ; i < MAP_HEIGHT ; i++){
            if (pathGrid[i][tempX] > tempValue){
                tempValue = pathGrid[i][tempX];
                tempY = i;
            }
        }
        
        grid[tempY][tempX] = new PathTile(tempX, tempY, null);

       
        while (tempValue > 1) {
            ATile nextTile = grid[tempY][tempX];
            if (tempX - 1 >= 0 && pathGrid[tempY][tempX - 1] == tempValue - 1) {
                tempX--;
            } else if (tempX + 1 < MAP_WIDTH - 1 && pathGrid[tempY][tempX + 1] == tempValue - 1) {
                tempX++;
            } else if (tempY - 1 >= 0 && pathGrid[tempY - 1][tempX] == tempValue - 1) {
                tempY--;
            } else if (tempY + 1 < MAP_HEIGHT - 1 && pathGrid[tempY + 1][tempX] == tempValue - 1) {
                tempY++;
            }
            tempValue--;

            // Setting the next tile in the grid
            nextTileInGrid(nextTile, tempX, tempY);
        }
        createPathDirections((PathTile)grid[tempY][tempX]);
    }
   
    /**
     * Fill the rest of the grid that isn't pathTile with towerTile
     */
    private void fillGridTowerTile(){
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                if (!(grid[y][x] instanceof PathTile || grid[y][x] instanceof OccupiedTile)) {
                    grid[y][x] = new TowerTile(x, y, true);
                }
            }
        }

    }

    /**
     * Find the starting position of the path
     */
    private void setStartPosition() {
        for (int i = 0; i < MAP_HEIGHT; i++) {
            if (pathGrid[i][0] == 1)
                startPosition = i;
        }
    }
    
    /**
     * Find the end position of the path
     */
    private void setEndPosition() {
        for (int i = 0; i < MAP_HEIGHT; i++) {
            if (pathGrid[i][MAP_WIDTH-1] != 0)
                endPosition = i;
        }
    }

    /**
     * Place the tiles where the player can't place towers and isn't a path
     */
    private void fillOccupiedTile(){
        //Tiles for player information
        grid[0][0] = new OccupiedTile(0, 0);
        grid[0][1] = new OccupiedTile(1, 0);
        grid[0][2] = new OccupiedTile(2, 0);
        grid[1][0] = new OccupiedTile(0, 1);
        grid[1][1] = new OccupiedTile(1, 1);
        grid[1][2] = new OccupiedTile(2, 1);
        grid[0][MAP_WIDTH-3] = new OccupiedTile(MAP_WIDTH-3, 0);
        grid[0][MAP_WIDTH-2] = new OccupiedTile(MAP_WIDTH-2, 0);
        grid[0][MAP_WIDTH-1] = new OccupiedTile(MAP_WIDTH-1, 0);
    }


    //----------------------------Constructor helper methods----------------------// 

    /**
     * Create the next pathTile and have it point at the previous pathTile
     * TODO comment parameters
     * @param previousTile needs to be a PathTile is the PathTile the new PathTile will point too
     * @param tempX is the same as x in previousTile or is a different by 1
     * @param tempY is the same as y in previousTile or is a different by 1
     */
    private void nextTileInGrid(ATile previousTile, int tempX, int tempY){
        if (previousTile instanceof PathTile) {
            PathTile pt = (PathTile) previousTile;
            grid[tempY][tempX] = new PathTile(tempX, tempY, pt);
        }
    }

    /**
     * Fill the pathDirections with the directions for the path
     * @param start The first PathTile where enemies will spawn
     */
    private void createPathDirections(PathTile start) {
        PathTile next;
        pathDirections.add(Direction.RIGHT);
        while (start.getNext() != null) {
            next = start.getNext();
            if (start.getX() == next.getX() - 1) {
                pathDirections.add(Direction.RIGHT);
            } else if (start.getX() == next.getX() + 1) {
                pathDirections.add(Direction.LEFT);
            } else if (start.getY() == next.getY() - 1) {
                pathDirections.add(Direction.DOWN);
            } else if (start.getY() == next.getY() + 1) {
                pathDirections.add(Direction.UP);
            }
            start = next;
            next = next.getNext();
        }
        pathDirections.add(Direction.RIGHT);
    }


    //----------------------------Tower methods----------------------//
    
    /**
     * Creates a new instance of a tower at position (x, y) and adds it to the tower list
     * @param x Grid x-index of where the tower will be created
     * @param y Grid y-index of where the tower will be created
     * @param type Type of the tower
     * @throws Exception if the player doesn't have enough money to buy the tower
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
            towers.add(tower);
            getTile(x, y).setPlaceable(false);
            ((TowerTile)getTile(x, y)).setTower(tower);
        }
    }

    /**
     * Upgrades the tower at tile (x, y)
     * @param x The towers x-index on the grid
     * @param y The towers y-index on the grid
     * @param upgrade The type of upgrade that will be added
     */
    public void upgradeTower(int x, int y, Upgrade upgrade) {
        if(!getTowerOnTile(x, y).getUpgrades().contains(upgrade)){
            getTowerOnTile(x, y).upgrade(upgrade);
        }
    }


    //----------------------------Getter and setters----------------------//

    public ATile getTile(int x, int y){
        return grid[y][x];
    }

    public ATower getTowerOnTile(int x, int y){
        return ((TowerTile)getTile(x, y)).getTower();
    }

    public ATile[][] getTileGrid() {
        return grid;
    }

    public List<Direction> getPathDirections() {
        return pathDirections;
    }

    /**
     * 
     * @return the position of the start Tile
     */
    public int getStartPosition() {
        return startPosition;
    }

    /**
     * @return the position of the end Tile
     */
    public int getEndPosition() {
        return endPosition;
    }

    public List<ATower> getTowers() {
        return towers;
    }

    public int getMapSizeX() {
        return MAP_WIDTH;
    }
    public int getMapSizeY() {
        return MAP_HEIGHT;
    }

    public int[][] getPathGrid() {
        return pathGrid;
    }
    public void setPlayer(Player player){
        this.player = player;
    }
}