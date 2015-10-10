package com.coder.dream;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2015/10/10.
 */
public class ConsumerTester {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        final Session session = connection.createSession(true,
                Session.AUTO_ACKNOWLEDGE);
        Queue destination = session.createQueue("my_test_queue");

        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener(){
            public void onMessage(Message message) {
                System.out.println(message);
                try {
                    session.commit();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
