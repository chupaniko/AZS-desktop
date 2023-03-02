package com.example.azs_fx_1;

import com.example.azs_fx_1.dto.TopologyDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TopologyConstructorController {
    public GridPane topologyGrid;
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
            //topologyGrid.getColumnConstraints().add(new ColumnConstraints(30));
            for (int j = 0; j < topologyDTO.getHeight(); j++) {
                ImageView templateImageView = TemplateAZS.ROAD.getImageView();
                //topologyGrid.getRowConstraints().add(new RowConstraints(30));
                topologyGrid.add(templateImageView, i, j);
            }
        }
        //topologyGrid.getRowConstraints().add(new RowConstraints(30));
        for (int i = 0; i < topologyDTO.getWidth(); i++) {
            ImageView templateImageView = TemplateAZS.HIGHWAY.getImageView();
            topologyGrid.add(templateImageView, i, topologyDTO.getHeight() - 1);
        }
    }

    final GridPane target = topologyGrid;

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
        if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    public void onImgViewDragDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        Node node = event.getPickResult().getIntersectedNode();

        Integer cIndex = GridPane.getColumnIndex(node);
        Integer rIndex = GridPane.getRowIndex(node);
        int x = cIndex == null ? 0 : cIndex;
        int y = rIndex == null ? 0 : rIndex;
        /*System.out.println(x);
        System.out.println(y);*/
        //System.out.println(mainArea.getRowConstraints().size());
        /*System.out.println(topologyDTO.getHeight());*/
        if (node != target && dragboard.hasImage() && y < topologyDTO.getHeight() - 1) {
            ImageView newImage = new ImageView(dragboard.getImage());
            newImage.setFitHeight(30);
            newImage.setFitWidth(30);
            topologyGrid.getChildren().removeAll(node);
            topologyGrid.add(TemplateAZS.ROAD.getImageView(), x, y, 1, 1);
            topologyGrid.add(newImage, x, y, 1, 1);

            event.setDropCompleted(true);
            event.consume();
        } else {
            event.setDropCompleted(false);
            event.consume();
        }
    }

    public void removeArea(ActionEvent event) {
        topologyGrid.getChildren().clear();
        setTopologyDTO(topologyDTO);
    }

    private Stage dialogStage;
    private Stage primaryStage;
    private Scene scene;
    private Parent root;

    public void onReferenceButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reference.fxml"));
        root = loader.load();
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        scene = new Scene(root);
        dialogStage.setTitle("Справка");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public void onSaveTopology(ActionEvent event) {
        // TODO: пофиксить некорректные значения для numRows и numCols
        int numRows = topologyGrid.getRowCount();
        int numCols = topologyGrid.getColumnCount();
        System.out.println("numRows = " + numRows);
        System.out.println("numCols = " + numCols);
        TopologyDTO.TemplateAZS[] templateAZS = new TopologyDTO.TemplateAZS[numRows * numCols];
        // TODO: Включить highway в итерацию (numRows - 1)
        for (int i = 0; i < numRows - 1; i++) {
            for (int j = 0; j < numCols; j++) {
                templateAZS[numCols * i + j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.road);
                templateAZS[numCols * (numRows - 1) + j] = new TopologyDTO.TemplateAZS(numRows - 1, j, TopologyDTO.TemplateAZS.Template.highway);
                /*Node node = topologyGrid.getChildren().get(j + (i * numCols));
                if (node instanceof ImageView) {
                    // TODO: fix null pointer exception
                    Image nodeImage = ((ImageView) node).getImage();
                    ImageView cashbox = TemplateAZS.CASHBOX.getImageView();
                    ImageView road = TemplateAZS.ROAD.getImageView();
                    *//*Image exit = TemplateAZS.EXIT.getImageView().getImage();*//*
                    ImageView entry = TemplateAZS.ENTRY.getImageView();
                    ImageView grass = TemplateAZS.GRASS.getImageView();
                    ImageView fuelStation = TemplateAZS.FUEL_STATION.getImageView();
                    ImageView highway = TemplateAZS.HIGHWAY.getImageView();
                    if ((cashbox.getImage()).equals(nodeImage)) {
                        templateAZS[i][j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.cashbox);
                    } else if ((road.getImage()).equals(nodeImage)) {
                        templateAZS[i][j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.road);
                    }*/ /*else if (exit.equals(nodeImage)) {
                        templateAZS[i][j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.exit);
                    }*/ /*else if (entry.equals(nodeImage)) {
                        templateAZS[i][j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.entry);
                    } else if (grass.equals(nodeImage)) {
                        templateAZS[i][j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.grass);
                    } else if (fuelStation.equals(nodeImage)) {
                        templateAZS[i][j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.fuel_station);
                    } else if (highway.equals(nodeImage)) {
                        templateAZS[i][j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.highway);
                    }
                }*/
            }
        }
        topologyDTO.setAzsField(templateAZS);
        System.out.println(topologyDTO.toString());
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
