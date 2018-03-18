package com.sistema.easyservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {

	//teste
	@RequestMapping(value= "/cliente",method = RequestMethod.GET)
	public String teste(){
		return "cliente/CadastroCliente";
	}
}
