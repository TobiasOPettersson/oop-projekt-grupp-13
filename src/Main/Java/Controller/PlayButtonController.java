package Controller;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import Model.MainModel;

public class PlayButtonController extends JPanel {
    String playImagePath;
    MainModel model;

    /**
     * The button that starts a new wave, i.e. starts the game
     * 
     * @param model is the main model where the method play() is called on
     */
    public PlayButtonController(MainModel model) {
        this.model = model;
        setBackground(Color.pink);
        setLayout(new GridLayout(0, 1, 0, 0));
        initButton();
    }

    /**
     * Initializes the button
     */
    private void initButton() {
        JButton button = new JButton();
        button.setSize(50, 50);
        button.setText("START WAVE");
        button.addActionListener(e -> model.play());
        add(button);
    }
}
