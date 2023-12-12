package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import Controller.CreateWidgetController;
import Controller.UpgradeWidgetController;
import Controller.Interfaces.IMoneyObserver;
import Controller.Interfaces.ITowerUpgradeObserver;
import Controller.Interfaces.IUpgradeTowerSubject;
import Model.MainModel;
import Model.Enums.EnemyType;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;
import Model.Map.AMap;
import Model.Map.TowerTile;
import Model.Towers.ATower;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class GameView extends JFrame {
    MainModel model;
    DrawPanel drawPanel;
    CreateWidgetController createWidget;
    List<UpgradeWidgetController> upgradeWidgets = new ArrayList<>();

    /**
     * TODO Javadoc comment
     * 
     * @param model
     */
    public GameView(MainModel model) {
        this.model = model;
        this.drawPanel = new DrawPanel(this, model);
        setLayout(null);
        this.drawPanel.setBounds(0, 0, 960, 480);
        add(drawPanel);
        initWidgits();
        initComponents();
    }

    /**
     * Initializes all wigits, one for buying new towers and one for upgrading for
     * each tower type
     */
    private void initWidgits() {
        createWidget = new CreateWidgetController(drawPanel, model);
        createWidget.setBounds(0, 480, 960, 300);
        add(createWidget);
        createWidget.setVisible(true);

        model.getPlayer().setMoneyObservers(getMoneyObservers());
    }

    /**
     * // TODO Javadoc comment
     */
    public void update() {
        drawPanel.update();
    }

    // ----------------------------Wigit methods--------------------------//

    /**
     * Opens the upgrade wigit of the clicked towers type, and closes all other
     * wigits
     * 
     * @param x               The grid x-position of the tile the player clicked on
     * @param y               The grid y-position of the tile the player clicked on
     * @param type            The the type of the tower that is on the tile the
     *                        player clicked on
     * @param currentUpgrades The current upgrades of the tower
     */
    public void openUpgradeWidgit(int x, int y, TowerType type, List<Upgrade> currentUpgrades) {
        createWidget.setVisible(false);
        for (UpgradeWidgetController upgradeWidget : upgradeWidgets) {
            if (x == upgradeWidget.getSavedTowerPosX() && y == upgradeWidget.getSavedTowerPosY()) {
            upgradeWidget.setVisible(true);
            upgradeWidget.updateAvailableUpgrades(currentUpgrades);
            } else{
                upgradeWidget.setVisible(false);
            }
        }
    }

    /**
     * Opens the create wigit, and closes all other wigits
     */
    public void openCreateWidgit() {
        createWidget.setVisible(true);
        for (UpgradeWidgetController upgradeWidget : upgradeWidgets) {
            upgradeWidget.setVisible(false);
        }
    }

    // initialize swing window
    private void initComponents() {
        setSize(GraphicsDependencies.getWidth(), GraphicsDependencies.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /**
     * Converts all TowerControllers into IMoneyObservers
     * 
     * @return TowerControllers as IMoneyObservers
     */
    public List<IMoneyObserver> getMoneyObservers() {
        List<IMoneyObserver> observers = new ArrayList<>();
        for (UpgradeWidgetController upWidget : upgradeWidgets) {
            observers.add(upWidget);
        }
        observers.add(createWidget);
        return observers;
    }

    // ----------------------------Getters and setters--------------------------//

    public DrawPanel getDrawPanel() {
        return drawPanel;
    } 

    protected void addNewUpgradeWidget(TowerType type, int towerPosX, int towerPosY){
        for(UpgradeWidgetController upgradeWidget : upgradeWidgets){
            if(upgradeWidget.getSavedTowerPosX() == towerPosX && upgradeWidget.getSavedTowerPosY() == towerPosY){
                return;
            }
        }
        UpgradeWidgetController newUpgradeWiget = new UpgradeWidgetController(model, type, model, towerPosX, towerPosY);
        upgradeWidgets.add(newUpgradeWiget);
        newUpgradeWiget.setBounds(0, 480, 960, 300);
        add(newUpgradeWiget);
        model.getPlayer().setMoneyObservers(getMoneyObservers());
        System.out.println(upgradeWidgets.size());
    }
}