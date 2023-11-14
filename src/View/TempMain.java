package src.View;
import src.View.GameView;
import src.Model.MainModel;

public class TempMain {
    
    public static void main(String[] args){
        MainModel model = new MainModel();
        GameView view = new GameView(model);
    }
}
