package src.View;

import View.GameView;
import Model.MainModel;

public class TempMain {

    public static void main(String[] args) {
        MainModel model = new MainModel();
        GameView view = new GameView(model);
    }
}
