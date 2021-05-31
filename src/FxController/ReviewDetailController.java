package FxController;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import DTO.ReviewDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ReviewDetailController implements Initializable {
	
	@FXML private ImageView iv_reviewImage;
	@FXML private Text tDestinationName;
	@FXML private Text tUserId;
	@FXML private Text tScope;
	@FXML private Text tReportingDate;
	@FXML private Text tModifyDate;
	@FXML private TextArea taContent;

	private ReviewDTO saveReviewDTO;
	public void setReviewDTO(ReviewDTO saveReviewDTO){
		this.saveReviewDTO = saveReviewDTO;
		tDestinationName.setText(saveReviewDTO.getDestination_name());
		tUserId.setText(saveReviewDTO.getUser_id());
	}
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