package com.pp.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class TopicClient {
	
	/**
	 * Let’s now look into a client that uses publish and subscribe message style. It’s not very different than
	   using the point to point style. It also needs a connection factory, connection and a session.
	   When a publisher sends a message, there may be more than one customer interested in such messages. 
	   Publisher broadcasts the message to JMS destination called topic. There may be more than one consumer 
	   subscribed to the topic. All the active clients subscribed to the topic will receive message and there is no 
	   need for the subscriber to poll for the messages. Every active subscriber receives its own copy of each message 
	   published to the topic. 
	   In this example, we will look into subscriber and publisher
	 * @param agrs
	 */
	public static void main(String[] agrs){
		TopicClient tc = new TopicClient();
		try{
		tc.subscribTopic();
		tc.publishTopic();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void subscribTopic() throws Exception{
		// get Connection Factory
		ConnectionFactory connectionFactory = null;//new ActiveMQConnectionFactory("tcp://localhost:61616"); (this will be based on the MQ provider we are using)
		Connection connection = connectionFactory.createConnection();
		connection.setClientID("DurabilityTest");
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("customerTopic");
		
		// Consumer1 subscribes to customerTopic
		MessageConsumer consumer1 = session.createDurableSubscriber(topic, "consumer1", "", false);		
		// Consumer2 subscribes to customerTopic
		MessageConsumer consumer2 = session.createDurableSubscriber(topic, "consumer2", "", false);	
		connection.start();
		
		TextMessage msg = (TextMessage) consumer1.receive();
		System.out.println("Consumer1 receives " + msg.getText());
		
		msg = (TextMessage) consumer2.receive();
		System.out.println("Consumer2 receives " + msg.getText());
		session.close();
		connection.close();
	}
	
	public void publishTopic() throws Exception{

		// get Connection Factory
		ConnectionFactory connectionFactory = null;//new ActiveMQConnectionFactory("tcp://localhost:61616"); (this will be based on the MQ provider we are using)
		Connection connection = connectionFactory.createConnection();
		connection.setClientID("DurabilityTest");
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("customerTopic");

		// Publish
		String payload = "Task";
		TextMessage msg = session.createTextMessage(payload);
		MessageProducer publisher = session.createProducer(topic);
		System.out.println("Sending text '" + payload + "'");
		publisher.send(msg, javax.jms.DeliveryMode.PERSISTENT, javax.jms.Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);
		session.close();
		connection.close();
	}

}
