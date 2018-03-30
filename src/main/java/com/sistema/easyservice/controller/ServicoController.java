package com.sistema.easyservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.easyservice.model.Produto;
import com.sistema.easyservice.model.Servico;
import com.sistema.easyservice.repository.ServicoRepository;
import com.sistema.easyservice.repository.filtro.ServicoFiltro;
import com.sistema.easyservice.service.ServicoService;

@Controller
@RequestMapping(value = "/servico")
public class ServicoController {
	
	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private ServicoRepository serviceRepository;
	
	@GetMapping(value = "/novo")
	public ModelAndView novo(Servico servico){
		ModelAndView mv = new ModelAndView("servico/CadastroServico");
			
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Servico servico,BindingResult bindingResult,RedirectAttributes attributes){
		
		if(bindingResult.hasErrors()){
			return novo(servico);
		}
		
		boolean edicao = false;		
		if(!servico.isNovo())
			edicao = true;
		
		servicoService.salvar(servico);
		attributes.addFlashAttribute("mensagem", edicao ? "Servico atualizado com sucesso" : "Servico Cadastrado com sucesso!");
		return new ModelAndView("redirect:/servico/novo");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(ServicoFiltro filtro, BindingResult result){
		ModelAndView mv = new ModelAndView("servico/PesquisarServico");
		
		mv.addObject("servicos", serviceRepository.recuperarPorCodigoNomeEDescricao(filtro));
		
		return mv;
		
	}
	
	@GetMapping(value="{id}")
	public ModelAndView editar(@PathVariable Long id){
		Optional<Servico> servico = serviceRepository.findById(id);
				
		ModelAndView mv = novo(servico.get());
		mv.addObject(servico.get());
		return mv;
	}
	
	@GetMapping(value="excluir/{id}")
	public ModelAndView excluir(@PathVariable Long id,RedirectAttributes attributes){
		serviceRepository.delete(new Servico(id));
		
		attributes.addFlashAttribute("mensagem","Servico excluido com sucesso!");
		
		return new ModelAndView("redirect:/servico");
	}
	
	@RequestMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Servico> pesquisar(String nome) {
		return serviceRepository.findByNomeStartingWithIgnoreCase(nome);
	}
}
