package com.example.azs_fx_1;

import com.example.azs_fx_1.dto.TopologyDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServiceAreaController {
    private TopologyDTO topologyDTO;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void onFurtherButtonClick(ActionEvent actionEvent) throws IOException {
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
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Конструктор топологии АЗС");
        stage.setScene(scene);
        stage.show();
    }
    public void setTopologyDTO(TopologyDTO topologyDTO) {
        this.topologyDTO = topologyDTO;
    }
}
