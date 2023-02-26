package com.example.azs_fx_1;

import com.example.azs_fx_1.dto.TopologyDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ServiceAreaController {
    private TopologyDTO topologyDTO;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public ComboBox<String> comboBox = new ComboBox<String>();
    public ListView listView;

    public void onFurtherButtonClick(ActionEvent actionEvent) throws IOException, InterruptedException {
        topologyDTO.setTanks(new TopologyDTO.FuelTank[]{
                new TopologyDTO.FuelTank(1, TopologyDTO.FuelTank.FuelType.AI_98),
                new TopologyDTO.FuelTank(2, TopologyDTO.FuelTank.FuelType.AI_95),
                new TopologyDTO.FuelTank(3, TopologyDTO.FuelTank.FuelType.DT),
                new TopologyDTO.FuelTank(4, TopologyDTO.FuelTank.FuelType.AI_80),
                new TopologyDTO.FuelTank(5, TopologyDTO.FuelTank.FuelType.AI_92),
                new TopologyDTO.FuelTank(6, TopologyDTO.FuelTank.FuelType.AI_95)
        });
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topologyConstructor.fxml"));
        root = loader.load();
        TopologyConstructorController constructorController = loader.getController();
        constructorController.setTopologyDTO(topologyDTO);
        System.out.println("ServiceAreaController:::::::::::::::::::: ");
        System.out.println("ServiceAreaController:::::::::::::::::::: " + topologyDTO.getName());
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Конструктор топологии АЗС " + topologyDTO.getName());
        stage.setScene(scene);
        stage.show();
    }
    public void setTopologyDTO(TopologyDTO topologyDTO) {
        this.topologyDTO = topologyDTO;
    }

    public void addGasType (ActionEvent actionEvent) {
        comboBox.getItems().addAll("Seafood Alfredo", "Chicken Alfredo", "Chicken Picatta", "Turkey Club",
                "Lobster Pie", "Prime Rib", "Shrimp Scampi", "Turkey Dinner", "Stuffed Chicken");
        listView.getItems().add(comboBox);
    }
}
