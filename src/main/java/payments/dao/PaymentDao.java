package payments.dao;

import payments.model.Payment;

import java.util.List;

public interface PaymentDao {

    public void addPayment(Payment payment);

    public void updatePayment(Payment payment);

    public void removePayment(long id);

    public Payment getPaymentById(long id);

    public List<Payment> listPayments(int send);
}
