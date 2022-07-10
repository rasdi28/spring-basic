package com.sdd.springbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication()
public class SpringBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicApplication.class, args);
	}

}
