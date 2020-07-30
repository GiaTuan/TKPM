package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sample.POJO.GroupBook;
import sample.POJO.Publisher;
import sample.POJO.TypeBook;
import sample.SessionUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Publisher groupBook = session.get(Publisher.class,1);
        transaction.commit();
        session.close();

    }

    @FXML
    TextField username;
    @FXML
    PasswordField password;

    public void signinBtnClick(ActionEvent actionEvent) throws IOException {
        boolean res = true;
        //check username and password later

        //if res = true -> change screen
        if(res)
        {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChooseAuthorizationFXML.fxml"));
            stage.setTitle("Chọn phân hệ");
            stage.setScene(new Scene(root, 1000, 600));
        }
        else //show alert
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Tên đăng nhập hoặc mật khẩu không chính xác");
            alert.showAndWait();
        }
    }

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignInFXML.fxml"));
        stage.setTitle("Đăng nhập hệ thống");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void adminBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }
}
