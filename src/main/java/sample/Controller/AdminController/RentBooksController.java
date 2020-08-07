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
import org.apache.commons.mail.EmailException;
import sample.BUS.LibraryBUS;
import sample.POJO.RentBook;
import sample.Window.AdminWindow.DetailRentBookWindow;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RentBooksController implements Initializable {

    @FXML
    TableView rentBookTableView;
    @FXML
    TableColumn<RentBook,Integer> IdRentCol;
    @FXML
    TableColumn<RentBook,Integer> numberBooksRentCol;
    @FXML
    TableColumn<RentBook,String> listRentCol;
    @FXML
    TableColumn<RentBook,Integer> idReaderCol;
    @FXML
    TableColumn<RentBook, Date> rentDateCol;
    @FXML
    TableColumn<RentBook,Integer> statusCol;
    @FXML
    ComboBox typeFilterComboBox;
    @FXML
    ComboBox typeViewComboBox;
    @FXML
    DatePicker dateRentPicker;
    @FXML
    ComboBox typeStatusComboBox;
    @FXML
    TextField infoRent;

    ObservableList<RentBook> rentBookObservableList;
    ObservableList<RentBook> tempRentBooksObservableList;
    List<RentBook> rentBookList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rentBookList = LibraryBUS.getRentBookList(false);
        rentBookObservableList = FXCollections.observableArrayList();
        tempRentBooksObservableList = rentBookObservableList;
        rentBookObservableList.addAll(rentBookList);
        IdRentCol.setCellValueFactory(new PropertyValueFactory<>("idRentBook"));
        numberBooksRentCol.setCellValueFactory(new PropertyValueFactory<>("numberBooksRent"));
        listRentCol.setCellValueFactory(new PropertyValueFactory<>("listRentBook"));
        idReaderCol.setCellValueFactory(new PropertyValueFactory<>("idReaderRent"));
        rentDateCol.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("stateRent"));
        rentBookTableView.setItems(rentBookObservableList);
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


    public void findRentBookListBtnClick(ActionEvent actionEvent) {
        String info = infoRent.getText();
        int typeFilter = typeFilterComboBox.getSelectionModel().getSelectedIndex();
        int typeView = typeViewComboBox.getSelectionModel().getSelectedIndex();
        int typeStatus = typeStatusComboBox.getSelectionModel().getSelectedIndex();
        LocalDate rentDate = dateRentPicker.getValue();
        tempRentBooksObservableList = LibraryBUS.filterRentBook(rentBookObservableList,info,typeFilter,typeView,typeStatus,rentDate);
        rentBookTableView.setItems(tempRentBooksObservableList);
    }

    public void sendEmailBtnClick(ActionEvent actionEvent) throws EmailException {
        RentBook selectedRentBook = (RentBook) rentBookTableView.getSelectionModel().getSelectedItem();

        boolean isSent = LibraryBUS.sendEmail(selectedRentBook.getReader().getEmailReader());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(isSent)
        {
            alert.setContentText("Gửi email thành công");
            alert.showAndWait();
        }
        else
        {
            alert.setContentText("Gửi email không thành công");
            alert.showAndWait();
        }
    }

    public void detailRentBookBtnClick(ActionEvent actionEvent) throws IOException {
        RentBook rentBook = (RentBook) rentBookTableView.getSelectionModel().getSelectedItem();
        DetailRentBookWindow.display(rentBook);
        if(DetailRentBookController.isChanged)
        {
            DetailRentBookController.isChanged = false;
            rentBookList = LibraryBUS.getRentBookList(true);
            rentBookObservableList.clear();
            rentBookObservableList.addAll(rentBookList);
            rentBookTableView.refresh();
        }
    }

    public void sendEmailToAllBtnClick(ActionEvent actionEvent) {
        boolean isSent = LibraryBUS.sendEmailToAll(tempRentBooksObservableList);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(isSent)
        {
            alert.setContentText("Gửi email thành công");
            alert.showAndWait();
        }
        else
        {
            alert.setContentText("Gửi email không thành công");
            alert.showAndWait();
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
