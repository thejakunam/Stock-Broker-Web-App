package com.example.stockapplication;

import controller.getstockdata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = getstockdata.class)
public class StockapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockapplicationApplication.class, args);
	}

}
