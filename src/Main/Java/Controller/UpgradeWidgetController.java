package Controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import Controller.Interfaces.IUpgradeTowerSubject;
import Model.MainModel;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;
import Model.Interfaces.ITowerUpgradeObserver;

public class UpgradeWidgetController extends AShopWidgetController implements IUpgradeTowerSubject {
    TowerType towerType;
    private ITowerUpgradeObserver observer;
    private int savedTowerPosX;
    private int savedTowerPosY;

    /**
     * 
     * @param observer  observer The DrawPanel that is notified when the player
     *                  wants to
     *                  create a tower
     * @param towerType The towerType that the player want to upgrade.
     * @param model     The mainModel with the data and logic of the game.
     * @param towerPosX TowerPosX represents the x-coordinate of the tower's
     *                  position.
     * @param towerPosY TowerPosY represents the x-coordinate of the tower's
     *                  position.
     */

    public UpgradeWidgetController(ITowerUpgradeObserver observer, TowerType towerType, MainModel model, int towerPosX,
            int towerPosY) {
        super(model);
        this.observer = observer;
        this.towerType = towerType;
        savedTowerPosX = towerPosX;
        savedTowerPosY = towerPosY;

        buttonPanel.setLayout(new GridLayout(0, 5, 5, 20));

        initHeader("Upgrade " + getTowerType().name());
        initButtons();
        initPlaybutton();

    }
    /*
     * * Initializes the buttons that the player click on when they want to upgrade
     * the
     * specific tower
     */

    private void initButtons() {
        switch (towerType) {
            case knife:
                buttons = List.of(
                        new UpgradeButton(2, this, Upgrade.Damage, towerType),
                        new UpgradeButton(3, this, Upgrade.Speed, towerType),
                        new UpgradeButton(4, this, Upgrade.Targets, towerType),
                        new UpgradeButton(2, this, Upgrade.Range, towerType));
                break;
            case mallet:
                buttons = List.of(
                        new UpgradeButton(1, this, Upgrade.Damage, towerType),
                        new UpgradeButton(3, this, Upgrade.Damage2, towerType),
                        new UpgradeButton(4, this, Upgrade.AoeRange, towerType),
                        new UpgradeButton(2, this, Upgrade.Range, towerType));
                break;
            case blowtorch:
                buttons = List.of(
                        new UpgradeButton(1, this, Upgrade.Damage, towerType),
                        new UpgradeButton(3, this, Upgrade.Range, towerType),
                        new UpgradeButton(4, this, Upgrade.AoeRange, towerType),
                        new UpgradeButton(2, this, Upgrade.SetOnFire, towerType));
                break;
            case slicer:
                buttons = List.of(
                        new UpgradeButton(1, this, Upgrade.Damage, towerType),
                        new UpgradeButton(3, this, Upgrade.Damage2, towerType),
                        new UpgradeButton(5, this, Upgrade.Damage3, towerType),
                        new UpgradeButton(4, this, Upgrade.AoeRange, towerType));
                break;
            case freezer:
                buttons = List.of(
                        new UpgradeButton(1, this, Upgrade.Frostbite, towerType),
                        new UpgradeButton(3, this, Upgrade.SuperChill, towerType),
                        new UpgradeButton(4, this, Upgrade.ConditionDuration, towerType),
                        new UpgradeButton(2, this, Upgrade.Range, towerType));
                break;
            default:
                break;
        }

        for (AWidgetButton button : buttons) {
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    public TowerType getTowerType() {
        return towerType;
    }

    @Override
    public void notifyObservers(Upgrade upgrade) {
        for (AWidgetButton button : buttons) {
            if (upgrade == ((UpgradeButton) button).upgrade) {
                ((UpgradeButton) button).setHasUpgrade(true);
                ((UpgradeButton) button).showHasUpgrade();
            }
        }
        observer.upgradeTower(getSavedTowerPosX(), getSavedTowerPosY(), upgrade);
    }

    /**
     * Called whenever the players bank changes
     * Calls setOpacity(), turns buttons grey if the player cant afford the upgrade
     */
    @Override
    public void updateMoney(int curMoney) {
        for (AWidgetButton button : buttons) {
            button.setOpacity(button.getCost() > curMoney);
        }
    }

    /**
     * Called whenever the players bank changes
     * Calls setOpacity(), turns buttons grey if the player cant afford the upgrade
     */
    public void updateAvailableUpgrades(List<Upgrade> currentUpgrades) {
        for (AWidgetButton button : buttons) {
            if (currentUpgrades.contains(((UpgradeButton) button).upgrade)) {
                ((UpgradeButton) button).setHasUpgrade(true);
                ((UpgradeButton) button).showHasUpgrade();
            }
        }
    }

    /**
     * Saves the position of the mouse as grid-indicies
     * 
     * @param x is the x-position of the mouse as grid-indicies
     * @param y is the y-position of the mouse as grid-indicies
     */
    public void setSavedTowerPos(int x, int y) {
        savedTowerPosX = x;
        savedTowerPosY = y;
    }

    public int getSavedTowerPosY() {
        return savedTowerPosY;
    }

    public int getSavedTowerPosX() {
        return savedTowerPosX;
    }
}