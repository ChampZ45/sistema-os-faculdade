package com.sistema.easyservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.easyservice.model.Cliente;
import com.sistema.easyservice.model.TipoPessoa;
import com.sistema.easyservice.repository.ClienteRepository;
import com.sistema.easyservice.repository.filtro.ClienteFiltro;
import com.sistema.easyservice.service.ClienteService;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping(value="/novo")
	public ModelAndView novo(Cliente cliente){
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");		
		mv.addObject("tiposPessoa", TipoPessoa.values());	
		
		return mv;
	}
	
	@RequestMapping(value="/novo",method = RequestMethod.POST)
	public ModelAndView cadastar(@Valid Cliente cliente,BindingResult result, Model model,RedirectAttributes attributes){
	
		if(result.hasErrors()){
			return novo(cliente);
		}
		
		clienteService.salvarCliente(cliente);	
		attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso");
		return new ModelAndView("redirect:/cliente/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisarClientes(ClienteFiltro filtro,BindingResult result) {
		ModelAndView mv = new ModelAndView("cliente/PesquisarClientes");
		
		mv.addObject("clientes", clienteRepository.findAll());
		
		return mv;
	}
}
