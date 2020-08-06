package sample.Controller.LibrarianController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.Reader;
import sample.POJO.RentBook;
import sample.Window.LibrarianWindow.EditRentBookDialogWindow;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RentBookHistoryController implements Initializable {


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
    private TextField idRentBookTextFied;
    @FXML
    private DatePicker dateRentBookPicker;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<RentBook, Integer> idRentBookCol;
    @FXML
    private TableColumn<RentBook, Reader> idReaderCol;
    @FXML
    private TableColumn<RentBook, Integer> numberOfRentBookCol;
    @FXML
    private TableColumn<RentBook, String> listRentBookCol;
    @FXML
    private TableColumn<RentBook, Date> rentDateCol;
    @FXML
    private TableColumn<RentBook, Date> returnDateCol;
    @FXML
    private TableColumn<RentBook, Double> despositFeeCol;
    @FXML
    private TableColumn<RentBook, Double> rentFeeCol;
    @FXML
    private TableColumn<RentBook, Integer> isReturn;
    @FXML
    private TableColumn<RentBook, Integer> isDeleted;

    private Reader reader;
    private ObservableList<RentBook> originalData = FXCollections.observableArrayList();
    private ObservableList<RentBook> displayData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            nameLabel.setText("Họ tên: " + reader.getNameReader());
            dobLabel.setText("Ngày sinh: " + reader.getDateOfBirth().toString());
            phoneLabel.setText("Số điện thoại: " + reader.getPhoneReader());
            emailLabel.setText("Email: " + reader.getEmailReader());
            addressLabel.setText("Địa chỉ: " + reader.getAddressReader());
            typeLabel.setText("Hạng độc giả: " + reader.getTypeReader());
            pointLabel.setText("Điểm độc giả: " + String.valueOf(reader.getPoint()));

            setupTable();
            loadInfo(false);
        });

    }

    private void loadInfo(boolean isReQuery)
    {
        List<RentBook> tempStorage = LibraryBUS.getRentBookList(isReQuery);
        originalData.clear();
        displayData.clear();

        for(RentBook item : tempStorage)
            if(item.getReader().getIdReader() == reader.getIdReader())
                originalData.add(item);

        displayData.addAll(originalData);
        table.setItems(displayData);
    }

    private void setupTable()
    {
        idRentBookCol.setCellValueFactory(new PropertyValueFactory<>("idRentBook"));

        idReaderCol.setCellValueFactory(new PropertyValueFactory<>("Reader"));
        idReaderCol.setCellFactory(col -> new TableCell<RentBook, Reader>(){
            @Override
            protected void updateItem(Reader item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : Integer.toString(item.getIdReader()));
            }
        });
        numberOfRentBookCol.setCellValueFactory(new PropertyValueFactory<>("numberBooksRent"));
        listRentBookCol.setCellValueFactory(new PropertyValueFactory<>("listRentBook"));
        rentDateCol.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        despositFeeCol.setCellValueFactory(new PropertyValueFactory<>("depositFee"));
        rentFeeCol.setCellValueFactory(new PropertyValueFactory<>("rentFee"));
        isReturn.setCellValueFactory(new PropertyValueFactory<>("stateRent"));
        isDeleted.setCellValueFactory(new PropertyValueFactory<>("isDeleted"));
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

    public void returnRentBookBtnClick(ActionEvent actionEvent) throws IOException {
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




    @FXML
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

    private void showAlert(String nofication)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(nofication);
        alert.showAndWait();
    }

    @FXML
    private void searchBtnClick()
    {
        if(idRentBookTextFied.getText().isEmpty() && dateRentBookPicker.getEditor().getText().isEmpty())
        {
            displayData.clear();
            displayData.addAll(originalData);
            table.refresh();
            return;
        }

        ObservableList<RentBook> cache = FXCollections.observableArrayList();



        if(!idRentBookTextFied.getText().isEmpty() && !dateRentBookPicker.getEditor().getText().isEmpty())
        {
            int idRentBook = Integer.parseInt(idRentBookTextFied.getText());
            Date rentDate = Date.valueOf(dateRentBookPicker.getValue());
            for(RentBook item : originalData)
                if(item.getIdRentBook() == idRentBook && item.getRentDate().equals(rentDate))
                    cache.add(item);

        }
        else if(!idRentBookTextFied.getText().isEmpty())
        {
            int idRentBook = Integer.parseInt(idRentBookTextFied.getText());

            for(RentBook item : originalData)
                if(item.getIdRentBook() == idRentBook)
                    cache.add(item);
        }
        else if(!dateRentBookPicker.getEditor().getText().isEmpty())
        {
            Date rentDate = Date.valueOf(dateRentBookPicker.getValue());
            for(RentBook item : originalData)
                if(item.getRentDate().equals(rentDate))
                    cache.add(item);
        }

        displayData.clear();
        displayData.addAll(cache);
        table.refresh();
    }

    @FXML
    private void changeBtnClick() throws IOException
    {
        if(table.getSelectionModel().getSelectedItem() == null)
            return;

        EditRentBookDialogWindow.display((RentBook)table.getSelectionModel().getSelectedItem());
        loadInfo(true);
    }

}
