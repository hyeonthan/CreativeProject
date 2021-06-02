package FxController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.InquireToiletParkingDAO;
import DTO.ToiletDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ToiletController implements Initializable {

    @FXML
    private WebView webView;
    private WebEngine engine;
    @FXML private TextField tfDistance;
    @FXML
    private TableView<ToiletDTO> myTableView;
    @FXML
    private TableColumn<ToiletDTO, String> sortColumn;
    @FXML
    private TableColumn<ToiletDTO, String> nameColumn;
    @FXML
    private TableColumn<ToiletDTO, String> addressColumn;
    @FXML
    private TableColumn<ToiletDTO, String> PublicColumn;
    @FXML
    private TableColumn<ToiletDTO, String> opening_timeColumn;
    @FXML
    private TableColumn<ToiletDTO, String> managementAgencyColumn;

    

    public void initialize(URL location, ResourceBundle resources) {
        engine = webView.getEngine();
        getLatLng();
        engine.load("http://localhost:8081/map.html");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDo() + " " + cellData.getValue().getCity() + " " + cellData.getValue().getAddress()));
        PublicColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublic()));
        opening_timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpening_time()));
        managementAgencyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getManagementAgency()));
    }

    public void getLatLng() { //위경도 값 가져오기
        engine.setOnAlert(event ->
        {
            String distance = tfDistance.getText();
            if(distance.equals("")){
                ShowAlert.showAlert("WARNING", "경고창", "거리를 입력해주세요");
                return;
            }
            
            try{
              int checkDistance = Integer.parseInt(tfDistance.getText());
            }catch(Exception e){
                e.printStackTrace();
                ShowAlert.showAlert("WARNING", "경고창", "정수만 입력해주세요");
                return;
            }
            String lat = event.getData().split(" ")[0];
            String lng = event.getData().split(" ")[1];
            ArrayList<ToiletDTO> list = InquireToiletParkingDAO.inquireToiletByLocation(lat, lng , distance);
            myTableView.getItems().clear();
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
