package FxController;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import DAO.DetailDAO;
import DAO.MyPageDAO;
import DTO.*;
import DataSetControl.RecentInquiryData;
import DataSetControl.RegionList;
import Network.Protocol;
import Network.clientMain;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MypageController implements Initializable {
    @FXML
    TextField tfId;
    @FXML
    TextField tfName;
    @FXML
    TextField tfAge;
    @FXML
    ComboBox<String> cbBoxGender;
    @FXML
    TextField tfAddress;
    @FXML
    ComboBox<String> cbBoxDo;
    @FXML
    ComboBox<String> cbBoxCity;
    @FXML // 즐겨찾기 테이블
    public TableView<FavoriteDTO> tv_favorite;
    @FXML
    public TableColumn<FavoriteDTO, String> tc_favoriteType;
    @FXML
    public TableColumn<FavoriteDTO, String> tc_favoriteName;
    @FXML // 최근조회 테이블
    public TableView<DestinationDTO> tv_recent;
    @FXML
    public TableColumn<DestinationDTO, String> tc_recentType;
    @FXML
    public TableColumn<DestinationDTO, String> tc_recentName;
    @FXML // 리뷰 테이블
    public TableView<ReviewDTO> tv_review;
    @FXML
    public TableColumn<ReviewDTO, String> tc_reviewDate;
    @FXML
    public TableColumn<ReviewDTO, String> tc_reviewName;
    @FXML
    public TableColumn<ReviewDTO, String> tc_reviewContent;
    @FXML
    public Button btn_deleteFavorite;
    @FXML
    public Button btn_deleteReview;
    private String userId;

    //	로그인 정보 받은 후 초기화
    public void setUserInformation(String userId) {
        clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_MYPAGE);
        String packet = clientMain.readPacket();
        String packetArr[] = packet.split("`");
        String packetType = packetArr[0];
        String packetCode = packetArr[1];

        if (packetType.equals(Protocol.PT_RES_RENEWAL)) {
            switch (packetCode) {
				case Protocol.RES_MYPAGE_Y: {
					UserDTO userDTO = (UserDTO) clientMain.readObject();
					ArrayList<ReviewDTO> arrayList = (ArrayList<ReviewDTO>) clientMain.readObject();
					ArrayList<FavoriteDTO> arrayList1 = (ArrayList<FavoriteDTO>) clientMain.readObject();
					this.userId = userDTO.getId();

					tfId.setText(userDTO.getId());
					tfName.setText(userDTO.getName());
					tfAge.setText(Integer.toString(userDTO.getAge()));
					cbBoxGender.setValue(userDTO.getGender());
					cbBoxDo.setValue(userDTO.getDo());
					cbBoxCity.setValue(userDTO.getCity());
					tfAddress.setText(userDTO.getAddress());
					tfId.setDisable(true);
					tfName.setDisable(true);
					cbBoxDo.setDisable(true);
					cbBoxCity.setDisable(true);
					tfAge.setDisable(true);
					cbBoxGender.setDisable(true);
					tfAddress.setDisable(true);

					tv_review.getItems().clear();
					tv_review.getItems().addAll(arrayList);

					tv_favorite.getItems().clear();
					tv_favorite.getItems().addAll(arrayList1);
                    return;
                }
				case Protocol.RES_MYPAGE_N: {
                    ShowAlert.showAlert("WARNING", "경고", "개인정보 접근 실패");
                    return;
                }
            }
        }
    }

    //	즐겨찾기 리스트 불러오기
    public void setFavoriteList(String userId) {
        tv_favorite.getItems().clear();
        clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_FAVORITES);
        String packet = clientMain.readPacket();
        String packetArr[] = packet.split("`");
        String packetType = packetArr[0];
        String packetCode = packetArr[1];

        if (packetType.equals(Protocol.PT_RES_VIEW)) {
            switch (packetCode) {
                case Protocol.RES_FAVORITES_Y:{
                    ArrayList<FavoriteDTO> arrayList = (ArrayList<FavoriteDTO>) clientMain.readObject();
                    tv_favorite.getItems().addAll(arrayList);
                    return;
                }
                case Protocol.RES_FAVORITES_N:{
                    ShowAlert.showAlert("WARNING", "경고", "즐겨찾기 업데이트 실패");
                    return;
                }
            }
        }
    }

    //	내가 쓴 리뷰 리스트 불러오기
    public void setReviewList(String userId) {
        tv_review.getItems().clear();
        clientMain.writePacket(Protocol.PT_REQ_VIEW + "`" + Protocol.REQ_REVIEWS);
        String packet = clientMain.readPacket();
        String packetArr[] = packet.split("`");
        String packetType = packetArr[0];
        String packetCode = packetArr[1];

        if (packetType.equals(Protocol.PT_RES_VIEW)) {
            switch (packetCode) {
                case Protocol.RES_REVIEWS_Y:{
                    ArrayList<ReviewDTO> arrayList = (ArrayList<ReviewDTO>) clientMain.readObject();
                    tv_review.getItems().addAll(arrayList);
                    return;
                }
                case Protocol.RES_REVIEWS_N:{
                    ShowAlert.showAlert("WARNING", "경고", "리뷰 업데이트 실패");
                    return;
                }
            }
        }
    }

    //	최근 조회 리스트 불러오기
    public void setRecentList(String userId) {
        if (RecentInquiryData.getRecentList(userId) != null) {
            tv_recent.getItems().addAll(RecentInquiryData.getRecentList(userId));
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //	즐겨찾기 테이블 초기화
        tc_favoriteType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSortation()));
        tc_favoriteName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination_name()));
        //	리뷰 테이블 초기화
        tc_reviewDate.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue().getReporting_date()).toString()));
        tc_reviewName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination_name()));
        tc_reviewContent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContent()));
        //	최근 조회 테이블 초기화
        tc_recentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        tc_recentType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSortation()));

    }

    //	수정 버튼 클릭
    @FXML
    public void handleBtnModify(ActionEvent event) {
        ShowAlert.showAlert("INFORMATION", "알림창", "개인정보 수정(수정 후 등록 버튼을 눌러주세요)");
        tfName.setDisable(false);
        tfAge.setDisable(false);
        cbBoxGender.setDisable(false);
        cbBoxDo.setDisable(false);
        cbBoxCity.setDisable(false);
        tfAddress.setDisable(false);
        cbBoxGender.setItems(FXCollections.observableArrayList(
                "M", "F"));
        cbBoxDo.getItems().addAll(RegionList.Do);
    }

    //	수정 후 등록 버튼 클릭
    @FXML
    public void handleBtnReg(ActionEvent event) {
        if (tfName.getText().equals("")) {
            ShowAlert.showAlert("WARNING", "경고", "이름 미입력");
            return;
        } else if (tfAge.getText().equals("")) {
            ShowAlert.showAlert("WARNING", "경고", "나이 미입력");
            return;
        } else if (cbBoxGender.getValue().equals("")) {
            ShowAlert.showAlert("WARNING", "경고", "성별 미선택");
            return;
        } else if (cbBoxDo.getValue().equals("")) {
            ShowAlert.showAlert("WARNING", "경고", "도 미선택");
            return;
        } else if (cbBoxCity.getValue().equals("") && !(cbBoxDo.getValue().equals("서울특별시") || cbBoxDo.getValue().equals("인천광역시") || cbBoxDo.getValue().equals("울산광역시")
                && cbBoxDo.getValue().equals("대전광역시") || cbBoxDo.getValue().equals("대구광역시") || cbBoxDo.getValue().equals("세종특별자치시") || cbBoxDo.getValue().equals("제주특별자치도"))) {
            ShowAlert.showAlert("WARNING", "경고", "시/군 미선택");
            return;
        }


        String id = userId;
        String name = tfName.getText();
        String age = tfAge.getText();
        String gender = cbBoxGender.getValue();
        String Do = cbBoxDo.getValue();
        String city = cbBoxCity.getValue();
        String address = tfAddress.getText();

        clientMain.writePacket(Protocol.PT_REQ_RENEWAL + "`" + Protocol.REQ_UPDATE_USER);
        clientMain.writeObject(new UserDTO(id, name, Integer.parseInt(age), gender, Do, city, address, Timestamp.valueOf(LocalDateTime.now())));

        while (true) {
            String packet = clientMain.readPacket();
            String packetArr[] = packet.split("`");
            String packetType = packetArr[0];
            String packetCode = packetArr[1];

            if (packetType.equals(Protocol.PT_RES_RENEWAL)) {
                switch (packetCode) {
                    case Protocol.RES_UPDATE_USER_Y: {
                        ShowAlert.showAlert("INFORMATION", "알림창", "개인정보 수정 완료");
                        return;
                    }
                    case Protocol.RES_UPDATE_USER_N: {
                        ShowAlert.showAlert("WARNING", "경고", "개인정보 수정 완료");
                        return;
                    }
                }
            }
        }
    }

    //	수정 클릭 후 '도' 선택 시 '시/군' 변경
    @FXML
    public void handleCbBoxDo(ActionEvent e) {
        cbBoxCity.getItems().clear();
        cbBoxCity.setValue("");
        String Do = cbBoxDo.getValue();
        // 특별시, 광역시인 경우 제외
        if (!(Do.equals("서울특별시") || Do.equals("울산광역시") || Do.equals("인천광역시") || Do.equals("대전광역시") || Do.equals("제주특별자치도") || Do.equals("대구광역시") || Do.equals("부산광역시"))) {
            //  RegionList class에서 hsMap 호출
            HashMap<String, String[]> hsMap = RegionList.hsMap;
            String[] cityList = hsMap.get(Do);
            cbBoxCity.getItems().addAll(cityList);
        }
    }

    @FXML // 즐겨찾기 삭제 버튼 클릭
    public void deleteFavorite(ActionEvent event) {
        try {
            if (tv_favorite.getSelectionModel().isEmpty()) {
                errorAlert("삭제 오류", "삭제할 즐겨찾기를 선택하세요.");
                return;
            }
            if (tv_favorite.getSelectionModel().getSelectedItem() != null) {
                ButtonType result = deleteAlert("선택한 즐겨찾기를 삭제하겠습니까?", "확인 버튼 클릭 시 즐겨찾기 삭제");
                if (result == ButtonType.OK) {
                    int no = tv_favorite.getSelectionModel().getSelectedItem().getNo();
					clientMain.writePacket(Protocol.PT_REQ_RENEWAL + "`" + Protocol.REQ_DELETE_FAVORITES + "`"+no);

					String packet = clientMain.readPacket();
					String packetArr[] = packet.split("`");
					String packetType = packetArr[0];
					String packetCode = packetArr[1];

					if (packetType.equals(Protocol.PT_RES_RENEWAL)) {
						switch (packetCode) {
							case Protocol.RES_DELETE_FAVORITES_Y: {
								ShowAlert.showAlert("INFORMATION", "알림창", "즐겨찾기 삭제 완료");
                                //	삭제 완료 후 리스트 초기화
                                setFavoriteList(userId);
								return;
							}
							case Protocol.RES_DELETE_FAVORITES_N: {
								ShowAlert.showAlert("WARNING", "경고", "즐겨찾기 삭제 실패");
								return;
							}
						}
					}
                } else if (result == ButtonType.CANCEL) {
                    event.consume(); // 이벤트 종료
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //	*************************리뷰 삭제 시 scope도 변화
    @FXML // 리뷰 삭제 버튼 클릭
    public void deleteReview(ActionEvent event) {
        try {
            if (tv_review.getSelectionModel().isEmpty()) {
                errorAlert("삭제 오류", "삭제할 리뷰를 선택하세요.");
                return;
            }
            if (tv_review.getSelectionModel().getSelectedItem() != null) {
                ButtonType result = deleteAlert("선택한 리뷰를 삭제하겠습니까?", "확인 버튼 클릭 시 리뷰 삭제");
                if (result == ButtonType.OK) {
                    ReviewDTO reviewDTO = tv_review.getSelectionModel().getSelectedItem();
                    clientMain.writePacket(Protocol.PT_REQ_RENEWAL + "`" + Protocol.REQ_DELETE_REVIEW +"`"+ reviewDTO.getNo()+"`"+ reviewDTO.getDestination_code());
                    String packet = clientMain.readPacket();
                    String packetArr[] = packet.split("`");
                    String packetType = packetArr[0];
                    String packetCode = packetArr[1];

                    if (packetType.equals(Protocol.PT_RES_RENEWAL)) {
                        switch (packetCode) {
                            case Protocol.RES_DELETE_REVIEW_Y:{
                                ShowAlert.showAlert("INFORMATION", "알림창", "리뷰 삭제 완료");
                                //	삭제 완료 후 리스트 초기화
                                setReviewList(userId);
                                return;
                            }
                            case Protocol.RES_DELETE_REVIEW_N:{
                                ShowAlert.showAlert("WARNING", "경고", "리뷰 삭제 실패");
                                return;
                            }
                        }
                    }
                } else if (result == ButtonType.CANCEL) {
                    event.consume(); // 이벤트 종료
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ButtonType deleteAlert(String head, String msg) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("삭제");
        alert.setHeaderText(head);
        alert.setContentText(msg);
        return alert.showAndWait().get();
    }

    private static void errorAlert(String head, String msg) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("오류");
        alert.setHeaderText(head);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    //	즐겨찾기 더블 클릭시 상세정보
    @FXML
    public void doubleClickFavorite(MouseEvent event) {
        if (tv_favorite.getSelectionModel().getSelectedItem() != null) {
            if (event.getClickCount() > 1) {
                try {
                    String destinationCode = tv_favorite.getSelectionModel().getSelectedItem().getDestination_code();
                    String destinationName = tv_favorite.getSelectionModel().getSelectedItem().getDestination_name();
                    MyPageDAO myPageDAO = new MyPageDAO();
                    DestinationDTO destinationDTO = myPageDAO.loadDestinationDTO(destinationCode);

                    FXMLLoader loader = null;
                    if (tv_favorite.getSelectionModel().getSelectedItem().getSortation().equals("해수욕장")) {
                        loader = new FXMLLoader(getClass().getResource("../FXML/beach_detail.fxml"));
                    }
                    if (tv_favorite.getSelectionModel().getSelectedItem().getSortation().equals("휴양림")) {
                        loader = new FXMLLoader(getClass().getResource("../FXML/forest_lodge_detail.fxml"));
                    }
                    if (tv_favorite.getSelectionModel().getSelectedItem().getSortation().equals("관광지")) {
                        loader = new FXMLLoader(getClass().getResource("../FXML/tourist_spot_detail.fxml"));
                    }
                    Parent root = (Parent) loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    if (tv_favorite.getSelectionModel().getSelectedItem().getSortation().equals("해수욕장")) {
                        //  해수욕장 상세정보로 code, userId 넘기기
                        String beachCode = destinationDTO.getBeach_code();
                        BeachDetailController beachDetailController = loader.<BeachDetailController>getController();
                        beachDetailController.setBeachCode(beachCode);
                        beachDetailController.setSaveUserId(userId);
                        beachDetailController.setDestinationCode(destinationCode);
                        beachDetailController.setDestinationName(destinationName);
                        beachDetailController.setBeachDetail(beachCode, userId, destinationCode, destinationName);
                    }
                    if (tv_favorite.getSelectionModel().getSelectedItem().getSortation().equals("휴양림")) {
                        String forestCode = destinationDTO.getForestLodge_code();
                        ForestLodgeDetailController forestLodgeDetailController = loader.<ForestLodgeDetailController>getController();
                        forestLodgeDetailController.setForestLodgeCode(forestCode);
                        forestLodgeDetailController.setSaveUserId(userId);
                        forestLodgeDetailController.setDestinationCode(destinationCode);
                        forestLodgeDetailController.setDestinationName(destinationName);
                        forestLodgeDetailController.setForestDetail(forestCode,userId,destinationCode,destinationName);
                    }
                    if (tv_favorite.getSelectionModel().getSelectedItem().getSortation().equals("관광지")) {
                        String touristSpotCode = destinationDTO.getTouristSpot_code();
                        TouristSpotDetailController touristSpotDetailController = loader.<TouristSpotDetailController>getController();
                        touristSpotDetailController.setTouristCode(touristSpotCode);
                        touristSpotDetailController.setSaveUserId(userId);
                        touristSpotDetailController.setDestinationCode(destinationCode);
                        touristSpotDetailController.setDestinationName(destinationName);
                        touristSpotDetailController.setTouristDetail(touristSpotCode,userId,destinationCode,destinationName);
                    }
                    stage.showAndWait();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    //	최근 조회 더블클릭 시 상세정보
    @FXML
    public void doubleClickRecent(MouseEvent event) {
        if (tv_recent.getSelectionModel().getSelectedItem() != null) {
            if (event.getClickCount() > 1) {
                try {
                    String destinationCode = tv_recent.getSelectionModel().getSelectedItem().getCode();
                    String destinationName = tv_recent.getSelectionModel().getSelectedItem().getName();
                    MyPageDAO myPageDAO = new MyPageDAO();
                    DestinationDTO destinationDTO = myPageDAO.loadDestinationDTO(destinationCode);

                    FXMLLoader loader = null;
                    if (tv_recent.getSelectionModel().getSelectedItem().getSortation().equals("해수욕장")) {
                        loader = new FXMLLoader(getClass().getResource("../FXML/beach_detail.fxml"));
                    }
                    if (tv_recent.getSelectionModel().getSelectedItem().getSortation().equals("휴양림")) {
                        loader = new FXMLLoader(getClass().getResource("../FXML/forest_lodge_detail.fxml"));
                    }
                    if (tv_recent.getSelectionModel().getSelectedItem().getSortation().equals("관광지")) {
                        loader = new FXMLLoader(getClass().getResource("../FXML/tourist_spot_detail.fxml"));
                    }
                    Parent root = (Parent) loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    if (tv_recent.getSelectionModel().getSelectedItem().getSortation().equals("해수욕장")) {
                        //  해수욕장 상세정보로 code, userId 넘기기
                        String beachCode = destinationDTO.getBeach_code();
                        BeachDetailController beachDetailController = loader.<BeachDetailController>getController();
                        beachDetailController.setBeachCode(beachCode);
                        beachDetailController.setSaveUserId(userId);
                        beachDetailController.setDestinationCode(destinationCode);
                        beachDetailController.setDestinationName(destinationName);
//                        beachDetailController.setBeachDetail(beachCode, userId, destinationCode, destinationName);
                    }
                    if (tv_recent.getSelectionModel().getSelectedItem().getSortation().equals("휴양림")) {
                        String forestCode = destinationDTO.getForestLodge_code();
                        ForestLodgeDetailController forestLodgeDetailController = loader.<ForestLodgeDetailController>getController();
                        forestLodgeDetailController.setForestLodgeCode(forestCode);
                        forestLodgeDetailController.setSaveUserId(userId);
                        forestLodgeDetailController.setDestinationCode(destinationCode);
                        forestLodgeDetailController.setDestinationName(destinationName);
                        //forestLodgeDetailController.setF
                    }
                    if (tv_recent.getSelectionModel().getSelectedItem().getSortation().equals("관광지")) {
                        String touristSpotCode = destinationDTO.getTouristSpot_code();
                        TouristSpotDetailController touristSpotDetailController = loader.<TouristSpotDetailController>getController();
                        //touristSpotDetailController.setCode(touristSpotCode);
                        touristSpotDetailController.setSaveUserId(userId);
                        touristSpotDetailController.setDestinationCode(destinationCode);
                        touristSpotDetailController.setDestinationName(destinationName);
                    }
                    stage.showAndWait();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    //	내가 쓴 리뷰 더블 클릭 시 상세정보
    @FXML
    public void doubleClickReview(MouseEvent event) {
        if (tv_review.getSelectionModel().getSelectedItem() != null) {
            if (event.getClickCount() > 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/review_detail.fxml"));
                    Parent root = (Parent) loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    ReviewDTO reviewDTO = tv_review.getSelectionModel().getSelectedItem();
                    ReviewDetailController reviewDetailController = loader.<ReviewDetailController>getController();
                    reviewDetailController.setReviewDTO(reviewDTO);
                    stage.showAndWait();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
