package ParkingToilet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PTController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    public Button btnP, btnT;
    @FXML
    private WebView webView;

    public void initialize(URL location, ResourceBundle resources) {
        btnP.setOnAction(event->loadPage("Parking"));
        btnT.setOnAction(event->loadPage("Toilet"));
    }

    private void loadPage(String page) {      //fxml파일이름 넣으면 boarderpane의 center에 넣기
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp.setCenter(root);
    }

}
