package com.example.azs_fx_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.controlsfx.control.action.Action;

import java.io.IOException;

public class AuthController {
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button enterButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void onEnterButtonClick(ActionEvent event) throws IOException {
        JSONObject json = new JSONObject();
        json.put("username", loginField.getText());
        json.put("password", passwordField.getText());
        /*JSONObject password1 = new JSONObject();
        password1.put("password1", passwordField.getText());
        json.put("password1", password1);*/
        System.out.println(json);

        /*String result = null;
        Request request = Request.post("http://localhost:8080/api/authentication");
        request.bodyForm(
                new BasicNameValuePair("username", loginField.getText()),
                new BasicNameValuePair("password", passwordField.getText()));
                try {
                    result = request.execute().returnContent().asString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(result);*/
        String result = null;
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/authentication");
        httpPost.setEntity(new StringEntity(json.toString(), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                // TODO assert non-null (exception in get username
                result = (new JSONObject(EntityUtils.toString(response.getEntity()))).get("username").toString();
            }
        } catch (IOException | ParseException | JSONException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка авторизации");
            alert.setContentText("Неверно введён логин или пароль");
            alert.showAndWait();
            e.printStackTrace();
        }
        System.out.println(result);
// TODO: вернуть проверку
        if (result != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Main");
            stage.setScene(scene);
            stage.show();
        }
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Main");
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

}
