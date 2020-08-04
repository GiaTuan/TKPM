package sample.Controller.AdminController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
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

    public static boolean isChanged = false;

    public void updateReaderBtnClick(ActionEvent actionEvent) {
        isChanged = true;

        String nameReader = name.getText();
        String addReader = addr.getText();
        String phoneReader = phone.getText();
        String emailReader = email.getText();
        LocalDate dobReader = dob.getValue();
        LocalDate memberDate = dateMember.getValue();
        boolean isMarkedReader = isMarked.isSelected();
        boolean isDeletedReader = isDeleted.isSelected();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if(!nameReader.equals("") && !addReader.equals("") && !phoneReader.equals("") && !emailReader.equals("") && dobReader != null && memberDate != null)
        {
            boolean isUpdated = LibraryBUS.updateReader(reader.getIdReader(),nameReader,addReader,phoneReader,emailReader,dobReader,memberDate,isMarkedReader,isDeletedReader);
            if(isUpdated)
            {
                alert.setContentText("Thay đổi thông tin độc giả thành công");
                alert.showAndWait();
            }
        }
        else
        {
            alert.setContentText("Thông tin độc giả không hợp lệ. Vui lòng điền lại thông tin");
            alert.showAndWait();
        }
    }

    public void cancelBtnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
