package FxController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import DAO.DetailDAO;
import DTO.BeachDTO;
import DTO.ReviewDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class BeachDetailController extends Object implements Initializable {
	
	@FXML public Text resultTextName;
	@FXML public Text resultTextAddress;
	@FXML public Text resultTextPhoneNum;
	@FXML public Text resultTextIntroduction;
	@FXML public Text resultTextConvenient;
	@FXML public Text reusltTextOpenPeriod;
	@FXML public Text resultTextAvailableTime;
	@FXML public Text resultTextHomepage;
	@FXML public Button btn_favorite;
	@FXML public Button btn_registerImg;
	@FXML public ComboBox<Integer> cb_star;
	ObservableList<Integer> starList = FXCollections.observableArrayList(1, 2, 3, 4, 5);
	@FXML public Button btn_registerReview;
	@FXML public TextArea ta_reviewContent;
	@FXML public TableView<ReviewDTO> tv_review;
	@FXML public TableColumn<ReviewDTO, String> tc_date;
	@FXML public TableColumn<ReviewDTO, String> tc_content;
	@FXML public TableColumn<ReviewDTO, String> tc_star;
	@FXML public TableColumn<ReviewDTO, String> tc_writer;

	private String beachCode;	//	넘어온 beachCode 변수 저장
	private String userId;		//	넘어온 userId 변수 저장
	private String destinationCode;	// 	넘어온 destinationCode 변수 저장
	private String destinationName;	//	넘어온 destinationName 변수 저장
	private byte[] imageInByte;
	public void setBeachCode(String beachCode){
		this.beachCode = beachCode;

		DetailDAO detailDAO = new DetailDAO();
		BeachDTO beachDTO = detailDAO.detailBeach(beachCode);
		resultTextName.setText(beachDTO.getName());
		resultTextAddress.setText(beachDTO.getDo() + " " + beachDTO.getCity() + " " + beachDTO.getAddress());
		resultTextPhoneNum.setText(beachDTO.getPhone_num());
		resultTextIntroduction.setText(beachDTO.getIntroduction());
		resultTextConvenient.setText(beachDTO.getAmenities());
		reusltTextOpenPeriod.setText(beachDTO.getOpening_period());
		resultTextAvailableTime.setText(beachDTO.getAvailable_time());
		resultTextHomepage.setText(beachDTO.getHome_page());
	}
	public void setSaveUserId(String userId){
		this.userId = userId;
	}
	public void setDestinationCode(String destinationCode){
		this.destinationCode = destinationCode;
		tv_review.getItems().clear();
		DetailDAO detailDAO = new DetailDAO();
		ArrayList<ReviewDTO> list = detailDAO.inquireReview(destinationCode);
		tv_review.getItems().addAll(list);
	}
	public void setDestinationName(String destinationName){
		this.destinationName = destinationName;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cb_star.setItems(starList);
        tc_date.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue().getReporting_date()).toString()));
		tc_content.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContent()));
		tc_star.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getScope())));
		tc_writer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser_id()));

	}
	//	이미지 선택
	@FXML
	public void registerImg (ActionEvent event){
        // 사진 선택 창
        FileChooser fc = new FileChooser();
        fc.setTitle("이미지 선택");
        fc.setInitialDirectory(new File("C:")); // default 디렉토리 설정
        // 확장자 제한
        ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
        fc.getExtensionFilters().add(imgType);
		
        File selectedFile =  fc.showOpenDialog(null); // showOpenDialog는 창을 띄우는데 어느 위치에 띄울건지 인자를 받고 선택한 파일의 경로값을 반환한다.
        //System.out.println(selectedFile);               // 선택한 경로가 출력된다.
		if (selectedFile == null) return;
        try {
			//	image to byteArray
			BufferedImage originalImage = ImageIO.read(selectedFile);

			//	이미지 확장자 구하기
			String val = (selectedFile).toString();
			int pos = val.lastIndexOf(".");
			String ext = val.substring(pos + 1, val.length());
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if ("jpg".equals(ext.toLowerCase())){
			ImageIO.write(originalImage, "jpg", baos);
			}
			if ("png".equals(ext.toLowerCase())){
				ImageIO.write(originalImage, "png", baos);
			}
			if ("gif".equals(ext.toLowerCase())){
				ImageIO.write(originalImage, "gif", baos);
			}
			baos.flush();
			//	이미지 바이트 배열로 변경
			imageInByte = baos.toByteArray();
			//System.out.println(Arrays.toString(imageInByte));
			
			baos.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	//	리뷰 등록 버튼
	@FXML
	public void registerReview (ActionEvent event) {
		if(cb_star.getValue() == null){
			ShowAlert.showAlert("WARNING", "경고", "별점 미선택");
			return;
		}
		else if(ta_reviewContent.getText().equals("")){
			ShowAlert.showAlert("WARNING", "경고", "내용 미입력");
			return;
		}
		String content = ta_reviewContent.getText();
		int scope = cb_star.getValue();
		Timestamp reportingDate = Timestamp.valueOf(LocalDateTime.now());
		ReviewDTO reviewDTO = new ReviewDTO(userId, content, scope, destinationCode, destinationName, null, reportingDate, imageInByte);
		DetailDAO detailDAO = new DetailDAO();
		detailDAO.inquireReview(reviewDTO);
		ShowAlert.showAlert("INFORMATION", "알림창", "리뷰 등록 완료");
		setDestinationCode(destinationCode);
	}
	
	@FXML
    public void doubleClickMouse(MouseEvent event){
        if(tv_review.getSelectionModel().getSelectedItem()!=null){
            if(event.getClickCount() > 1){
                FXMLLoader loader = null;
                String destinationCode="", destinationName="";
                loader = new FXMLLoader(getClass().getResource("../FXML/review_detail.fxml"));
				ReviewDTO reviewDTO = tv_review.getSelectionModel().getSelectedItem();
				
            }
        }
	
	}
}
