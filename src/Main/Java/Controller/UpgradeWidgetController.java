package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Controller.Interfaces.IUpgradeTowerSubject;
import Model.MainModel;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;
import Model.Interfaces.ITowerUpgradeObserver;

/**
 * Represents a controller managing the upgrade widget for a specific tower type in the game.
 * This controller is responsible for initializing upgrade buttons, handling player interactions,
 * with these buttons, notifying observers about upgrades, and managing upgrade availability based on the player's bank.
 * It extends AShopWidgetController and implements the IUpgradeTowerSubject interface to observe and notify upgrades.
 * The class maintains information about the tower type, its position, and updates available upgrades.
 */

public class UpgradeWidgetController extends AShopWidgetController implements IUpgradeTowerSubject {
    TowerType towerType;
    private ITowerUpgradeObserver observer;
    private int savedTowerPosX;
    private int savedTowerPosY;

    /**
     * Constructor
     * @param observer  observer The DrawPanel that is notified when the player wants to create a tower
     * @param towerType The towerType that the player want to upgrade.
     * @param model     The mainModel with the data and logic of the game.
     * @param towerPosX TowerPosX represents the x-coordinate of the tower's position.
     * @param towerPosY TowerPosY represents the x-coordinate of the tower's position.
     */
    public UpgradeWidgetController(ITowerUpgradeObserver observer, TowerType towerType, MainModel model, int towerPosX,
            int towerPosY) {
        super(model);
        this.observer = observer;
        this.towerType = towerType;
        savedTowerPosX = towerPosX;
        savedTowerPosY = towerPosY;

        buttonPanel.setLayout(new GridLayout(0, 5, 5, 20));

        initHeader("UPGRADE " + getTowerType().name());
        initButtons();
        initPlaybutton(model);

    }

    /*
     * Initializes the buttons that the player click on when they want to upgrade the specific tower
     */
    private void initButtons() {
        switch (towerType) {
            case knife:
                buttons = List.of(
                        new UpgradeButton(2, Upgrade.Damage, towerType),
                        new UpgradeButton(3, Upgrade.Speed, towerType),
                        new UpgradeButton(4, Upgrade.Targets, towerType),
                        new UpgradeButton(2, Upgrade.Range, towerType));
                break;
            case mallet:
                buttons = List.of(
                        new UpgradeButton(1, Upgrade.Damage, towerType),
                        new UpgradeButton(3, Upgrade.Damage2, towerType),
                        new UpgradeButton(4, Upgrade.AoeRange, towerType),
                        new UpgradeButton(2, Upgrade.Range, towerType));
                break;
            case blowtorch:
                buttons = List.of(
                        new UpgradeButton(1, Upgrade.Damage, towerType),
                        new UpgradeButton(3, Upgrade.Range, towerType),
                        new UpgradeButton(4, Upgrade.AoeRange, towerType),
                        new UpgradeButton(2, Upgrade.SetOnFire, towerType));
                break;
            case slicer:
                buttons = List.of(
                        new UpgradeButton(1, Upgrade.Damage, towerType),
                        new UpgradeButton(3, Upgrade.Damage2, towerType),
                        new UpgradeButton(5, Upgrade.Damage3, towerType),
                        new UpgradeButton(4, Upgrade.AoeRange, towerType));
                break;
            case freezer:
                buttons = List.of(
                        new UpgradeButton(1, Upgrade.Frostbite, towerType),
                        new UpgradeButton(3, Upgrade.SuperChill, towerType),
                        new UpgradeButton(4, Upgrade.ConditionDuration, towerType),
                        new UpgradeButton(2, Upgrade.Range, towerType));
                break;
            default:
                break;
        }

        for (AWidgetButton button : buttons) {
            addMouseListenersToButton(button);
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Adds mouse listeners to a button that notifies observers when the player wants to upgrade a tower
     * @param button The button to add the mouse listener to
     */
    private void addMouseListenersToButton(AWidgetButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                try {
                    notifyObservers(((UpgradeButton)button).upgrade, button.getCost());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TowerType getTowerType() {
        return towerType;
    }

    /**
     * Notifies the observer (MainModel) about the player wanting to upgrade a tower
     * @param upgrade The upgrade to be notified to observers
     */
    @Override
    public void notifyObservers(Upgrade upgrade, int cost) throws Exception {
        for (AWidgetButton button : buttons) {
            if (upgrade == ((UpgradeButton) button).upgrade
                    && !button.getBackground().equals(new Color(0, 0, 0, 150))) {
                ((UpgradeButton) button).setHasUpgrade(true);
                ((UpgradeButton) button).showHasUpgrade();
            }
        }
        observer.upgradeTower(getSavedTowerPosX(), getSavedTowerPosY(), upgrade, cost);
    }

    /**
     * Called whenever the players bank changes
     */
    @Override
    public void updateMoney(int curMoney) {
        for (AWidgetButton button : buttons) {
            button.setOpacity(button.getCost() > curMoney);
        }
    }

    /**
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
     * Saves the position of the mouse as grid-indicies, i.e. the position of the tower the player clicked on
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