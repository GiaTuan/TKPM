package sample.Controller.AdminController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controller.LibrarianController.LibrarianController;
import sample.POJO.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseTypeUserController implements Initializable {
    @FXML
    Button librarianBtn;
    @FXML
    Button adminBtn;
    Staff staff;
    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/SignInFXML.fxml"));
        stage.setTitle("Đăng nhập hệ thống");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void adminBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/AdminFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void librarianBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/LibrarianFXML.fxml"));
        Parent root = fxmlLoader.load();
        LibrarianController librarianController = fxmlLoader.getController();
        librarianController.setStaff(staff);
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            Platform.runLater(() -> {
                if (!staff.isAdmin()) {
                    adminBtn.setDisable(true);
                }
            });
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
