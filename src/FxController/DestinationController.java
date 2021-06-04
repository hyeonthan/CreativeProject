package FxController;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import DAO.DetailDAO;
import DAO.InquireByRegionDAO;
import DTO.DestinationDTO;
import DataSetControl.RecentInquiryData;
import DataSetControl.RegionList;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DestinationController implements Initializable{
    @FXML private ComboBox<String> comboBoxClassification;
    @FXML private ComboBox<String> comboBoxDo;
    @FXML private ComboBox<String> comboBoxCity;
    @FXML private TableView<DestinationDTO> myTableView;
	@FXML private TableColumn<DestinationDTO, String> sortColumn;
	@FXML private TableColumn<DestinationDTO, String> nameColumn;
	@FXML private TableColumn<DestinationDTO, String> addressColumn;
	@FXML private TableColumn<DestinationDTO, String> gradeColumn;

    private String userId;
    public void setSaveUserId(String userId){
		this.userId = userId;
	}
    @Override
	public void initialize(URL location, ResourceBundle bundle) {
        //  RegionList class에서 Do 호출
        String[] DoList = RegionList.Do;
        comboBoxDo.getItems().addAll(DoList);
        comboBoxClassification.setItems(FXCollections.observableArrayList(
				"통합검색", "해수욕장", "휴양림", "관광지"));
        sortColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSortation()));
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDo() + " " + cellData.getValue().getCity() + " " + cellData.getValue().getAddress()));
		gradeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getScope())));
	}

    //  도 선택 시 시/군 추가
    @FXML
    public void handleCbBoxDo(ActionEvent event) {
        comboBoxCity.getItems().clear();
        String Do = comboBoxDo.getValue();
        // 특별시, 광역시인 경우 제외
        if(!(Do.equals("서울특별시") || Do.equals("울산광역시") || Do.equals("인천광역시") || Do.equals("대전광역시") || Do.equals("제주특별자치도") || Do.equals("대구광역시") || Do.equals("부산광역시"))){
            //  RegionList class에서 hsMap 호출
            HashMap<String, String[]> hsMap = RegionList.hsMap;
            String[] cityList = hsMap.get(Do);
            comboBoxCity.getItems().addAll(cityList);
        }
    }
    //  검색 버튼 event
    @FXML
	public void handleBtnSearch(ActionEvent event) {
        myTableView.getItems().clear();
        ArrayList<DestinationDTO> list = null;
        InquireByRegionDAO inquireByRegionDAO = new InquireByRegionDAO();
        String sortation = " ", Do = " ", city = " ";
        if(comboBoxDo.getValue() == null){
            ShowAlert.showAlert("WARNING", "경고", "도 미선택");
            return;
        }
        else if(comboBoxCity.getValue() == null && !(comboBoxDo.getValue().equals("서울특별시") || comboBoxDo.getValue().equals("인천광역시") || comboBoxDo.getValue().equals("울산광역시") 
        || comboBoxDo.getValue().equals("대전광역시") || comboBoxDo.getValue().equals("대구광역시") || comboBoxDo.getValue().equals("제주특별자치도") || comboBoxDo.getValue().equals("부산광역시"))){
            ShowAlert.showAlert("WARNING", "경고", "시/군 미선택");
            return;
        }
        //  구분 미선택 조회
        if(comboBoxClassification.getValue() == null || comboBoxClassification.getValue().equals("통합")){
            Do = comboBoxDo.getValue();
            if(Do.equals("서울특별시") || Do.equals("인천광역시") || Do.equals("울산광역시") || Do.equals("대전광역시") || Do.equals("대구광역시") || Do.equals("제주특별자치도") || Do.equals("부산광역시")){
                list = inquireByRegionDAO.inquireDestinationByRegion("", Do, "");
            }
            else{
                city = comboBoxCity.getValue();
                list = inquireByRegionDAO.inquireDestinationByRegion("", Do, city);
            }
        }
        //  구분 선택 조회
        else{
            sortation = comboBoxClassification.getValue();
            Do = comboBoxDo.getValue();
            if(Do.equals("서울특별시") || Do.equals("인천광역시") || Do.equals("울산광역시") || Do.equals("대전광역시") || Do.equals("대구광역시") || Do.equals("제주특별자치도") || Do.equals("부산광역시")){
                list = inquireByRegionDAO.inquireDestinationByRegion(sortation, Do, "");
            }
            else{
                city = comboBoxCity.getValue();
                list = inquireByRegionDAO.inquireDestinationByRegion(sortation, Do, city);
            }
        }
//        clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_DESTINATION_REGION + "`" + sortation + "`" + Do + "`" + city);
//		
//		while (true) {
//			String packet = clientMain.readPacket();
//			String packetArr[] = packet.split("`");
//			String packetType = packetArr[0];
//			String packetCode = packetArr[1];
//			
//			if (packetType.equals(Protocol.PT_RES_VIEW)) {
//				switch (packetCode) {
//					case Protocol.RES_DESTINATION_REGION_Y: {
//						try {
//							list = (ArrayList<DestinationDTO>)clientMain.readObject();
//					        myTableView.getItems().addAll(list);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//						return;
//					}
//					case Protocol.RES_DESTINATION_REGION_N: {
//						ShowAlert.showAlert("WARNING", "경고", "검색 결과가 없습니다.");
//						return;
//					}
//				}
//			}
//		}
        if(list == null){
            ShowAlert.showAlert("INFORMATION", "알림창", "검색 결과가 없습니다.");
            return;
        }
        myTableView.getItems().addAll(list);
	}
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
                        beachDetailController.setBeachCode(beachCode);
                        beachDetailController.setSaveUserId(userId);
                        destinationCode = myTableView.getSelectionModel().getSelectedItem().getCode();
                        destinationName = myTableView.getSelectionModel().getSelectedItem().getName();
                        beachDetailController.setDestinationCode(destinationCode);
                        beachDetailController.setDestinationName(destinationName);
//                        beachDetailController.setBeachDetail(beachCode, userId, destinationCode, destinationName);
                    }
                    else if(myTableView.getSelectionModel().getSelectedItem().getSortation().equals("휴양림")){
                        String forestCode= myTableView.getSelectionModel().getSelectedItem().getForestLodge_code();
                        ForestLodgeDetailController forestLodgeDetailController = loader.<ForestLodgeDetailController>getController();
                        forestLodgeDetailController.setForestLodgeCode(forestCode);
                        forestLodgeDetailController.setSaveUserId(userId);
                        destinationCode = myTableView.getSelectionModel().getSelectedItem().getCode();
                        destinationName = myTableView.getSelectionModel().getSelectedItem().getName();
                        forestLodgeDetailController.setDestinationCode(destinationCode);
                        forestLodgeDetailController.setDestinationName(destinationName);
                        //forestLodgeDetailController.setForestDetail(forestCode,userId,destinationCode,destinationName);
                    }
                    else if(myTableView.getSelectionModel().getSelectedItem().getSortation().equals("관광지")){
                        String touristCode= myTableView.getSelectionModel().getSelectedItem().getTouristSpot_code();
                        TouristSpotDetailController touristSpotDetailController = loader.<TouristSpotDetailController>getController();
                        touristSpotDetailController.setTouristCode(touristCode);
                        touristSpotDetailController.setSaveUserId(userId);
                        destinationCode = myTableView.getSelectionModel().getSelectedItem().getCode();
                        destinationName = myTableView.getSelectionModel().getSelectedItem().getName();
                        touristSpotDetailController.setDestinationCode(destinationCode);
                        touristSpotDetailController.setDestinationName(destinationName);
                        //touristSpotDetailController.setForestDetail(touristCode,userId,destinationCode,destinationName);
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
                    DetailDAO detailDAO = new DetailDAO();
                    detailDAO.viewsCountIncrease(destinationCode);
                    //  최근 조회 리스트 추가
                    RecentInquiryData.setRecentList(userId, myTableView.getSelectionModel().getSelectedItem());
                    stage.showAndWait();
                    
                }
                catch(Exception e) {
                    System.out.println(e);
                }
            }
        }

    }
}
