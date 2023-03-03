package com.example.azs_fx_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TopologyParamsController {

    public TextField topologyName;
    public TextField length_AZS;
    public TextField width_AZS;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String username;

    public void onSetTopologyParamsClick(ActionEvent actionEvent) throws IOException {
        TopologyDTO topologyDTO = new TopologyDTO(topologyName.getText(), Integer.parseInt(length_AZS.getText()), Integer.parseInt(width_AZS.getText()) + 1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("serviceArea.fxml"));
        root = loader.load();

        ServiceAreaController serviceAreaController = loader.getController();
        serviceAreaController.setTopologyDTO(username, topologyDTO);

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Настройка служебной области АЗС");
        stage.setScene(scene);
        stage.show();
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
