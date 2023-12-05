package Controller;

import java.util.Map;
import static java.util.Map.entry;
import java.awt.Dimension;
import javax.swing.JPanel;

import Controller.Interfaces.IMoneyObserver;
import Controller.Interfaces.ITowerObserver;
import Controller.Interfaces.ITowerSubject;
import Model.Enums.TowerType;
import View.GraphicsDependencies;

public abstract class TowerController extends JPanel implements ITowerSubject, IMoneyObserver{
    private int savedMousePosX;
    private int savedMousePosY;
    private ITowerObserver observer;
    private final int MAP_HEIGHT = 480;
    private final int FRAME_HEIGHT = GraphicsDependencies.getHeight();

    /**
     * Constructor of the abstract Tower Controller class
     * @param observer is the Map which is notified when the player wants to create a tower
     */
    public TowerController(ITowerObserver observer) {
        this.observer = observer;
        setSize(new Dimension(600, FRAME_HEIGHT - MAP_HEIGHT));
        setVisible(true);
    }



    /**
     * When a button is clicked this method calls notifyObservers
     * @throws Exception
     */
    protected void handleButtonClick(TowerType type) throws Exception {
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