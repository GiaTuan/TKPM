package sample.Controller.AdminController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.Regulation;
import sample.Window.AdminWindow.UpdateRuleDialogWindow;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateRuleController implements Initializable {
    @FXML
    private TableView ruleTable;
    @FXML
    private TableColumn<Regulation, Integer> idRegulationCol;
    @FXML
    private TableColumn<Regulation, String> nameRegualtionCol;
    @FXML
    private TableColumn<Regulation, Integer> detailRegulationCol;
    private static ObservableList<Regulation> dataTable = FXCollections.observableArrayList();
    private static ArrayList<Integer> listIdOfChangingRule = new ArrayList<Integer>();

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/UpdateRuleFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    //tạo file fxml rồi nhét cái tên file vào thôi

    public void manageRentBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/RentBooksFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageBookBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/BookManagerFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageLibrarianBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/StaffManagerFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageReaderBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }
    
    public void manageStatisticBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/AdminFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataTable = FXCollections.observableArrayList(LibraryBUS.getRegulationList());
        setupTable();
        ruleTable.setItems(dataTable);

    }

    private void setupTable()
    {
        idRegulationCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameRegualtionCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        detailRegulationCol.setCellValueFactory(new PropertyValueFactory<>("detail"));
    }


    private void storeChange(Regulation changeData)
    {
        for(Regulation item : dataTable)
        {
            if(item.getId() == changeData.getId())
            {
                listIdOfChangingRule.add(item.getId());
                item.setName(changeData.getName());
                item.setDetail(changeData.getDetail());
            }
        }
        ruleTable.refresh();
    }

    @FXML
    private void updateBtnClick()
    {
        LibraryBUS.updateRegulation(dataTable, listIdOfChangingRule);
    }

    @FXML
    private void editBtnClick()throws IOException
    {
        if(!ruleTable.getSelectionModel().isEmpty())
        {
            UpdateRegulationDialogController.setSelectedRegulation((Regulation)ruleTable.getSelectionModel().getSelectedItem());
            UpdateRuleDialogWindow.display();

            if(UpdateRegulationDialogController.getCacheData() != null)
                storeChange(UpdateRegulationDialogController.getCacheData());
        }
    }

    public void manangeCompensateBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/CompensateFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void mangeReportBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ReportBookFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }
}
