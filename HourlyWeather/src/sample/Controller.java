package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Weather, String> timeColumn;
    @FXML
    private TableColumn<Weather, Double> tempColumn;
    @FXML
    private TableColumn<Weather, String> descColumn;

    private ObservableList<Weather> listHour;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeColumn.setCellValueFactory(new PropertyValueFactory<Weather,String>("hour"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<Weather, Double>("temperature"));
        descColumn.setCellValueFactory(new PropertyValueFactory<Weather, String>("desc"));

        listHour = FXCollections.observableArrayList();

        try {
            String url = "http://api.openweathermap.org/data/2.5/forecast?q=hanoi,vn&APPID=4774e49c315e0c3961efaaaf22bdbb2f";
            URL objUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection)objUrl.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String response = new String();
            String inputLine;
            while((inputLine = bufferedReader.readLine()) != null) {
                response += inputLine;
            }

            JSONObject root = (JSONObject) JSONValue.parse(response);
            JSONArray list = (JSONArray)root.get("list");

            for (int i = 0; i < list.size(); i++) {

                JSONObject object = (JSONObject) list.get(i); // object

                String date = (String) object.get("dt_txt");

                JSONObject objTemp = (JSONObject) object.get("main");
                double temp = (Double) objTemp.get("temp");

                JSONArray objDesc = (JSONArray) object.get("weather");
                JSONObject obj = (JSONObject) objDesc.get(0);
                String desc = (String) obj.get("description");

                listHour.add(new Weather(date, (int)temp -273, desc));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableView.setItems(listHour);
    }
}
