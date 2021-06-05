package FxController;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.DetailDAO;
import DAO.InquireByLocationDAO;
import DTO.DestinationDTO;
import DataSetControl.RecentInquiryData;
import Network.Protocol;
import Network.clientMain;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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
    @FXML private TextField tfDistance;
    private String userId;

    //  넘어온 userId 받기
    public void setSaveUserId(String userId){
        this.userId = userId;
    }

    //  위경도 저장하는 리스트
    private ArrayList<String> list;

    public void initialize(URL location, ResourceBundle resources) {
        list = new ArrayList<String>(); //알림창에서 띄운 위경도 리스트
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
    //위경도 값 가져오기
    public void getLatLng() { //마커가 여러개 일때 alert()도 여러개 띄웠음 -> 배열 만들 때 참고
        engine.setOnAlert (event ->
        {
            String latlng = event.getData(); //위도 경도
            //String lat = event.getData().split(" ")[0]; //위도
            //String lng = event.getData().split(" ")[1]; //경도
            list.add(latlng);
        });
    }
    //  검색 버튼 기능
    @FXML
    public void handleBtnSearch(ActionEvent event){
        myTableView.getItems().clear();
        if(comboBoxClassification.getValue() == null){
            ShowAlert.showAlert("WARNING", "경고창", "구분을 선택해주세요!");
            return;
        }
        if(tfDistance.getText().equals("")){
            ShowAlert.showAlert("WARNING", "경고창", "거리를 입력해주세요!");
            return;
        }
        if(list.isEmpty() == true){
            ShowAlert.showAlert("WARNING", "경고창", "지도에 좌표를 추가해주세요!");
            return;
        }
        double lat = 0, lng = 0;
        String distance = tfDistance.getText();
        //  선택한 사람 수 만큼 나눠서 중간지점 찾기
        for(int i = 0; i < list.size(); i++){
            String latlng = list.get(i);
            lat += Double.parseDouble(latlng.split(" ")[0]);
            lng += Double.parseDouble(latlng.split(" ")[1]);
        }
        lat /= list.size();
        lng /= list.size();
        System.out.println(lat + " " + lng);
        
        InquireByLocationDAO inquireByLocationDAO = new InquireByLocationDAO();
        
        String sortation = comboBoxClassification.getValue();
        ArrayList<DestinationDTO> dtos = null;
        if(sortation.equals("통합검색")){
            //clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_DESTINATION_LOCATION + "`" + " " + "`" + Double.toString(lat) + "`" + Double.toString(lng) + "`" + distance);
            ArrayList<Object> objectList = new ArrayList<Object>();
            objectList.add(Protocol.PT_REQ_VIEW);
            objectList.add(Protocol.REQ_DESTINATION_LOCATION);
            objectList.add(" ");
            objectList.add(Double.toString(lat));
            objectList.add(Double.toString(lng));
            objectList.add(distance);
            clientMain.writeObject(objectList);
            objectList.clear();

    		while (true) {
    			ArrayList<Object> packet =(ArrayList<Object>) clientMain.readObject();
    			System.out.println(packet+".");
    			//String packetArr[] = packet.split("`");
    			String packetType =(String) packet.get(0);
    			String packetCode =(String) packet.get(1);
    			
    			if (packetType.equals(Protocol.PT_RES_VIEW)) {
    				switch (packetCode) {
    					case Protocol.RES_DESTINATION_LOCATION_Y: {
    						try {
    							dtos = (ArrayList<DestinationDTO>)packet.get(2);
    					        myTableView.getItems().addAll(dtos);
                                engine.executeScript("loadMarker('33.311500250000000', '126.458761000000000', 1)");
                            } catch (Exception e) {
    							e.printStackTrace();
    						}
    						return;
    					}
    					case Protocol.RES_DESTINATION_LOCATION_N: {
    						ShowAlert.showAlert("WARNING", "경고", "주변 검색에 실패하였습니다.");
    						return;
    					}
    				}
    			}
    		}
        }
        else if(sortation.equals("해수욕장")){
        	//clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_DESTINATION_LOCATION + "`" + "해수욕장" + "`" + Double.toString(lat) + "`" + Double.toString(lng) + "`" + distance);
            ArrayList<Object> objectList = new ArrayList<Object>();
            objectList.add(Protocol.PT_REQ_VIEW);
            objectList.add(Protocol.REQ_DESTINATION_LOCATION);
            objectList.add("해수욕장");
            objectList.add(Double.toString(lat));
            objectList.add(Double.toString(lng));
            objectList.add(distance);
            clientMain.writeObject(objectList);
            objectList.clear();

    		while (true) {
    			ArrayList<Object> packet = (ArrayList<Object>) clientMain.readObject();
    			//String packetArr[] = packet.split("`");
    			String packetType = (String) packet.get(0);
    			String packetCode = (String) packet.get(1);
    			
    			if (packetType.equals(Protocol.PT_RES_VIEW)) {
    				switch (packetCode) {
    					case Protocol.RES_DESTINATION_LOCATION_Y: {
    						try {
    							dtos = (ArrayList<DestinationDTO>)packet.get(2);
    					        myTableView.getItems().addAll(dtos);
    						} catch (Exception e) {
    							e.printStackTrace();
    						}
    						return;
    					}
    					case Protocol.RES_DESTINATION_LOCATION_N: {
    						ShowAlert.showAlert("WARNING", "경고", "주변 검색에 실패하였습니다.");
    						return;
    					}
    				}
    			}
    		}
        }
        else if(sortation.equals("휴양림")){
        	//clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_DESTINATION_LOCATION + "`" + "휴양림" + "`" + Double.toString(lat) + "`" + Double.toString(lng) + "`" + distance);
            ArrayList<Object> objectList = new ArrayList<Object>();
            objectList.add(Protocol.PT_REQ_VIEW);
            objectList.add(Protocol.REQ_DESTINATION_LOCATION);
            objectList.add("휴양림");
            objectList.add(Double.toString(lat));
            objectList.add(Double.toString(lng));
            objectList.add(distance);
            clientMain.writeObject(objectList);
            objectList.clear();

    		while (true) {
    			ArrayList<Object> packet = (ArrayList<Object>) clientMain.readObject();
    			//String packetArr[] = packet.split("`");
    			String packetType = (String) packet.get(0);
    			String packetCode = (String) packet.get(1);
    			
    			if (packetType.equals(Protocol.PT_RES_VIEW)) {
    				switch (packetCode) {
    					case Protocol.RES_DESTINATION_LOCATION_Y: {
    						try {
    							dtos = (ArrayList<DestinationDTO>)packet.get(2);
    					        myTableView.getItems().addAll(dtos);
    						} catch (Exception e) {
    							e.printStackTrace();
    						}
    						return;
    					}
    					case Protocol.RES_DESTINATION_LOCATION_N: {
    						ShowAlert.showAlert("WARNING", "경고", "주변 검색에 실패하였습니다.");
    						return;
    					}
    				}
    			}
    		}
        }
        else if(sortation.equals("관광지")){
        	//clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_DESTINATION_LOCATION + "`" + "관광지" + "`" + Double.toString(lat) + "`" + Double.toString(lng) + "`" + distance);
            ArrayList<Object> objectList = new ArrayList<Object>();
            objectList.add(Protocol.PT_REQ_VIEW);
            objectList.add(Protocol.REQ_DESTINATION_LOCATION);
            objectList.add("관광지");
            objectList.add(Double.toString(lat));
            objectList.add(Double.toString(lng));
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
    					case Protocol.RES_DESTINATION_LOCATION_Y: {
    						try {
    							dtos = (ArrayList<DestinationDTO>)packet.get(2);
    					        myTableView.getItems().addAll(dtos);
    						} catch (Exception e) {
    							e.printStackTrace();
    						}
    						return;
    					}
    					case Protocol.RES_DESTINATION_LOCATION_N: {
    						ShowAlert.showAlert("WARNING", "경고", "주변 검색에 실패하였습니다.");
    						return;
    					}
    				}
    			}
    		}
        }
    }

     //  더블 클릭시 해당 여행지 상세정보
     @FXML
     public void doubleClickMouse(MouseEvent event){
         if(myTableView.getSelectionModel().getSelectedItem()!=null){
             if(event.getClickCount() > 1){
                 try {
                     FXMLLoader loader = null;
                     String destinationCode="", destinationName="";
                     if(myTableView.getSelectionModel().getSelectedItem().getSortation().equals("해수욕장")){
                         loader = new FXMLLoader(getClass().getResource("../FXML/beach_detail.fxml"));
                     }
                     if(myTableView.getSelectionModel().getSelectedItem().getSortation().equals("휴양림")){
                         loader = new FXMLLoader(getClass().getResource("../FXML/forest_lodge_detail.fxml"));
                     }
                     if(myTableView.getSelectionModel().getSelectedItem().getSortation().equals("관광지")){
                         loader = new FXMLLoader(getClass().getResource("../FXML/tourist_spot_detail.fxml"));
                     }
                     Parent root = (Parent)loader.load();
                     Stage stage = new Stage();
                     stage.setScene(new Scene(root));
                     if(myTableView.getSelectionModel().getSelectedItem().getSortation().equals("해수욕장")){
                         //  해수욕장 상세정보로 code, userId 넘기기
                         String beachCode = myTableView.getSelectionModel().getSelectedItem().getBeach_code();
                         BeachDetailController beachDetailController = loader.<BeachDetailController>getController();
//                         beachDetailController.setBeachCode(beachCode);
//                         beachDetailController.setSaveUserId(userId);
                         destinationCode = myTableView.getSelectionModel().getSelectedItem().getCode();
                         destinationName = myTableView.getSelectionModel().getSelectedItem().getName();
//                         beachDetailController.setDestinationCode(destinationCode);
//                         beachDetailController.setDestinationName(destinationName);
                         beachDetailController.setBeachDetail(beachCode, userId, destinationCode, destinationName);
                     }
                     else if(myTableView.getSelectionModel().getSelectedItem().getSortation().equals("휴양림")){
                         String forestCode= myTableView.getSelectionModel().getSelectedItem().getForestLodge_code();
                         ForestLodgeDetailController forestLodgeDetailController = loader.<ForestLodgeDetailController>getController();
//                         forestLodgeDetailController.setForestLodgeCode(forestCode);
//                         forestLodgeDetailController.setSaveUserId(userId);
                         destinationCode = myTableView.getSelectionModel().getSelectedItem().getCode();
                         destinationName = myTableView.getSelectionModel().getSelectedItem().getName();
//                         forestLodgeDetailController.setDestinationCode(destinationCode);
//                         forestLodgeDetailController.setDestinationName(destinationName);
                         forestLodgeDetailController.setForestDetail(forestCode,userId,destinationCode,destinationName);
                     }
                     else if(myTableView.getSelectionModel().getSelectedItem().getSortation().equals("관광지")){
                         String touristCode= myTableView.getSelectionModel().getSelectedItem().getTouristSpot_code();
                         TouristSpotDetailController touristSpotDetailController = loader.<TouristSpotDetailController>getController();
//                         touristSpotDetailController.setTouristCode(touristCode);
//                         touristSpotDetailController.setSaveUserId(userId);
                         destinationCode = myTableView.getSelectionModel().getSelectedItem().getCode();
                         destinationName = myTableView.getSelectionModel().getSelectedItem().getName();
//                         touristSpotDetailController.setDestinationCode(destinationCode);
//                         touristSpotDetailController.setDestinationName(destinationName);
                         touristSpotDetailController.setTouristDetail(touristCode,userId,destinationCode,destinationName);
                     }
                     //  상세정보 클릭시 조회수 증가0
 //                  clientMain.writePacket(Protocol.PT_REQ_RENEWAL + "`" + Protocol.REQ_UPDATE_VIEWSCOUNT+ "`" + destinationCode);
 //          		
 //          		while (true) {
 //          			String packet = clientMain.readPacket();
 //          			String packetArr[] = packet.split("`");
 //          			String packetType = packetArr[0];
 //          			String packetCode = packetArr[1];
 //          			
 //          			if (packetType.equals(Protocol.PT_RES_RENEWAL)) {
 //          				switch (packetCode) {
 //          					case Protocol.RES_UPDATE_VIEWSCOUNT_Y: {
 //          						return;
 //          					}
 //          					case Protocol.RES_UPDATE_VIEWSCOUNT_N: {
 //          						ShowAlert.showAlert("WARNING", "경고", "조회수 증가 오류.");
 //          						return;
 //          					}
 //          				}
 //          			}
 //          		}
                     //  상세정보 클릭시 조회수 증가0
                    // clientMain.writePacket(Protocol.PT_REQ_RENEWAL + "`" + Protocol.REQ_UPDATE_VIEWSCOUNT+ "`" + destinationCode);
                     ArrayList<Object> objectList = new ArrayList<Object>();
                     objectList.add(Protocol.PT_REQ_RENEWAL);
                     objectList.add(Protocol.REQ_UPDATE_VIEWSCOUNT);
                     objectList.add(destinationCode);
                     clientMain.writeObject(objectList);
                     objectList.clear();

                     while (true) {
                     	ArrayList<Object> packet =(ArrayList<Object>) clientMain.readObject();
                     	//String packetArr[] = packet.split("`");
                     	String packetType = (String) packet.get(0);
                     	String packetCode = (String) packet.get(1);
           			
                     	if (packetType.equals(Protocol.PT_RES_RENEWAL)) {
                     		switch (packetCode) {
                     			case Protocol.RES_UPDATE_VIEWSCOUNT_Y: {
                     				RecentInquiryData.setRecentList(userId, myTableView.getSelectionModel().getSelectedItem());
                     				stage.showAndWait();
                     				return;
                     			}
                     			case Protocol.RES_UPDATE_VIEWSCOUNT_N: {
                     				ShowAlert.showAlert("WARNING", "경고", "조회수 증가 오류.");
                     				return;
                     			}
                     		}
                     	}
                     }
//                     DetailDAO detailDAO = new DetailDAO();
//                     detailDAO.viewsCountIncrease(destinationCode);
//                     //  최근 조회 리스트 추가
//                     RecentInquiryData.setRecentList(userId, myTableView.getSelectionModel().getSelectedItem());
//                     stage.showAndWait();
                     
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
             }
 
         }
 
     }
}