package Controller;

import Model.Enums.TowerType;
import Model.Enums.Upgrade;

import java.awt.Color;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpgradeButton extends AWidgetButton{
    protected Upgrade upgrade;
    private boolean hasUpgrade = false;

    /**
     * Constructor
     * @param cost The cost of the upgrade
     * @param towerController The UpgradeTowerController the button will be added to
     * @param upgrade The upgrade the button has
     * @param type The type of tower the upgrade is for
     */
    public UpgradeButton(int cost, UpgradeWidgetController towerController, Upgrade upgrade, TowerType type) {
        super(cost, type, towerController);
        this.upgrade = upgrade;
        nameLabel.setText(upgrade.name());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                try {
                    towerController.notifyObservers(upgrade, cost);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    public boolean hasUpgrade(){
        return hasUpgrade;
    }

    public void setHasUpgrade(boolean bool){
        hasUpgrade = bool;
    }

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
            topPanel.setBackground(Color.orange);
            bottomPanel.setBackground(Color.pink);
        }
        if(hasUpgrade){
            showHasUpgrade();
        }
    }

    protected void showHasUpgrade(){
        Color color = Color.blue;
        setOpaque(false);
        setBackground(color);
        topPanel.setBackground(color);
        bottomPanel.setBackground(color);
    }
}
