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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.DAO.LibraryDAO;
import sample.POJO.Books;
import sample.POJO.GroupBook;
import sample.POJO.Reader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportBookController implements Initializable {
    @FXML
    Label idLabel;
    @FXML
    Label nameLabel;
    @FXML
    Label authorLabel;
    @FXML
    Label typeLabel;
    @FXML
    Label nxbLabel;
    @FXML
    Label stateLabel;
    @FXML
    TextField idBookTextField;
    @FXML
    TextField phoneReaderTextField;
    @FXML
    TextArea infoReportTextArea;

    GroupBook groupBook;
    public void changeReaderFXMLBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/ReaderFXML.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/BookFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void listBookBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/ListBookFXML.fxml"));
        Parent root = fxmlLoader.load();
        FindBookListController findBookListController = fxmlLoader.getController();
        findBookListController.setGroupBook(groupBook);
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            idLabel.setText("Mã sách: "+String.valueOf(groupBook.getIdGroupBook()));
            nameLabel.setText("Tên sách: "+groupBook.getNameBook());
            authorLabel.setText("Tác giả: "+groupBook.getAuthor());
            typeLabel.setText("Thể loại: "+groupBook.getTypeBook().getNameType());
            nxbLabel.setText("Nhà xuất bản: "+groupBook.getPublisher());

            String state = LibraryBUS.getGroupBookStateName(groupBook.getIsAvailable());
            stateLabel.setText("Trạng thái: "+state);
        });
    }

    public void setGroupBook(GroupBook groupBook) {
        this.groupBook= groupBook;
    }

    public void confirmReportBtnClick(ActionEvent actionEvent) {
        String idBook = idBookTextField.getText();
        String phoneReader = phoneReaderTextField.getText();
        String infoReport = infoReportTextArea.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(!idBook.equals("") && !phoneReader.equals("") && !infoReport.equals(""))
        {
            Books books = LibraryBUS.getBooksFromId(idBook);
            if(books != null)
            {
                Reader reader = LibraryBUS.getReaderFromPhone(phoneReader);
                if(reader != null)
                {
                    LibraryBUS.addReport(books,reader,infoReport);
                    alert.setContentText("Báo cáo thành công");
                    alert.showAndWait();
                }
                else
                {
                    alert.setContentText("Số điện thoại không hợp lệ");
                    alert.showAndWait();
                }
            }
            else
            {
                alert.setContentText("Mã sách không hợp lệ");
                alert.showAndWait();
            }
        }
        else
        {
            alert.setContentText("Chưa điền đủ thông tin");
            alert.showAndWait();
        }
    }
}
