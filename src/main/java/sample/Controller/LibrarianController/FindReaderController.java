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
import org.controlsfx.control.textfield.TextFields;
import sample.BUS.LibraryBUS;
import sample.POJO.Reader;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FindReaderController implements Initializable {
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

    public void changeToBookFXMLBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LibrarianFXML/BookFXML.fxml"));
        stage.setTitle("Phân hệ thủ thư");
        stage.setScene(new Scene(root, 1000, 600));
    }
}
