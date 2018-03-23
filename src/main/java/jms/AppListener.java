package jms;

import jms.listener.PaymentConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class AppListener {

    private static final Logger LOG = LoggerFactory.getLogger(AppListener.class);


    public static void main(String[] args) throws JMSException {
         int i=0;
        //thread(new PaymentConsumerService(), false);
        while (true) {
            i++;
            Thread thread = new Thread(new PaymentConsumerService());
            try {
                thread.start();
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            LOG.info("loop " + i);
            if (i>20) break;
        }
        LOG.info("--> main end");
    }


    public static void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();

    }

}
