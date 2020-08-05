package sample.POJO;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "compensate")
public class Compensate {
    @Id
    @Column(name = "idcompensate")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCompensate;

    @Column(name = "idreader")
    int idReader;

    @Column(name = "idbook")
    int idBook;

    @Column(name = "compensatefee")
    double compensateFee;

    @Column(name = "date")
    Date date;

    @Column(name = "state")
    Integer state;

    @ManyToOne
    @JoinColumn(name = "idreader" , insertable = false , updatable = false)
    Reader readerCompensate;

    @ManyToOne
    @JoinColumn(name = "idbook", insertable = false, updatable =  false)
    Books book;

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Compensate(){}

    public Compensate(int idBook, int idReader,double fee)
    {
        this.idBook = idBook;
        this.idReader = idReader;
        this.compensateFee = fee;
        this.date = Date.valueOf(LocalDate.now());
        this.state = 0;
    }

    public int getIdCompensate() {
        return idCompensate;
    }

    public void setIdCompensate(int idCompensate) {
        this.idCompensate = idCompensate;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public double getCompensateFee() {
        return compensateFee;
    }

    public void setCompensateFee(double compensateFee) {
        this.compensateFee = compensateFee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Reader getReaderCompensate() {
        return readerCompensate;
    }

    public void setReaderCompensate(Reader readerCompensate) {
        this.readerCompensate = readerCompensate;
    }
}
