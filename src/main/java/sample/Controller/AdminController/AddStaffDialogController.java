package sample.Controller.AdminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.Staff;


import java.net.URL;
import java.util.ResourceBundle;

public class AddStaffDialogController implements Initializable {

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
    @FXML
    PasswordField password;
    @FXML
    CheckBox isAdminAcount;
    @FXML
    Text nofication;
    private static Staff userSeleted = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void setUserSeleted(Staff seletedItem)
    {
        userSeleted = seletedItem;
    }

    @FXML
    private void cancleBtnClick(ActionEvent e)
    {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    private boolean checkEmptyInfo()
    {
        if(name.getText().isEmpty() || username.getText().isEmpty() || addr.getText().isEmpty() || phone.getText().isEmpty()
        || email.getText().isEmpty())
            return true;

        if(isAdminAcount.isSelected() && password.getText().isEmpty())
            return true;

        return false;
    }

    private boolean isNumber()
    {
        String phoneNumber = phone.getText();

        for(int i = 0; i < phoneNumber.length(); i++)
            if(phoneNumber.charAt(i) < '0' || phoneNumber.charAt(i) > '9')
                return false;

            return true;
    }

    private Staff buildInfo()
    {
        Staff newStaff = new Staff();
        newStaff.setUsername(username.getText());
        newStaff.setNameStaff(name.getText());
        newStaff.setAddrStaff(addr.getText());
        newStaff.setEmailStaff(email.getText());
        newStaff.setPhoneStaff(phone.getText());
        newStaff.setAdmin(isAdminAcount.isSelected());
        newStaff.setPassword(LibraryBUS.hashPassword("123456"));

        return newStaff;
    }

    @FXML
    private void createBtnClick(ActionEvent e)
    {
        if(checkEmptyInfo())
        {
            nofication.setText("Không được bỏ trống thông tin");
            return;
        }

        if(LibraryBUS.isEmailDuplicate(email.getText()) || LibraryBUS.isUsernameDuplicate(username.getText()))
        {
            nofication.setText("Email hoặc username trùng");
            return;
        }

        if(LibraryBUS.isPhoneDuplicate(phone.getText()))
        {
            nofication.setText("Trùng số điện thoại");
            return;
        }

        if(isAdminAcount.isSelected() && !LibraryBUS.checkVerifyPassword(password.getText()))
        {
            nofication.setText("Mật khẩu xác thực không chính xác");
            return;
        }

        if(!isNumber())
        {
            nofication.setText("Số điện thoại không hợp lệ");
            return;
        }

        LibraryBUS.addStaff(buildInfo());

        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }



}
