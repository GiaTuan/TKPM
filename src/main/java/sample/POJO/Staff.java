package sample.POJO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @Column(name = "idstaff")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStaff;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String nameStaff;

    @Column(name = "email")
    private String emailStaff;

    @Column(name = "phone")
    private String phoneStaff;

    @Column(name = "address")
    private String addrStaff;

    @Column(name = "isadmin")
    private boolean isAdmin;

    public Staff(){};

    public Staff(int id, String nameStaff, String username, String password, String emailStaff, String phoneStaff, String addrStaff, boolean isAdmin)
    {
        this.idStaff = id;
        this.username = username;
        this.password = password;
        this.nameStaff = nameStaff;
        this.emailStaff = emailStaff;
        this.phoneStaff = phoneStaff;
        this.addrStaff = addrStaff;
        this.isAdmin = isAdmin;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public String getEmailStaff() {
        return emailStaff;
    }

    public void setEmailStaff(String emailStaff) {
        this.emailStaff = emailStaff;
    }

    public String getPhoneStaff() {
        return phoneStaff;
    }

    public void setPhoneStaff(String phoneStaff) {
        this.phoneStaff = phoneStaff;
    }

    public String getAddrStaff() {
        return addrStaff;
    }

    public void setAddrStaff(String addrStaff) {
        this.addrStaff = addrStaff;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
