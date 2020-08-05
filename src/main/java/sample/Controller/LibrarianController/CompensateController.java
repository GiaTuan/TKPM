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
import sample.BUS.LibraryBUS;
import sample.POJO.Reader;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CompensateController implements Initializable {
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
    Label dobLabel;
    @FXML
    TextField idBookTextField;
    @FXML
    ComboBox damageComboBox;
    @FXML
    TextField numberOfDamages;
    @FXML
    Label compensateFeeLabel;



    Reader reader;
    public void changeToBookFXMLBtnClick(ActionEvent actionEvent) {
    }

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/LibrarianFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void refreshBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void changeInfoReaderBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/ChangeInfoReaderFXML.fxml"));
        Parent root = fxmlLoader.load();
        ChangeInfoReaderController changeInfoReaderController = fxmlLoader.getController();
        changeInfoReaderController.setReader(reader);
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

    public void returnBookBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/ReturnBookFXML.fxml"));
        Parent root = fxmlLoader.load();
        ReturnBookController returnBookController = fxmlLoader.getController();
        returnBookController.setReader(reader);
        stage.setTitle("Thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
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

    public void setReader(Reader reader) {
        this.reader = reader;
    }

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
        });
    }

    double fee = 0;
    public void calculateCompensateFee(ActionEvent actionEvent) {
        int selectedDamage = damageComboBox.getSelectionModel().getSelectedIndex();
        int numofDamages;
        try {
            numofDamages=Integer.valueOf(numberOfDamages.getText());
            fee = LibraryBUS.calculateCompensateFee(selectedDamage,numofDamages);
            compensateFeeLabel.setText(String.valueOf(fee));
        }catch (NumberFormatException ex)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Số lượng sách không hợp lệ");
            alert.showAndWait();
        }
    }

    public void confirmCompensate(ActionEvent actionEvent) {
        String bookId = idBookTextField.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        boolean isValidBookId = LibraryBUS.checkIsValidBookId(bookId);
        if(isValidBookId)
        {
            LibraryBUS.addCompensate(Integer.valueOf(bookId),reader.getIdReader(),fee);
            alert.setContentText("Đền bù thành công");
            alert.showAndWait();
        }
        else
        {
            alert.setContentText("Mã sách không tồn tại");
            alert.showAndWait();
        }
    }
}
