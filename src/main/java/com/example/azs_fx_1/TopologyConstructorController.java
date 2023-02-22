package com.example.azs_fx_1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TopologyConstructorController implements Initializable {
    private int gridSize = 50;

    @FXML
    private AnchorPane pane;

    private GridHandler backgroundGridHandler;
    private DraggableMakerGrid draggableMakerGrid;
    private DraggableMaker draggableMaker = new DraggableMaker();
    private DraggableMakerGrid gridMaker2;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        draggableMakerGrid = new DraggableMakerGrid(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        gridMaker2 = new DraggableMakerGrid(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);

        backgroundGridHandler = new GridHandler(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        backgroundGridHandler.updateGrid();

        Component component = new Component(gridSize, 100, 100);
        pane.getChildren().add(component.getRectangle());

        //draggableMakerGrid.makeDraggable(component);
        draggableMaker.makeDraggable(component.getRectangle());
    }
}
