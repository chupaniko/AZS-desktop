package com.example.azs_fx_1;

import com.example.azs_fx_1.dto.TopologyDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private List<TopologyDTO.FuelTank> tankList = new ArrayList<>();
    public ListView listView;

    public void onFurtherButtonClick(ActionEvent actionEvent) throws IOException, InterruptedException {
        //TODO: build tanklist from listView and setTanks()
        TopologyDTO.FuelTank[] tanks = new TopologyDTO.FuelTank[] {};
        for (int i=0; i < listView.getItems().size(); i++ ) {
            HBox hbox = (HBox) listView.getItems().get(0);
            ComboBox comboBox = (ComboBox) hbox.getChildren().get(1);
            TopologyDTO.FuelTank.FuelType fuelType = (TopologyDTO.FuelTank.FuelType) comboBox.getSelectionModel().getSelectedItem();
            tanks[i] = new TopologyDTO.FuelTank(i, fuelType );
        }
        topologyDTO.setTanks(tanks);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topologyConstructor.fxml"));
        root = loader.load();
        TopologyConstructorController constructorController = loader.getController();
        constructorController.setTopologyDTO(topologyDTO);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
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
        if (listView.getItems().size() < 6) {
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
            Label label = new Label("Топливо" + " " + (tankList.size() + 1) + ":" + "  ");
            hbox.getChildren().addAll(label, comboBox);
            listView.getItems().add(hbox);
            tankList.add(new TopologyDTO.FuelTank(tankList.size() + 1, fuelType));
        }
    }

    public void deleteTankListNode () {
        if (listView.getItems().size() > 5) {
            listView.getItems().remove(5);
            tankList.remove(5);
        } else if (listView.getItems().size() > 4) {
            listView.getItems().remove(4);
            tankList.remove(4);
        } else if (listView.getItems().size() > 3) {
            listView.getItems().remove(3);
            tankList.remove(3);
        }
    }

    @FXML
    public void onTopologyParamsButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topologyParams.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Настройка служебной области АЗС");
        stage.setScene(scene);
        stage.show();
    }

    public void addFuelType() {
        createTankListNode(TopologyDTO.FuelTank.FuelType.AI_92);
    }
}
