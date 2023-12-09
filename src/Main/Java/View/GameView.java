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
    private BufferedImage image;
    private BufferedImage imageKnife;
    private Map<TowerType, BufferedImage[]> towerImageMap = new HashMap<TowerType,BufferedImage[]>();
    CreateTowerController createWidget;
    List<UpgradeTowerController> upgradeWidgets;
    private BufferedImage knifeTowerSprite;
    private BufferedImage malletTowerSprite;
    private BufferedImage blowtorchTowerSprite;
    private BufferedImage slicerTowerSprite;
    private BufferedImage fridgeTowerSprite;
    private BufferedImage[] knifeTowerSpritesArr;
    private BufferedImage[] malletTowerSpritesArr;
    private BufferedImage[] blowtorchTowerSpritesArr;
    private BufferedImage[] slicerTowerSpritesArr;
    private BufferedImage[] fridgeTowerSpritesArr;
    

    /*
     * Constructor
     */ 
    CreateTowerController createWidgit;
    List<UpgradeTowerController> upgradeWidgits;

    /**
     * TODO Javadoc comment
     * @param model
     */
    public GameView(MainModel model) { // Moved initComponents down so setVisible is done last
        importImg();
        this.model = model;
        //this.drawPanel = new DrawPanel(this, model, image, imageKnife);
        this.drawPanel = new DrawPanel(this, model, image, towerImageMap);
        setLayout(null);
        drawPanel.setBounds(0, 0, 960, 480);
        add(drawPanel);
        initWidgits();
        initComponents();
    }S

    /**
     * Initializes all wigits, one for buying new towers and one for upgrading for each tower type
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
     * Imports sprite sheet
     */
    private void importImg() {
        InputStream is = this.getClass().getResourceAsStream("resView/spriteatlas.png");

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadSprites() {
        /*for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                // Get the subimage that is 32x32 and scale it before putting it in the array of
                // sprites
                sprites.add(SpriteHelper.scaleSprite(image.getSubimage(x * 32, y * 32, 32, 32), 1.5));
            }
        }*/
        for (int i = 0; i < 4; i++){
            knifeTowerSpritesArr[i] = (knifeTowerSprite.getSubimage(i * 48, 0, 48, 48));
        }
        for (int i = 0; i < 4; i++){
            malletTowerSpritesArr[i] = (malletTowerSprite.getSubimage(i * 48, 0, 48, 48));
        }
        for (int i = 0; i < 4; i++){
            blowtorchTowerSpritesArr[i] = (blowtorchTowerSprite.getSubimage(i * 48, 0, 48, 48));
        }
        for (int i = 0; i < 4; i++){
            slicerTowerSpritesArr[i] = (slicerTowerSprite.getSubimage(i * 48, 0, 48, 48));
        }
        for (int i = 0; i < 4; i++){
            fridgeTowerSpritesArr[i] = (fridgeTowerSprite.getSubimage(i * 48, 0, 48, 48));
        }
    }

    /**
     * // TODO Javadoc comment
     */
    public void update() {
        drawPanel.update();
    }


    //----------------------------Wigit methods--------------------------//

    /**
     * Opens the upgrade wigit of the clicked towers type, and closes all other wigits
     * @param x The grid x-position of the tile the player clicked on
     * @param y The grid y-position of the tile the player clicked on
     * @param type The the type of the tower that is on the tile the player clicked on
     * @param currentUpgrades The current upgrades of the tower
     */
    public void openUpgradeWidgit(int x, int y, TowerType type, List<Upgrade> currentUpgrades) {
        createWidgit.setVisible(true);
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

    public List<IMoneyObserver> getMoneyObservers(){
    /**
     * Converts all TowerControllers into IMoneyObservers
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

    //----------------------------Getters and setters--------------------------// 

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }
}