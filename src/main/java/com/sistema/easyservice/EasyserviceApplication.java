package com.sistema.easyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sistema.easyservice.config.SecurityConfig;
import com.sistema.easyservice.controller.ClienteController;
import com.sistema.easyservice.repository.ClienteRepository;
import com.sistema.easyservice.security.CustomAuthenticationProvider;
import com.sistema.easyservice.service.ClienteServiceImpl;
import com.sistema.easyservice.session.ItensOrdemServico;
import com.sistema.easyservice.validator.OrdemServicoValidator;

@SpringBootApplication
@ComponentScan(basePackageClasses = {ClienteController.class, ClienteServiceImpl.class, ItensOrdemServico.class, OrdemServicoValidator.class, 
		SecurityConfig.class, CustomAuthenticationProvider.class})
@EnableJpaRepositories(basePackageClasses = {ClienteRepository.class})
public class EasyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyserviceApplication.class, args);
	}
	
	@Bean(name="messageSource")
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames("classpath:/messages");
	    messageSource.setCacheSeconds(10); 
	    return messageSource;
	}
}
