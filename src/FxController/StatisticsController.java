package FxController;

import DTO.DestinationDTO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML private ComboBox<String> comboBoxDestination;
    @FXML private ComboBox<String> comboBoxStatistics;
    @FXML private TableView<DestinationDTO> myTableView;
    @FXML private TableColumn<DestinationDTO, Integer> rankingColumn;
    @FXML private TableColumn<DestinationDTO, String> nameColumn;
    @FXML private TableColumn<DestinationDTO, String> valueColumn;


    public void initialize(URL location, ResourceBundle resources) {
        comboBoxDestination.setItems(FXCollections.observableArrayList(
                "통합검색", "해수욕장", "휴양림", "관광지"));
        comboBoxStatistics.setItems(FXCollections.observableArrayList("조회수", "평점", "거주지", "리뷰수"));
        //rankingColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty();
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        valueColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getScope())));
    }
}
