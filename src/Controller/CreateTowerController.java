package src.Controller;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Model.AMap;

public class CreateTowerController extends TowerController {

    public CreateTowerController(AMap map, int x, int y, List<JButton> buttons, JLabel label, JPanel panel) {
        super(map, x, y, buttons, label, panel);
        // TODO Auto-generated constructor stub
    }

    public void createTower(int x, int y, String type) {
        map.createTower(x, y, type);
    }

}
