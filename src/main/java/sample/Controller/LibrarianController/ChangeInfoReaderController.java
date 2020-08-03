package sample.Controller.LibrarianController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.Reader;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChangeInfoReaderController implements Initializable {
    @FXML
    Label nameLabel;
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
            phoneLabel.setText("Số điện thoại: "+reader.getPhoneReader());
            emailLabel.setText("Email: "+reader.getEmailReader());
            addressLabel.setText("Địa chỉ: "+reader.getAddressReader());
            typeLabel.setText("Hạng độc giả: "+reader.getTypeReader());
            pointLabel.setText("Điểm độc giả: "+String.valueOf(reader.getPoint()));

            idReaderLabel.setText(String.valueOf(reader.getIdReader()));
            nameReaderTextField.setText(reader.getNameReader());
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
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void findReaderBtnClick(ActionEvent actionEvent) {

    }

    public void refreshBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/ReaderFXML.fxml"));
        stage.setTitle("Thủ thư");
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

    public void rentBookBtnClick(ActionEvent actionEvent) throws IOException {
//        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/FindReaderFXML.fxml"));
//        Parent root = fxmlLoader.load();
//        FindReaderController findReaderController = fxmlLoader.getController();
//        findReaderController.setReader(reader);
//        stage.setTitle("Thủ thư");
//        stage.setScene(new Scene(root, 1000, 600));
    }

    public void returnBookBtnClick(ActionEvent actionEvent) throws IOException {
//        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/FindReaderFXML.fxml"));
//        Parent root = fxmlLoader.load();
//        FindReaderController findReaderController = fxmlLoader.getController();
//        findReaderController.setReader(reader);
//        stage.setTitle("Thủ thư");
//        stage.setScene(new Scene(root, 1000, 600));
    }

    public void changeInfoBtnClick(ActionEvent actionEvent) {
        String name = nameReaderTextField.getText();
        String phone = phoneReaderTextField.getText();
        String mail = mailReaderTextField.getText();
        String addr= addrReaderTextField.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(!name.equals("") && !phone.equals("") && !mail.equals("") && !addr.equals(""))
        {
            boolean isUpdated = LibraryBUS.updateInfoReader(reader.getIdReader(),name,phone,mail,addr);
            if(isUpdated){
                nameLabel.setText("Họ tên: "+ name);
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
}
