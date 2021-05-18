package com.pp.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.pp")
public class RestStudentServiceMain {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(
				RestStudentServiceMain.class, args);
		//System.out.println(ctx);
	}
}
