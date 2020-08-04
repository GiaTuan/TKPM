package sample.BUS;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import sample.Controller.LibrarianController.FindReaderController;
import sample.DAO.LibraryDAO;
import sample.POJO.*;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigInteger;
import javafx.collections.ObservableList;


import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import sample.POJO.Reader;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LibraryBUS {

    public static void setUpData()
    {
        LibraryDAO.setUpData();
    }

    public static List<TypeBook> getTypeBookList()
    {
        List<TypeBook> result = LibraryDAO.getTypeBookList(false);
        return result;
    }
    public static int getNumberOfBooksByIdTypeBook(int idtypebook) {
        int result = LibraryDAO.getNumberOfBooksByIdTypeBook(idtypebook);
        return result;
    }

    public static List<Reader> getReaderList(boolean isRequey) {
        List<Reader> result = LibraryDAO.getReaderList(isRequey);
        return result;
    }

    public static boolean printReader(Reader selectedReader) throws IOException {
        boolean isExported = true;
        String fileOut =  selectedReader.getPhoneReader() + ".docx";

        XWPFDocument document= new XWPFDocument();
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File("ReaderFiles/" + fileOut));
            XWPFParagraph tmpParagraph = document.createParagraph();
            tmpParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun tmpRun = tmpParagraph.createRun();
            tmpRun.setText("THẺ ĐỘC GIẢ");
            tmpRun.setFontSize(18);
            tmpRun.setBold(true);

            XWPFTable table = document.createTable(2,4);
            CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
            width.setType(STTblWidth.DXA);
            width.setW(BigInteger.valueOf(9072));
            table.getRow(0).getCell(0).setText("Họ tên: " + selectedReader.getNameReader());
            table.getRow(0).getCell(1).setText("Loại độc giả: " + selectedReader.getTypeReader());
            table.getRow(0).getCell(2).setText("Email: " + selectedReader.getEmailReader());
            table.getRow(0).getCell(3).setText("Ngày lập thẻ: " + selectedReader.getDateMember().toString() );
            table.getRow(1).getCell(0).setText("Địa chỉ: " + selectedReader.getAddressReader());
            table.getRow(1).getCell(1).setText("Ngày sinh: " + selectedReader.getDateOfBirth().toString() );
            table.getRow(1).getCell(2).setText("Số điện thoại: " + selectedReader.getPhoneReader());
            table.getRow(1).getCell(3).setText("Điểm tích được: " + selectedReader.getPoint());

            document.write(fileOutputStream);
            fileOutputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            isExported = false;
        }
        finally {
            return isExported;
        }
    }

    public static List<RentBook> getRentBookList(boolean isRequery) {
        List<RentBook> result = LibraryDAO.getRentBookList(isRequery);
        return result;
    }

    public static boolean sendEmail(String emailReader) throws EmailException {

        boolean isSent = true;

        try {
            // Tạo đối tượng Email
            Email email = new SimpleEmail();

            // Cấu hình thông tin Email Server
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("tuan0949@gmail.com", "Tuantun123"));
            email.setSSLOnConnect(true);

            // Người gửi
            email.setFrom("tuan0949@gmail.com");

            // Người nhận
            email.addTo(emailReader);
            // addTo nhiều để gửi nhiều email

            // Tiêu đề
            email.setSubject("Thư viện Paylak_2/5 - Thông báo trả sách");

            // Nội dung email
            email.setMsg("Chào bạn, \n" +
                    "Theo thông tin được lưu trong dữ liệu của thư viện thì hiện tại bạn đang mượn sách của thư viện và đã quá hạn trả sách. " +
                    "Độc giả vui lòng đến thư viện để trả lại sách và thanh toán phí mượn.\n" +
                    "Xin cảm ơn!");

            // send message
            email.send();
        }catch (EmailException ex) {
            ex.printStackTrace();
            isSent = false;

        }
        finally {
            return isSent;
        }

    }
    public static List<Regulation> getRegulationList() // khong co trong doc4
    {
        List<Regulation> result = LibraryDAO.getRegulationList(false);
        return result;
    }

    public static void updateRegulation(ObservableList<Regulation> newRegulationData, ArrayList<Integer> listIdOfChangingRule)
    {
        LibraryDAO.updateRegulation(newRegulationData, listIdOfChangingRule);
    }

    public static boolean updateIsReturnedRentBook(int idRentBook, boolean isReturned) {
        boolean res = LibraryDAO.updateIsReturnedRentBook(idRentBook,isReturned);
        return res;
    }

    public static boolean updateReader(int idReader, String nameReader, String addReader, String phoneReader, String emailReader, LocalDate dobReader, LocalDate memberDate, boolean isMarked, boolean isDeleted) {
        boolean res = LibraryDAO.updateReader(idReader,nameReader,addReader,phoneReader,emailReader, Date.valueOf(dobReader),Date.valueOf(memberDate),isMarked,isDeleted);
        return res;
    }

    public static boolean sendEmailToAll(ObservableList<RentBook> tempRentBooksObservableList) {
        boolean isSent = true;

        try {
            // Tạo đối tượng Email
            Email email = new SimpleEmail();

            // Cấu hình thông tin Email Server
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("tuan0949@gmail.com", "Tuantun123"));
            email.setSSLOnConnect(true);

            // Người gửi
            email.setFrom("tuan0949@gmail.com");

            tempRentBooksObservableList.forEach(rentBook -> {
                try {
                    email.addTo(rentBook.getReader().getEmailReader());
                } catch (EmailException e) {
                    e.printStackTrace();
                }
            });

            // Tiêu đề
            email.setSubject("Thư viện Paylak_2/5 - Thông báo trả sách");

            // Nội dung email
            email.setMsg("Chào bạn, \n" +
                    "Theo thông tin được lưu trong dữ liệu của thư viện thì hiện tại bạn đang mượn sách của thư viện và đã quá hạn trả sách. " +
                    "Độc giả vui lòng đến thư viện để trả lại sách và thanh toán phí mượn.\n" +
                    "Xin cảm ơn!");

            // send message
            email.send();
        }catch (EmailException ex) {
            ex.printStackTrace();
            isSent = false;

        }
        finally {
            return isSent;
        }
    }

    public static ObservableList<Reader> filterReader(ObservableList<Reader> readerObservableList, String info, int typeFilter, int typeDisplay) {
        ObservableList<Reader> result = readerObservableList;
        if(typeFilter == 0)
        {
            if(!info.equals("")) {
                result = result.filtered(reader -> reader.getNameReader().toLowerCase().contains(info));
            }

        }
        if(typeFilter == 1)
        {
            if(!info.equals("")) {
                result = result.filtered(reader -> reader.getPhoneReader().contains(info));
            }

        }
        if(typeFilter == 2)
        {
            if(!info.equals("")) {
                result = result.filtered(reader -> reader.getEmailReader().toLowerCase().contains(info));
            }
        }
        if(typeDisplay == 1)
        {
            result = result.filtered(reader -> reader.getIsDeleted() == 0);
        }
        if(typeDisplay == 2)
        {
            result = result.filtered(reader -> reader.getIsMarked() == 1 && reader.getIsDeleted() == 0);
        }
        if(typeDisplay == 3)
        {
            result = result.filtered(reader -> reader.getIsDeleted() == 1);
        }
        return result;
    }

    public static ObservableList<RentBook> filterRentBook(ObservableList<RentBook> rentBooksObservableList, String info, int typeFilter, int typeView, int typeStatus, LocalDate rentDate) {
        ObservableList<RentBook> tempRentBooksObservableList = rentBooksObservableList;

        if(typeFilter == 0)
        {
            tempRentBooksObservableList = tempRentBooksObservableList.filtered(rentBook -> String.valueOf(rentBook.getIdRentBook()).contains(info));
        }
        if(typeFilter == 1)
        {
            tempRentBooksObservableList = tempRentBooksObservableList.filtered(rentBook -> String.valueOf(rentBook.getIdReaderRent()).contains(info));
        }
        if(rentDate != null)
        {
            tempRentBooksObservableList = tempRentBooksObservableList.filtered(rentBook -> rentBook.getRentDate().compareTo(Date.valueOf(rentDate)) == 0);
        }
        if(typeStatus == 1)
        {
            tempRentBooksObservableList = tempRentBooksObservableList.filtered(rentBook -> rentBook.getStateRent() == 1);
        }
        if(typeStatus == 2)
        {
            tempRentBooksObservableList = tempRentBooksObservableList.filtered(rentBook -> rentBook.getStateRent() == 0);
        }
        if(typeView == 1)
        {
            LocalDate dateNow = LocalDate.now();
            tempRentBooksObservableList = tempRentBooksObservableList.filtered(rentBook -> ChronoUnit.DAYS.between(rentBook.getRentDate().toLocalDate(),dateNow) > 15 && ChronoUnit.DAYS.between(rentBook.getRentDate().toLocalDate(),dateNow) <= 20);
        }
        if(typeView == 2)
        {
            LocalDate dateNow = LocalDate.now();
            tempRentBooksObservableList = tempRentBooksObservableList.filtered(rentBook -> ChronoUnit.DAYS.between(rentBook.getRentDate().toLocalDate(),dateNow) > 20);
        }

        return tempRentBooksObservableList;
    }
    public static boolean isEmailDuplicate(String emailCheck)
    {
        return LibraryDAO.isEmailDuplicate(emailCheck);
    }

    public static boolean isUsernameDuplicate(String usernameCheck)
    {
        return LibraryDAO.isUsernameDuplicate(usernameCheck);
    }

    public static boolean isPhoneDuplicate(String phoneCheck)
    {
        return LibraryDAO.isPhoneDuplicate(phoneCheck);
    }

    public static String hashPassword(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            String hashText = no.toString(16);
            while(hashText.length() < 32)
                hashText = "0" + hashText;

            System.out.println(hashText);
            return hashText;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkVerifyPassword(String password)
    {
        return LibraryDAO.checkVerifyPassword(hashPassword(password), 1);
    }

    public static void addStaff(Staff newStaff)
    {
        LibraryDAO.addStaff(newStaff);
    }

    public static List<Staff> getStaffList(boolean isReQuery) // khong co trong doc4
    {
        List<Staff> result = LibraryDAO.getStaffList(isReQuery);
        return result;
    }


    public static List<Integer> getBooksRemainForEachType(List<TypeBook> list) {
        List<Integer> result = new ArrayList<>();
        int numberOfBooksRemain = 0;
        for(TypeBook typeBook : list)
        {
            List<GroupBook> listGroupBook = typeBook.getGroupBookList();
            for(GroupBook groupBook : listGroupBook)
            {
                numberOfBooksRemain += LibraryDAO.getNumberOfBooksRemainByIdGroupBook(groupBook.getIdGroupBook());
            }
            result.add(numberOfBooksRemain);
            numberOfBooksRemain = 0;
        }

        return result;
    }

    public static Reader getReaderFromPhone(String readerPhone) {
        Reader reader;
        reader = LibraryDAO.getReaderFromPhone(readerPhone);
        return reader;
    }

    public static boolean markReader(int idReader) {
        boolean res = LibraryDAO.markReader(idReader);
        if(res) //cập nhật lại trạng thái đánh dấu trong danh sách độc giả
        {
            LibraryDAO.updateMarkedReaderList(idReader);
        }
        return res;
    }

    public static boolean updateInfoReader(int idReader, String name, String phone, String mail, String addr,LocalDate dob) {
        Date dobFormatInDatabase = Date.valueOf(dob);
        boolean res = LibraryDAO.updateInfoReader(idReader,name,phone,mail,addr,dobFormatInDatabase);
        if(res){// cập nhật lại thông tin độc giả trong danh sách độc giả
            LibraryDAO.updateInfoReaderInReaderList(idReader,name,phone,mail,addr,dobFormatInDatabase);
        }
        return  res;
    }

    public static boolean checkPhoneReader(String phone) {
        boolean result = LibraryDAO.checkPhoneReader(phone);
        return result;
    }

    public static boolean checkEmailReader(String mail) {
        boolean result = LibraryDAO.checkEmailReader(mail);
        return result;
    }

    public static boolean addReader(String name, String phone, String mail, String add, LocalDate dob) {
        boolean result = LibraryDAO.addReader(name,phone,mail,add,Date.valueOf(dob));
        return result;
    }

    public static void findReader(String infoReader) {
//        if(!infoReader.equals(""))
//        {
//            String readerPhone = null;
//            if(infoReader.contains(" - "))
//            {
//                readerPhone = infoReader.split("- ")[1];
//            }
//            else
//            {
//                readerPhone = infoReader;
//            }
//            reader = LibraryBUS.getReaderFromPhone(readerPhone);
//            if(reader != null) {
//                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LibrarianFXML/FindReaderFXML.fxml"));
//                Parent root = fxmlLoader.load();
//                FindReaderController findReaderController = fxmlLoader.getController();
//                findReaderController.setReader(reader);
//                stage.setTitle("Thủ thư");
//                stage.setScene(new Scene(root, 1000, 600));
//            }
//            else{
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setContentText("Thông tin độc giả không có trong hệ thống");
//                alert.showAndWait();
//            }
//        }
    }

    public static String getReaderPhoneFromInputTextField(String infoReader) {
        String readerPhone = null;
        if(infoReader.contains(" - "))
        {
            readerPhone = infoReader.split("- ")[1];
        }
        else
        {
            readerPhone = infoReader;
        }
        return readerPhone;

    public static List<GroupBook> getGroupBookList(boolean isReQuery)
    {
        return LibraryDAO.getGroupBookList(isReQuery);
    }

    public static List<Publisher> getPublisherList()
    {
        return LibraryDAO.getPublisherList(false);
    }

    public static boolean isNumber(String checkString)
    {

        for(int i = 0; i < checkString.length(); i++)
            if(checkString.charAt(i) < '0' || checkString.charAt(i) > '9')
                return false;

        return true;
    }

    public static String buildIdBook(int idGroupBook, int index)
    {
        return Integer.toString(idGroupBook) + "_" + Integer.toString(index);
    }


    public static void addGroupBook(GroupBook newGroupBook)
    {
        LibraryDAO.addGroupBook(newGroupBook);
    }

    public static void updateGroupBookDetail(GroupBook tempObject)
    {
        LibraryDAO.updateGroupBookDetail(tempObject);
    }

    public static List<Books> getBookList(int groupBookId)
    {
        return LibraryDAO.getBookList(groupBookId);
    }

    public static void deleteBook(int id, int numberOfAvailabe)
    {
        LibraryDAO.deleteBook(id, numberOfAvailabe);
    }

    public static void deleteALLBookInGroup(List<Books> listBooksDelete, int numberOfAvailabe)
    {
        LibraryDAO.deleteALLBookInGroup(listBooksDelete, numberOfAvailabe);
    }

    public static String getGroupBookStateName(int code)
    {
        if(code == 0)
            return "Chưa nhập";
        else if(code == 1)
            return "Sẵn sàng";
        else if(code == 2)
            return "Xóa";
        else if(code == 3)
            return "Hết sách";
        else if(code == 4)
            return "Quá hạn";
        else
            return "Không xác định";
    }

    public static void printGroupBook(List<GroupBook> printList)
    {
        String uniqueID = UUID.randomUUID().toString();
        String fileOut =  LocalDate.now().toString() + uniqueID + ".docx";

        XWPFDocument document= new XWPFDocument();
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File("ReaderFiles/" + fileOut));
            XWPFParagraph tmpParagraph = document.createParagraph();
            tmpParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun tmpRun = tmpParagraph.createRun();
            tmpRun.setText("DANH SÁCH SÁCH");
            tmpRun.setFontSize(18);
            tmpRun.setBold(true);

            XWPFTable table = document.createTable(printList.size() + 1,7);
            CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
            width.setType(STTblWidth.DXA);
            width.setW(BigInteger.valueOf(9072));
            table.getRow(0).getCell(0).setText("ID");
            table.getRow(0).getCell(1).setText("Tên");
            table.getRow(0).getCell(2).setText("Thể loại");
            table.getRow(0).getCell(3).setText("Số lượng" );
            table.getRow(0).getCell(4).setText("Tác giả");
            table.getRow(0).getCell(5).setText("Nhà xuất bản" );
            table.getRow(0).getCell(6).setText("Tình trạng");

            for(int i = 0; i < printList.size(); i++)
            {
                table.getRow(i + 1).getCell(0).setText(Integer.toString(printList.get(i).getIdGroupBook()));
                table.getRow(i + 1).getCell(1).setText(printList.get(i).getNameBook());
                table.getRow(i + 1).getCell(2).setText(printList.get(i).getTypeBook().toString());
                table.getRow(i + 1).getCell(3).setText(Integer.toString(printList.get(i).getQuantity()));
                table.getRow(i + 1).getCell(4).setText(printList.get(i).getAuthor());
                table.getRow(i + 1).getCell(5).setText(printList.get(i).getPublisher());
                table.getRow(i + 1).getCell(6).setText(LibraryBUS.getGroupBookStateName(printList.get(i).getIsAvailable()));

            }

            document.write(fileOutputStream);
            fileOutputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
