package sample.BUS;

import sample.DAO.LibraryDAO;
import sample.POJO.Reader;
import sample.POJO.TypeBook;

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
}
