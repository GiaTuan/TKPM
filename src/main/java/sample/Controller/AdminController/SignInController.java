package sample.Controller.AdminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.Staff;

import java.io.IOException;

public class SignInController {

    @FXML
    TextField username;
    @FXML
    PasswordField password;

    Staff staff;
    public void signinBtnClick(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //check username and password later
        String user = username.getText();
        String pass =password.getText();
        if(!user.equals("") && !pass.equals(""))
        {
            staff = LibraryBUS.checkValidUser(user,pass);
            if(staff != null)
            {
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminFXML/ChooseAuthorizationFXML.fxml"));
                Parent root = fxmlLoader.load();
                stage.setTitle("Chọn phân hệ");
                ChooseTypeUserController chooseTypeUserController = fxmlLoader.getController();
                chooseTypeUserController.setStaff(staff);
                stage.setScene(new Scene(root, 1000, 600));
            }
            else //show alert
            {
                alert.setContentText("Tên đăng nhập hoặc mật khẩu không chính xác");
                alert.showAndWait();
            }
        }
        else
        {
            alert.setContentText("Chưa điền đủ thông tin đăng nhập");
            alert.showAndWait();
        }
    }

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/SignInFXML.fxml"));
        stage.setTitle("Đăng nhập hệ thống");
        stage.setScene(new Scene(root, 1000, 600));
    }
}
