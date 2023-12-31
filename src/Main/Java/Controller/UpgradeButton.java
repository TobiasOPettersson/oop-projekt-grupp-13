package Controller;

import Model.Enums.TowerType;
import Model.Enums.Upgrade;

import java.awt.Color;

/**
 * Represents a button for upgrading a specific tower in the game. 
 * It extends AWidgetButton and handles the display and functionality for an upgrade associated with a TowerType.
 * The class initializes button images for different TowerTypes and manages the upgrade process.
 * It allows for setting the opacity based on upgrade availability and showing the upgraded state with a distinct color scheme. 
 * The button triggers the 'notifyObservers(upgrade)' method in the UpgradeTowerController upon being clicked.
 */
public class UpgradeButton extends AWidgetButton {
    protected Upgrade upgrade;
    private boolean hasUpgrade = false;

    /**
     * Constructor
     * @param cost            The cost of the upgrade
     * @param towerController The UpgradeTowerController the button will be added to
     * @param upgrade         The upgrade the button has
     * @param type            The type of tower the upgrade is for
     */
    public UpgradeButton(int cost, Upgrade upgrade, TowerType type) {
        super(cost, type);
        this.upgrade = upgrade;
        nameLabel.setText(upgrade.name());
    }

    /**
     * Initializes the map containing each upgrades image
     */
    @Override
    protected void initButtonImagePaths() {
        String resPath = "src/Main/Java/Controller/resController/";
        buttonImgPaths.put(TowerType.knife, resPath + "knife.png");
        buttonImgPaths.put(TowerType.mallet, resPath + "mallet.png");
        buttonImgPaths.put(TowerType.blowtorch, resPath + "blowtorch.png");
        buttonImgPaths.put(TowerType.slicer, resPath + "slicer.png");
        buttonImgPaths.put(TowerType.freezer, resPath + "fridge.png");
    }

    public boolean hasUpgrade() {
        return hasUpgrade;
    }

    public void setHasUpgrade(boolean bool) {
        hasUpgrade = bool;
    }

    /**
     * Sets the opacity of the entity and adjusts background colors based on the boolean value.
     * If hasUpgrade is true, a specific display for having an upgrade is shown.
     * @param bool the boolean value determining the opacity
     */

    @Override
    public void setOpacity(boolean bool) {
        Color color = new Color(0, 0, 0, 150);

        if (bool) {
            setOpaque(false);
            setBackground(color);
            topPanel.setBackground(color);
            bottomPanel.setBackground(color);
        } else {
            setOpaque(true);
            setBackground(Color.gray);
            topPanel.setBackground(new Color(200, 0, 0, 80));
            bottomPanel.setBackground(new Color(35, 0, 200, 80));
        }
        if (hasUpgrade) {
            showHasUpgrade();
        }
    }

    /**
     * Displays the upgraded state with a specific color scheme.
     */
    protected void showHasUpgrade() {
        Color color = Color.blue;
        setOpaque(false);
        setBackground(color);
        topPanel.setBackground(color);
        bottomPanel.setBackground(color);
    }
}
