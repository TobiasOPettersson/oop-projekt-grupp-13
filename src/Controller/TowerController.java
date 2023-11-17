package src.Controller;

/**
 * This is an abstract class representing a Tower Controller in a game.
 * It defines the common properties and behaviors for tower controllers.
 * Subclasses are expected to extend this class and provide specific implementations.
 * 
 * Properties:
 * - x: X-coordinate of the tower's position.
 * - y: Y-coordinate of the tower's position.
 * - buttons: A list of JButton objects associated with the tower.
 * - label: A JLabel object associated with the tower.
 * - panel: A JPanel object associated with the tower.
 
 */

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;

public abstract class TowerController {

    private int x;
    private int y;
    protected List<JButton> buttons = new ArrayList<>();
    private JLabel label;
    private JPanel panel;

    // x an y för model förstå var man ska skapa tornet
    // i addtower

    public TowerController(int x, int y, List<JButton> buttons, JLabel label, JPanel panel) {
        this.x = x;
        this.y = y;
        this.buttons = new ArrayList<>(buttons);
        this.label = label;
        this.panel = panel;

        initTowerController();
    }

    public void initTowerController() {

    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public List<JButton> getButtons() {
        return buttons;
    }

}