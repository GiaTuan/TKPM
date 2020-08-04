package sample.Controller.LibrarianController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.BUS.LibraryBUS;

import java.time.LocalDate;

public class SignUpReaderController {
    @FXML
    TextField nameTextField;
    @FXML
    TextField phoneTextField;
    @FXML
    TextField mailTextField;
    @FXML
    TextField addTextField;
    @FXML
    DatePicker dobDatePicker;

    public void signUpBtnClick(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String phone = phoneTextField.getText();
        String mail = mailTextField.getText();
        String add = addTextField.getText();
        LocalDate dob = dobDatePicker.getValue();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(!name.equals("") && !phone.equals("") && !mail.equals("") && !add.equals("") && dob!=null)
        {
            //check phone
            boolean phoneCheck = LibraryBUS.checkPhoneReader(phone);
            if(phoneCheck)
            {
                boolean emailCheck = LibraryBUS.checkEmailReader(mail);
                if(emailCheck)
                {
                    boolean isAdded = LibraryBUS.addReader(name,phone,mail,add,dob);
                    if(isAdded)
                    {
                        alert.setContentText("Thêm độc giả thành công");
                        alert.showAndWait();
                    }
                }
                else
                {
                    alert.setContentText("Email đã tồn tại");
                    alert.showAndWait();
                }
            }
            else
            {
                alert.setContentText("Số điện thoại đã tồn tại");
                alert.showAndWait();
            }
        }
        else
        {
            alert.setContentText("Thông tin điền vào không hợp lệ");
            alert.showAndWait();
        }
    }
}
