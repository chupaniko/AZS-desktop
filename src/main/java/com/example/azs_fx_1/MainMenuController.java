package com.example.azs_fx_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainMenuController {
    @FXML
    private Button topology;

    @FXML
    private Button modelling;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String username;

    @FXML
    protected void onModellingButtonClick(ActionEvent event) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("modelling.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Моделирование");
        stage.setScene(scene);
        stage.show();*/
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

    public void setUsername(String username) {
        this.username = username;
    }
    public void onTopologyButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topologyChoice.fxml"));
        root = loader.load();
        TopologyChoiceController topologyChoiceController = loader.getController();
        topologyChoiceController.setUsername(username);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Топология");
        stage.setScene(scene);
        stage.show();
    }
    private Stage dialogStage;
    private Stage primaryStage;
    public void onAboutButtonClick (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about.fxml"));
        root = loader.load();
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        scene = new Scene(root);
        dialogStage.setTitle("О разработчиках");
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    public void onAboutAppButtonClick () {
        URL url = this.getClass().getResource("/com/example/azs_fx_1/about-app.html");
        File htmlFile = new File(url.getFile());
        if (htmlFile.exists()) {
            try {
                // Check if desktop is supported
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(htmlFile.toURI());
                } else {
                    // AWT desktop is not supported, use fallback method
                    Runtime.getRuntime().exec("xdg-open " + htmlFile.getAbsolutePath());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("HTML file not found!");
        }
    }
}

