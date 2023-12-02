package Model.Towers;

public class BlowtorchTower extends AttackTower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public BlowtorchTower(int x, int y) {
        super(x, y, 4, 3, 0.5, 10, TowerType.blowtorch, 1);
        setTargetTypes(TargetType.first, TargetType.enemies);
    }
}