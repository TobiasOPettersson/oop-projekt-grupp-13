package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import Controller.CreateTowerController;
import Controller.UpgradeTowerController;
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

    CreateTowerController createWidgit;
    List<UpgradeTowerController> upgradeWidgits;

    /**
     * TODO Javadoc comment
     * 
     * @param model
     */
    public GameView(MainModel model) {
        this.model = model;
        this.drawPanel = new DrawPanel(this, model);
        setLayout(null);
        drawPanel.setBounds(0, 0, 960, 480);
        add(drawPanel);
        initWidgits();
        initComponents();
    }

    /**
     * Initializes all wigits, one for buying new towers and one for upgrading for
     * each tower type
     */
    private void initWidgits() {
        upgradeWidgits = List.of(
                new UpgradeTowerController(model, TowerType.knife, model),
                new UpgradeTowerController(model, TowerType.mallet, model),
                new UpgradeTowerController(model, TowerType.blowtorch, model),
                new UpgradeTowerController(model, TowerType.slicer, model),
                new UpgradeTowerController(model, TowerType.freezer, model));

        for (UpgradeTowerController upgradeWidgit : upgradeWidgits) {
            upgradeWidgit.setBounds(0, 480, 960, 300);
            add(upgradeWidgit);
            upgradeWidgit.setVisible(false);
        }

        createWidgit = new CreateTowerController(drawPanel, model);
        createWidgit.setBounds(0, 480, 960, 300);
        add(createWidgit);
        createWidgit.setVisible(true);

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
        createWidgit.setVisible(false);
        for (UpgradeTowerController upgradeWidget : upgradeWidgits) {
            if (type.equals(upgradeWidget.getTowerType())) {
                upgradeWidget.setVisible(true);
                upgradeWidget.setSavedMousePos(x, y);
                upgradeWidget.updateAvailableUpgrades(currentUpgrades);
            }
        }
    }

    /**
     * Opens the create wigit, and closes all other wigits
     */
    public void openCreateWidgit() {
        createWidgit.setVisible(true);
        for (UpgradeTowerController upgradeWidget : upgradeWidgits) {
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
        if (upgradeWidgits != null) {
            for (UpgradeTowerController upWidget : upgradeWidgits) {
                observers.add(upWidget);
            }
        }
        observers.add(createWidgit);
        return observers;
    }

    // ----------------------------Getters and setters--------------------------//

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }
}