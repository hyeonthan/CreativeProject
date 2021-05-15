package sample;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class DController implements Initializable{

    @FXML
    public Label  myLabel;

    @FXML
    public ComboBox<String> combobox;

    ObservableList<String> list = FXCollections.observableArrayList("관광지", "휴양림", "해수욕장");

    @FXML
    private TableView<DestinationTable> myTableView;
    @FXML
    private TableColumn<DestinationTable, String> sortColumn;
    @FXML
    private TableColumn<DestinationTable, String> nameColumn;
    @FXML
    private TableColumn<DestinationTable, String> addressColumn;
    @FXML
    private TableColumn<DestinationTable, Double> gradeColumn;


    ObservableList<DestinationTable> myList = FXCollections.observableArrayList(
            new DestinationTable(new SimpleStringProperty("관광지"), new SimpleStringProperty("동백섬"), new SimpleStringProperty("부산광역시 해운대구 동백로 116"), new SimpleDoubleProperty(2.8)),
            new DestinationTable(new SimpleStringProperty("관광지"), new SimpleStringProperty("해동용궁사"), new SimpleStringProperty("부산광역시 기장군 기장읍"), new SimpleDoubleProperty(4.5)),
            new DestinationTable(new SimpleStringProperty("해수욕장"), new SimpleStringProperty("해운대 해수욕장"), new SimpleStringProperty("부산광역시 해운대구 좌동"), new SimpleDoubleProperty(4.7))
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combobox.setItems(list);
        //listView.setItems(list);
        sortColumn.setCellValueFactory(cellData -> cellData.getValue().sortProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());
        myTableView.setItems(myList);
    }

    //DB에서 전체 회원 정보를 가져와 TebleView에 셋팅
//    public void setTableView() {
//        List<DestinationTable> memList = service.getAllMemberList();
//        data = FXCollections.observableArrayList(memList);
//        //테이블에 observable 리스트 데이터를 추가한다.
//        table.setItems(data);
//    }
//
//    public void alert(String head, String msg) {
//        Alert alert = new Alert(AlertType.WARNING);
//        alert.setTitle("경고");
//        alert.setHeaderText(head);
//        alert.setContentText(msg);
//
//        alert.showAndWait(); //Alert창 보여주기
//    }


//    public void comboChange(ActionEvent event) {
//        myLabel.setText(combobox.getValue());
//    }
//    public void buttonAction(ActionEvent event) {
//        combobox.getItems().addAll("Ram", "Ben", "Steve", "Ma");
//
//    }

}
