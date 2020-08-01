package sample.POJO;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "rentbook")
public class RentBook {
    @Id
    @Column(name = "idrentbook")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idRentBook;

    @Column(name = "listrentbook")
    String listRentBook;

    @Column(name = "numberbooks")
    int numberBooksRent;

    @Column(name = "idreader")
    int idReaderRent;

    @Column(name = "rentdate")
    Date rentDate;

    @Column(name = "returndate")
    Date returnDate;

    @Column(name = "rentfee")
    Double rentFee;

    @Column(name = "depositfee")
    Double depositFee;

    @Column(name = "state")
    Integer stateRent = 0;

    @ManyToOne
    @JoinColumn(name = "idreader" ,insertable = false,updatable = false)
    Reader reader;

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public int getIdRentBook() {
        return idRentBook;
    }

    public void setIdRentBook(int idRentBook) {
        this.idRentBook = idRentBook;
    }

    public String getListRentBook() {
        return listRentBook;
    }

    public void setListRentBook(String listRentBook) {
        this.listRentBook = listRentBook;
    }

    public int getNumberBooksRent() {
        return numberBooksRent;
    }

    public void setNumberBooksRent(int numberBooksRent) {
        this.numberBooksRent = numberBooksRent;
    }

    public int getIdReaderRent() {
        return idReaderRent;
    }

    public void setIdReaderRent(int idReaderRent) {
        this.idReaderRent = idReaderRent;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Double getRentFee() {
        return rentFee;
    }

    public void setRentFee(Double rentFee) {
        this.rentFee = rentFee;
    }

    public Double getDepositFee() {
        return depositFee;
    }

    public void setDepositFee(Double depositFee) {
        this.depositFee = depositFee;
    }

    public Integer getStateRent() {
        return stateRent;
    }

    public void setStateRent(Integer stateRent) {
        this.stateRent = stateRent;
    }
}
