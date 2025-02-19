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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import sample.BUS.LibraryBUS;
import sample.POJO.Reader;
import sample.Window.AdminWindow.DetailReaderWindow;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReaderController implements Initializable {

    @FXML
    TableView readerTableView;
    @FXML
    TableColumn<Reader, Integer> idReaderCol;
    @FXML
    TableColumn<Reader, String> nameReaderCol;
    @FXML
    TableColumn<Reader, String> emailReaderCol;
    @FXML
    TableColumn<Reader, String> phoneReaderCol;
    @FXML
    TableColumn<Reader, Integer> pointReaderCol;
    @FXML
    TableColumn<Reader, Integer> statusReaderCol;
    @FXML
    ComboBox typeDisplayComboBox;
    @FXML
    ComboBox typeFilterComboBox;
    @FXML
    TextField infoReader;


    ObservableList<Reader> readerObservableList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Reader> readerList = LibraryBUS.getReaderList(false);
        readerObservableList = FXCollections.observableArrayList();
        readerObservableList.addAll(readerList);
        idReaderCol.setCellValueFactory(new PropertyValueFactory<>("idReader"));
        nameReaderCol.setCellValueFactory(new PropertyValueFactory<>("nameReader"));
        emailReaderCol.setCellValueFactory(new PropertyValueFactory<>("emailReader"));
        phoneReaderCol.setCellValueFactory(new PropertyValueFactory<>("phoneReader"));
        pointReaderCol.setCellValueFactory(new PropertyValueFactory<>("point"));
        statusReaderCol.setCellValueFactory(new PropertyValueFactory<>("isMarked"));
        readerTableView.setItems(readerObservableList);

        //initialize table reader
    }

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ChooseAuthorizationFXML.fxml"));
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

    public void detailReaderBtnClick(ActionEvent actionEvent) throws IOException {
        Reader selectedReader = (Reader) readerTableView.getSelectionModel().getSelectedItem();
        DetailReaderWindow.display(selectedReader);
        if(DetailReaderController.isChanged)
        {
            DetailReaderController.isChanged = false;
            List<Reader> readerList = LibraryBUS.getReaderList(true);
            readerObservableList.clear();
            readerObservableList.addAll(readerList);
            readerTableView.refresh();
        }
    }

    public void exportReaderFromBtnClick(ActionEvent actionEvent) throws IOException, InvalidFormatException {
        Reader selectedReader = (Reader) readerTableView.getSelectionModel().getSelectedItem();
        boolean isExported = LibraryBUS.printReader(selectedReader);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(isExported)
        {
            alert.setContentText("Xuất thẻ độc giả thành công!");
            alert.showAndWait();
        }
        else
        {
            alert.setContentText("Không thể xuất thẻ độc giả!");
            alert.showAndWait();
        }
    }

    public void findReaderBtnClick(ActionEvent actionEvent) {
        String info = infoReader.getText();
        int typeFilter = typeFilterComboBox.getSelectionModel().getSelectedIndex();
        int typeDisplay = typeDisplayComboBox.getSelectionModel().getSelectedIndex();
        ObservableList<Reader> filteredReaderList = LibraryBUS.filterReader(readerObservableList,info,typeFilter,typeDisplay);
        readerTableView.setItems(filteredReaderList);
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
