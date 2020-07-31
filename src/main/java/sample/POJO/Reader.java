package sample.POJO;

import javax.persistence.*;
import java.sql.Date;
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
    String typeReader;

    @Column(name = "ismarked")
    Integer isMarked = 0;

    @Column(name = "point")
    int point = 0;

    @OneToMany(mappedBy = "reader" ,fetch = FetchType.LAZY)
    List<RentBook> rentBookList;

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

    @Column(name = "isdeleted")
    Integer isDeleted = 0 ;



}
