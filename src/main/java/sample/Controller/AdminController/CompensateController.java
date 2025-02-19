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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.Compensate;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class CompensateController implements Initializable {


    @FXML
    TableView denBuTableView;
    @FXML
    TableColumn<Compensate,Integer> idDBCol;
    @FXML
    TableColumn<Compensate,Integer> idReaderCol;
    @FXML
    TableColumn<Compensate,String> idBookCool;
    @FXML
    TableColumn<Compensate,Double> feeDBCol;
    @FXML
    TableColumn<Compensate, Date> dateDBCol;
    @FXML
    TableColumn<Compensate,Integer> isDeletedCol;
    @FXML
    ComboBox typeCombobox;

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ChooseAuthorizationFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

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

    public void mangeRegulationBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/UpdateRuleFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageStatisticBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/AdminFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    ObservableList<Compensate> compensateObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Compensate> compensateList = LibraryBUS.getCompensateList(false);
        setupCompensateTable(compensateList);
    }

    private void setupCompensateTable(List<Compensate> list) {
        idDBCol.setCellValueFactory(new PropertyValueFactory<>("idCompensate"));
        idReaderCol.setCellValueFactory(new PropertyValueFactory<>("idReader"));
        idBookCool.setCellValueFactory(new PropertyValueFactory<>("idBook"));;
        feeDBCol.setCellValueFactory(new PropertyValueFactory<>("compensateFee"));
        dateDBCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        isDeletedCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        compensateObservableList = FXCollections.observableArrayList();
        compensateObservableList.addAll(list);
        denBuTableView.setItems(compensateObservableList);

    }

    public void deleteCompensateBtnClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Bạn muốn tiếp tục xóa?");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get() == ButtonType.OK) {
            Compensate compensate = (Compensate) denBuTableView.getSelectionModel().getSelectedItem();
            LibraryBUS.deleteCompensate(compensate);
            denBuTableView.refresh();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Xóa thành công");
            alert.showAndWait();
        }
    }

    public void findBtnClick(ActionEvent actionEvent) {
        int index = typeCombobox.getSelectionModel().getSelectedIndex();
        ObservableList<Compensate> tempList = LibraryBUS.filterCompensateList(compensateObservableList,index);
        denBuTableView.setItems(tempList);
    }

    public void mangeReportBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ReportBookFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }
}
