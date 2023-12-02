package Controller.Interfaces;

import Model.Towers.TowerType;

public interface ITowerObserver {
    void createTower(int x, int y, TowerType type);
    void upgradeTower(int x, int y, int upgradeLvl);
}
