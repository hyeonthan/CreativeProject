package ParkingToilet;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class TController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private TableView<ToiletTable> myTableView;
    @FXML
    private TableColumn<ToiletTable, String> sortColumn;
    @FXML
    private TableColumn<ToiletTable, String> nameColumn;
    @FXML
    private TableColumn<ToiletTable, String> addressColumn;
    @FXML
    private TableColumn<ToiletTable, String> PublicColumn;
    @FXML
    private TableColumn<ToiletTable, String> opening_timeColumn;
    @FXML
    private TableColumn<ToiletTable, String> managementAgencyColumn;

    ObservableList<ToiletTable> myList = FXCollections.observableArrayList(
            new ToiletTable(new SimpleStringProperty("화장실"), new SimpleStringProperty("맥도날드 달맞이점"), new SimpleStringProperty("부산 해운대구 좌동순환로 455 맥도날드"), new SimpleStringProperty("N"), new SimpleStringProperty("00:00~24:00"), new SimpleStringProperty("맥도날드")),
            new ToiletTable(new SimpleStringProperty("화장실"), new SimpleStringProperty("해운대 해수욕장"), new SimpleStringProperty("부산광역시 해운대구 좌동"), new SimpleStringProperty("N"), new SimpleStringProperty("00:00~24:00"), new SimpleStringProperty("부산광역시 해운대구"))
    );

    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = webView.getEngine();
        engine.load("http://localhost:8080/map.html");
        sortColumn.setCellValueFactory(cellData -> cellData.getValue().sort());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().name());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().address());
        PublicColumn.setCellValueFactory(cellData -> cellData.getValue().Public());
        opening_timeColumn.setCellValueFactory(cellData -> cellData.getValue().opening_time());
        managementAgencyColumn.setCellValueFactory(cellData -> cellData.getValue().managementAgency());
        myTableView.setItems(myList);
    }
}
