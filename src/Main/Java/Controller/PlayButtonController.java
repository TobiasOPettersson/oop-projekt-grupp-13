package Controller;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.MainModel;

public class PlayButtonController extends JPanel {
    // ImageIcon playImage;
    JButton button;
    MainModel model;

    public PlayButtonController(MainModel model) {
        this.setBackground(Color.red);
        this.setSize(150, 75);
        this.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        button = new JButton();
        button.setSize(125, 50);
        button.setText("PLAY");
        this.add(button);
        button.addActionListener(e -> model.play());
    }
}