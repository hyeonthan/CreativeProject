package application;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReviewDetail implements Initializable {
	
	@FXML
	public ImageView iv_reviewImage;
	
	@FXML
	public Text t_content;
	
	@FXML
	public Text t_writer;
	
	@FXML
	public Text t_star;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			String path = "C:\\Users\\jyw\\Desktop\\gwang.jpg";
			FileInputStream fis = new FileInputStream(path);
			BufferedInputStream bis = new BufferedInputStream(fis);
			Image img = new Image(bis);	
			iv_reviewImage.setImage(img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}