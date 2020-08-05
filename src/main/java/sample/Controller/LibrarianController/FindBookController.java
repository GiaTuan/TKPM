package sample.Controller.LibrarianController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import sample.BUS.LibraryBUS;

import sample.POJO.GroupBook;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FindBookController implements Initializable {
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

    GroupBook groupBook;

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

    public void listBookBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/ListBookFXML.fxml"));
        Parent root = fxmlLoader.load();
        FindBookListController findBookListController = fxmlLoader.getController();
        findBookListController.setGroupBook(groupBook);
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void reportBookBtnClick(ActionEvent actionEvent) {
    }

    public void enrollQueueBtnClick(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/QueueBookRegisterFXML.fxml"));
        Parent root = fxmlLoader.load();
        QueueBookRegisterController queueBookRegisterController = fxmlLoader.getController();
        queueBookRegisterController.setGroupBook(groupBook);
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void changeReaderFXMLBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }


    public void setGroupBook(GroupBook groupBook) {
        this.groupBook = groupBook;
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
        });
    }
}
