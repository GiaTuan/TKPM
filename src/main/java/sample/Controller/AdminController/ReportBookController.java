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
import sample.BUS.LibraryBUS;
import sample.POJO.Report;
import sample.Window.AdminWindow.DetailReportWindow;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReportBookController implements Initializable {

    @FXML
    TableView reportTableView;
    @FXML
    TableColumn<Report,Integer> idReportCol;
    @FXML
    TableColumn<Report,String> idBookCol;
    @FXML
    TableColumn<Report,Integer> idReaderCol;
    @FXML
    TableColumn<Report,String> detailCol;
    @FXML
    TableColumn<Report,Integer> deleteCol;

    ObservableList<Report> reportObservableList;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Report> list = LibraryBUS.getReportList(false);
        setupReportTable(list);
    }

    private void setupReportTable(List<Report> list) {
        reportObservableList = FXCollections.observableArrayList();
        reportObservableList.addAll(list);

        idReportCol.setCellValueFactory(new PropertyValueFactory<>("idReport"));
        idBookCol.setCellValueFactory(new PropertyValueFactory<>("idBook"));
        idReaderCol.setCellValueFactory(new PropertyValueFactory<>("idReader"));
        detailCol.setCellValueFactory(new PropertyValueFactory<>("detailReport"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("isDeleted"));
        reportTableView.setItems(reportObservableList);
    }

    public void manangeCompensateBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/CompensateFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void detailReportBtnClick(ActionEvent actionEvent) throws IOException {
        Report report = (Report) reportTableView.getSelectionModel().getSelectedItem();
        DetailReportWindow.display(report);
    }

    public void deleteBtnClick(ActionEvent actionEvent) {
        Report report = (Report) reportTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(report.getIsDeleted() == 1)
        {
            alert.setContentText("Báo cáo đã được xóa trước đó");
            alert.showAndWait();
        }
        else
        {
            LibraryBUS.deleteReport(report);
            reportTableView.refresh();
            alert.setContentText("Xoá báo cáo thành công");
            alert.showAndWait();
        }
    }

    @FXML
    ComboBox typeCombobox;
    public void findBtnClick(ActionEvent actionEvent) {
        int type = typeCombobox.getSelectionModel().getSelectedIndex();
        ObservableList<Report> resultList = LibraryBUS.filterReportList(reportObservableList,type);
        reportTableView.setItems(resultList);
        reportTableView.refresh();
    }
}
