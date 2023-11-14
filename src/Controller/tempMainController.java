package src.Controller;

import javax.swing.JButton;

import src.Model.MainModel;
import src.View.GameView;

public class tempMainController {

    public static void main(String[] args) {
        MainModel model = new MainModel();
        TowerController towerController = new CreateTowerController(0, 0, null, null, null);

        towerView towerView = new towerView(towerController);
        towerView.updateLabel("CREATE Towers");
        JButton button = new JButton("Tower one");
        towerView.addButton(button);
    }

}
