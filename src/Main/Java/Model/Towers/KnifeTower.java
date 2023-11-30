package Model.Towers;

public class KnifeTower extends AttackTower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public KnifeTower(int x, int y) {
        super(x, y, 1, 1, 0, TowerType.knife, 1);
    }
}
