package payments.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import payments.model.Payment;

import java.util.List;

@Repository
public class PaymentDaoImpl implements PaymentDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPayment(Payment payment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(payment);

    }

    @Override
    public void removePayment(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Payment payment = (Payment) session.get(Payment.class, new Long(id));
        if(payment!=null){
            session.delete(payment);
        }
    }

    @Override
    public Payment getPaymentById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Payment payment = (Payment) session.get(Payment.class,id);
        return payment;
    }

    @Override
    public List<Payment> listPayments(int send) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Payment where send = " + send).list();

    }
}
