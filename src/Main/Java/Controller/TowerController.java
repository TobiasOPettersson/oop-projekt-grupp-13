package Controller;

import java.util.Map;
import static java.util.Map.entry;
import java.awt.Dimension;
import javax.swing.JPanel;

import Controller.Interfaces.IMoneyObserver;
import Controller.Interfaces.ITowerUpgradeObserver;
import Controller.Interfaces.IUpgradeTowerSubject;
import Model.Enums.TowerType;
import View.GraphicsDependencies;

public abstract class TowerController extends JPanel implements IMoneyObserver{
    private final int MAP_HEIGHT = 480;
    private final int FRAME_HEIGHT = GraphicsDependencies.getHeight();

    /**
     * Constructor of the abstract Tower Controller class
     * @param observer is the Map which is notified when the player wants to create a tower
     */
    public TowerController() {
        setSize(new Dimension(600, FRAME_HEIGHT - MAP_HEIGHT));
        setVisible(true);
    }
}