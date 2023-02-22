package com.example.azs_fx_1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TopologyConstructorController implements Initializable {
    public GridPane mainArea;
    private int gridSize = 50;

    @FXML
    private AnchorPane pane;

    private GridHandler backgroundGridHandler;
    private DraggableMakerGrid draggableMakerGrid;
    private DraggableMaker draggableMaker = new DraggableMaker();
    private DraggableMakerGrid gridMaker2;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainArea.add(new ImageView("src/main/resources/com/example/azs_fx_1/images/road.png"), 2, 2);
        mainArea.add(new Button(), 0, 0);
        /*draggableMakerGrid = new DraggableMakerGrid(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        gridMaker2 = new DraggableMakerGrid(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);

        backgroundGridHandler = new GridHandler(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        backgroundGridHandler.updateGrid();

        Component component = new Component(gridSize, 100, 100);
        pane.getChildren().add(component.getRectangle());

        //draggableMakerGrid.makeDraggable(component);
        draggableMaker.makeDraggable(component.getRectangle());*/
    }
}
