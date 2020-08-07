package sample.Controller.AdminController;

import javafx.application.Platform;
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
import sample.DAO.LibraryDAO;
import sample.POJO.GroupBook;
import sample.POJO.QueueRentBook;
import sample.POJO.Reader;
import sample.POJO.RentBook;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class QueueRentBookController implements Initializable {

    @FXML
    private TextField groupBookName;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<QueueRentBook, Integer> idCol;
    @FXML
    private TableColumn<QueueRentBook, Integer> readerNameCol;
    @FXML
    private TableColumn<QueueRentBook, Date> registerDateCol;
    @FXML
    private TableColumn<QueueRentBook, Double> pointCol;
    @FXML
    private TableColumn<QueueRentBook, Integer> isDeletedCol;


    private static GroupBook groupBook;
    private ObservableList<QueueRentBook> tableData = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() ->{
            groupBookName.setText(groupBook.getNameBook());

            setupTable();
            loadInfo();
            table.setItems(tableData);
        });

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

    public void manageReaderBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public static void setGroupBook(GroupBook groupBookSelected)
    {
        groupBook = groupBookSelected;
    }

    private void loadInfo()
    {
        tableData.clear();
        tableData.addAll(LibraryBUS.getQueueRentBookFromGroupBookId(groupBook.getIdGroupBook()));

        LocalDate today = LocalDate.now();

        for(QueueRentBook item : tableData)
        {
            double point = 10;
            Reader reader = LibraryBUS.getReaderFromId(item.getIdReader());
            int accountLevel = LibraryBUS.getReaderTypeByTypeName(reader.getTypeReader());
            int numberOfDay = LibraryBUS.getDayBetween(item.getRentDate().toLocalDate(), today);
            point += accountLevel * 5 + 10 * numberOfDay;

            item.setPoint(point);
        }
    }

    private void setupTable()
    {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idQueueRentBook"));

        readerNameCol.setCellValueFactory(new PropertyValueFactory<>("idReader"));
        /*readerNameCol.setCellFactory(col -> new TableCell<QueueRentBook, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty)
            {
                super.updateItem(item, empty);
                Reader reader = LibraryBUS.getReaderFromId(item);
                String name = reader.getNameReader();
                setText(empty ? null : name);
            }
        });*/

        registerDateCol.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        pointCol.setCellValueFactory(new PropertyValueFactory<>("point"));

        isDeletedCol.setCellValueFactory(new PropertyValueFactory<>("isDeleted"));
        isDeletedCol.setCellFactory(col -> new TableCell<QueueRentBook, Integer>(){
            @Override
            protected void updateItem(Integer item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : item == 0 ? "Trong hàng đợi" : "Đã xóa");
            }
        });

    }

    @FXML
    private void deletedBtnClick()
    {
        if(table.getSelectionModel().getSelectedItem() == null)
            return;

        QueueRentBook queueRentBook = (QueueRentBook)table.getSelectionModel().getSelectedItem();

        if(queueRentBook.getIsDeleted() == 1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Tài khoản đã xóa khỏi hàng đợi");
            alert.showAndWait();
            return;
        }

        queueRentBook.setIsDeleted(1);
        LibraryBUS.updateQueueRentBookStatus(queueRentBook);
        table.refresh();
    }


}
