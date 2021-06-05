package FxController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.InquireToiletParkingDAO;
import DTO.ParkingLotsDTO;
import DTO.ToiletDTO;
import Network.Protocol;
import Network.clientMain;
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
            //clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_TOILET + "`" + lat + "`" + lng + "`" + distance);
            ArrayList<Object> objectList = new ArrayList<Object>();
            objectList.add(Protocol.PT_REQ_VIEW);
            objectList.add(Protocol.REQ_TOILET);
            objectList.add(lat);
            objectList.add(lng);
            objectList.add(distance);
            clientMain.writeObject(objectList);
            objectList.clear();

            while (true) {
            	ArrayList<Object> packet =(ArrayList<Object>) clientMain.readObject();
            	//String packetArr[] = packet.split("`");
            	String packetType = (String) packet.get(0);
            	String packetCode = (String) packet.get(1);
  			
            	if (packetType.equals(Protocol.PT_RES_VIEW)) {
            		switch (packetCode) {
            			case Protocol.RES_TOILET_Y: {
            				ArrayList<ToiletDTO> list = (ArrayList<ToiletDTO>) packet.get(2);
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
            			case Protocol.RES_TOILET_N: {
            				ShowAlert.showAlert("WARNING", "경고", "화장실 정보를 불러오는데 실패하였습니다.");
            				return;
            			}
            		}
            	}
            }
        });
    }
}
