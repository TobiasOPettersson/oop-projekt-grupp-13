package Controller;

import java.util.Map;
import static java.util.Map.entry;
import java.awt.Dimension;
import javax.swing.JPanel;

import Controller.Interfaces.ITowerObserver;
import Controller.Interfaces.ITowerSubject;
import Model.Towers.TowerType;
import View.GraphicsDependencies;

public abstract class TowerController extends JPanel implements ITowerSubject{
    private int savedMousePosX;
    private int savedMousePosY;
    private ITowerObserver observer;
    private final int MAP_HEIGHT = 480;
    private final int FRAME_HEIGHT = GraphicsDependencies.getHeight();
    private Map<TowerType, String> buttonImgPaths;

    /**
     * Constructor of the abstract Tower Controller class
     * @param observer is the Map which is notified when the player wants to create a tower
     */
    public TowerController(ITowerObserver observer) {
        this.observer = observer;
        initButtonImagePaths();
        setSize(new Dimension(600, FRAME_HEIGHT - MAP_HEIGHT));
        setVisible(true);
    }

    /**
     * Initializes the map containing the pats of button images
     */
    private void initButtonImagePaths(){
        buttonImgPaths = Map.ofEntries(
            entry(TowerType.knife, "res/knife-sprite.png"),
            entry(TowerType.mallet, "res/mallet-sprite.png"),
            entry(TowerType.blowtorch, "res/blowtorch-sprite.png"),
            entry(TowerType.slicer, "res/slicer-sprite.png")
            );
    }

    /**
     * When a button is clicked this method calls notifyObservers
     */
    protected void handleButtonClick(TowerType type) {
        notifyObservers(type);
    }

    /**
     * Saves the position of the mouse as grid-indicies
     * @param x is the x-position of the mouse as grid-indicies
     * @param y is the y-position of the mouse as grid-indicies
     */
    public void setSavedMousePos(int x, int y){
        savedMousePosX = x;
        savedMousePosY = y;
    }

    protected int getSavedMousePosY() {
        return savedMousePosY;
    }

    protected int getSavedMousePosX() {
        return savedMousePosX;
    }

    protected ITowerObserver getObserver() {
        return observer;
    }
}