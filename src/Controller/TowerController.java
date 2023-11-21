package src.Controller;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;

import src.Model.ATower;

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

    public void handleButtonClick(String type) {
    }

}