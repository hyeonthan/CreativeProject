package FxController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class TouristSpotDetail implements Initializable {
	
	@FXML
	public Text t_name;
	
	@FXML
	public Text t_address;
	
	@FXML
	public Text t_introduction;
	
	@FXML
	public Text t_phoneNumber;
	
	@FXML
	public Text t_convenient;

	@FXML
	public TableView<Review> tv_review;
	@FXML
	public TableColumn<Review, String> tc_date;
	@FXML
	public TableColumn<Review, Double> tc_star;
	@FXML
	public TableColumn<Review, String> tc_content;
	@FXML
	public TableColumn<Review, String> tc_name;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	class Review {
		
	}

}
