package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import Controller.CreateTowerController;
import Controller.UpgradeTowerController;
import Controller.Interfaces.IMoneyObserver;
import Controller.Interfaces.ITowerObserver;
import Controller.Interfaces.ITowerSubject;
import Model.MainModel;
import Model.Enums.TowerType;
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

    public GameView(MainModel model) { // Moved initComponents down so setVisible is done last
        importImg();
        this.model = model;
        //this.drawPanel = new DrawPanel(this, model, image, imageKnife);
        this.drawPanel = new DrawPanel(this, model, image, towerImageMap);
        add(drawPanel, BorderLayout.CENTER);
        createWidget = new CreateTowerController(this.model);
        add(createWidget, BorderLayout.SOUTH);
        model.getPlayer().setMoneyObservers(getMoneyObservers());
        initComponents();
    }

    /*
     *  import sprite sheet
    */
    private void importImg() {
        InputStream is = this.getClass().getResourceAsStream("res/spriteatlas.png");
        /*InputStream is2 = this.getClass().getResourceAsStream("res/knifeTower.png");
        InputStream isMallet = this.getClass().getResourceAsStream("res/malletTower.png");
        InputStream isBlowtorch = this.getClass().getResourceAsStream("res/blowtorchTower.png");
        InputStream isSlicer = this.getClass().getResourceAsStream("res/slicerTower.png");
        InputStream isFridge = this.getClass().getResourceAsStream("res/slicerTower.png"); // change to fridge*/

        try {
            image = ImageIO.read(is);
            /*knifeTowerSprite = ImageIO.read(is2);
            malletTowerSprite = ImageIO.read(isMallet);
            blowtorchTowerSprite = ImageIO.read(isBlowtorch);
            slicerTowerSprite = ImageIO.read(isSlicer);
            fridgeTowerSprite = ImageIO.read(isFridge);*/
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

    public void update(){
        drawPanel.update();
    }

    public void openWidgit(int x, int y) {
        if (model.getMap().getTile(x, y).placeable) {
            createWidget.setVisible(true);
            createWidget.setSavedMousePos(x, y);
        } else {
            for (UpgradeTowerController upgradeWidget : upgradeWidgets) {
                TowerType type = ((TowerTile) model.getMap().getTile(x, y)).getTower().getTowerType(); // too much method chaining?
                if (type.equals(upgradeWidget.getTowerType())) {
                    upgradeWidget.setVisible(true);
                    upgradeWidget.setSavedMousePos(x, y);
                }
            }
        }
    }

    public DrawPanel getDrawPanel(){
        return drawPanel;
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
        List<IMoneyObserver> observers = new ArrayList<>();
        if(upgradeWidgets != null){
            for (UpgradeTowerController upWidget : upgradeWidgets) {
                observers.add(upWidget);
            }
        }
        observers.add(createWidget);
        return observers;
    }
}