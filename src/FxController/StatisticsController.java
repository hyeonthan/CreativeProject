package FxController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.DetailDAO;
import DAO.StatisticsDAO;
import DTO.DestinationDTO;
import DataSetControl.RecentInquiryData;
import Network.Protocol;
import Network.clientMain;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class StatisticsController implements Initializable {

    @FXML private ComboBox<String> comboBoxSortation;
    @FXML private ComboBox<String> comboBoxStatistics;
    @FXML private TableView<DestinationDTO> myTableView;
    @FXML private TableColumn<DestinationDTO, String> sortationColumn;
    @FXML private TableColumn<DestinationDTO, String> nameColumn;
    private String userId;

    //  넘어온 userId 받기
    public void setSaveUserId(String userId){
        this.userId = userId;
    }

    public void initialize(URL location, ResourceBundle resources) {
        comboBoxSortation.setItems(FXCollections.observableArrayList(
                "통합검색", "해수욕장", "휴양림", "관광지"));
        comboBoxStatistics.setItems(FXCollections.observableArrayList(
            "조회수", "별점", "출신지", "리뷰수"));
        sortationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSortation()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
    }
    @FXML
    public void handleBtnSearch(ActionEvent event){
        ObservableList<DestinationDTO> items = myTableView.getItems();
        //  테이블이 비어있지 않으면 INDEX 2의 컬럼을 지움 -> 기준마다 새로운 컬럼을 부여하기 위함.
        if(items.isEmpty() == false){
            myTableView.getColumns().remove(2);
        }
        myTableView.getItems().clear();
        if(comboBoxSortation.getValue() == null){
            ShowAlert.showAlert("WARNING", "경고창", "구분을 선택하시오!");
            return;
        }
        if(comboBoxStatistics.getValue() == null){
            ShowAlert.showAlert("WARNING", "경고창", "기준을 선택하시오!");
            return;
        }
        String sortation = comboBoxSortation.getValue();
        String statistics = comboBoxStatistics.getValue();
        
        //  통계 DAO 메모리 할당
        StatisticsDAO statisticsDAO = new StatisticsDAO();
        //  list 메모리 할당
        ArrayList<DestinationDTO> list = null;
        //  테이블 컬럼 변수
        TableColumn<DestinationDTO, Number> tbViews;
        TableColumn<DestinationDTO, Number> tbScope;
        TableColumn<DestinationDTO, String> tbBirth;
        TableColumn<DestinationDTO, Number> tbReviewCount;
        if(sortation.equals("통합검색")){
            if(statistics.equals("조회수")){
            	clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_STATISTICS + "`" + " " + "`" + "조회수");
        		
        		while (true) {
        			String packet = clientMain.readPacket();
        			String packetArr[] = packet.split("`");
        			String packetType = packetArr[0];
        			String packetCode = packetArr[1];
        			
        			if (packetType.equals(Protocol.PT_RES_VIEW)) {
        				switch (packetCode) {
        					case Protocol.RES_STATISTICS_Y: {
        						try {
        							list = (ArrayList<DestinationDTO>)clientMain.readObject();
        			                //  조회수 컬럼 동적 생성
        			                tbViews = new TableColumn<DestinationDTO, Number>("조회수");
        			                tbViews.setPrefWidth(115);
        			                tbViews.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getViews()));
        			                myTableView.getColumns().add(tbViews);
        			                System.out.println("aaaA" + list.get(0).getBeach_code());
        			                System.out.println("aaaA" + list.get(0).getForestLodge_code());
        			                System.out.println("aaaA" + list.get(0).getTouristSpot_code());
        			                myTableView.getItems().addAll(list);
        						} catch (Exception e) {
        							e.printStackTrace();
        						}
        						return;
        					}
        					case Protocol.RES_STATISTICS_N: {
        						ShowAlert.showAlert("WARNING", "경고", "통계 조회에 실패하였습니다.");
        						return;
        					}
        				}
        			}
        		}
            }
            else if (statistics.equals("별점")){
            	clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_STATISTICS + "`" + " " + "`" + "별점");
        		
        		while (true) {
        			String packet = clientMain.readPacket();
        			String packetArr[] = packet.split("`");
        			String packetType = packetArr[0];
        			String packetCode = packetArr[1];
        			
        			if (packetType.equals(Protocol.PT_RES_VIEW)) {
        				switch (packetCode) {
        					case Protocol.RES_STATISTICS_Y: {
        						try {
        							list = (ArrayList<DestinationDTO>)clientMain.readObject();
        			                //  별점 컬럼 동적 생성
        			                tbScope = new TableColumn<DestinationDTO, Number>("별점");
        			                tbScope.setPrefWidth(115);
        			                tbScope.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getScope()));
        			                myTableView.getColumns().add(tbScope);
        			                myTableView.getItems().addAll(list);
        						} catch (Exception e) {
        							e.printStackTrace();
        						}
        						return;
        					}
        					case Protocol.RES_STATISTICS_N: {
        						ShowAlert.showAlert("WARNING", "경고", "통계 조회에 실패하였습니다.");
        						return;
        					}
        				}
        			}
        		}
            }
            else if(statistics.equals("출신지")){
            	clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_STATISTICS + "`" + " " + "`" + "출신지");
        		
        		while (true) {
        			String packet = clientMain.readPacket();
        			String packetArr[] = packet.split("`");
        			String packetType = packetArr[0];
        			String packetCode = packetArr[1];
        			
        			if (packetType.equals(Protocol.PT_RES_VIEW)) {
        				switch (packetCode) {
        					case Protocol.RES_STATISTICS_Y: {
        						try {
        							list = (ArrayList<DestinationDTO>)clientMain.readObject();
        			                //  출신지 컬럼 동적 생성
        			                tbBirth = new TableColumn<DestinationDTO, String>("출신지");
        			                tbBirth.setPrefWidth(115);
        			                tbBirth.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDo()));
        			                myTableView.getColumns().add(tbBirth);
        			                myTableView.getItems().addAll(list);
        						} catch (Exception e) {
        							e.printStackTrace();
        						}
        						return;
        					}
        					case Protocol.RES_STATISTICS_N: {
        						ShowAlert.showAlert("WARNING", "경고", "통계 조회에 실패하였습니다.");
        						return;
        					}
        				}
        			}
        		}
            }
            else if(statistics.equals("리뷰수")){
            	clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_STATISTICS + "`" + " " + "`" + "리뷰수");
        		
        		while (true) {
        			String packet = clientMain.readPacket();
        			String packetArr[] = packet.split("`");
        			String packetType = packetArr[0];
        			String packetCode = packetArr[1];
        			
        			if (packetType.equals(Protocol.PT_RES_VIEW)) {
        				switch (packetCode) {
        					case Protocol.RES_STATISTICS_Y: {
        						try {
        							list = (ArrayList<DestinationDTO>)clientMain.readObject();
        			                //  리뷰수 컬럼 동적 생성
        			                tbReviewCount = new TableColumn<DestinationDTO, Number>("리뷰수");
        			                tbReviewCount.setPrefWidth(115);
        			                tbReviewCount.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCount()));
        			                myTableView.getColumns().add(tbReviewCount);
        			                myTableView.getItems().addAll(list);
        						} catch (Exception e) {
        							e.printStackTrace();
        						}
        						return;
        					}
        					case Protocol.RES_STATISTICS_N: {
        						ShowAlert.showAlert("WARNING", "경고", "통계 조회에 실패하였습니다.");
        						return;
        					}
        				}
        			}
        		}
            }
        }
        else {
            if(statistics.equals("조회수")){
            	clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_STATISTICS + "`" + sortation + "`" + "조회수");
        		
        		while (true) {
        			String packet = clientMain.readPacket();
        			String packetArr[] = packet.split("`");
        			String packetType = packetArr[0];
        			String packetCode = packetArr[1];
        			
        			if (packetType.equals(Protocol.PT_RES_VIEW)) {
        				switch (packetCode) {
        					case Protocol.RES_STATISTICS_Y: {
        						try {
        							list = (ArrayList<DestinationDTO>)clientMain.readObject();
        			                //  조회수 컬럼 동적 생성
        			                tbViews = new TableColumn<DestinationDTO, Number>("조회수");
        			                tbViews.setPrefWidth(115);
        			                tbViews.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getViews()));
        			                myTableView.getColumns().add(tbViews);
        			                myTableView.getItems().addAll(list);
        						} catch (Exception e) {
        							e.printStackTrace();
        						}
        						return;
        					}
        					case Protocol.RES_STATISTICS_N: {
        						ShowAlert.showAlert("WARNING", "경고", "통계 조회에 실패하였습니다.");
        						return;
        					}
        				}
        			}
        		}
            }
            else if (statistics.equals("별점")){
            	clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_STATISTICS + "`" + sortation + "`" + "별점");
        		
        		while (true) {
        			String packet = clientMain.readPacket();
        			String packetArr[] = packet.split("`");
        			String packetType = packetArr[0];
        			String packetCode = packetArr[1];
        			
        			if (packetType.equals(Protocol.PT_RES_VIEW)) {
        				switch (packetCode) {
        					case Protocol.RES_STATISTICS_Y: {
        						try {
        							list = (ArrayList<DestinationDTO>)clientMain.readObject();
        			                //  별점 컬럼 동적 생성
        			                tbScope = new TableColumn<DestinationDTO, Number>("별점");
        			                tbScope.setPrefWidth(115);
        			                tbScope.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getScope()));
        			                myTableView.getColumns().add(tbScope);
        			                myTableView.getItems().addAll(list);
        						} catch (Exception e) {
        							e.printStackTrace();
        						}
        						return;
        					}
        					case Protocol.RES_STATISTICS_N: {
        						ShowAlert.showAlert("WARNING", "경고", "통계 조회에 실패하였습니다.");
        						return;
        					}
        				}
        			}
        		}
            }
            else if(statistics.equals("출신지")){
            	clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_STATISTICS + "`" + sortation + "`" + "출신지");
        		
        		while (true) {
        			String packet = clientMain.readPacket();
        			String packetArr[] = packet.split("`");
        			String packetType = packetArr[0];
        			String packetCode = packetArr[1];
        			
        			if (packetType.equals(Protocol.PT_RES_VIEW)) {
        				switch (packetCode) {
        					case Protocol.RES_STATISTICS_Y: {
        						try {
        							list = (ArrayList<DestinationDTO>)clientMain.readObject();
        			                //  출신지 컬럼 동적 생성
        			                tbBirth = new TableColumn<DestinationDTO, String>("출신지");
        			                tbBirth.setPrefWidth(115);
        			                tbBirth.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDo()));
        			                myTableView.getColumns().add(tbBirth);
        			                myTableView.getItems().addAll(list);
        						} catch (Exception e) {
        							e.printStackTrace();
        						}
        						return;
        					}
        					case Protocol.RES_STATISTICS_N: {
        						ShowAlert.showAlert("WARNING", "경고", "통계 조회에 실패하였습니다.");
        						return;
        					}
        				}
        			}
        		}
            }
            else if(statistics.equals("리뷰수")){
            	clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_STATISTICS + "`" + sortation + "`" + "리뷰수");
        		
        		while (true) {
        			String packet = clientMain.readPacket();
        			String packetArr[] = packet.split("`");
        			String packetType = packetArr[0];
        			String packetCode = packetArr[1];
        			
        			if (packetType.equals(Protocol.PT_RES_VIEW)) {
        				switch (packetCode) {
        					case Protocol.RES_STATISTICS_Y: {
        						try {
        							list = (ArrayList<DestinationDTO>)clientMain.readObject();
        			                //  리뷰수 컬럼 동적 생성
        			                tbReviewCount = new TableColumn<DestinationDTO, Number>("리뷰수");
        			                tbReviewCount.setPrefWidth(115);
        			                tbReviewCount.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCount()));
        			                myTableView.getColumns().add(tbReviewCount);
        			                myTableView.getItems().addAll(list);
        						} catch (Exception e) {
        							e.printStackTrace();
        						}
        						return;
        					}
        					case Protocol.RES_STATISTICS_N: {
        						ShowAlert.showAlert("WARNING", "경고", "통계 조회에 실패하였습니다.");
        						return;
        					}
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
//                        beachDetailController.setBeachCode(beachCode);
//                        beachDetailController.setSaveUserId(userId);
                        destinationCode = myTableView.getSelectionModel().getSelectedItem().getCode();
                        destinationName = myTableView.getSelectionModel().getSelectedItem().getName();
//                        beachDetailController.setDestinationCode(destinationCode);
//                        beachDetailController.setDestinationName(destinationName);
                        System.out.println(beachCode + "/" + userId + "/" + destinationCode + "/" + destinationName);
                        beachDetailController.setBeachDetail(beachCode, userId, destinationCode, destinationName);
                    }
                    else if(myTableView.getSelectionModel().getSelectedItem().getSortation().equals("휴양림")){
                        String forestCode= myTableView.getSelectionModel().getSelectedItem().getForestLodge_code();
                        ForestLodgeDetailController forestLodgeDetailController = loader.<ForestLodgeDetailController>getController();
//                        forestLodgeDetailController.setForestLodgeCode(forestCode);
//                        forestLodgeDetailController.setSaveUserId(userId);
                        destinationCode = myTableView.getSelectionModel().getSelectedItem().getCode();
                        destinationName = myTableView.getSelectionModel().getSelectedItem().getName();
//                        forestLodgeDetailController.setDestinationCode(destinationCode);
//                        forestLodgeDetailController.setDestinationName(destinationName);
                        System.out.println(forestCode + "/" + userId + "/" + destinationCode + "/" + destinationName);
                        forestLodgeDetailController.setForestDetail(forestCode,userId,destinationCode,destinationName);
                    }
                    else if(myTableView.getSelectionModel().getSelectedItem().getSortation().equals("관광지")){
                        String touristCode= myTableView.getSelectionModel().getSelectedItem().getTouristSpot_code();
                        TouristSpotDetailController touristSpotDetailController = loader.<TouristSpotDetailController>getController();
//                        touristSpotDetailController.setTouristCode(touristCode);
//                        touristSpotDetailController.setSaveUserId(userId);
                        destinationCode = myTableView.getSelectionModel().getSelectedItem().getCode();
                        destinationName = myTableView.getSelectionModel().getSelectedItem().getName();
//                        touristSpotDetailController.setDestinationCode(destinationCode);
//                        touristSpotDetailController.setDestinationName(destinationName);
                        System.out.println(touristCode + "/" + userId + "/" + destinationCode + "/" + destinationName);
                        touristSpotDetailController.setTouristDetail(touristCode,userId,destinationCode,destinationName);
                    }
                    //  상세정보 클릭시 조회수 증가0
                    clientMain.writePacket(Protocol.PT_REQ_RENEWAL + "`" + Protocol.REQ_UPDATE_VIEWSCOUNT+ "`" + destinationCode);
          		
                    while (true) {
                    	String packet = clientMain.readPacket();
                    	String packetArr[] = packet.split("`");
                    	String packetType = packetArr[0];
                    	String packetCode = packetArr[1];
          			
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
//                    DetailDAO detailDAO = new DetailDAO();
//                    detailDAO.viewsCountIncrease(destinationCode);
                    //  최근 조회 리스트 추가
//                    RecentInquiryData.setRecentList(userId, myTableView.getSelectionModel().getSelectedItem());
//                    stage.showAndWait();
                    
                }
                catch(Exception e) {
                    System.out.println(e);
                }
            }

        }

    }
}