package com.pp.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * This is working local example of ActiveMq with connection factory, destination etc.
 * 
 * High Level Steps for JMS Communication
 * 
 * 	Step1 : Create ConnectionFactory from broker url of jndi lookup.
 *  Step2 : Create Connection object from connection factory.
 *  Step3 : Start connection and create session.
 *  Step4 : Create or Retrieve destination(Queue or topic) from jndi lookup.
 *  Step5 : 
 *        > If client is producer then create Producer object from destination and send message.
 *        > If client is consumer then create Consumer object from queue and receive message Synchronously or Asynchronously.
 *  Step6 : close connection and session
 * 
 * @author Prakash Pawar
 */
public class MqActiveClient {

	public static void main(String[] args) {

		try{
			// create 3 thread to produce and consume message independently.
			
			Thread producer = new Thread(new MyMqThreadProducer());
			Thread consumer = new Thread(new MyMqThreadConsumer());
			Thread asynchConsumer = new Thread(new MyMqThreadAsynchConsumer());

			producer.start(); // start() method of thread execute in separate Thread, never call run method which execute as normal method.

			Thread.sleep(10000);

			//Message can be received Synchronous and ASynchronous

			//Option#1 Synchronous
			//consumer.start(); 

			//Option#2 Asynchronous using Listener
			asynchConsumer.start();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class MyMqThreadAsynchConsumer implements Runnable {

	@Override
	public void run(){
		try{
			System.out.println("*******Asynchronous Receiving Message Start********");

			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
			Connection con = connectionFactory.createConnection();
			con.start();

			//Next, use the connection object to create a queue session.
			Session session = con.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("TEST.FOO");

			// AsynchConsumer
			MessageConsumer consumer = session.createConsumer(queue);
			ConsumerMessageListener consumerListener = new ConsumerMessageListener("MyMqThreadAsynchConsumer");
			consumer.setMessageListener(consumerListener);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class MyMqThreadProducer implements Runnable {
	
	@Override
	public void run(){

		try{
			
			System.out.println("*******Sending Message Start********");
			
			// In managed Env ConnectionFactory and Destination Object is retrived by JNDI Lookup it comment below
			//jndiContext = new InitialContext();
			//ConnectionFactory connectionFactory = (ConnectionFactory)jndiContext.lookup("queueConnectionFactory");
			//destination = (Destination)jndiContext.lookup("MyQueue");

			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue), This is dynamic creation of Queue
			// In managed Env this is retrieved by Jndi lookup.
			Destination destination = session.createQueue("TEST.FOO");

			// Create a MessageProducer from the Session to the Queue
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage();

			// Sendign business Message using producer.
			for (int i = 0; i < 50; i++) {
				message.setText("This is message " + (i + 1));
				System.out.println("**Inside Loop Sending Msg Number : "+i);
				Thread.sleep(1000);
				producer.send(message);
			}
			System.out.println("*******Sending Message End********");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class MyMqThreadConsumer implements Runnable {
	
	@Override
	public void run(){

		try{
			
			System.out.println("*******Synchronous Receiving Message Start********");
			
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
			Queue queue = session.createQueue("TEST.FOO");
			
			// Consumer
			MessageConsumer consumer = session.createConsumer(queue);
			connection.start();
			
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
			
			System.out.println("*******Synchronous Receiving Message End********");
			//Finally, close the session and the connections.
			session.close();
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}


