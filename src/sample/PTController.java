package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PTController implements Initializable {
    @FXML
    public ComboBox<String> combobox;

    ObservableList<String> list = FXCollections.observableArrayList("주차장", "화장실");

    public void initialize(URL location, ResourceBundle resources) {
        combobox.setItems(list);

    }

}
