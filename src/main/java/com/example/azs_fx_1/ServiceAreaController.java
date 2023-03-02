package com.example.azs_fx_1;

import com.example.azs_fx_1.dto.TopologyDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceAreaController {
    private TopologyDTO topologyDTO;
    private Stage stage;
    private Scene scene;
    private Parent root;

    //private List<TopologyDTO.FuelTank> tankList = new ArrayList<>();
    public ListView<HBox> listView;

    public void onFurtherButtonClick(ActionEvent actionEvent) throws IOException, InterruptedException {
        TopologyDTO.FuelTank[] tanks = new TopologyDTO.FuelTank[listView.getItems().size()];
        for (int i = 0; i < listView.getItems().size(); i++) {
            HBox hbox = listView.getItems().get(i);
            // TODO: fix warning
            Node comboBoxNode = hbox.getChildren().get(1);
            //ComboBox<TopologyDTO.FuelTank.FuelType> comboBox = comboBoxNode instanceof ComboBox<TopologyDTO.FuelTank.FuelType> ? (ComboBox<TopologyDTO.FuelTank.FuelType>)comboBoxNode : new ComboBox<>();
            ComboBox<TopologyDTO.FuelTank.FuelType> comboBox = (ComboBox<TopologyDTO.FuelTank.FuelType>) comboBoxNode;
            tanks[i] = new TopologyDTO.FuelTank(i + 1, comboBox.getValue());
        }
        topologyDTO.setTanks(tanks);

        /*topologyDTO.setTanks(new TopologyDTO.FuelTank[]{
                new TopologyDTO.FuelTank(1, TopologyDTO.FuelTank.FuelType.AI_98),
                new TopologyDTO.FuelTank(2, TopologyDTO.FuelTank.FuelType.AI_95),
                new TopologyDTO.FuelTank(3, TopologyDTO.FuelTank.FuelType.DT),
                new TopologyDTO.FuelTank(4, TopologyDTO.FuelTank.FuelType.AI_80),
                new TopologyDTO.FuelTank(5, TopologyDTO.FuelTank.FuelType.AI_92),
                new TopologyDTO.FuelTank(6, TopologyDTO.FuelTank.FuelType.AI_95)
        });*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topologyConstructor.fxml"));
        root = loader.load();
        TopologyConstructorController constructorController = loader.getController();
        constructorController.setTopologyDTO(topologyDTO);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Конструктор топологии АЗС " + topologyDTO.getName());
        stage.setScene(scene);
        stage.show();
    }

    public void setTopologyDTO(TopologyDTO topologyDTO) {
        this.topologyDTO = topologyDTO;
        createTankListNode(TopologyDTO.FuelTank.FuelType.AI_92);
        createTankListNode(TopologyDTO.FuelTank.FuelType.AI_95);
        createTankListNode(TopologyDTO.FuelTank.FuelType.DT);
    }

    private void createTankListNode(TopologyDTO.FuelTank.FuelType fuelType) {
        ComboBox<TopologyDTO.FuelTank.FuelType> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                TopologyDTO.FuelTank.FuelType.DT,
                TopologyDTO.FuelTank.FuelType.AI_80,
                TopologyDTO.FuelTank.FuelType.AI_92,
                TopologyDTO.FuelTank.FuelType.AI_95,
                TopologyDTO.FuelTank.FuelType.AI_98
        );
        comboBox.setValue(fuelType);
        HBox hbox = new HBox();
        Label label = new Label(Integer.toString(listView.getItems().size() + 1));
        hbox.getChildren().addAll(label, comboBox);
        listView.getItems().add(hbox);
        //TODO: make ObservableList
        //tankList.add(new TopologyDTO.FuelTank(tankList.size() + 1, fuelType));
    }

    public void addFuelType() {
        createTankListNode(TopologyDTO.FuelTank.FuelType.AI_92);
    }
}
