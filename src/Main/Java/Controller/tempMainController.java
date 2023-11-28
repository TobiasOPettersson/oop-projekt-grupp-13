package Controller;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Model.MainModel;
import Model.TowerType;
import View.GameView;
import View.GraphicsDependencies;

public class tempMainController {
    public static void main(String[] args){
        // Customize JPanel settings as needed...
        WidgetButtonTower wb = new WidgetButtonTower(null, 0, "Knife", TowerType.knife, null);
        CreateTowerController controller = new CreateTowerController(null);

        // TowerView towervies = new TowerView(controller);
        JFrame frame = new JFrame();

        frame.setSize(600, 600);
        frame.add(controller);
        frame.setVisible(true);
    }
}