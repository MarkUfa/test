package payments.service;

import payments.model.Payment;

import java.util.List;

public interface PaymentService {

    public void addPayment(Payment payment);

    public void updatePayment(Payment Payment);

    public void removePayment(long id);

    public Payment getPaymentById(long id);

    public List<Payment> listPayments(int send);
}
