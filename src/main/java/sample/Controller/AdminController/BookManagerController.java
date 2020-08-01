package sample.Controller.AdminController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.POJO.GroupBook;

import java.net.URL;
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
    TableColumn<GroupBook, String> type;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
