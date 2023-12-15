package View;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Controller.CreateWidgetController;
import Controller.ShowTutorialDialog;
import Controller.UpgradeWidgetController;
import Controller.Interfaces.IMoneyObserver;
import Model.MainModel;
import Model.Enums.TowerType;
import Model.Enums.Upgrade;

import Model.Interfaces.IObservable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The main frame of the game
 */
public class GameView extends JFrame implements IObservable {
    MainModel model;
    DrawPanel drawPanel;
    CreateWidgetController createWidget;
    List<UpgradeWidgetController> upgradeWidgets = new ArrayList<>();

    /**
     * Constructor
     * @param model The main model
     */
    public GameView(MainModel model) {
        this.model = model;
        this.drawPanel = new DrawPanel(model);
        addMouseListenersToDrawPanel();
        setLayout(null);
        add(drawPanel);
        initWidgits();
        initComponents();
        setTitle("Kitchen Defence");
        ImageIcon img = new ImageIcon("src/Main/Java/View/resView/towers/fridgeTower.png");
        setIconImage(img.getImage());

        showTutorial();
    }

    /**
     * Initializes the JFrame
     */
    private void initComponents() {
        setSize(GraphicsDependencies.getWidth(), GraphicsDependencies.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    /**
     * Initializes all wigits, one for buying new towers and one for upgrading for each tower type
     */
    private void initWidgits() {
        createWidget = new CreateWidgetController(drawPanel, model);
        createWidget.setBounds(0, 480, 960, 300);
        add(createWidget);
        createWidget.setVisible(true);

        model.getPlayer().setMoneyObservers(getMoneyObservers());

    }

    /**
     * Opens the tutorial window, called at start
     */
    private void showTutorial() {
        ShowTutorialDialog tutorial = new ShowTutorialDialog(this);
        tutorial.setVisible(true);
    }
    
    /**
     * Calls DrawPanel to repaint all components (tiles, towers, enemies etc.)
     */
    @Override
    public void update() {
        drawPanel.update();
    }

    // ----------------------------Wigit methods--------------------------//

    /**
     * Adds mouselisteners to DrawPanel
     * mouseClicked left:   Exits placing towers
     * mouseClicked right:  Creates a tower at the mouse position
     * mouseMoved:          Saves which tile the player is hovering over in the hoveredTile variable
     */
    private void addMouseListenersToDrawPanel() {
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                if (mEvent.getButton() == MouseEvent.BUTTON3) {
                    drawPanel.setPlacingTower(false);
                    openCreateWidgit();
                } else {
                    try {
                        handleDrawPanelTileClick();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        drawPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent mEvent) {
                // if(!model.activeWave()){
                drawPanel.hoverOverTile(mEvent.getX(), mEvent.getY());
                // }
            }
        });
    }

    /**
     * Creates a tower at the mouse position if the player is placing towers
     * OR
     * Opens the upgrade widget for the tower at the mouse position
     */
    private void handleDrawPanelTileClick() throws Exception {
        if (drawPanel.isHoveredTileTowerTile()) {
            int[] hoveredTile = drawPanel.getHoveredTile();
            if (drawPanel.isPlacingTower()) {
                model.createTower(hoveredTile[0], hoveredTile[1], drawPanel.getTowerTypeToPlace());
                addNewUpgradeWidget(drawPanel.getTowerTypeToPlace(), hoveredTile[0], hoveredTile[1]);
            } else if (drawPanel.getTowerAtMousePos() != null) {
                drawPanel.setSelectedTile();
                openUpgradeWidgit(hoveredTile[0], hoveredTile[1], drawPanel.getTowerAtMousePos().getTowerType(), drawPanel.getTowerAtMousePos().getUpgrades());
            }
        }
    }


    /**
     * Opens the upgrade wigit of the clicked towers type, and closes all other wigits
     * @param x               The grid x-position of the tile the player clicked on
     * @param y               The grid y-position of the tile the player clicked on
     * @param type            The the type of the tower that is on the tile the player clicked on
     * @param currentUpgrades The current upgrades of the tower
     */
    public void openUpgradeWidgit(int x, int y, TowerType type, List<Upgrade> currentUpgrades) {
        createWidget.setVisible(false);
        for (UpgradeWidgetController upgradeWidget : upgradeWidgets) {
            if (x == upgradeWidget.getSavedTowerPosX() && y == upgradeWidget.getSavedTowerPosY()) {
                upgradeWidget.setVisible(true);
                upgradeWidget.updateAvailableUpgrades(currentUpgrades);
            } else {
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

    /**
     * Adds a new upgrade widget to the list of upgrade widgets when a new tower has been created
     * @param type      The type of the created tower
     * @param towerPosX The towers x-position on the grid
     * @param towerPosY The towers y-position on the grid
     */
    protected void addNewUpgradeWidget(TowerType type, int towerPosX, int towerPosY) {
        for (UpgradeWidgetController upgradeWidget : upgradeWidgets) {
            if (upgradeWidget.getSavedTowerPosX() == towerPosX && upgradeWidget.getSavedTowerPosY() == towerPosY) {
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

    /**
     * Converts all AShopWidgetControllers into IMoneyObservers to be used by the Player class
     * @return AShopWidgetControllers as IMoneyObservers
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
}