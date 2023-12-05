package Model.Towers;

import Model.Enemies.AEnemy;

public class MalletTower extends AttackTower{

    /**
     * Constructor for knife tower that uses the default attack from AttackTower
     * @param x the x-position of the tower as a grid-index, i.e. not the x-position of the sprite in view
     * @param y the y-position of the tower as a grid-index, i.e. not the y-position of the sprite in view 
     */
    public MalletTower(int x, int y) {
        super(x, y, 3, 1, 1, 200, TowerType.mallet, 3);
        setTargetTypes(TargetType.first, TargetType.enemies);
    }
}
