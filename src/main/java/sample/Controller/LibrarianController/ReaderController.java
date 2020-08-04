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
import sample.POJO.Reader;
import sample.Window.LibrarianWindow.SignUpReaderWindow;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReaderController implements Initializable {
    @FXML
    TextField textFieldInfoBtn;


    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/LibrarianFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Reader> list = LibraryBUS.getReaderList(false);
        TextFields.bindAutoCompletion(textFieldInfoBtn,list);
    }

    Reader reader;
    public void findReaderBtnClick(ActionEvent actionEvent) throws IOException {
        String infoReader = textFieldInfoBtn.getText();
        if(!infoReader.equals(""))
        {
            String readerPhone = LibraryBUS.getReaderPhoneFromInputTextField(infoReader);
            reader = LibraryBUS.getReaderFromPhone(readerPhone);
            if(reader != null) {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/FindReaderFXML.fxml"));
                Parent root = fxmlLoader.load();
                FindReaderController findReaderController = fxmlLoader.getController();
                findReaderController.setReader(reader);
                stage.setTitle("Thủ thư");
                stage.setScene(new Scene(root, 1000, 600));
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Thông tin độc giả không có trong hệ thống");
                alert.showAndWait();
            }
        }
    }

    public void signUpBtnClick(ActionEvent actionEvent) throws IOException {
        SignUpReaderWindow.display();
    }

    public void changeToBookFXMLBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/BookFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }
}
