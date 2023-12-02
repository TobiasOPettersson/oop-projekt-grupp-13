package Model.Towers;

public class SlicerTower extends AttackTower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public SlicerTower(int x, int y) {
        super(x, y, 2, 1, 0, 30, TowerType.slicer, 2);
        setTargetTypes(TargetType.first, TargetType.enemies);
    }
}