package Controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowTutorialDialog extends JDialog {

    public ShowTutorialDialog(JFrame GameView) {
        super(GameView, "", true);
        setSize(500, 350);
        setLocationRelativeTo(GameView);
        setUndecorated(true);

        Color backgroundColor = new Color(255, 255, 255, 150);
        setBackground(backgroundColor);

        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outerPanel.setBackground(backgroundColor);

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        JLabel tutorialHeader = new JLabel("Welcome to Kitchen Defence!");
        tutorialHeader.setFont(new Font("Arial", Font.BOLD, 25));
        innerPanel.add(tutorialHeader);

        String tutorialText ="<html>When you click the button <b>Start Wave</b> enemies will appear on the"
                            + "<br>left side of the playing field. They will follow the path to the right side"
                            + "<br>and if they get there you will loose health points."
                            + "<br>"
                            + "<br>A good way to stop the enemiees from getting to the right side is to place"
                            + "<br>kitchen utencils along the path."
                            + "<br>To <b>place a utencil</b> first left click one of them in the bottom of the screen."
                            + "<br>Make sure you have money to pay for them!"
                            + "<br>Right click to unselect the utencil."
                            + "<br>"
                            + "<br>You can left click on a placed utencil to <b>upgrade it</b> to a faster,"
                            + "<br>stronger version."
                            + "<br>"
                            + "<br>"
                            + "<br>"
                            + "<br>";
        JLabel tutorialLabel = new JLabel(tutorialText);
        tutorialLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        innerPanel.add(tutorialLabel);


        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        outerPanel.add(innerPanel);
        outerPanel.add(startGameButton);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 5, 5));
        setContentPane(outerPanel);

    }

}
