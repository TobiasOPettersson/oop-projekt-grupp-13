package src.Controller;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import src.Model.MainModel;
import src.View.GameView;
import src.View.GraphicsDependencies;

public class tempMainController {
    public static void main(String[] args) {
        // List<JButton> buttons = new ArrayList<>();
        // JButton button = new JButton();
        // JLabel label = new JLabel();
        // JPanel panel = new JPanel();
        // // Customize JPanel settings as needed...

        // TowerController controller = new CreateTowerController(0, 0, buttons, label,
        // panel);
        // TowerView towervies = new TowerView(controller);
        JFrame frame = new JFrame();
        WidgetButtonTower tower = new WidgetButtonTower(null, 0, "Archer", null);
        frame.setSize(400, 400);
        frame.add(tower);
        frame.setVisible(true);

    }
}
