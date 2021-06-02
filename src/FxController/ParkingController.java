package FxController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DTO.ParkingLotsDTO;
import DAO.InquireToiletParkingDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ParkingController implements Initializable {

    @FXML
    private WebView webView;
    private WebEngine engine;
    @FXML
    private TableView<ParkingLotsDTO> myTableView;
    @FXML
    private TableColumn<ParkingLotsDTO, String> nameColumn;
    @FXML
    private TableColumn<ParkingLotsDTO, String> addressColumn;
    @FXML
    private TableColumn<ParkingLotsDTO, String> sizeColumn;
    @FXML
    private TableColumn<ParkingLotsDTO, String> opening_daysColumn;
    @FXML
    private TableColumn<ParkingLotsDTO, String> weekdays_opening_timeColumn;
    @FXML
    private TableColumn<ParkingLotsDTO, String> sat_opening_timeColumn;
    @FXML
    private TableColumn<ParkingLotsDTO, String> holiday_opening_timeColumn;
    @FXML
    private TableColumn<ParkingLotsDTO, String> feeColumn;

   

    public void initialize(URL location, ResourceBundle resources) {
        engine = webView.getEngine();
        getLatLng();
        engine.load("http://localhost:8081/map.html");

        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDo() + " " + cellData.getValue().getCity() + " " + cellData.getValue().getAddress()));
        sizeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getSize())));
        opening_daysColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpening_days()));
        weekdays_opening_timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWeekdays_opening_time()));
        sat_opening_timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSat_opening_time()));
        holiday_opening_timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoliday_opening_time()));
        feeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFee()));
       
    }

    public void getLatLng() { //위경도 값 가져오기
        engine.setOnAlert (event ->
        {
            String lat = event.getData().split(" ")[0];
            String lng = event.getData().split(" ")[1];

            ArrayList<ParkingLotsDTO> list = InquireToiletParkingDAO.inquireParkingLotByLocation(lat, lng , "1");
            myTableView.getItems().addAll(list);
            try {
                System.out.println(lat);
                System.out.println(lng);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
//inquireParkingLotByLocation