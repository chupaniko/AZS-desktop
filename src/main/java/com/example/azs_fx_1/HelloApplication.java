package com.example.azs_fx_1;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authorization.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Auth");
        stage.setScene(scene);
        stage.show();
    }
    // Можно не писать метод main(), т.к. метод launch() вызовется в любом случае
    /*public static void main(String[] args) {
        launch();
    }*/
}
