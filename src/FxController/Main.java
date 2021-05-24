package FxController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import FxController.ShowAlert;
public class Main extends Application {
	
	@FXML // 아이디
	public TextField tf_id;
	
	@FXML // 비밀번호
	public PasswordField pf_password;
	
	@FXML // 로그인 버튼
	public Button btn_login;
	
	@FXML // 회원가입 버튼
	public Button btn_signup;
	
	@FXML // 로그인 실패 텍스트
	public Text t_result;
	
	@FXML // 리뷰 테스트 후 삭제
	public Button btn_review;
	
	@FXML // 로그인 버튼 클릭
	public void login(ActionEvent event) {
		String id = tf_id.getText();
		String pw = pf_password.getText();
		if(id.equals("a") && pw.equals("b")){
			ShowAlert.showAlert("INFORMATION", "로그인 알림창", "로그인 성공");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/MainDisplay.fxml"));
				Parent root = (Parent)loader.load();
				Stage primaryStage = (Stage) btn_login.getScene().getWindow();
				primaryStage.setScene(new Scene(root));
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else ShowAlert.showAlert("INFORMATION", "로그인 알림창", "로그인 실패");

	}
	
	@FXML // 회원가입 버튼 클릭
	public void signUp(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("../FXML/sign_up.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) btn_signup.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void review(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("../FXML/mypage.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) btn_login.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override // 로그인 화면 출력
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("../FXML/Destination.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
