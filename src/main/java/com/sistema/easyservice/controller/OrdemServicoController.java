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

import com.sistema.easyservice.model.OrdemServico;
import com.sistema.easyservice.model.StatusOs;
import com.sistema.easyservice.service.OrdemServicoService;

@Controller
@RequestMapping(value = "ordemServico")
public class OrdemServicoController {

	@Autowired private OrdemServicoService ordemServicoService;
	
	@GetMapping(value = "/novo")
	public ModelAndView novo(OrdemServico ordemServico){
		ModelAndView mv = new ModelAndView("ordem/CadastroOrdemServico");
		mv.addObject("listaStatus",StatusOs.values());
			
		return mv;
	}
	
	@RequestMapping(value="/novo",method = RequestMethod.POST)
	public ModelAndView cadastar(@Valid OrdemServico ordemServico,BindingResult result, Model model,RedirectAttributes attributes){
	
		if(result.hasErrors()){
			return novo(ordemServico);
		}
		
		boolean edicao = false;
		if(!ordemServico.isNovo())
			edicao = true;
				
		ordemServicoService.salvar(ordemServico);	
		attributes.addFlashAttribute("mensagem", edicao ? "Ordem de servico atualizado com sucesso" : "Ordem de servico cadastrado com sucesso");
		return new ModelAndView("redirect:/ordemServico/novo");
	}
	
}
