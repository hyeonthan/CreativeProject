package FxController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.InquireByRegionDAO;
import DTO.DestinationDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DestinationController implements Initializable{
    @FXML private ComboBox<String> comboBoxClassification;
    @FXML private ComboBox<String> comboBoxDo;
    @FXML private ComboBox<String> comboBoxCity;
    @FXML private TableView<DestinationDTO> myTableView;
	@FXML private TableColumn<DestinationDTO, String> sortColumn;
	@FXML private TableColumn<DestinationDTO, String> nameColumn;
	@FXML private TableColumn<DestinationDTO, String> addressColumn;
	@FXML private TableColumn<DestinationDTO, String> gradeColumn;

    @Override
	public void initialize(URL location, ResourceBundle bundle) {
        comboBoxDo.setItems(FXCollections.observableArrayList(
           "강원도"));
        comboBoxCity.setItems(FXCollections.observableArrayList(
            "강릉시"));
        sortColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSortation()));
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDo() + " " + cellData.getValue().getCity() + " " + cellData.getValue().getAddress()));
		gradeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getScope())));
	}
    //public ArrayList<DestinationDTO> inquireDestinationByRegion(String Do,String city){
    
    //  검색 버튼 event
    @FXML
	public void handleBtnSearch(ActionEvent e) {
        ArrayList<DestinationDTO> list = null;
        InquireByRegionDAO inquireByRegionDAO = new InquireByRegionDAO();
        String sortation, Do, city;
        if(comboBoxDo.getValue() == null){
            ShowAlert.showAlert("WARNING", "경고", "도 미선택");
            return;
        }
        else if(comboBoxCity.getValue() == null){
            ShowAlert.showAlert("WARNING", "경고", "도 미선택");
            return;
        }
        //  구분 미선택 조회
        if(comboBoxClassification.getValue() == null){
            Do = comboBoxDo.getValue();
            city = comboBoxCity.getValue();
            list = inquireByRegionDAO.inquireDestinationByRegion(Do, city);
            System.out.println(list.get(1).getAddress());
            myTableView.getItems().addAll(list);
        }
		
	}
}
