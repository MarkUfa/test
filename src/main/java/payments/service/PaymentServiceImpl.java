package payments.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import payments.dao.PaymentDao;
import payments.model.Payment;

import java.util.List;

@Service
public class PaymentServiceImpl implements  PaymentService {

    private PaymentDao paymentDao;

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    @Transactional
    public void addPayment(Payment payment) {
        this.paymentDao.addPayment(payment);

    }

    @Override
    @Transactional
    public void updatePayment(Payment payment) {
        this.paymentDao.updatePayment(payment);

    }

    @Override
    @Transactional
    public void removePayment(long id) {
        this.paymentDao.removePayment(id);

    }

    @Override
    @Transactional
    public Payment getPaymentById(long id) {
        return this.paymentDao.getPaymentById(id);
    }

    @Override
    @Transactional
    public List<Payment> listPayments(int send) {
        return this.paymentDao.listPayments(send);
    }
}
