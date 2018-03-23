package jms;

import jms.producer.JmsMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import payments.controller.PaymentController;
import payments.model.Payment;

public class AppSender {

    private static final Logger LOG = LoggerFactory.getLogger(AppSender.class);

    // init spring context
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("sender-jms-context.xml");

    public static void main(String[] args) {

        sendPayments();
        ((ClassPathXmlApplicationContext)ctx).close();

    }

    public static void sendPayments(){
        LOG.info("sendPayments ->");
        PaymentController paymentController = (PaymentController) ctx.getBean("paymentController");

        JmsMessageSender jmsMessageSender = (JmsMessageSender)ctx.getBean("jmsMessageSender");

        LOG.info("get beans ");
        for (Payment payment : paymentController.getListPaymentsNotSend()){
           // System.out.println("payment = [" + payment + "]");
            LOG.info("payment -> " + payment);
            jmsMessageSender.send(payment.toString());
            payment.setSend(1);
            paymentController.updatePayment(payment);
        }

    }

    public static void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }
}
