package Controller;

import Model.Enums.TowerType;
import Model.Enums.Upgrade;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpgradeButton extends WidgetButton{
    protected Upgrade upgrade;

    /**
     * Constructor
     * @param cost The cost of the upgrade
     * @param towerController The UpgradeTowerController the button will be added to
     * @param upgrade The upgrade the button has
     * @param type The type of tower the upgrade is for
     */
    public UpgradeButton(int cost, UpgradeTowerController towerController, Upgrade upgrade, TowerType type) {
        super(cost, type, towerController);
        this.upgrade = upgrade;
        nameLabel.setText(upgrade.name());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                towerController.notifyObservers(upgrade);
            }
        });
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
}
