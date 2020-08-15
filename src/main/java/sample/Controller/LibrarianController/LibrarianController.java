package sample.Controller.LibrarianController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller.AdminController.ChooseTypeUserController;
import sample.POJO.Staff;

import java.io.IOException;

public class LibrarianController {

    Staff staff;
    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminFXML/ChooseAuthorizationFXML.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Chọn phân hệ");
        ChooseTypeUserController chooseTypeUserController = fxmlLoader.getController();
        chooseTypeUserController.setStaff(staff);
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void readerBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }


    public void bookBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/BookFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
