package sample.Controller.LibrarianController;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.Books;
import sample.POJO.GroupBook;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FindBookListController implements Initializable {
    @FXML
    Label idLabel;
    @FXML
    Label nameLabel;
    @FXML
    Label authorLabel;
    @FXML
    Label typeLabel;
    @FXML
    Label nxbLabel;
    @FXML
    Label stateLabel;
    @FXML
    TableView booksTableView;
    @FXML
    TableColumn<Books,String> idBookCol;
    @FXML
    TableColumn<Books,String> stateBookCol;


    GroupBook groupBook;

    public void changeReaderFXMLBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/LibrarianFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void refreshBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/BookFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }


    public void reportBookBtnClick(ActionEvent actionEvent) {
    }

    public void enrollQueueBtnClick(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            idLabel.setText("Mã sách: "+String.valueOf(groupBook.getIdGroupBook()));
            nameLabel.setText("Tên sách: "+groupBook.getNameBook());
            authorLabel.setText("Tác giả: "+groupBook.getAuthor());
            typeLabel.setText("Thể loại: "+groupBook.getTypeBook().getNameType());
            nxbLabel.setText("Nhà xuất bản: "+groupBook.getPublisher());
            String state = LibraryBUS.getGroupBookStateName(groupBook.getIsAvailable());
            stateLabel.setText("Trạng thái: "+state);
            setUpTableListBook();
        });
    }

    private void setUpTableListBook() {
        idBookCol.setCellValueFactory(new PropertyValueFactory<>("idBook"));
        stateBookCol.setCellValueFactory(new PropertyValueFactory<>("state"));
    //    System.out.println(groupBook.getIdGroupBook());
        List<Books> bookList = LibraryBUS.getBookList(groupBook.getIdGroupBook());
        ObservableList<Books> booksObservableList = FXCollections.observableArrayList();
        booksObservableList.addAll(bookList);
        booksTableView.setItems(booksObservableList);
    }

    public void setGroupBook(GroupBook groupBook) {
        this.groupBook = groupBook;
    }
}
