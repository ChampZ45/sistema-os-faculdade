package com.sistema.easyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sistema.easyservice.controller.ClienteController;

@SpringBootApplication
@ComponentScan(basePackageClasses = ClienteController.class)
public class EasyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyserviceApplication.class, args);
	}
}
