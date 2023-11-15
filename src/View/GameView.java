package src.View;

import javax.swing.JFrame;
import src.Model.MainModel;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GameView extends JFrame {
    MainModel model;
    DrawPanel drawPanel;
    private BufferedImage image;

    // Constructor
    public GameView(MainModel model) {
        importImg();
        initComponents();
        this.model = model;
        this.drawPanel = new DrawPanel(model, image);
        add(drawPanel);

    }

    // import sprite sheet
    private void importImg() {
        InputStream is = this.getClass().getResourceAsStream("res/spriteatlas.png");

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // initialize swing window
    private void initComponents() {
        setSize(GraphicsDependencies.getWidth(), GraphicsDependencies.getHeight());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
}