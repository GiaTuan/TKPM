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
import sample.POJO.Bill;
import sample.POJO.Reader;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ExtendCardController implements Initializable {

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
    private TextField outdateTextField;
    @FXML
    private TextField feeTextField;

    private LocalDate outDate;

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

            outDate = reader.getDateMember().toLocalDate();
            outdateTextField.setText(outDate.plusYears(1).toString());
        });

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

    public void changeToBookFXMLBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/BookFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }
    public void noficationRegisterBtnClick(ActionEvent actionEvent) throws IOException {
        if(reader.getIsReceivedNofication() == 1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Tài khoản đã được đăng ký");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           if(LibraryBUS.noficationResgister(reader.getIdReader()))
               alert.setContentText("Đăng ký thành công");
           else
               alert.setContentText("Đăng ký thất bại");

            alert.showAndWait();
        }

    }

    @FXML
    private void extendBtnClick()
    {
        LocalDate today = LocalDate.now();
        LocalDate subtractedDay = today.minusMonths(1);
        double fee = Double.parseDouble(feeTextField.getText());

        if(feeTextField.getText().isEmpty())
            return;

        if(outDate.isBefore(subtractedDay))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Thẻ quá thời gian gia hạn");
            alert.showAndWait();
            return;
        }

        if(fee != 20000)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Số tiền gia hạn không hợp lệ");
            alert.showAndWait();
            return;
        }

        reader.setDateMember(Date.valueOf(today));
        Bill bill = new Bill();
        bill.setTypeBill(3);
        bill.setCreateDate(Date.valueOf(today));
        bill.setCost(fee);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(LibraryBUS.extendCard(bill, reader))
            alert.setContentText("Gia hạn thành công");
        else
            alert.setContentText("Gia hạn thất bại");

        alert.showAndWait();

    }

    public void compensateBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/CompensateFXML.fxml"));
        Parent root = fxmlLoader.
                load();
        CompensateController compensateController = fxmlLoader.getController();
        compensateController.setReader(reader);
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }
}
