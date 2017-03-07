package com.pp.jms;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MqActiveClient {

	public static void main(String[] args) {
		
		try{
		// Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        
        
        // Create a Connection
        Connection connection = connectionFactory.createConnection();
       // connection.start();

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("TEST.FOO");

        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
