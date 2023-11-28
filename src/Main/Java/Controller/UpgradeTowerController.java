package Controller;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.TowerType;

public class UpgradeTowerController extends TowerController{
    TowerType towerType;

    public UpgradeTowerController(ITowerObserver observer, TowerType towerType) {
        super(observer);
        this.towerType = towerType;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    @Override
    public void handleButtonClick(TowerType type) {
        notifyObservers(type);
    }

    @Override
    public void notifyObservers(TowerType towerType) {
        observer.upgradeTower(savedMousePosX, savedMousePosY, 0);
    }

    /// Methods///

    // TODO METHOD upgradeTower();

    // public void upgradeTower(int x, int y, Atower tower){
    // Example: upgrading a tower object and adding it to the game world.
    // ATower newTower = new ATower(x, y, tower);
    // Tell mainModel to upgrade the tower..
    // MainModel.upgrade(newTower);

}