package FxController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class Mypage implements Initializable {
	
//	public ObservableList<Favorite> favoriteList;
	
	@FXML
	public Text t_id;
	
	@FXML
	public Text t_name;
	
	@FXML
	public Text t_age;
	
	@FXML
	public Text t_gender;
	
	@FXML
	public Text t_address;
	
	@FXML // 즐겨찾기 테이블
	public TableView<Favorite> tv_favorite;
	@FXML
	public TableColumn<Favorite, String> tc_favoriteType;
	@FXML
	public TableColumn<Favorite, String> tc_favoriteName;
	
	@FXML // 최근조회 테이블
	public TableView<Recent> tv_recent;
	@FXML
	public TableColumn<Recent, String> tc_recentType;
	@FXML
	public TableColumn<Recent, String> tc_recentName;
	
	@FXML // 리뷰 테이블
	public TableView<Review> tv_review;
	@FXML
	public TableColumn<Review, String> tc_reviewDate;
	@FXML
	public TableColumn<Review, String> tc_reviewName;
	@FXML
	public TableColumn<Review, String> tc_reviewContent;
	
	@FXML
	public Button btn_deleteFavorite;
	
	@FXML
	public Button btn_deleteReview;
	
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
	
//	ObservableList<Favorite> myList = FXCollections.observableArrayList(
//			new Favorite(new SimpleStringProperty("a"), new SimpleStringProperty("b"))
//			);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		favoriteList = FXCollections.observableArrayList();
//		tc_favoriteType.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getType()));
//		tc_favoriteName.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getName()));
//		tv_favorite.setItems(myList);
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
