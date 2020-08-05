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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.Reader;
import sample.POJO.RentBook;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class RentBookController implements Initializable {

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
    TextField rentBookAmount;
    @FXML
    TextField firstBook;
    @FXML
    TextField secondBook;
    @FXML
    TextField thirdBook;
    @FXML
    TextField fourBook;
    @FXML
    Text rentBookFee;

    private Reader reader;
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

    private void showAlert(String nofication)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(nofication);
        alert.showAndWait();
    }

    private boolean checkEmptyBookName()
    {
        int numberOfRentBook = Integer.parseInt(rentBookAmount.getText());

        if(numberOfRentBook > 0 && (firstBook.getText().isEmpty() || firstBook.getText().compareTo("") == 0))
        {
            showAlert("Thông tin sách mượn chưa được điền");
            return false;
        }

        if(numberOfRentBook > 1 && (secondBook.getText().isEmpty() || secondBook.getText().compareTo("") == 0))
        {
            showAlert("Thông tin sách mượn chưa được điền");
            return false;
        }

        if(numberOfRentBook > 2 && (thirdBook.getText().isEmpty() || thirdBook.getText().compareTo("") == 0))
        {
            showAlert("Thông tin sách mượn chưa được điền");
            return false;
        }

        if(numberOfRentBook > 3 && (fourBook.getText().isEmpty() || fourBook.getText().compareTo("") == 0))
        {
            showAlert("Thông tin sách mượn chưa được điền");
            return false;
        }

        return true;

    }


    private boolean verifyRentBook()
    {
        if(rentBookAmount.getText().isEmpty())
            return false;

        if(!LibraryBUS.isNumber(rentBookAmount.getText()))
        {
            showAlert("Số lượng sách được mượn không phù hợp");
            return false;
        }

        int numberOfRentBook = Integer.parseInt(rentBookAmount.getText());

        if(numberOfRentBook > 4 || numberOfRentBook < 0)
        {
            showAlert("Số lượng sách được mượn không phù hợp");
            return false;
        }

        String alertContent = "Số sách mượn và danh sách sách mượn không trùng khớp";

        if(numberOfRentBook < 1)
        {
            showAlert("Số lượng sách được mượn không phù hợp");
            return false;
        }

        if(numberOfRentBook < 2 && (!secondBook.getText().isEmpty() || secondBook.getText().compareTo("") != 0))
        {
            showAlert(alertContent);
            return false;
        }

        if(numberOfRentBook < 3 && (!thirdBook.getText().isEmpty() || thirdBook.getText().compareTo("") != 0))
        {
            showAlert(alertContent);
            return false;
        }

        if(numberOfRentBook < 4 && (!fourBook.getText().isEmpty() || fourBook.getText().compareTo("") != 0))
        {
            showAlert(alertContent);
            return false;
        }

        return true;
    }

    private boolean checkBookId()
    {
        int numberOfRentBook = Integer.parseInt(rentBookAmount.getText());

        if(numberOfRentBook > 0 && !LibraryBUS.isGroupBookIdValid(firstBook.getText()))
        {
            showAlert("Mã sách 1 không phù hợp");
            return false;
        }
        if(numberOfRentBook > 1 && !LibraryBUS.isGroupBookIdValid(secondBook.getText()))
        {
            showAlert("Mã sách 2 không phù hợp");
            return false;
        }
        if(numberOfRentBook > 2 && !LibraryBUS.isGroupBookIdValid(thirdBook.getText()))
        {
            showAlert("Mã sách 3 không phù hợp");
            return false;
        }
        if(numberOfRentBook > 3 && !LibraryBUS.isGroupBookIdValid(fourBook.getText()))
        {
            showAlert("Mã sách 4 không phù hợp");
            return false;
        }

        return true;
    }

    private ArrayList<String> buildListRentBook()
    {
        int numberOfRentBook = Integer.parseInt(rentBookAmount.getText());
        ArrayList<String> listRentBook = new ArrayList<>();
        if(numberOfRentBook > 0)
            listRentBook.add(firstBook.getText());
        if(numberOfRentBook > 1)
            listRentBook.add(secondBook.getText());
        if(numberOfRentBook > 2)
            listRentBook.add(thirdBook.getText());
        if(numberOfRentBook > 3)
            listRentBook.add(fourBook.getText());

        return listRentBook;

    }

    private String convertListRentBookToString(ArrayList<String> listRentBook)
    {
        String output = "";
        int i = 0, size = listRentBook.size();

        while(true)
        {
            output += listRentBook.get(i++);

            if(i >= size)
                break;

            output += "-";
        }

        return output;
    }



    @FXML
    private void caculateFeeBtnClick()
    {
        if(!verifyRentBook() || !checkEmptyBookName() || !checkBookId())
            return;

        int rentBookCost = Integer.parseInt(rentBookAmount.getText()) * 20000;
        rentBookFee.setText(Integer.toString(rentBookCost));
    }

    @FXML
    private void confirmBtnClick()
    {
        if(!verifyRentBook())
            return;

        ArrayList<String> listRentBook = new ArrayList<>();

        listRentBook = buildListRentBook();
        RentBook rentBookRecord = new RentBook();

        rentBookRecord.setStateRent(0);
        rentBookRecord.setListRentBook(convertListRentBookToString(listRentBook));
        rentBookRecord.setNumberBooksRent(listRentBook.size());
        rentBookRecord.setIdReaderRent(reader.getIdReader());

        rentBookRecord.setRentDate(Date.valueOf(LocalDate.now()));
        rentBookRecord.setReturnDate(Date.valueOf(LocalDate.now().plusDays(20)));

        rentBookRecord.setRentFee((double)0);
        rentBookRecord.setDepositFee(Double.parseDouble(rentBookFee.getText()));

        LibraryBUS.addRentBookRecord(rentBookRecord, listRentBook);
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
}
