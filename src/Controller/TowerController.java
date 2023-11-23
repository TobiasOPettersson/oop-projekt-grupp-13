package src.Controller;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;

import src.Model.ATower;

public abstract class TowerController extends JPanel {

    private int x;
    private int y;
    protected List<WidgetButtonTower> buttons = new ArrayList<>();
    private JLabel label;

    // x an y för model förstå var man ska skapa tornet
    // i addtower
    // random kommentar

    public TowerController(int x, int y, JLabel label) {
        this.x = x;
        this.y = y;
        this.label = label;

        setSize(new Dimension(600, 600));
        setVisible(true);

        System.out.println("iniside towercontroler consrtuctor");

        // setBackground(Color.ORANGE);

    }

    // public JPanel getPanel() {
    // return panel;
    // }

    public JLabel getLabel() {
        return label;
    }

    // public List<JButton> getButtons() {
    // return buttons;
    // }

    public void handleButtonClick(String type) {
    }

}