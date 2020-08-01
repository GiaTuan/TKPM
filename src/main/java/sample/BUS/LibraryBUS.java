package sample.BUS;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import sample.DAO.LibraryDAO;
import sample.POJO.*;

import java.io.*;
import java.math.BigInteger;
import javafx.collections.ObservableList;
import sample.POJO.Reader;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Reader> getReaderList() {
        List<Reader> result = LibraryDAO.getReaderList();
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
            table.getRow(0).getCell(3).setText("Ngày lập thẻ: " );
            table.getRow(1).getCell(0).setText("Địa chỉ: " + selectedReader.getAddressReader());
            table.getRow(1).getCell(1).setText("Ngày sinh: " );
            table.getRow(1).getCell(2).setText("Số điện thoại: " + selectedReader.getPhoneReader());
            table.getRow(1).getCell(3).setText("Điểm tích được: " + selectedReader.getPoint());

            document.write(fileOutputStream);
            fileOutputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();;
            isExported = false;
        }
        finally {
            return isExported;
        }
    }

    public static List<RentBook> getRentBookList() {
        List<RentBook> result = LibraryDAO.getRentBookList();
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
                    "Độc giả vui lòng đến thư viện để trả lại sách và thanh toán phí mượn. " +
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

}
