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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.DAO.LibraryDAO;
import sample.POJO.Books;
import sample.POJO.GroupBook;
import sample.POJO.QueueRentBook;
import sample.POJO.Reader;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class QueueBookRegisterController implements Initializable {
    @FXML
    Label idGroupBook;
    @FXML
    Label bookName;
    @FXML
    Label bookAuthor;
    @FXML
    Label publisher;
    @FXML
    Label state;
    @FXML
    TextField readerPhone;

    private GroupBook groupBook;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            idGroupBook.setText("Mã đầu sách : " + groupBook.getIdGroupBook());
            bookName.setText("Tên sách : " + groupBook.getNameBook());
            bookAuthor.setText("Tác giả : " + groupBook.getAuthor());
            publisher.setText("Nhà xuất bản : " + groupBook.getPublisher());
            state.setText("Trạng thái : " + LibraryBUS.getGroupBookStateName(groupBook.getIsAvailable()));
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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/BookFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void listBookBtnClick(ActionEvent actionEvent) {
    }

    public void reportBookBtnClick(ActionEvent actionEvent) {
    }

    public void enrollQueueBtnClick(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/QueueBookRegisterFXML.fxml"));
        Parent root = fxmlLoader.load();
        QueueBookRegisterController queueBookRegisterController = fxmlLoader.getController();
        queueBookRegisterController.setGroupBook(groupBook);
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void changeReaderFXMLBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void setGroupBook(GroupBook groupBook) {
        this.groupBook = groupBook;
    }

    private void showAlert(String nofication)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(nofication);
        alert.showAndWait();
    }

    @FXML
    private void confirmBtnClick()
    {
        if(readerPhone.getText().isEmpty())
            return;

        Reader reader = LibraryBUS.getReaderFromPhone(readerPhone.getText());

        if(reader == null)
        {
            showAlert("Độc giả không tồn tại");
            return;
        }

        if(LibraryBUS.isReaderEnrollQueueBook(reader.getIdReader(), groupBook.getIdGroupBook()))
        {
            showAlert("Tài khoản đã đăng kí vào hàng đợi");
            return;
        }

        QueueRentBook record = new QueueRentBook();

        record.setIdGroupBook(groupBook.getIdGroupBook());
        record.setIdReader(reader.getIdReader());
        record.setRentDate(Date.valueOf(LocalDate.now()));
        record.setIsDeleted(0);
        record.setPoint(0);

        if(LibraryBUS.queueRentBookRegister(record))
            showAlert("Đăng kí thành công");
        else
            showAlert("Đăng kí thất bại");
    }
}
