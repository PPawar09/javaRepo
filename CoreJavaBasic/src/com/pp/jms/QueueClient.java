package com.pp.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class QueueClient {
   /** reference link http://timjansen.github.io/jarfiller/guide/jms/standaloneclient.xhtml
   	   https://examples.javacodegeeks.com/enterprise-java/jms/jms-client-example/
   
       For a client to interact with the JMS provider to needs to get hold of a connection to the broker and 
       a connection represents a logical connection to the JMS provider. In order to obtain this connection each 
       JMS provider provides a connection factory. There are two types of connection factories: one for point to point 
       and another for publish and subscribe. Based on the desired messaging style, the client obtains the appropriate 
       connection factory and connects to the JMS provider.
   
       In case of ActiveMQ, it provides one ConnectionFactory and internally it implements both 
       QueueConnectionFactory and TopicConnectionFactory.
	*/
	
	public static void main(String[] args){
		QueueClient qc = new QueueClient();
		try{
			qc.sendMsg2Queue();
			qc.receiveMsgAsynch();
			qc.receiveMsgFrmQueue();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * we will show how to create a connection factory, create a new connection and session, create message producers and consumers 
	 * which we will then use to send and receive messages. First let’s look at a message producer client. We will use point to point messaging style.
	   We will first obtain a connection factory, which we will then use to create a connection.
	 */
	public void sendMsg2Queue() throws Exception{
		// get Connection Factory
		ConnectionFactory connectionFactory = null;//new ActiveMQConnectionFactory("tcp://localhost:61616"); (this will be based on the MQ provider we are using)
		Connection con = connectionFactory.createConnection();
		
		//Next, use the connection object to create a queue session.
		Session session = con.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("customerQueue");  // this can be JNDI lookup
		
		//The session object obtained is used to create a producer that will be used to send a message. 
		//When the producer is created, it is told which queue to send the messages to.
		MessageProducer producer = session.createProducer(queue);
		
		//Next, we create messages and send in a loop.
		for (int i = 0; i < 10; i++) {
		    String payload = String.valueOf(i);
		    Message msg = session.createTextMessage(payload);
		    System.out.println("Sending text '" + payload + "'");
		    producer.send(msg);
		}
		
		//Finally, we send message ‘END’ to indicate the client that we have sent the last message.
		producer.send(session.createTextMessage("END"));
		
		//Finally, close the session and the connections.
		session.close();
		con.close();
	}
	
	/**
	 * The consuming message. It also needs a connection factory, the connection, 
	 * the session, and the same queue. In this client program, however, the session is used to create a consumer 
	 * instead of a producer. This consumer is told which queue to consume messages from when it is created.
	 */
	public void receiveMsgFrmQueue() throws Exception{
		// get Connection Factory
		ConnectionFactory connectionFactory = null;//new ActiveMQConnectionFactory("tcp://localhost:61616"); (this will be based on the MQ provider we are using)
		Connection con = connectionFactory.createConnection();
		
		//Next, use the connection object to create a queue session.
		Session session = con.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("customerQueue");
		
		// Consumer
		MessageConsumer consumer = session.createConsumer(queue);
		con.start();
		
		//To actually receive a message, the client calls the receive method as follows:
		//draw back of below approach is loop run for infinite
		
		while (true) {
		    TextMessage textMsg = (TextMessage) consumer.receive();
		    System.out.println(textMsg);
		    System.out.println("Received: " + textMsg.getText());
		    if (textMsg.getText().equals("END")) {
		        break;
		    }
		}
		//Finally, close the session and the connections.
		session.close();
		con.close();
	}
	
	/**
	 * This section describes how to consume messages asynchronously. It uses a message listener in order to consume messages asynchronously.
	 * In order to make sure the asynchronous consumer doesn’t run indefinitely, it calls countDown() on latch when the message received is ‘END’.
	 * 
	 * @throws Exception
	 */
	
	public void receiveMsgAsynch() throws Exception{
		// Producer
		ConnectionFactory connectionFactory = null;//new ActiveMQConnectionFactory("tcp://localhost:61616"); (this will be based on the MQ provider we are using)
		Connection con = connectionFactory.createConnection();
		
		//Next, use the connection object to create a queue session.
		Session session = con.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("customerQueue");
		
		// Consumer
		MessageConsumer consumer = session.createConsumer(queue);
		ConsumerMessageListener consumerListener = new ConsumerMessageListener("Customer");
		consumer.setMessageListener(consumerListener);
		con.start();
		session.close();
		con.close();
	}
}

class ConsumerMessageListener implements MessageListener {
	private String consumerName;

	public ConsumerMessageListener(String consumerName) {
		this.consumerName = consumerName;
	}

	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println(consumerName + " received " + textMessage.getText());
			if ("END".equals(textMessage.getText())) {
				//business logic
			}
		} catch (JMSException e) {			
			e.printStackTrace();
		}
	}
}
