package View;
import javax.swing.JFrame;
import Model.MainModel;

public class GameView extends JFrame {
    MainModel model;
    DrawPanel drawPanel;

    // Constructor
    public GameView(MainModel model){
        this.model = model;
        this.drawPanel = new DrawPanel(model);
        initComponents();
    }
    
    private void initComponents(){
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
}