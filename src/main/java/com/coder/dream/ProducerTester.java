package com.coder.dream;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2015/10/10.
 */
public class ProducerTester {

    public ProducerTester() throws Exception{
    }

    public static void main(String[] args) throws JMSException {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //JMS 客户端到JMS Provider 的连接
        Connection connection = connectionFactory.createConnection();
        System.out.println("start...");

        Session session = connection.createSession(true,
                Session.AUTO_ACKNOWLEDGE);
        Queue destination = session.createQueue("my_test_queue");

        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        TextMessage message = session.createTextMessage("中文");
        producer.send(message);
        session.commit();

        connection.close();
        System.out.println("send text ok.");
    }
}
