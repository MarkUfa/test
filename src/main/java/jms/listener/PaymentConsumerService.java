package jms.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import payments.controller.PaymentController;
import payments.model.Payment;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

@Service
public class PaymentConsumerService implements Runnable, ExceptionListener {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentConsumerService.class);
    public static final ApplicationContext ctx = new ClassPathXmlApplicationContext("listener-jms-context.xml");

    //init jms listener
    //get message
    // persist to hyber
    public void run() {
        JmsMessageListenerDAO jmsMessageListenerDAO = new JmsMessageListenerDAO();
        try {

            jmsMessageListenerDAO.init();
            LOG.info("init");
            Message message = jmsMessageListenerDAO.getMessage();
            if (message == null) {
                LOG.info("Not message in queue");
                return;
            }  else {
                LOG.info(message.toString());
                PaymentController paymentController = (PaymentController) ctx.getBean("paymentController");

                if (message instanceof TextMessage) {

                    Payment payment = convertToPayment(message);
                    payment.setId(0);
                    // try add to hybernate
                    paymentController.addPayment(payment);
                    jmsMessageListenerDAO.session.commit();

                } else {
                    System.out.println("Received not Text : " + message);
                    jmsMessageListenerDAO.session.rollback();
                }
            }

        }
        catch (IOException e){
            System.out.println("Caught IOException: " + e);
            e.printStackTrace();
        }
        catch (Exception e) {
            try {
                jmsMessageListenerDAO.session.rollback();
            } catch (JMSException e1) {
                e1.printStackTrace();
            }
            System.out.println("Caught: " + e);
            e.printStackTrace();
        } finally {
            jmsMessageListenerDAO.close();
        }
        LOG.info("-->End run");
    }

    public Payment convertToPayment(Message message) throws IOException, JMSException {

        ObjectMapper mapper = new ObjectMapper();
        TextMessage textMessage = (TextMessage) message;
        String text = textMessage.getText();
        System.out.println("Received: " + text);
        return mapper.readValue(text, Payment.class);

    }
    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }
}
