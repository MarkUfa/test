package payments.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "paymentdocs")
public class Payment {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @Column(name = "customer")
    private long customerId;

    @Column(name = "customer_data")
    private long customerDataId;

    @Column(name = "doc_type")
    private String docType;

    @Column(name = "doc_num")
    private long docNum;

    @Column(name = "sum")
    private Long sum;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "send_status")
    private int send;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerDataId() {
        return customerDataId;
    }

    public void setCustomerDataId(long customerDataId) {
        this.customerDataId = customerDataId;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public long getDocNum() {
        return docNum;
    }

    public void setDocNum(long docNum) {
        this.docNum = docNum;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    @Override
    public String toString() {
        return "{" +
//                "\"id\":\"" + id + "\"," +
                "\"date\":\"" + date + "\"" +
                ", \"customerId\":\"" + customerId + "\"" +
                ", \"customerDataId\":\"" + customerDataId + "\"" +
                ", \"docType\":\"'" + docType + "\"" +
                ", \"docNum\":\"" + docNum + "\"" +
                ", \"sum\":\"" + sum + "\"" +
                ", \"receiver\":\"" + receiver + "\"" +
                "}";
    }
}
