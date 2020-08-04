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
import sample.POJO.Staff;
import sample.Window.AdminWindow.AddStaffDialogWindow;
import sample.Window.AdminWindow.DetailStaffDialogWindow;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StaffMangerController implements Initializable {

    @FXML
    private TableView table;
    @FXML
    TableColumn <Staff, Integer> id;
    @FXML
    TableColumn <Staff, String> name;
    @FXML
    TableColumn <Staff, String> username;
    @FXML
    TableColumn <Staff, String> phone;
    @FXML
    TableColumn <Staff, String> email;
    private ObservableList<Staff> dataTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadInfo();
    }

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ChooseAuthorizationFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageStatisticBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/AdminFXML.fxml"));
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


    public void manageReaderBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void mangeRegulationBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/UpdateRuleFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    private void loadInfo()
    {
        dataTable = FXCollections.observableArrayList(LibraryBUS.getStaffList(false));
        setupTable();
        table.setItems(dataTable);
    }

    private void setupTable()
    {
        id.setCellValueFactory(new PropertyValueFactory<>("idStaff"));
        name.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneStaff"));
        email.setCellValueFactory(new PropertyValueFactory<>("emailStaff"));
    }

    @FXML
    private void createAccoutn() throws IOException
    {
        AddStaffDialogWindow.display();
        dataTable = FXCollections.observableArrayList(LibraryBUS.getStaffList(true));
        table.setItems(dataTable);
    }

    @FXML
    private void viewDetailBtnClick() throws IOException
    {
        if(table.getSelectionModel().isEmpty())
            return;

        StaffDetailDialogController.setStaffSelected((Staff)table.getSelectionModel().getSelectedItem());
        DetailStaffDialogWindow.display();
    }
}
