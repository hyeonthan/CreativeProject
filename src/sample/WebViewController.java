package sample;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebViewController {


        @FXML
        private WebView webView;

        @FXML
        private void initialize()
        {
            WebEngine engine = webView.getEngine();
            engine.load("https://www.google.co.kr/");
        }

}
