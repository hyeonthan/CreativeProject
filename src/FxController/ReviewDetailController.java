package FxController;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

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
		tScope.setText(Integer.toString(saveReviewDTO.getScope()));
		tReportingDate.setText(saveReviewDTO.getReporting_date().toString());
		String modifyDate = "";
		if(saveReviewDTO.getModify_date() != null){
			modifyDate = saveReviewDTO.getModify_date().toString();
		}
		tModifyDate.setText(modifyDate);
		taContent.setText(saveReviewDTO.getContent());
		byte[] image = saveReviewDTO.getImage();
		ByteArrayInputStream bis = new ByteArrayInputStream(image);
		Image img = new Image(bis);	
		iv_reviewImage.setImage(img);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}
}