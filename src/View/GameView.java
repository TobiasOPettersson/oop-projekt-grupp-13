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
    public GameView(MainModel model) { // Moved initComponents down so setVisible is done last
        importImg();
        this.model = model;
        this.drawPanel = new DrawPanel(model, image);
        add(drawPanel);
        initComponents();
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

    public void update(){
        drawPanel.update();
    }

    // initialize swing window
    private void initComponents() {
        setSize(GraphicsDependencies.getWidth(), GraphicsDependencies.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}