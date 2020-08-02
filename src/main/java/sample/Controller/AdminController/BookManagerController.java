package sample.Controller.AdminController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.Books;
import sample.POJO.GroupBook;
import sample.POJO.Publisher;
import sample.POJO.TypeBook;
import sample.Window.AddGroupBookDialogWindow;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookManagerController implements Initializable {

    @FXML
    TextField filterText;
    @FXML
    ComboBox displayMode;
    @FXML
    TableView table;
    @FXML
    TableColumn<GroupBook, Integer> id;
    @FXML
    TableColumn<GroupBook, String> name;
    @FXML
    TableColumn<GroupBook, Integer> amount;
    @FXML
    TableColumn<GroupBook, TypeBook> type;
    @FXML
    TableColumn<GroupBook, String> author;
    @FXML
    TableColumn<GroupBook, Publisher> publisher;
    @FXML
    TableColumn<GroupBook, Integer> status;

    private ObservableList<GroupBook> originalData = null;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        originalData = FXCollections.observableArrayList();
        loadInfo();
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

    public void manageBookBtnClick(ActionEvent actionEvent) {
//        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChooseAuthorizationFXML.fxml"));
//        stage.setTitle("Phân hệ quản lý");
//        stage.setScene(new Scene(root, 1000, 600));
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

    private void loadInfo()
    {
        originalData = FXCollections.observableArrayList(LibraryBUS.getGroupBookList(false));
        setupTable();
        table.setItems(originalData);
    }

    private void setupTable()
    {
        id.setCellValueFactory(new PropertyValueFactory<>("idGroupBook"));
        name.setCellValueFactory(new PropertyValueFactory<>("nameBook"));

        amount.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        type.setCellValueFactory(new PropertyValueFactory<>("typeBook"));
        type.setCellFactory(col -> new TableCell<GroupBook, TypeBook>(){
            @Override
            protected void updateItem(TypeBook item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : item.getNameType());
            }
        });

        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        status.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
        status.setCellFactory(col -> new TableCell<GroupBook, Integer>(){
            @Override
            protected  void updateItem(Integer item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : item == 0 ? "Chưa nhập" : "Sẵn sàng");
            }
        });
    }

    @FXML
    private void addBook() throws IOException
    {
        AddGroupBookDialogWindow.display();
    }
}

