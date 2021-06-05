package FxController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DTO.ParkingLotsDTO;
import DTO.ToiletDTO;
import Network.Protocol;
import Network.clientMain;
import DAO.InquireToiletParkingDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ParkingController implements Initializable {

    @FXML
    private WebView webView;
    private WebEngine engine;
    @FXML private TextField tfDistance;
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

        //    clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_PARKING + "`" + lat + "`" + lng + "`" + distance);

            ArrayList<Object> objectList = new ArrayList<Object>();
            objectList.add(Protocol.PT_REQ_VIEW);
            objectList.add(Protocol.REQ_PARKING);
            objectList.add(lat);
            objectList.add(lng);
            objectList.add(distance);
            clientMain.writeObject(objectList);
            objectList.clear();

            while (true) {
            	ArrayList<Object> packet = (ArrayList<Object>) clientMain.readObject();
            	//String packetArr[] = packet.split("`");
            	String packetType =(String) packet.get(0);
            	String packetCode =(String) packet.get(1);
  			
            	if (packetType.equals(Protocol.PT_RES_VIEW)) {
            		switch (packetCode) {
            			case Protocol.RES_PARKING_Y: {
            				ArrayList<ParkingLotsDTO> list = (ArrayList<ParkingLotsDTO>)packet.get(2);
            				System.out.println(list.get(0).getAddress());
            	            myTableView.getItems().clear();
            	            myTableView.getItems().addAll(list);
            	            try {
            	                System.out.println(lat);
            	                System.out.println(lng);
            	            } catch (Exception e) {
            	                e.printStackTrace();
            	            }
            				return;
            			}
            			case Protocol.RES_PARKING_N: {
            				ShowAlert.showAlert("WARNING", "경고", "주차장 정보를 불러오는데 실패하였습니다.");
            				return;
            			}
            		}
            	}
            }
        });
    }
}