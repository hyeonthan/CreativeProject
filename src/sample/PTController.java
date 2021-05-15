package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PTController implements Initializable {
    @FXML
    public ComboBox<String> combobox;

    ObservableList<String> list = FXCollections.observableArrayList("주차장", "화장실");

    @FXML
    private TableView<PTTable> myTableView;
    @FXML
    private TableColumn<PTTable, String> sortColumn;
    @FXML
    private TableColumn<PTTable, String> nameColumn;
    @FXML
    private TableColumn<PTTable, String> addressColumn;
    @FXML
    private TableColumn<PTTable, Integer> feeColumn;

    ObservableList<PTTable> myList = FXCollections.observableArrayList(
            new PTTable(new SimpleStringProperty("주차장"), new SimpleStringProperty("동백섬"), new SimpleStringProperty("부산광역시 해운대구 동백로 116"), new SimpleIntegerProperty(2000)),
            new PTTable(new SimpleStringProperty("주차장"), new SimpleStringProperty("해운대 해수욕장"), new SimpleStringProperty("부산광역시 해운대구 좌동"), new SimpleIntegerProperty(5000))
    );

    public void initialize(URL location, ResourceBundle resources) {
        combobox.setItems(list);
        sortColumn.setCellValueFactory(cellData -> cellData.getValue().sortProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        feeColumn.setCellValueFactory(cellData -> cellData.getValue().feeProperty().asObject());
        myTableView.setItems(myList);
    }

}
