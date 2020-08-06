package sample.Controller.LibrarianController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import sample.BUS.LibraryBUS;
import sample.Controller.ReturnBookController;
import sample.POJO.Reader;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChangeInfoReaderController implements Initializable {
    @FXML
    Label nameLabel;
    @FXML
    Label dobLabel;
    @FXML
    Label phoneLabel;
    @FXML
    Label emailLabel;
    @FXML
    Label addressLabel;
    @FXML
    Label typeLabel;
    @FXML
    Label pointLabel;
    @FXML
    Label idReaderLabel;
    @FXML
    TextField nameReaderTextField;
    @FXML
    DatePicker dobDatePicker;
    @FXML
    TextField phoneReaderTextField;
    @FXML
    TextField mailReaderTextField;
    @FXML
    TextField addrReaderTextField;
    @FXML
    Label pointReaderLabel;
    @FXML
    Label typeReaderLabel;

    Reader reader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            nameLabel.setText("Họ tên: "+reader.getNameReader());
            dobLabel.setText("Ngày sinh: "+reader.getDateOfBirth().toString());
            phoneLabel.setText("Số điện thoại: "+reader.getPhoneReader());
            emailLabel.setText("Email: "+reader.getEmailReader());
            addressLabel.setText("Địa chỉ: "+reader.getAddressReader());
            typeLabel.setText("Hạng độc giả: "+reader.getTypeReader());
            pointLabel.setText("Điểm độc giả: "+String.valueOf(reader.getPoint()));

            idReaderLabel.setText(String.valueOf(reader.getIdReader()));
            nameReaderTextField.setText(reader.getNameReader());
            dobDatePicker.setValue(reader.getDateOfBirth().toLocalDate());
            phoneReaderTextField.setText(reader.getPhoneReader());
            mailReaderTextField.setText(reader.getEmailReader());
            addrReaderTextField.setText(reader.getAddressReader());
            pointReaderLabel.setText(String.valueOf(reader.getPoint()));
            typeReaderLabel.setText(reader.getTypeReader());
        });

    }

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/LibrarianFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void findReaderBtnClick(ActionEvent actionEvent) {

    }

    public void refreshBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void printReaderBtnClick(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        boolean res = LibraryBUS.printReader(reader);
        if(res)
        {
            alert.setContentText("In thẻ độc giả thành công");
        }
        else
        {
            alert.setContentText("In thẻ độc giả thất bại. Vui lòng kiểm tra lại");
        }
        alert.showAndWait();
    }

    public void markReaderBtnClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(reader.getIsMarked() == 1)
        {
            alert.setContentText("Độc giả đã bị đánh dấu trước đó");
            alert.showAndWait();
        }
        else
        {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setContentText("Bạn có muốn tiếp tục thực hiện đánh dấu?");
            Optional<ButtonType> buttonType = confirmAlert.showAndWait();
            if(buttonType.get() == ButtonType.OK) {
                boolean isMarked = LibraryBUS.markReader(reader.getIdReader());
                if (isMarked) {
                    reader.setIsMarked(1);
                    alert.setContentText("Đánh dấu độc giả thành công");
                    alert.showAndWait();
                }
            }
        }
    }


    public void returnBookBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/ReturnBookFXML.fxml"));
        Parent root = fxmlLoader.load();
        ReturnBookController returnBookController = fxmlLoader.getController();
        returnBookController.setReader(reader);
        stage.setTitle("Thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void changeInfoBtnClick(ActionEvent actionEvent) {
        String name = nameReaderTextField.getText();
        String phone = phoneReaderTextField.getText();
        String mail = mailReaderTextField.getText();
        String addr= addrReaderTextField.getText();
        LocalDate dob = dobDatePicker.getValue();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(!name.equals("") && !phone.equals("") && !mail.equals("") && !addr.equals("") && dob != null)
        {
            boolean isUpdated = LibraryBUS.updateInfoReader(reader.getIdReader(),name,phone,mail,addr,dob);
            if(isUpdated){
                nameLabel.setText("Họ tên: "+ name);
                dobLabel.setText("Ngày sinh: "+ dob.toString());
                phoneLabel.setText("Số điện thoại: "+ phone);
                emailLabel.setText("Email: "+ mail);
                addressLabel.setText("Địa chỉ: "+ addr);
                alert.setContentText("Thay đổi thông tin thành công");
                alert.showAndWait();
            }
        }
        else
        {
            alert.setContentText("Thông tin điền vào không hợp lệ");
            alert.showAndWait();
        }
    }

    public void changeToBookFXMLBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/BookFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void rentBookBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(RentBookController.class.getClass().getResource("/fxml/LibrarianFXML/RentBookFXML.fxml"));
        Parent root = fxmlLoader.load();
        RentBookController rentBookControllerController = fxmlLoader.getController();
        rentBookControllerController.setReader(reader);
        stage.setTitle("Thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void extendCardBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/ExtendCardFXML.fxml"));
        Parent root = fxmlLoader.load();
        ExtendCardController extendCardController = fxmlLoader.getController();
        extendCardController.setReader(reader);
        stage.setTitle("Thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void noficationRegisterBtnClick(ActionEvent actionEvent) {
        if (reader.getIsReceivedNofication() == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Tài khoản đã được đăng ký");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (LibraryBUS.noficationResgister(reader.getIdReader()))
                alert.setContentText("Đăng ký thành công");
            else
                alert.setContentText("Đăng ký thất bại");

            alert.showAndWait();
        }
    }

}
