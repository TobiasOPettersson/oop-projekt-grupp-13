package src.Controller;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayButtonController extends JPanel{
    //ImageIcon playImage;
    JButton button;

    public PlayButtonController(){
        this.setBackground(Color.red);
        this.setSize(150, 75);
        this.setBorder(BorderFactory.createEmptyBorder(200, 20, 20, 20));
        button = new JButton();
        button.setSize(125,50);
        button.setText("PLAY");
        this.add(button);
        
        //this.setBounds(frame.getWidth()+100, frame.getHeight()+25,150,75);
        //playImage = new ImageIcon();
        
        // button.addActionListener(e -> MainModel.play());
    }
}