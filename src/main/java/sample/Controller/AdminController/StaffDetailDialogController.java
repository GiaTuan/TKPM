package sample.Controller.AdminController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.POJO.Staff;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffDetailDialogController implements Initializable {

    @FXML
    Text adminNotifyText;
    @FXML
    TextField name;
    @FXML
    TextField username;
    @FXML
    TextField addr;
    @FXML
    TextField phone;
    @FXML
    TextField email;

    private static Staff staffSelected = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            if(staffSelected.isAdmin())
                adminNotifyText.setText("Admin account");

            name.setText(staffSelected.getNameStaff());
            username.setText(staffSelected.getUsername());
            addr.setText(staffSelected.getAddrStaff());
            phone.setText(staffSelected.getPhoneStaff());
            email.setText(staffSelected.getEmailStaff());
        });
    }

    public static void setStaffSelected(Staff selecteditem)
    {
        staffSelected = selecteditem;
    }

    @FXML
    private void cancelBtnClick(ActionEvent e)
    {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

}
