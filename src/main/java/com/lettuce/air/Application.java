package com.lettuce.air;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot框架主入口
 * 
 * @author Lando
 *
 */
@SpringBootApplication
public class Application {
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Started-Successfully");
	}

}
