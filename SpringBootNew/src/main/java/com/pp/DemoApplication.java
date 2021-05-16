package com.pp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		
		//Dependency Injection example getting bean from context
		//second way of DI is using @Autowired
		//DI is achieved by IOC Priciple
		Customer c = context.getBean(Customer.class);
		c.setCustname("Prakash");
		System.out.println("******getCustomer via context***** " + c.getCustname());
		System.out.println("******getAddresss State via @Autowired ***** " + c.printAddr());
		
	}

}
