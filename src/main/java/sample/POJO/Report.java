package sample.POJO;

import javax.persistence.*;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @Column(name = "idreport")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idReport;

    @Column(name = "idreader")
    int idReader;

    @Column(name = "idbook")
    String idBook;

    @Column (name ="detail")
    String detailReport;

    public Report(){}

    public Report(String idBook, int idReader, String infoReport) {
        this.idBook = idBook;
        this.idReader = idReader;
        this.detailReport = infoReport;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getDetailReport() {
        return detailReport;
    }

    public void setDetailReport(String detailReport) {
        this.detailReport = detailReport;
    }
}
