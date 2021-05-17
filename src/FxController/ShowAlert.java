package FxController;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShowAlert {
    public static void showAlert(String type, String title, String contentText) {
		Alert alert = null;
		if(type.equals("WARNING")) 
			alert = new Alert(AlertType.WARNING);
		if(type.equals("INFORMATION")) 
			alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(contentText);
		alert.setHeaderText("알림 메시지");
		alert.showAndWait();
	}
}
