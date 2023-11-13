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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class TowerController {

    private int x;
    private int y;
    private List<JButton> buttons = new ArrayList<>();
    private JLabel label;
    private JPanel panel;

    public TowerController(int x, int y, List<JButton> buttons, JLabel label, JPanel panel) {
        this.x = x;
        this.y = y;
        this.buttons = new ArrayList<>(buttons);
        this.label = label;
        this.panel = panel;
    }

}