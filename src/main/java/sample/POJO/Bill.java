package sample.POJO;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @Column(name = "idbill")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBill;

    @Column(name = "typebill")
    private int typeBill;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "cost")
    private Double cost;

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getTypeBill() {
        return typeBill;
    }

    public void setTypeBill(int typeBill) {
        this.typeBill = typeBill;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

}
