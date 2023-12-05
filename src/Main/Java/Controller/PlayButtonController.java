package Controller;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.MainModel;

// todo: implement and add an image, maybe an arrow representing "play"

public class PlayButtonController extends JPanel{
    String playImagePath;
    MainModel model;

    /**
     * The button that starts a new wave, i.e. starts the game
     * @param model is the main model where the method play() is called on
     */
    public PlayButtonController(MainModel model){
        this.model = model;   
        setBackground(Color.red);
        setSize(150, 75);
        setBorder(BorderFactory.createEmptyBorder(200, 20, 20, 20));
        initButton();
        //initPlayImage();
    }

    /**
     * Initializes the button
     */
    private void initButton(){
        JButton button = new JButton();
        button.setSize(125,50);
        button.setText("PLAY");
        button.addActionListener(e -> model.play());
        add(button);
    }

    /**
     * Initializes the image of the play button
     */
    private void initPlayImage(){
        JLabel playImage = new JLabel(new ImageIcon(playImagePath));
        add(playImage);
    }
}