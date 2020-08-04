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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.Books;
import sample.POJO.GroupBook;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
    @FXML
    TextField groupBookName;
    @FXML
    TableView table;
    @FXML
    TableColumn<Books, String> id;
    @FXML
    TableColumn<Books, GroupBook> name;
    @FXML
    TableColumn<Books, String> status;
    @FXML
    Text nofication;

    private ObservableList<Books> tableData;

    private static int groupBookId;
    private static int notDeletedBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadInfo();
        setupTable();
        table.setItems(tableData);
        groupBookName.setText(tableData.get(0).getGroupBook().getNameBook());
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


    private void loadInfo()
    {
        tableData = FXCollections.observableArrayList(LibraryBUS.getBookList(groupBookId));

        for(Books item : tableData)
            if(item.getState().compareTo("Xóa") != 0)
                ++notDeletedBook;
    }

    public static void setGroupBookId(int groupBookIdSelected)
    {
        groupBookId = groupBookIdSelected;
    }

    private void setupTable()
    {
        id.setCellValueFactory(new PropertyValueFactory<>("idBook"));

        name.setCellValueFactory(new PropertyValueFactory<>("groupBook"));
        name.setCellFactory(col -> new TableCell<Books, GroupBook>(){
            @Override
            protected  void updateItem(GroupBook item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : item.getNameBook());
            }
        });

        status.setCellValueFactory(new PropertyValueFactory<>("state"));
    }

    @FXML
    private void deletedBtnClick()
    {
        if(table.getSelectionModel().isEmpty())
            return;
        Books selectedBooks = (Books)table.getSelectionModel().getSelectedItem();
        if(selectedBooks.getState().compareTo("Sẵn sàng") != 0)
        {
            nofication.setText("Hiện tại không thể xóa");
            return;
        }
        else
            nofication.setText("");

        notDeletedBook--;
        selectedBooks.setState("Xóa");
        table.refresh();
        LibraryBUS.deleteBook(selectedBooks.getId(), notDeletedBook);
    }

    @FXML
    private void deletedAllBtnClick()
    {

        for(Books item : tableData)
        {
            if(item.getState().compareTo("Sẵn sàng") == 0)
            {
                item.setState("Xóa");
                notDeletedBook--;
            }
        }

        table.refresh();
        LibraryBUS.deleteALLBookInGroup(tableData, notDeletedBook);
    }

}
