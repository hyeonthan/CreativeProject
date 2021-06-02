package FxController;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import DAO.MyPageDAO;
import DTO.FavoriteDTO;
import DTO.ReviewDTO;
import DTO.UserDTO;
import DataSetControl.RegionList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MypageController implements Initializable {
	@FXML TextField tfId;
	@FXML TextField tfName;
	@FXML TextField tfAge;
	@FXML ComboBox<String> cbBoxGender;
	@FXML TextField tfAddress;
	@FXML ComboBox<String> cbBoxDo;
	@FXML ComboBox<String> cbBoxCity;
	@FXML // 즐겨찾기 테이블
	public TableView<FavoriteDTO> tv_favorite;
	@FXML
	public TableColumn<FavoriteDTO, String> tc_favoriteType;
	@FXML
	public TableColumn<FavoriteDTO, String> tc_favoriteName;
	// @FXML // 최근조회 테이블
	// public TableView<Recent> tv_recent;
	// @FXML
	// public TableColumn<Recent, String> tc_recentType;
	// @FXML
	// public TableColumn<Recent, String> tc_recentName;
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
	public void setUserInformation(String userId){
		MyPageDAO myPageDAO = new MyPageDAO();
		UserDTO userDTO = myPageDAO.roadUser(userId);
		this.userId = userId;

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
	}
	//	즐겨찾기 리스트 불러오기
	public void setFavoriteList(String userId){
		tv_favorite.getItems().clear();
		
		MyPageDAO myPageDAO = new MyPageDAO();
		ArrayList<FavoriteDTO> list = myPageDAO.inquiryFavorite(userId);
		tv_favorite.getItems().addAll(list);
	}
	//	내가 쓴 리뷰 리스트 불러오기
	public void setReviewList(String userId){
		tv_review.getItems().clear();

		MyPageDAO myPageDAO = new MyPageDAO();
		ArrayList<ReviewDTO> list = myPageDAO.inquireMyReview(userId);
		tv_review.getItems().addAll(list);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//	즐겨찾기 테이블 초기화
		tc_favoriteType.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getSortation()));
		tc_favoriteName.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getDestination_name()));
		//	리뷰 테이블 초기화 
		tc_reviewDate.setCellValueFactory(cellData-> new SimpleStringProperty((cellData.getValue().getReporting_date()).toString()));
		tc_reviewName.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getDestination_name()));
		tc_reviewContent.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getContent()));

	}
	//	수정 버튼 클릭
	@FXML
	public void handleBtnModify(ActionEvent event){
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
	public void handleBtnReg(ActionEvent event){
		if(tfName.getText().equals("")){
			ShowAlert.showAlert("WARNING", "경고", "이름 미입력");
			return;
		}
		else if(tfAge.getText().equals("")){
			ShowAlert.showAlert("WARNING", "경고", "나이 미입력");
			return;
		}
		else if(cbBoxGender.getValue().equals("")){
			ShowAlert.showAlert("WARNING", "경고", "성별 미선택");
			return;
		}
		else if(cbBoxDo.getValue().equals("")){
			ShowAlert.showAlert("WARNING", "경고", "도 미선택");
			return;
		}
		else if(cbBoxCity.getValue().equals("") && !(cbBoxDo.getValue().equals("서울특별시") || cbBoxDo.getValue().equals("인천광역시") || cbBoxDo.getValue().equals("울산광역시")
		&& cbBoxDo.getValue().equals("대전광역시") || cbBoxDo.getValue().equals("대구광역시") || cbBoxDo.getValue().equals("세종특별자치시") || cbBoxDo.getValue().equals("제주특별자치도"))){
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
		MyPageDAO myPageDAO = new MyPageDAO();
		myPageDAO.reservationUser(new UserDTO(id, name, Integer.parseInt(age), gender, Do, city, address, Timestamp.valueOf(LocalDateTime.now())));
		ShowAlert.showAlert("INFORMATION", "알림창", "개인정보 수정 완료");
		//	수정 완료 후 개인정보 초기화
		setUserInformation(id);
	}
	//	수정 클릭 후 '도' 선택 시 '시/군' 변경
	@FXML
    public void handleCbBoxDo(ActionEvent e) {
        cbBoxCity.getItems().clear();
		cbBoxCity.setValue("");
        String Do = cbBoxDo.getValue();
        // 특별시, 광역시인 경우 제외
        if(!(Do.equals("서울특별시") || Do.equals("울산광역시") || Do.equals("인천광역시") || Do.equals("대전광역시") || Do.equals("제주특별자치도") || Do.equals("대구광역시") || Do.equals("부산광역시"))){
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
			if(tv_favorite.getSelectionModel().getSelectedItem()!=null){
				ButtonType result = deleteAlert("선택한 즐겨찾기를 삭제하겠습니까?", "확인 버튼 클릭 시 즐겨찾기 삭제");
				if (result == ButtonType.OK) {
					MyPageDAO myPageDAO = new MyPageDAO();
					int no = tv_favorite.getSelectionModel().getSelectedItem().getNo();
					myPageDAO.deleteFavorite(no);
					ShowAlert.showAlert("INFORMATION", "알림창", "즐겨찾기 삭제 완료!");
					//	삭제 완료 후 리스트 초기화
					setFavoriteList(userId);
				}
				else if (result == ButtonType.CANCEL) {
					event.consume(); // 이벤트 종료
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//	*************************리뷰 삭제시 scope도 변화
	@FXML // 리뷰 삭제 버튼 클릭
	public void deleteReview(ActionEvent event) {
		try {
			if (tv_review.getSelectionModel().isEmpty()) {
				errorAlert("삭제 오류", "삭제할 리뷰를 선택하세요.");
				return;
			}
			if(tv_review.getSelectionModel().getSelectedItem()!=null){
				ButtonType result = deleteAlert("선택한 리뷰를 삭제하겠습니까?", "확인 버튼 클릭 시 리뷰 삭제");
				if (result == ButtonType.OK) {
					MyPageDAO myPageDAO = new MyPageDAO();
					ReviewDTO reviewDTO = tv_review.getSelectionModel().getSelectedItem();
					myPageDAO.deleteReview(reviewDTO);
					ShowAlert.showAlert("INFORMATION", "알림창", "리뷰 삭제 완료!");
					//	삭제 완료 후 리스트 초기화
					setReviewList(userId);
				}
				else if (result == ButtonType.CANCEL) {
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

}
