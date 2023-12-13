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

    /**
     * TODO Javadoc comment
     * @param pathGrid
     */
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
   
    /*
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

    /*
     * Find the starting position of the path
     */
    private void setStartPosition() {
        for (int i = 0; i < this.MAP_HEIGHT; i++) {
            if (pathGrid[i][0] == 1)
                startPosition = i;
        }
    }

    /*
     * Place the tiles where the player can't place towers and isn't a path
     */
    private void fillOccupiedTile(){
        //Tiles for player information
        this.grid[0][0] = new OccupiedTile(0, 0);
        this.grid[0][1] = new OccupiedTile(1, 0);
        this.grid[0][2] = new OccupiedTile(2, 0);
        this.grid[1][0] = new OccupiedTile(0, 1);
        this.grid[1][1] = new OccupiedTile(1, 1);
        this.grid[1][2] = new OccupiedTile(2, 1);
        this.grid[0][MAP_WIDTH-3] = new OccupiedTile(MAP_WIDTH-3, 0);
        this.grid[0][MAP_WIDTH-2] = new OccupiedTile(MAP_WIDTH-2, 0);
        this.grid[0][MAP_WIDTH-1] = new OccupiedTile(MAP_WIDTH-1, 0);
    }


    //----------------------------Constructor helper methods----------------------// 

    /**
     * Create the next pathTile and have it point at the previous pathTile
     * TODO comment parameters
     * @param nextTile
     * @param tempX
     * @param tempY
     */
    private void nextTileInGrid(ATile nextTile, int tempX, int tempY){
        if (nextTile instanceof PathTile) {
            PathTile pt = (PathTile) nextTile;
            grid[tempY][tempX] = new PathTile(tempX, tempY, pt);
        }
    }

    /**
     * Fill the pathDirections with the directions for the path
     * @param start The tile where the enemies start from
     */
    private void createPathDirections(PathTile start) {
        PathTile next;
        pathDirections.add(Direction.RIGHT);
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

    /**
     * Add the direction to pathDirection
     * @param direction The direction to add
     */
    private void addPathDirections(Direction direction){
        this.pathDirections.add(direction);
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
            if(!player.canAfford(tower.getCost())){
                return;
            }
            player.subtractMoney(tower.getCost());
            this.towers.add(tower);
            getTile(x, y).setPlaceable(false);
            ((TowerTile)getTile(x, y)).setTower(tower);
        }
    }

    /**
     * Upgrades the tower at tile (x, y)
     * @param x The towers x-index on the grid
     * @param y The towers y-index on the grid
     * @param upgrade The type of upgrade that will be added
     * @throws Exception If the player doesn't have enough money for the upgrade 
     */
    public void upgradeTower(int x, int y, Upgrade upgrade, int cost) throws Exception {
        if(!player.canAfford(cost)){
            return;
        }
        player.subtractMoney(cost);
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
        return this.pathDirections;
    }

    public int getStartPosition() {
        return startPosition;
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

    public boolean tileIsTowerTile(int x, int y){
        return getTile(x, y) instanceof TowerTile;
    }
}