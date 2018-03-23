package jms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;



public class JmsMessageListenerDAO implements ExceptionListener {

    private static final Logger LOG = LoggerFactory.getLogger(JmsMessageListenerDAO.class);
    public static final ApplicationContext ctx = new ClassPathXmlApplicationContext("listener-jms-context.xml");


    public Connection connection;

    public  Session session;
    private  MessageConsumer consumer;
    private  Destination destination;
    //@Autowired
    //private  Destination destination;

    public void init() throws JMSException {
        connection = (Connection) ctx.getBean("connection");
        //connection.start();
        connection.setExceptionListener(this);

        // Create a Session
        session = (Session) ctx.getBean("session");

        // Create the destination (Topic or Queue)
        destination = (Destination) ctx.getBean("destination");

        // Create a MessageConsumer from the Session to the Topic or Queue
        consumer = (MessageConsumer) ctx.getBean("consumer");
    }

    public Message getMessage() throws JMSException {
            return consumer.receive(1000);
    }

    public void close() {
        try {
            consumer.close();
            session.close();
            //connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onException(JMSException exception) {

    }
}
