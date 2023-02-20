package com.example.azs_fx_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button topology;

    @FXML
    private Button modelling;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void onModellingButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Расчёт АЗС.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Моделирование");
        stage.setScene(scene);
        stage.show();
        /*try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("main-menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("error");
        }*/
    }

    public void onTopologyButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topologyChoice.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Топология");
        stage.setScene(scene);
        stage.show();
    }
}

