package com.sistema.easyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sistema.easyservice.controller.ClienteController;
import com.sistema.easyservice.repository.ClienteRepository;
import com.sistema.easyservice.service.ClienteService;
import com.sistema.easyservice.service.ClienteServiceImpl;
import com.sistema.easyservice.session.ItensOrdemServico;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ClienteController.class, ClienteServiceImpl.class, ItensOrdemServico.class})
@EnableJpaRepositories(basePackageClasses = {ClienteRepository.class})
public class EasyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyserviceApplication.class, args);
	}
}
