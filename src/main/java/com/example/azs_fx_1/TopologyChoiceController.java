package com.example.azs_fx_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TopologyChoiceController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String username;
    @FXML
    public void onCreateAZSButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topologyParams.fxml"));
        root = loader.load();
        TopologyParamsController topologyParamsController = loader.getController();
        topologyParamsController.setUsername(username);
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
