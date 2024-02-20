package com.example.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class EmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpApplication.class, args);
	}

}
