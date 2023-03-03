package com.example.azs_fx_1;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TopologyConstructorController {
    public GridPane topologyGrid;
    public ImageView imgViewCashRegister;
    public ImageView imgViewGrass;
    public ImageView imgViewEntry;
    public ImageView imgViewExit;
    public ImageView imgViewRoad;
    private TopologyDTO topologyDTO;
    private TopologyDTO.TemplateAZS[] templateAZS;
    private Map<String, TopologyDTO.TemplateAZS> templateAZSMap = new HashMap<>();
    private Image CASHBOX;
    private Image ROAD;
    private Image EXIT;
    private Image ENTRY;
    private Image GRASS;
    private Image FUEL_STATION;
    private Image HIGHWAY;

    /*private int gridSize = 50;

    @FXML
    private AnchorPane pane;

    private GridHandler backgroundGridHandler;
    private DraggableMakerGrid draggableMakerGrid;
    private DraggableMaker draggableMaker = new DraggableMaker();
    private DraggableMakerGrid gridMaker2;*/

    public void setTopologyDTO(TopologyDTO topologyDTO) {
        this.topologyDTO = topologyDTO;
        ROAD = TemplateAZS.ROAD.getImageView(getClass()).getImage();
        CASHBOX = TemplateAZS.CASHBOX.getImageView(getClass()).getImage();
        EXIT = TemplateAZS.EXIT.getImageView(getClass()).getImage();
        ENTRY = TemplateAZS.ENTRY.getImageView(getClass()).getImage();
        GRASS = TemplateAZS.GRASS.getImageView(getClass()).getImage();
        FUEL_STATION = TemplateAZS.FUEL_STATION.getImageView(getClass()).getImage();
        HIGHWAY = TemplateAZS.HIGHWAY.getImageView(getClass()).getImage();
        imgViewCashRegister.setImage(CASHBOX);
        imgViewGrass.setImage(GRASS);
        imgViewEntry.setImage(ENTRY);
        imgViewExit.setImage(EXIT);
        imgViewRoad.setImage(ROAD);

        for (int i = 0; i < topologyDTO.getWidth(); i++) {
            //topologyGrid.getColumnConstraints().add(new ColumnConstraints(30));
            for (int j = 0; j < topologyDTO.getHeight(); j++) {
                ImageView templateImageView = new ImageView(ROAD);
                templateImageView.setFitWidth(30.0);
                templateImageView.setFitHeight(30.0);
                //topologyGrid.getRowConstraints().add(new RowConstraints(30));
                topologyGrid.add(templateImageView, i, j);
                templateAZSMap.put(i + " " + j, new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.road));
            }
        }
        //topologyGrid.getRowConstraints().add(new RowConstraints(30));
        for (int i = 0; i < topologyDTO.getWidth(); i++) {
            //ImageView templateImageView = TemplateAZS.HIGHWAY.getImageView(getClass());
            ImageView templateImageView = new ImageView(HIGHWAY);
            templateImageView.setFitWidth(30.0);
            templateImageView.setFitHeight(30.0);
            topologyGrid.add(templateImageView, i, topologyDTO.getHeight() - 1);
            templateAZSMap.put(i + " " + (topologyDTO.getHeight() - 1), new TopologyDTO.TemplateAZS(i, (topologyDTO.getHeight() - 1), TopologyDTO.TemplateAZS.Template.highway));
        }
    }

    //final GridPane target = topologyGrid;

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
        if (event.getGestureSource() != topologyGrid && event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    public void onImgViewDragDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        Node node = event.getPickResult().getIntersectedNode();
        //templateAZS = new TopologyDTO.TemplateAZS[topologyDTO.getHeight() * topologyDTO.getWidth()];
        Integer cIndex = GridPane.getColumnIndex(node);
        Integer rIndex = GridPane.getRowIndex(node);
        int x = cIndex == null ? 0 : cIndex;
        int y = rIndex == null ? 0 : rIndex;

        /*System.out.println(x);
        System.out.println(y);*/
        //System.out.println(mainArea.getRowConstraints().size());
        /*System.out.println(topologyDTO.getHeight());*/
        if (node != topologyGrid && dragboard.hasImage() && y < topologyDTO.getHeight() - 1) {
            Image nodeImage = dragboard.getImage();
            if (imagesEqual(nodeImage, CASHBOX)) {
                System.out.println("CASHBOX " + x + " " + y);
                templateAZSMap.put(x + " " + y, new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.cashbox));
                //templateAZS[topologyNodeIndex] = new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.cashbox);
            } else if (imagesEqual(nodeImage, ROAD)) {
                System.out.println("ROAD " + x + " " + y);
                templateAZSMap.put(x + " " + y, new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.road));
                //templateAZS[topologyNodeIndex] = new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.road);
            } else if (imagesEqual(nodeImage, EXIT)) {
                System.out.println("EXIT " + x + " " + y);
                templateAZSMap.put(x + " " + y, new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.exit));
                //templateAZS[topologyNodeIndex] = new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.exit);
            } else if (imagesEqual(nodeImage, ENTRY)) {
                System.out.println("ENTRY " + x + " " + y);
                templateAZSMap.put(x + " " + y, new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.entry));
                //templateAZS[topologyNodeIndex] = new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.entry);
            } else if (imagesEqual(nodeImage, GRASS)) {
                System.out.println("GRASS " + x + " " + y);
                templateAZSMap.put(x + " " + y, new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.grass));
                //templateAZS[topologyNodeIndex] = new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.grass);
            } else if (imagesEqual(nodeImage, FUEL_STATION)) {
                System.out.println("FUEL_STATION " + x + " " + y);
                templateAZSMap.put(x + " " + y, new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.fuel_station));
                //templateAZS[topologyNodeIndex] = new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.fuel_station);
            } else if (imagesEqual(nodeImage, HIGHWAY)) {
                System.out.println("HIGHWAY " + x + " " + y);
                templateAZSMap.put(x + " " + y, new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.highway));
                //templateAZS[topologyNodeIndex] = new TopologyDTO.TemplateAZS(x, y, TopologyDTO.TemplateAZS.Template.highway);
            }

            //templateAZS = templateAZSMap.values().toArray(new TopologyDTO.TemplateAZS[topologyDTO.getHeight() * topologyDTO.getWidth()]);
            ImageView newImage = new ImageView(dragboard.getImage());
            newImage.setFitHeight(30);
            newImage.setFitWidth(30);
            topologyGrid.getChildren().removeAll(node);
            ImageView roadImgView = new ImageView(ROAD);
            roadImgView.setFitHeight(30.0);
            roadImgView.setFitWidth(30.0);
            topologyGrid.add(roadImgView, x, y, 1, 1);
            topologyGrid.add(newImage, x, y, 1, 1);

            event.setDropCompleted(true);
            event.consume();
        } else {
            event.setDropCompleted(false);
            event.consume();
        }
    }

    private boolean imagesEqual(Image image1, Image image2) {
        PixelReader reader1 = image1.getPixelReader();
        PixelReader reader2 = image2.getPixelReader();
        boolean isEqual = false;
        if (image1.getWidth() == image2.getWidth() && image1.getHeight() == image2.getHeight()) {
            isEqual = true;
            for (int y = 0; y < image1.getHeight() && isEqual; y++) {
                for (int x = 0; x < image1.getWidth() && isEqual; x++) {
                    if (reader1.getArgb(x, y) != reader2.getArgb(x, y)) {
                        isEqual = false;
                    }
                }
            }
        }
        return isEqual;
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

    public void onSaveTopology(ActionEvent event) throws JsonProcessingException {
        // TODO: пофиксить некорректные значения для numRows и numCols
        int numRows = topologyGrid.getRowCount();
        int numCols = topologyGrid.getColumnCount();
        System.out.println("numRows = " + numRows);
        System.out.println("numCols = " + numCols);

        /*templateAZS = new TopologyDTO.TemplateAZS[numRows * numCols];
        // TODO: Включить highway в итерацию (numRows - 1)
        for (int i = 0; i < numRows - 1; i++) {
            for (int j = 0; j < numCols; j++) {
                *//*templateAZS[numCols * i + j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.road);
                templateAZS[numCols * (numRows - 1) + j] = new TopologyDTO.TemplateAZS(numRows - 1, j, TopologyDTO.TemplateAZS.Template.highway);*//*
                Node node = topologyGrid.getChildren().get(j + (i * numCols));
                if (node instanceof ImageView) {
                    // TODO: fix null pointer exception
                    Image nodeImage = ((ImageView) node).getImage();
                    //System.out.println("nodeImage = " + nodeImage);
                    *//*ImageView cashbox = TemplateAZS.CASHBOX.getImageView();
                    ImageView road = TemplateAZS.ROAD.getImageView();
                    Image exit = TemplateAZS.EXIT.getImageView().getImage();
                    ImageView entry = TemplateAZS.ENTRY.getImageView();
                    ImageView grass = TemplateAZS.GRASS.getImageView();
                    ImageView fuelStation = TemplateAZS.FUEL_STATION.getImageView();
                    ImageView highway = TemplateAZS.HIGHWAY.getImageView();*//*
                    if (imagesEqual(nodeImage, CASHBOX)) {
                        templateAZS[numCols * i + j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.cashbox);
                    } else if (imagesEqual(nodeImage, ROAD)) {
                        templateAZS[numCols * i + j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.road);
                    } else if (imagesEqual(nodeImage, EXIT)) {
                        templateAZS[numCols * i + j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.exit);
                    } else if (imagesEqual(nodeImage, ENTRY)) {
                        templateAZS[numCols * i + j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.entry);
                    } else if (imagesEqual(nodeImage, GRASS)) {
                        templateAZS[numCols * i + j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.grass);
                    } else if (imagesEqual(nodeImage, FUEL_STATION)) {
                        templateAZS[numCols * i + j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.fuel_station);
                    } else if (imagesEqual(nodeImage, HIGHWAY)) {
                        templateAZS[numCols * i + j] = new TopologyDTO.TemplateAZS(i, j, TopologyDTO.TemplateAZS.Template.highway);
                    }
                }
            }
        }*/
        Collection<TopologyDTO.TemplateAZS> values = templateAZSMap.values();
        templateAZS = values.toArray(new TopologyDTO.TemplateAZS[0]);

        topologyDTO.setAzsField(templateAZS);
        //
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString = objectMapper.writeValueAsString(topologyDTO);
        System.out.println(jsonString);
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
