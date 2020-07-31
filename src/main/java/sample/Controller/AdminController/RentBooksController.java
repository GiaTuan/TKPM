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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.mail.EmailException;
import sample.BUS.LibraryBUS;
import sample.POJO.RentBook;
import sample.Window.DetailRentBookWindow;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.time.chrono.*;

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

    ObservableList<RentBook> rentBookObservableList;
    List<RentBook> rentBookList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rentBookList = LibraryBUS.getRentBookList(false);
        rentBookObservableList = FXCollections.observableArrayList();
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

    public void manageBookBtnClick(ActionEvent actionEvent) {
//        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChooseAuthorizationFXML.fxml"));
//        stage.setTitle("Phân hệ quản lý");
//        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageLibrarianBtnClick(ActionEvent actionEvent) {
//        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChooseAuthorizationFXML.fxml"));
//        stage.setTitle("Phân hệ quản lý");
//        stage.setScene(new Scene(root, 1000, 600));
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

    @FXML
    ComboBox typeViewComboBox;

    ObservableList<RentBook> tempRentBooksObservableList = rentBookObservableList;
    public void findRentBookListBtnClick(ActionEvent actionEvent) {
        int type = typeViewComboBox.getSelectionModel().getSelectedIndex();
        LocalDate localDate = LocalDate.now();
        if(type == 1)
        {

        }
        else if(type == 2)
        {
            tempRentBooksObservableList = rentBookObservableList.filtered(rentBook ->  ChronoUnit.DAYS.between(rentBook.getRentDate().toLocalDate(),localDate) > 14);
        }
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
}
