package FxController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainDisplayController implements Initializable{
    @FXML private BorderPane borderpaneId;
    @FXML private Button btnDestinationSearch;
    @FXML private Button btnAroundSearch;
    @FXML private Button btnParkingToilet;
    @FXML private Button btnStatistics;
    @FXML private Button btnLogout;
    @FXML private Button btnMypage;

	@Override
	public void initialize(URL location, ResourceBundle bundle) {

	}

    @FXML
	public void handleBtnDestinationSearch(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Destination.fxml"));
			Parent inquiry = (Parent)loader.load();
			borderpaneId.setCenter(inquiry);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
    @FXML
	public void handleBtnAroundSearch(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Destination.fxml"));
			Parent inquiry = (Parent)loader.load();
			borderpaneId.setCenter(inquiry);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
