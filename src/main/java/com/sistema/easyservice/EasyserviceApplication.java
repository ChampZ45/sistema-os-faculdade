package com.sistema.easyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sistema.easyservice.controller.ClienteController;
import com.sistema.easyservice.service.ClienteService;
import com.sistema.easyservice.service.ClienteServiceImpl;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ClienteController.class, ClienteServiceImpl.class})
public class EasyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyserviceApplication.class, args);
	}
}
