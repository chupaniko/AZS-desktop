package com.example.azs_fx_1;

import com.example.azs_fx_1.dto.TopologyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class TopologyChoiceController {
    public ChoiceBox topologyChoice;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String username;
    private Map<String, String> topologyMap;
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
        //topologyNames = new ArrayList<>();
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("username", username);
        jsonRequest.put("topologyName", "");
        jsonRequest.put("topologyJSON", "");

        //String result = "";
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/getTopologiesByUser");
        httpPost.setEntity(new StringEntity(jsonRequest.toString(), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                // TODO assert non-null (exception in get username
                System.out.println("response = " + response.getEntity());
                topologyMap = new HashMap<>();
                /*topologies = new HashMap<>();
                JSONObject topologiesJSON = new JSONObject(EntityUtils.toString(response.getEntity()));
                Iterator<String> keys = topologiesJSON.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    String value = topologiesJSON.get(key)
                }*/
                JSONArray result = new JSONArray(EntityUtils.toString(response.getEntity()));
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonObject = result.getJSONObject(i);
                    topologyMap.put(jsonObject.get("topologyName").toString(), jsonObject.get("topologyJSON").toString());
                    topologyChoice.getItems().add(jsonObject.get("topologyName").toString());
                }
                if (topologyChoice.getItems().size() != 0) {
                    topologyChoice.setValue(topologyChoice.getItems().get(0));
                }
                System.out.println("result = " + topologyMap);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException | JSONException e) {
            /*Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка чтения топологий");
            alert.setContentText("Топологий нет в базе данных.");
            alert.showAndWait();
            e.printStackTrace();*/
        }

// TODO: вернуть проверку
        //if (result != null) {
            /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Топологии прочтены из БД!");
            alert.setContentText("Топологии \"" *//*+ topologyDTO.getName()*//* + "\" успешно прочтены из БД.");
            alert.showAndWait();*/
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            root = loader.load();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setUsername(loginField.getText());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Main");
            stage.setScene(scene);
            stage.show();*/
        //}
    }
    @FXML
    public void onMainMenuButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUsername(username);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
    }

    public void onOpenAZSButtonClick(ActionEvent actionEvent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        TopologyDTO topologyDTO = objectMapper.readValue(topologyMap.get((topologyChoice.getValue()).toString()), TopologyDTO.class);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("topologyConstructor.fxml"));
        root = loader.load();
        TopologyConstructorController constructorController = loader.getController();
        constructorController.setTopologyDTO(username, topologyDTO);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Конструктор топологии АЗС " + topologyDTO.getName());
        stage.setScene(scene);
        stage.show();
    }
}
