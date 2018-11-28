package com.boraji.tutorial.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.boraji.tutorial.springboot.config.MailProperties;
import com.boraji.tutorial.springboot.service.MailService;

@SpringBootApplication
@EnableConfigurationProperties(MailProperties.class)
public class SpringBootApp {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(SpringBootApp.class, args);
    MailService mailService = context.getBean(MailService.class);
    mailService.print();
  }
  
}
