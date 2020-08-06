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
import sample.POJO.RentBook;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailRentBookController implements Initializable {
    @FXML
    TextField idRent;
    @FXML
    TextField numBooks;
    @FXML
    TextField listBooks;
    @FXML
    TextField idReader;
    @FXML
    DatePicker rentDate;
    @FXML
    DatePicker returnDate;
    @FXML
    TextField rentFee;
    @FXML
    TextField depositFee;
    @FXML
    CheckBox isReturned;
    @FXML
    CheckBox isDeleted;

    RentBook rentBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            idRent.setText(String.valueOf(rentBook.getIdRentBook()));
            numBooks.setText(String.valueOf(rentBook.getNumberBooksRent()));
            listBooks.setText(rentBook.getListRentBook());
            idReader.setText((String.valueOf(rentBook.getIdReaderRent())));
            rentDate.setValue(rentBook.getRentDate().toLocalDate());
            if(rentBook.getReturnDate() != null)
            {
                returnDate.setValue(rentBook.getReturnDate().toLocalDate());
            }
            if(rentBook.getRentFee() != null)
            {
                rentFee.setText(String.valueOf(rentBook.getRentFee()));
            }
            depositFee.setText(rentBook.getDepositFee().toString());
            if(rentBook.getStateRent() == 1)
            {
                isReturned.setSelected(true);
            }

            if(rentBook.getIsDeleted() == 1)
            {
                isDeleted.setSelected(true);
            }
        });

    }

    public static boolean isChanged = false;
    public void changeBtnClick(ActionEvent actionEvent) {
        isChanged = true;
        boolean isUpdated = LibraryBUS.updateRentBook(rentBook.getIdRentBook(),isReturned.isSelected(),isDeleted.isSelected());
        if(isUpdated)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Thay đổi thông tin thành công!");
            alert.showAndWait();
        }
    }

    public void cancelBtnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setRentBook(RentBook selectedRentBook) {
        rentBook = selectedRentBook;
    }

}
