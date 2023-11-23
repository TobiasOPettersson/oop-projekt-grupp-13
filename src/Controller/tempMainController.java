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

        JLabel label = new JLabel("CREATE TOWERS");

        // Customize JPanel settings as needed...

        CreateTowerController controller = new CreateTowerController(0, 0, label);

        // TowerView towervies = new TowerView(controller);
        JFrame frame = new JFrame();

        frame.setSize(600, 600);
        frame.add(controller);
        frame.setVisible(true);

    }
}
