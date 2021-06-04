package FxController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainDisplayController implements Initializable{
    @FXML private BorderPane borderpaneId;
    @FXML private Button btnDestinationSearch;
    @FXML private Button btnAroundSearch;
    @FXML private Button btnParkingToilet;
    @FXML private Button btnStatistics;
    @FXML private Button btnLogout;
    @FXML private Button btnMypage;

	private String userId;
	public void setSaveUserId(String userId){
		this.userId = userId;
	}
	@Override
	public void initialize(URL location, ResourceBundle bundle) {

	}
	//	여행지 검색
    @FXML
	public void handleBtnDestinationSearch(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Destination.fxml"));
			Parent inquiry = (Parent)loader.load();
			//	여행지 화면으로 userId 넘기기
			DestinationController destinationController = loader.<DestinationController>getController();
			destinationController.setSaveUserId(userId);
			borderpaneId.setCenter(inquiry);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	//	주변 검색
    @FXML
	public void handleBtnAroundSearch(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Nearby.fxml"));
			Parent inquiry = (Parent)loader.load();
			borderpaneId.setCenter(inquiry);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	//	주차장/화장실 검색
	@FXML
	public void handleBtnParkingToiletSearch(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/ParkingToiletDisplay.fxml"));
			Parent inquiry = (Parent)loader.load();
			borderpaneId.setCenter(inquiry);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	//	통계
	@FXML
	public void handleBtnStatistics(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Statistics.fxml"));
			Parent inquiry = (Parent)loader.load();
			//	통계 화면으로 userId 넘기기
			StatisticsController statisticsController = loader.<StatisticsController>getController();
			statisticsController.setSaveUserId(userId);
			borderpaneId.setCenter(inquiry);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	//	마이페이지 이동
	@FXML
	public void handleBtnMypage(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Mypage.fxml"));
			Parent root = (Parent)loader.load();
			
			//	마이페이지로 로그인한 유저 정보 넘기기
			MypageController mypageController = loader.<MypageController>getController();
			mypageController.setUserInformation(userId);
			mypageController.setRecentList(userId);
			borderpaneId.setCenter(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	//	로그아웃
	@FXML
	public void handleBtnLogout(ActionEvent e) {
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/login.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) btnLogout.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	
}
