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
        List<JButton> buttons = new ArrayList<>();
        JButton button = new JButton();
        buttons.add(button);
        // Add JButton instances to the buttons list...

        JLabel label = new JLabel("Tower Label");

        JPanel panel = new JPanel();
        // Customize JPanel settings as needed...

        TowerController controller = new ConcreteTowerController(0, 0, buttons, label, panel);
        TowerView towervies = new TowerView(controller);
    }
}
