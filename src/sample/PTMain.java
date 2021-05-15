package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class PTMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ParkingToilet.fxml"));

        publishServices();
//        root.getChildren().add(btn);
        HBox hh = new HBox();
        hh.getChildren().add(webview);

        primaryStage.setTitle("여정시");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
    private WebEngine webengine;
    private static WebView webview;

    private void publishServices() {
        try {
            webview = new WebView();
            webview.setVisible(true);
            webengine = webview.getEngine();
            webengine.setJavaScriptEnabled(true);
            //File file = new File("http://localhost:8080/map.html");
            //System.out.println(file.exists() + " file exitence");
            //webengine.load(file.toURI().toURL().toString());
            webengine.load("http://localhost:8080/map.html");
        } catch (Exception ex) {
            System.err.print("error " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
