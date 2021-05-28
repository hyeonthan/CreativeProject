package FxController;

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
import javafx.scene.text.Text;

public class BeachDetail implements Initializable {
	
	@FXML
	public Text t_name;
	
	@FXML
	public Text t_address;

	@FXML
	public Text t_phoneNumber;
	
	@FXML
	public Text t_introduction;
	
	@FXML
	public Text t_convenient;

	@FXML
	public Text t_openingPeriod;
	
	@FXML
	public Text t_availableTime;

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
		
	}
	
	@FXML
	public void starSelect (ActionEvent event) {
		
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
