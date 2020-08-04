package sample.Controller.AdminController;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.TypeBook;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController {

    @FXML
    DatePicker statisticTotalFromDate;
    @FXML
    DatePicker statisticTotalToDate;
    @FXML
    LineChart lineChart;
    @FXML
    BarChart barchartBook;
    @FXML
    PieChart pieChartBookRemain;

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ChooseAuthorizationFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    //tạo file fxml rồi nhét cái tên file vào thôi

    public void manageRentBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/RentBooksFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageBookBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/BookManagerFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageLibrarianBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/StaffManagerFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageReaderBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void mangeRegulationBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/UpdateRuleFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void watchStatisticTotalClick(ActionEvent actionEvent) {

    }

    public void statisticBookTabSelected(Event event) {
        barchartBook.getData().clear();
        pieChartBookRemain.getData().clear();
        List<TypeBook> list = LibraryBUS.getTypeBookList();
        drawBarChartOfBooks(list);
        List<Integer> numberOfBooksReamain = LibraryBUS.getBooksRemainForEachType(list);
        drawPieChartOfBooksRemain(list,numberOfBooksReamain);
    }

    private void drawPieChartOfBooksRemain(List<TypeBook> list, List<Integer> numberOfBooksReamain) {
        for(int i = 0 ; i < list.size() ; i++)
        {
            PieChart.Data slice = new PieChart.Data(list.get(i).getNameType(),numberOfBooksReamain.get(i));
        //    slice.setName(list.get(i).getNameType());
            pieChartBookRemain.getData().add(slice);
             //numberOfBooksReamain.get(i)
        }

    }

    private void drawBarChartOfBooks(List<TypeBook> list) {
        int numberBooksOfEachType = 0;
        for(TypeBook typeBook : list)
        {
            numberBooksOfEachType = LibraryBUS.getNumberOfBooksByIdTypeBook(typeBook.getIdtypebook());
            XYChart.Series series = new XYChart.Series();
         //   series.setName(typeBook.getNameType());
            series.getData().add(new XYChart.Data<>(typeBook.getNameType(),numberBooksOfEachType));
            barchartBook.getData().add(series);
        }
    }

}
