package FxController;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ForestLodgeDetail implements Initializable {
	
	@FXML
	public Text t_name;
	
	@FXML
	public Text t_address;

	@FXML
	public Text t_phoneNumber;
	
	@FXML
	public Text t_convenient;

	@FXML
	public Text t_enterFee;

	@FXML
	public Text t_homePage;
	
	@FXML
	public Button btn_favorite;
	
	@FXML
	public Button btn_registerImg;
	
	@FXML
	public ComboBox<Integer> cb_star;
	ObservableList<Integer> starList = FXCollections.observableArrayList(1, 2, 3, 4, 5);

	@FXML
	public Button btn_registerReview;
	
	@FXML
	public TextArea ta_reviewContent;
	
	@FXML
	public TableView<Review> tv_review;
	@FXML
	public TableColumn<Review, String> tc_date;
	@FXML
	public TableColumn<Review, String> tc_content;
	@FXML
	public TableColumn<Review, Double> tc_star;
	@FXML
	public TableColumn<Review, String> tc_writer;

	@FXML
	public void registerImg (ActionEvent event) {
        // 사진 선택 창
        FileChooser fc = new FileChooser();
        fc.setTitle("이미지 선택");
        fc.setInitialDirectory(new File("C:")); // default 디렉토리 설정
        // 확장자 제한
        ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
        fc.getExtensionFilters().add(imgType);
         
        File selectedFile =  fc.showOpenDialog(null); // showOpenDialog는 창을 띄우는데 어느 위치에 띄울건지 인자를 받고 선택한 파일의 경로값을 반환한다.
        //System.out.println(selectedFile);               // 선택한 경로가 출력된다.
        try {
            FileInputStream fis = new FileInputStream(selectedFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Image img = new Image(bis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void registerReview (ActionEvent event) {
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cb_star.setItems(starList);
	}
	
	class Review {
		private String date;
		private String content;
		private double star;
		private String writer;
		
		public Review (String date, String content, double star, String writer) {
			this.date = date;
			this.content = content;
			this.star = star;
			this.writer = writer;
		}
		
		public String getDate() { return date; }
		public String getContent() { return content; }
		public double getStar() { return star; }
		public String getWriter() { return writer; }
	}

}
