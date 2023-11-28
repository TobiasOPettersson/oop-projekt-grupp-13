package Controller;

import java.util.List;
import java.util.Map;
import static java.util.Map.entry;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import Model.TowerType;
import View.GraphicsDependencies;

public abstract class TowerController extends JPanel implements ITowerSubject{
    protected int savedMousePosX;
    protected int savedMousePosY;
    protected ITowerObserver observer;
    protected List<WidgetButtonTower> buttons = new ArrayList<>();
    private final int MAP_HEIGHT = 480;
    private final int FRAME_HEIGHT = GraphicsDependencies.getHeight();
    protected Map<TowerType, String> buttonImgPaths = Map.ofEntries(
        entry(TowerType.knife, "res/knife-sprite.png"),
        entry(TowerType.mallet, "res/mallet-sprite.png"),
        entry(TowerType.blowtorch, "res/blowtorch-sprite.png"),
        entry(TowerType.slicer, "res/slicer-sprite.png")
);

    public TowerController(ITowerObserver observer) {
        this.observer = observer;
        setSize(new Dimension(600, FRAME_HEIGHT - MAP_HEIGHT));
        setVisible(true);
    }

    public void handleButtonClick(TowerType type) {
    }

    public void setSavedMousePos(int x, int y){
        savedMousePosX = x;
        savedMousePosY = y;
    }
}