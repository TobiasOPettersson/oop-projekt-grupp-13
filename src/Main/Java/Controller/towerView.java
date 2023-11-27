package Controller;

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

    // Add the compnetns to the CreateTowerController.. to pain it up.
    private void initComponents() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("CREATE TOWERS");
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();
        // SpriteButtonController spriteButton = new SpriteButtonController();

        buttons.add(b1);

        buttons.add(b2);
        buttons.add(b3);

        // controller = new ConcreteTowerController(0, 0, buttons, label, panel);
        // controller = new CreateTowerController(0, 0, new ArrayList<JButton>(buttons),
        // label, panel);
       // controller.initTowerController();

        add(panel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }
}