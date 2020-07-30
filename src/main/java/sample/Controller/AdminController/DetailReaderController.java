package sample.Controller.AdminController;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.POJO.Reader;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DetailReaderController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField addr;
    @FXML
    TextField phone;
    @FXML
    TextField email;
    @FXML
    TextField point;
    @FXML
    TextField type;
    @FXML
    DatePicker dob;
    @FXML
    DatePicker dateMember;
    @FXML
    CheckBox isMarked;
    @FXML
    CheckBox isDeleted;


    Reader reader;

    public void setSelectedReader(Reader selectedReader) {
        reader = selectedReader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            name.setText(reader.getNameReader());
            addr.setText(reader.getAddressReader());
            phone.setText(reader.getPhoneReader());
            email.setText(reader.getEmailReader());
            point.setText(String.valueOf(reader.getPoint()));
            type.setText(reader.getTypeReader());
            dob.setValue(reader.getDateOfBirth().toLocalDate());
            dateMember.setValue(reader.getDateMember().toLocalDate());
            if(reader.getIsMarked() == 1)
            {
                isMarked.setSelected(true);
            }
            if(reader.getIsDeleted() == 1)
            {
                isDeleted.setSelected(true);
            }
        });
    }
}
