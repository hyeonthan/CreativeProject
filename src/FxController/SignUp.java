package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUp {

	@FXML
	public TextField tf_id;
	
	@FXML
	public PasswordField pf_password;
	
	@FXML // 비밀번호 확인
	public PasswordField pf_passwordCheck;
	
	@FXML
	public TextField tf_name;

	@FXML
	public TextField tf_age;

	@FXML
	public MenuButton mb_gender;
	
	@FXML // 주소 도, 광역시
	public MenuButton mb_do;
	
	@FXML // 주소 시/군
	public MenuButton mb_city;
	
	@FXML // 상세주소
	public TextField tf_detailAddress;
	
	@FXML
	public Button btn_signUp;
	
	@FXML
	public Button btn_cancle;
	
	@FXML
	public void selectGender(ActionEvent event) {
		try {
			mb_gender.setText(((MenuItem)event.getSource()).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void seoul (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void gyeonggi (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
			mb_city.getItems().addAll(new MenuItem("가평시"), new MenuItem("고양시"), new MenuItem("과전시"), new MenuItem("광명시"), new MenuItem("광주시"), 
					new MenuItem("구리시"), new MenuItem("군포시"), new MenuItem("김포시"), new MenuItem("남양주시"), new MenuItem("동두천시"), new MenuItem("부천시"), 
					new MenuItem("성남시"), new MenuItem("수원시"), new MenuItem("시흥시"), new MenuItem("안산시"), new MenuItem("안성시"), new MenuItem("안양시"), 
					new MenuItem("양주시"), new MenuItem("양평군"), new MenuItem("여주시"), new MenuItem("연천군"), new MenuItem("오산시"), new MenuItem("용인시"), 
					new MenuItem("의왕시"), new MenuItem("의정부시"), new MenuItem("이천시"), new MenuItem("파주시"), new MenuItem("평택시"), new MenuItem("포천시"), 
					new MenuItem("하남시"), new MenuItem("화성시"));
			for (int i = 0; i < mb_city.getItems().size(); i++) {
				mb_city.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) { mb_city.setText(((MenuItem)event.getSource()).getText()); }
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void incheon (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void daejeon (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void daegu (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void busan (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void ulsan (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void gwangju (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void gangwon (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
			mb_city.getItems().addAll(new MenuItem("춘천시"), new MenuItem("원주시"), new MenuItem("강릉시"), new MenuItem("동해시"), new MenuItem("태백시"), 
					new MenuItem("속초시"), new MenuItem("삼척시"), new MenuItem("홍천군"), new MenuItem("횡성군"), new MenuItem("영월군"), new MenuItem("평창군"), 
					new MenuItem("정선군"), new MenuItem("철원군"), new MenuItem("화천군"), new MenuItem("양구군"), new MenuItem("인제군"), new MenuItem("고성군"), 
					new MenuItem("양양군"));
			for (int i = 0; i < mb_city.getItems().size(); i++) {
				mb_city.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) { mb_city.setText(((MenuItem)event.getSource()).getText()); }
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void sejong (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void chungbuk (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
			mb_city.getItems().addAll(new MenuItem("청주시"), new MenuItem("충주시"), new MenuItem("제천시"), new MenuItem("보은군"), new MenuItem("옥천군"), 
					new MenuItem("영동군"), new MenuItem("증평군"), new MenuItem("진천군"), new MenuItem("괴산군"), new MenuItem("음성군"), new MenuItem("단양군"));
			for (int i = 0; i < mb_city.getItems().size(); i++) {
				mb_city.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) { mb_city.setText(((MenuItem)event.getSource()).getText()); }
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void chungnam (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
			mb_city.getItems().addAll(new MenuItem("천안시"), new MenuItem("공주시"), new MenuItem("보령시"), new MenuItem("아산시"), new MenuItem("서산시"), 
					new MenuItem("논산시"), new MenuItem("계룡시"), new MenuItem("당진시"), new MenuItem("금산군"), new MenuItem("부여군"), new MenuItem("서천군"), 
					new MenuItem("청양군"), new MenuItem("홍성군"), new MenuItem("예산군"), new MenuItem("태안군"));
			for (int i = 0; i < mb_city.getItems().size(); i++) {
				mb_city.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) { mb_city.setText(((MenuItem)event.getSource()).getText()); }
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void gyeongbuk (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
			mb_city.getItems().addAll(new MenuItem("포항시"), new MenuItem("경주시"), new MenuItem("김천시"), new MenuItem("안동시"), new MenuItem("구미시"), 
					new MenuItem("영주시"), new MenuItem("영천시"), new MenuItem("상주시"), new MenuItem("문경시"), new MenuItem("경산시"), new MenuItem("군위군"), 
					new MenuItem("의성군"), new MenuItem("청송군"), new MenuItem("영양군"), new MenuItem("영덕군"), new MenuItem("청도군"), new MenuItem("고령군"), 
					new MenuItem("성주군"), new MenuItem("칠곡군"), new MenuItem("예천군"), new MenuItem("봉화군"), new MenuItem("울진군"), new MenuItem("울릉군"));
			for (int i = 0; i < mb_city.getItems().size(); i++) {
				mb_city.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) { mb_city.setText(((MenuItem)event.getSource()).getText()); }
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void gyeongnam (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
			mb_city.getItems().addAll(new MenuItem("창원시"), new MenuItem("진주시"), new MenuItem("통영시"), new MenuItem("사천시"), new MenuItem("김해시"), 
					new MenuItem("밀양시"), new MenuItem("거제시"), new MenuItem("양산시"), new MenuItem("의령군"), new MenuItem("함안군"), new MenuItem("창녕군"), 
					new MenuItem("고성군"), new MenuItem("남해군"), new MenuItem("하동군"), new MenuItem("산청군"), new MenuItem("함양군"), new MenuItem("거창군"), 
					new MenuItem("합천군"));
			for (int i = 0; i < mb_city.getItems().size(); i++) {
				mb_city.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) { mb_city.setText(((MenuItem)event.getSource()).getText()); }
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void jeonbuk (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
			mb_city.getItems().addAll(new MenuItem("전주시"), new MenuItem("군산시"), new MenuItem("익산시"), new MenuItem("정읍시"), new MenuItem("남원시"), 
					new MenuItem("김제시"), new MenuItem("완주군"), new MenuItem("진안군"), new MenuItem("무주군"), new MenuItem("장수군"), new MenuItem("임실군"), 
					new MenuItem("순창군"), new MenuItem("고창군"), new MenuItem("부안군"));
			for (int i = 0; i < mb_city.getItems().size(); i++) {
				mb_city.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) { mb_city.setText(((MenuItem)event.getSource()).getText()); }
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void jeonnam (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
			mb_city.getItems().addAll(new MenuItem("강진군"), new MenuItem("고흥군"), new MenuItem("곡성군"), new MenuItem("광양시"), new MenuItem("구례군"), 
					new MenuItem("나주시"), new MenuItem("담양군"), new MenuItem("목포시"), new MenuItem("무안군"), new MenuItem("보성군"), new MenuItem("순천시"), 
					new MenuItem("신안군"), new MenuItem("여수시"), new MenuItem("영광군"), new MenuItem("영암군"), new MenuItem("완도군"), new MenuItem("장성군"), 
					new MenuItem("장흥군"), new MenuItem("진도군"), new MenuItem("함평군"), new MenuItem("해남군"), new MenuItem("화순군"));
			for (int i = 0; i < mb_city.getItems().size(); i++) {
				mb_city.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) { mb_city.setText(((MenuItem)event.getSource()).getText()); }
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void jeju (ActionEvent event) {
		try {
			mb_do.setText(((MenuItem)event.getSource()).getText());
			mb_city.getItems().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML // 회원가입버튼 클릭
	public void signUp(ActionEvent event) {
		
	}
	
	@FXML // 취소버튼 클릭
	public void cancle(ActionEvent evnet) {
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("xml/login.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) btn_cancle.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
