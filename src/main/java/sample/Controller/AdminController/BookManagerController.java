package sample.Controller.AdminController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.BUS.LibraryBUS;
import sample.POJO.Books;
import sample.POJO.GroupBook;
import sample.POJO.Publisher;
import sample.POJO.TypeBook;

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
    public void initialize(URL location, ResourceBundle resources) {
        loadInfo();
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
        publisher.setCellFactory(col -> new TableCell<GroupBook, Publisher>(){
            @Override
            protected  void updateItem(Publisher item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        });
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
}
