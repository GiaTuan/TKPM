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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.Controller.LibrarianController.ChangeInfoReaderController;
import sample.Controller.LibrarianController.ExtendCardController;
import sample.Controller.LibrarianController.RentBookController;
import sample.DAO.LibraryDAO;
import sample.POJO.Reader;
import sample.POJO.RentBook;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReturnBookController implements Initializable {
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
    TextField rentIdTextField;
    @FXML
    VBox returnBookInfoVBox;
    @FXML
    Label idRentBookLabel;
    @FXML
    Label idReaderLabel;
    @FXML
    Label numberOfBooksLabel;
    @FXML
    Label listBooksLabel;
    @FXML
    Label dateRentBookLabel;
    @FXML
    Label depositLabel;
    @FXML
    Label numberOfDaysRentLabel;
    @FXML
    Label rentFeeLabel;


    Reader reader;

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void changeToBookFXMLBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/BookFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
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

    RentBook rentBook;
    public void findRentBookBtnClick(ActionEvent actionEvent) {
        String idRentBook = rentIdTextField.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(!idRentBook.equals(""))
        {
            rentBook = LibraryBUS.getRentBookById(idRentBook);
            if(rentBook!= null)
            {
                idRentBookLabel.setText(String.valueOf(rentBook.getIdRentBook()));
                idReaderLabel.setText(String.valueOf(rentBook.getIdReaderRent()));
                numberOfBooksLabel.setText(String.valueOf(rentBook.getNumberBooksRent()));
                listBooksLabel.setText(rentBook.getListRentBook());
                dateRentBookLabel.setText(rentBook.getRentDate().toString());
                depositLabel.setText(String.valueOf(rentBook.getDepositFee()));
                numberOfDaysRentLabel.setText(String.valueOf(LibraryBUS.getDayBetween(rentBook.getRentDate().toLocalDate(), LocalDate.now())));
                returnBookInfoVBox.setVisible(true);
            }
            else
            {
                alert.setContentText("Mã phiếu mượn vừa nhập không tồn tại");
                alert.showAndWait();
            }
        }
        else
        {
            alert.setContentText("Bạn chưa điền mã sách");
            alert.showAndWait();
        }
    }

    public void getRentFeeBtnClick(ActionEvent actionEvent) {
        Double rentFee = LibraryBUS.calculateRentFee(rentBook);
        rentFeeLabel.setText(String.valueOf(rentFee) + "đ");
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

    public void confirmReturn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(rentFeeLabel.getText().equals(""))
        {
            alert.setContentText("Chưa tính phí mượn");
            alert.showAndWait();
        }
        else
        {
            LibraryBUS.confirmReturn(rentBook);
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Xác nhận trả sách thành công. Bạn có muốn in phiếu trả?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.get() == ButtonType.OK)
            {
                LibraryBUS.printReturnBook(rentBook);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("In phiếu trả thành công");
                alert.showAndWait();
            }
        }
    }

    public void compensateBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/CompensateFXML.fxml"));
        Parent root = fxmlLoader.load();
        CompensateController compensateController = fxmlLoader.getController();
        compensateController.setReader(reader);
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
