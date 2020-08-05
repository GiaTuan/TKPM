package sample.POJO;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reader" )
public class Reader {
    @Id
    @Column(name = "idreader")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idReader;

    @Column(name = "name")
    String nameReader;

    @Column(name = "address")
    String addressReader;

    @Column(name = "dob")
    Date dateOfBirth;

    @Column(name = "phone")
    String phoneReader;

    @Column(name = "email")
    String emailReader;

    @Column(name = "datemember")
    Date dateMember;

    @Column(name = "type")
    String typeReader =  "Tay mơ";

    @Column(name = "ismarked")
    Integer isMarked = 0;

    @Column(name = "point")
    int point = 0;

    @Column(name = "isreceivednofication")
    int isReceivedNofication;

    public int getIsReceivedNofication() {
        return isReceivedNofication;
    }

    public void setIsReceivedNofication(int isReceivedNofication) {
        this.isReceivedNofication = isReceivedNofication;
    }

    @Column(name = "isdeleted")
    Integer isDeleted = 0 ;

    @OneToMany(mappedBy = "reader" ,fetch = FetchType.LAZY)
    List<RentBook> rentBookList;

    @OneToMany(mappedBy = "readerCompensate",fetch =  FetchType.LAZY)
    List<Compensate> compensateList;

    public List<Compensate> getCompensateList() {
        return compensateList;
    }

    public void addCompensateList(Compensate compensate) {
        this.compensateList.add(compensate);
        compensate.setReaderCompensate(this);
    }

    public Reader(){}

    public Reader(String name, String phone, String mail, String addr, Date dob)
    {
        this.nameReader = name;
        this.phoneReader = phone;
        this.emailReader = mail;
        this.addressReader = addr;
        this.dateOfBirth = dob;
        this.dateMember = Date.valueOf(LocalDate.now());
    }

    public void addRentBookList(RentBook rentBook) {
        rentBookList.add(rentBook);
        rentBook.setReader(this);
    }

    public void setRentBookList(List<RentBook> rentBookList) {
        this.rentBookList = rentBookList;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public String getNameReader() {
        return nameReader;
    }

    public void setNameReader(String nameReader) {
        this.nameReader = nameReader;
    }

    public String getAddressReader() {
        return addressReader;
    }

    public void setAddressReader(String addressReader) {
        this.addressReader = addressReader;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneReader() {
        return phoneReader;
    }

    public void setPhoneReader(String phoneReader) {
        this.phoneReader = phoneReader;
    }

    public String getEmailReader() {
        return emailReader;
    }

    public void setEmailReader(String emailReader) {
        this.emailReader = emailReader;
    }

    public Date getDateMember() {
        return dateMember;
    }

    public void setDateMember(Date dateMember) {
        this.dateMember = dateMember;
    }

    public String getTypeReader() {
        return typeReader;
    }

    public void setTypeReader(String typeReader) {
        this.typeReader = typeReader;
    }

    public Integer getIsMarked() {
        return isMarked;
    }

    public void setIsMarked(Integer isMarked) {
        this.isMarked = isMarked;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }


    public List<RentBook> getRentBookList() {
        return rentBookList;
    }

    public String toString()
    {
        return this.getNameReader() + " - " + this.getPhoneReader();
    }
}
