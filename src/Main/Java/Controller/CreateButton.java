package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Enums.TowerType;

/**
 * CreateButton represents a specific button type used in the shop interface to
 * create towers.
 * It extends the AWidgetButton class and handles the creation of tower buttons.
 * This button allows users to select a tower type and notifies the associated
 * CreateWidgetController
 * when clicked, facilitating tower creation in the game.
 */

public class CreateButton extends AWidgetButton {

    /**
     * Constructor for CreateButton
     * 
     * @param cost            The cost of the tower
     * @param towerController The CreateTowerController the button will be added to
     * @param type            The type of the tower
     */
    public CreateButton(int cost, TowerType type, CreateWidgetController towerController) {
        super(cost, type, towerController);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mEvent) {
                towerController.notifyObservers(type);
            }
        });
    }

    /**
     * Initializes the map containing the pats of button images
     */
    @Override
    protected void initButtonImagePaths() {
        String resPath = "src/Main/Java/Controller/resController/";
        buttonImgPaths.put(TowerType.knife, resPath + "knife.png");
        buttonImgPaths.put(TowerType.mallet, resPath + "mallet.png");
        buttonImgPaths.put(TowerType.blowtorch, resPath + "blowtorch.png");
        buttonImgPaths.put(TowerType.slicer, resPath + "slicer.png");
        buttonImgPaths.put(TowerType.freezer, resPath + "fridge.png");
    }
}
