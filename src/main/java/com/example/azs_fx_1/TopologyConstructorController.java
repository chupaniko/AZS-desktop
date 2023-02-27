package com.example.azs_fx_1;

import com.example.azs_fx_1.dto.TopologyDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TopologyConstructorController {
    public GridPane mainArea;
    private TopologyDTO topologyDTO;

    /*private int gridSize = 50;

    @FXML
    private AnchorPane pane;

    private GridHandler backgroundGridHandler;
    private DraggableMakerGrid draggableMakerGrid;
    private DraggableMaker draggableMaker = new DraggableMaker();
    private DraggableMakerGrid gridMaker2;*/

    public void setTopologyDTO(TopologyDTO topologyDTO) {
        this.topologyDTO = topologyDTO;
        for (int i = 0; i < topologyDTO.getWidth(); i++) {
            mainArea.getColumnConstraints().add(new ColumnConstraints(30));
            for (int j = 0; j < topologyDTO.getHeight(); j++) {
                ImageView templateImageView = TemplateAZS.ROAD.getImageView();
                mainArea.getRowConstraints().add(new RowConstraints(30));
                mainArea.add(templateImageView, i, j);
            }
        }
        for (int i = 0; i < topologyDTO.getHeight(); i++) {
            ImageView templateImageView = TemplateAZS.HIGHWAY.getImageView();
            mainArea.getRowConstraints().add(new RowConstraints(30));
            mainArea.add(templateImageView, i, topologyDTO.getHeight() - 1);
        }
    }

    final GridPane target = mainArea;
    public void onImgViewDragDetected(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        Dragboard dragboard = imageView.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(imageView.getImage());
        dragboard.setContent(content);
        event.consume();
    }

    public void onImgViewDragOver(DragEvent event) {
        if (event.getGestureSource() != target && event.getDragboard().hasImage()){
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }
    public void onImgViewDragDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        Node node = event.getPickResult().getIntersectedNode();

        if ( node != target && dragboard.hasImage()) {

            Integer cIndex = GridPane.getColumnIndex(node);
            Integer rIndex = GridPane.getRowIndex(node);
            int x = cIndex == null ? 0 : cIndex;
            int y = rIndex == null ? 0 : rIndex;

//            Image image = dragboard.getImage();
//
//            GridPane gridPane = (GridPane) event.getGestureTarget();

            ImageView newImage = new ImageView(dragboard.getImage());
            newImage.setFitHeight(30);
            newImage.setFitWidth(30);
            mainArea.getChildren().remove(node);
            mainArea.add(TemplateAZS.ROAD.getImageView(), x, y, 1, 1);
            mainArea.add(newImage, x, y, 1, 1);

            event.setDropCompleted(true);
            event.consume();
        } else {
            event.setDropCompleted(false);
            event.consume();
        }
    }

   /* @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("TopologyConstructorController:::::::::::::::::::: " + topologyDTO.getName());

        *//*mainArea.add(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/azs_fx_1/images/road.png")))), 2, 2);
        mainArea.add(new Button(), 0, 0);
        for (int i = 0; i < mainArea.getColumnCount(); i++) {
            ColumnConstraints column = mainArea.getColumnConstraints().get(i);
            column.setPrefWidth(30);
        }
        for (int i = 0; i < mainArea.getRowCount(); i++) {
            RowConstraints row = mainArea.getRowConstraints().get(i);
            row.setPrefHeight(30);
        }*//*
     *//*draggableMakerGrid = new DraggableMakerGrid(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        gridMaker2 = new DraggableMakerGrid(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);

        backgroundGridHandler = new GridHandler(pane.getPrefWidth(), pane.getPrefHeight(), gridSize, pane);
        backgroundGridHandler.updateGrid();

        Component component = new Component(gridSize, 100, 100);
        pane.getChildren().add(component.getRectangle());

        //draggableMakerGrid.makeDraggable(component);
        draggableMaker.makeDraggable(component.getRectangle());*//*
    }*/
}
