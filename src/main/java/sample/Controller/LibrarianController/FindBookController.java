package sample.Controller.LibrarianController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.POJO.GroupBook;

import java.io.IOException;

public class FindBookController {

    private GroupBook groupBook;


    public void backBtnClick(ActionEvent actionEvent) {
    }

    public void refreshBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/BookFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void listBookBtnClick(ActionEvent actionEvent) {
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
}
