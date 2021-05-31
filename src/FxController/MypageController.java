package FxController;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import DAO.MyPageDAO;
import DTO.FavoriteDTO;
import DTO.ReviewDTO;
import DTO.UserDTO;
import DataSetControl.RegionList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
	@FXML // 최근조회 테이블
	public TableView<Recent> tv_recent;
	@FXML
	public TableColumn<Recent, String> tc_recentType;
	@FXML
	public TableColumn<Recent, String> tc_recentName;
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
	public void setSaveUserId(String userId){
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
		tfName.setDisable(true);
		tfAge.setDisable(true);
		cbBoxGender.setDisable(true);
		tfAddress.setDisable(true);
		cbBoxDo.setDisable(true);
		cbBoxCity.setDisable(true);

		String id = userId;
		String name = tfName.getText();
		String age = tfAge.getText();
		String gender = cbBoxGender.getValue();
		String Do = cbBoxDo.getValue();
		String city = cbBoxCity.getValue();
		String address = tfAddress.getText();
		MyPageDAO myPageDAO = new MyPageDAO();
		myPageDAO.reservationUser(id, name, age, gender, Do, city, address);
		ShowAlert.showAlert("INFORMATION", "알림창", "개인정보 수정 완료");
		setSaveUserId(id);
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
			
//			ArrayList<Favorite> arr = new ArrayList<Favorite>();
//			arr.add(new Favorite("a", "b"));
//
//			for (Favorite temp : arr) // 리스트에 추가
//			{
//				favoriteList.add(temp);
//			}
			if (tv_favorite.getSelectionModel().isEmpty()) {
				errorAlert("삭제 오류", "삭제할 즐겨찾기를 선택하세요.");
				return;
			}
			
			ButtonType result = deleteAlert("선택한 즐겨찾기를 삭제하겠습니까?", "확인 버튼 클릭 시 즐겨찾기 삭제");
			if (result == ButtonType.OK) {
			}
			else if (result == ButtonType.CANCEL) {
				event.consume(); // 이벤트 종료
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
			
			ButtonType result = deleteAlert("선택한 리뷰를 삭제하겠습니까?", "확인 버튼 클릭 시 리뷰 삭제");
			if (result == ButtonType.OK) {
				
			}
			else if (result == ButtonType.CANCEL) {
				event.consume(); // 이벤트 종료
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
	

	
	// 즐겨찾기 테이블 행 클래스
	private class Favorite {
		private String type;
		private String name;
		
		public Favorite(String type, String name) {
			this.type = type;
			this.name = name;
		}
		
		public String getType() { return type; }
		public String getName() { return name; }
	}

	// 최근조회 테이블 행 클래스
	private class Recent {
		private StringProperty type;
		private StringProperty name;
		
		public Recent(StringProperty type, StringProperty name) {
			this.type = type;
			this.name = name;
		}
		
		public StringProperty getType() { return type; }
		public StringProperty getName() { return name; }
	}
	
	// 리뷰 테이블 행 클래스
	private class Review {
		private StringProperty date;
		private StringProperty name;
		private StringProperty content;
		
		public Review(StringProperty date, StringProperty name, StringProperty content) {
			this.date = date;
			this.name = name;
			this.content = content;
		}
		
		public StringProperty getDate() { return date; }
		public StringProperty getName() { return name; }
		public StringProperty getContent() { return content; }
	}
}
