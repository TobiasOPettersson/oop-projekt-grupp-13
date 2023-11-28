package Controller;

import Model.TowerType;

public interface ITowerObserver {
    void createTower(int x, int y, TowerType type);
    void upgradeTower(int x, int y, int upgradeLvl);
}
