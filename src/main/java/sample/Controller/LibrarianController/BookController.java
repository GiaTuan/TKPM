package sample.Controller.LibrarianController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import sample.BUS.LibraryBUS;
import sample.DAO.LibraryDAO;
import sample.POJO.GroupBook;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    GroupBook groupBook;
    @FXML
    TextField textFieldInfoBtn;
    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/LibrarianFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void findBookBtnClick(ActionEvent actionEvent) throws IOException {
        String info = textFieldInfoBtn.getText();
        if(!info.equals(""))
        {
            String groupBookId = LibraryBUS.getGroupBookFromInputTextField(info);
            groupBook = LibraryBUS.getGroupBookFromId(Integer.parseInt(groupBookId));
            if(groupBook != null) {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/FindBookFXML.fxml"));
                Parent root = fxmlLoader.load();
                FindBookController findBookController = fxmlLoader.getController();
                findBookController.setGroupBook(groupBook);
                stage.setTitle("Thủ thư");
                stage.setScene(new Scene(root, 1000, 600));
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Sách không có trong hệ thống");
                alert.showAndWait();
            }
        }
    }

    public void changeReaderFXMLBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<GroupBook> groupBookList = LibraryDAO.getGroupBookList(false);
        TextFields.bindAutoCompletion(textFieldInfoBtn,groupBookList);
    }
}
