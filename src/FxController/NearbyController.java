package FxController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DTO.DestinationDTO;
import DTO.ParkingLotsDTO;
import DAO.InquireToiletParkingDAO;
import DataSetControl.RegionList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class NearbyController implements Initializable {

    @FXML
    private WebView webView;
    private WebEngine engine;
    @FXML private ComboBox<String> comboBoxClassification;
    @FXML private TableView<DestinationDTO> myTableView;
    @FXML private TableColumn<DestinationDTO, String> sortColumn;
    @FXML private TableColumn<DestinationDTO, String> nameColumn;
    @FXML private TableColumn<DestinationDTO, String> addressColumn;
    @FXML private TableColumn<DestinationDTO, String> gradeColumn;


    public void initialize(URL location, ResourceBundle resources) {
        engine = webView.getEngine();
        getLatLng();
        engine.load("http://localhost:8081/nearby.html");
        comboBoxClassification.setItems(FXCollections.observableArrayList(
                "통합검색", "해수욕장", "휴양림", "관광지"));
        sortColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSortation()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDo() + " " + cellData.getValue().getCity() + " " + cellData.getValue().getAddress()));
        gradeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getScope())));
    }

    private ArrayList lists = new ArrayList(); //알림창에서 띄운 위경도 리스트
    //위경도 값 가져오기
    public void getLatLng() { //마커가 여러개 일때 alert()도 여러개 띄웠음 -> 배열 만들 때 참고
        engine.setOnAlert (event ->
        {
            String latlng = event.getData(); //위도 경도
            String lat = event.getData().split(" ")[0]; //위도
            String lng = event.getData().split(" ")[1]; //경도
            lists.add(latlng);
//            ArrayList<DestinationDTO> list = InquireToiletParkingDAO.inquireParkingLotByLocation(lat, lng , "1");
//            myTableView.getItems().addAll(list);
        });
    }
}