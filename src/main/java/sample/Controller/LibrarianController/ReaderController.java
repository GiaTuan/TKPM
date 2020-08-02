package sample.Controller.LibrarianController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import sample.BUS.LibraryBUS;
import sample.POJO.Reader;


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
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Reader> list = LibraryBUS.getReaderList(false);
        TextFields.bindAutoCompletion(textFieldInfoBtn,list);
    }

    Reader reader;
    public void findReaderBtnClick(ActionEvent actionEvent) {
        String readerPhone = textFieldInfoBtn.getText().split("- ")[1];
        reader = LibraryBUS.getReaderFromPhone(readerPhone);
        System.out.println(readerPhone);
    }
}
