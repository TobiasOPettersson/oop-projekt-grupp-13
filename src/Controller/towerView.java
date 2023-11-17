package src.Controller;

import java.awt.BorderLayout;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TowerView extends JFrame {

    private TowerController controller;

    public TowerView(TowerController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("CREATE TOWERS");
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();
        // b1.setBounds(50, 100, 300, 300);
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);

        controller = new ConcreteTowerController(0, 0, buttons, label, panel);
        controller.initTowerController();

        add(panel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }
}