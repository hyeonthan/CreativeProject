package application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ReviewWrite {
	
	@FXML
	public ImageView iv_uploadedImg;

	@FXML
	public Button btn_imgUpload;
	
	@FXML
	public TextArea ta_content;
	
	@FXML
	public RadioButton rbtn_1;

	@FXML
	public RadioButton rbtn_2;

	@FXML
	public RadioButton rbtn_3;

	@FXML
	public RadioButton rbtn_4;

	@FXML
	public RadioButton rbtn_5;
	
	@FXML
	public Button btn_register;
	
	@FXML
	public void upload() {
        // 사진 선택 창
        FileChooser fc = new FileChooser();
        fc.setTitle("이미지 선택");
        fc.setInitialDirectory(new File("C:\\Users\\jyw\\Desktop")); // default 디렉토리 설정
        // 선택한 파일 정보 추출
        // 확장자 제한
        ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
//      fc.getExtensionFilters().add(imgType);
        ExtensionFilter txtType = new ExtensionFilter("text file", "*.txt", "*.doc");
        fc.getExtensionFilters().addAll(imgType, txtType);
         
        File selectedFile =  fc.showOpenDialog(null); // showOpenDialog는 창을 띄우는데 어느 위치에 띄울건지 인자를 받고
                                                                // 그리고 선택한 파일의 경로값을 반환한다.
        System.out.println(selectedFile);               // 선택한 경로가 출력된다.
         
        // 파일을 InputStream으로 읽어옴
        try {
            // 파일 읽어오기
            FileInputStream fis = new FileInputStream(selectedFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            // 이미지 생성하기
            Image img = new Image(bis);
            // 이미지 띄우기
            iv_uploadedImg.setImage(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void register() {
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("xml/review_detail.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) btn_register.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
