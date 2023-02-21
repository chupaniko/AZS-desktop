package com.example.azs_fx_1;

import com.example.azs_fx_1.dto.TopologyDTO;
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

    public void onSetTopologyParamsClick(ActionEvent actionEvent) throws IOException {
        TopologyDTO topologyDTO = new TopologyDTO(topologyName.getText(), Integer.parseInt(length_AZS.getText()), Integer.parseInt(width_AZS.getText()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topologyParams.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Настройка служебной области АЗС");
        stage.setScene(scene);
        stage.show();
    }
}
