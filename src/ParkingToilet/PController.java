package ParkingToilet;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

public class PController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private TableView<ParkingTable> myTableView;
    @FXML
    private TableColumn<ParkingTable, String> sortColumn;
    @FXML
    private TableColumn<ParkingTable, String> nameColumn;
    @FXML
    private TableColumn<ParkingTable, String> addressColumn;
    @FXML
    private TableColumn<ParkingTable, Integer> sizeColumn;
    @FXML
    private TableColumn<ParkingTable, String> opening_daysColumn;
    @FXML
    private TableColumn<ParkingTable, String> weekdays_opening_timeColumn;
    @FXML
    private TableColumn<ParkingTable, String> sat_opening_timeColumn;
    @FXML
    private TableColumn<ParkingTable, String> holiday_opening_timeColumn;
    @FXML
    private TableColumn<ParkingTable, Integer> feeColumn;

    ObservableList<ParkingTable> myList = FXCollections.observableArrayList(
            new ParkingTable(new SimpleStringProperty("주차장"), new SimpleStringProperty("동백섬"), new SimpleStringProperty("부산광역시 해운대구 동백로 116"), new SimpleIntegerProperty(500), new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleIntegerProperty(2000)),
            new ParkingTable(new SimpleStringProperty("주차장"), new SimpleStringProperty("해운대 해수욕장"), new SimpleStringProperty("부산광역시 해운대구 좌동"), new SimpleIntegerProperty(2000), new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleIntegerProperty(1000))
    );

    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = webView.getEngine();
        engine.load("http://localhost:8080/map.html");
        sortColumn.setCellValueFactory(cellData -> cellData.getValue().sortProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        sizeColumn.setCellValueFactory(cellData -> cellData.getValue().feeProperty().asObject());
        opening_daysColumn.setCellValueFactory(cellData -> cellData.getValue().opening_daysProperty());
        weekdays_opening_timeColumn.setCellValueFactory(cellData -> cellData.getValue().weekdays_opening_timeProperty());
        sat_opening_timeColumn.setCellValueFactory(cellData -> cellData.getValue().sat_opening_timeProperty());
        holiday_opening_timeColumn.setCellValueFactory(cellData -> cellData.getValue().holiday_opening_timeProperty());
        feeColumn.setCellValueFactory(cellData -> cellData.getValue().feeProperty().asObject());
        myTableView.setItems(myList);
    }
}
