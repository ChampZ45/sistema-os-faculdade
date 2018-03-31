package com.sistema.easyservice.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.easyservice.model.OrdemServico;
import com.sistema.easyservice.model.Produto;
import com.sistema.easyservice.model.Servico;
import com.sistema.easyservice.model.StatusOs;
import com.sistema.easyservice.repository.ProdutoRepository;
import com.sistema.easyservice.repository.ServicoRepository;
import com.sistema.easyservice.service.OrdemServicoService;
import com.sistema.easyservice.session.ItensOrdemServico;

@Controller
@RequestMapping(value = "ordemServico")
public class OrdemServicoController {

	@Autowired private OrdemServicoService ordemServicoService;
	
	@Autowired private ProdutoRepository produtoRepository;
	
	@Autowired private ServicoRepository servicoRepository;
	
	@Autowired private ItensOrdemServico itens;
	
	@GetMapping(value = "/novo")
	public ModelAndView novo(OrdemServico ordemServico){
		ModelAndView mv = new ModelAndView("ordem/CadastroOrdemServico");
		
		itens = new ItensOrdemServico();
		
		itens.setProdutos(ordemServico.getProdutos());
		itens.setServicos(ordemServico.getServicos());
		
		mv.addObject("listaStatus",StatusOs.values());
		
		mv.addObject("listaProdutos",itens.getProdutos());
		mv.addObject("valorTotalProdutos",itens.getValorTotalProdutos());

		mv.addObject("listaServicos",itens.getServicos());
		mv.addObject("valorTotalServicos",itens.getValorTotalServicos());
		mv.addObject("valorTotal",itens.getValorTotal());
		
		return mv;
	}
	
	@RequestMapping(value="/novo",method = RequestMethod.POST)
	public ModelAndView cadastar(@Valid OrdemServico ordemServico,BindingResult result, Model model,RedirectAttributes attributes){
	
		ordemServico.setProdutos(itens.getProdutos());
		ordemServico.setServicos(itens.getServicos());
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
	
	
	@PostMapping(value="/adicionarProduto")
	public @ResponseBody ModelAndView adicionarProduto(String id){
	
		Optional<Produto> produto = produtoRepository.findById(Long.parseLong(id));
		
		itens.adicionarProduto(produto.get());
				
		ModelAndView mv = new ModelAndView("ordem/TabelaItensOrdemServico");
		
		mv.addObject("listaProdutos",itens.getProdutos());
		mv.addObject("valorTotalProdutos",itens.getValorTotalProdutos());
		mv.addObject("valorTotal",itens.getValorTotal());
		
		return mv;
	}
	
	@PostMapping(value="/adicionarServico")
	public @ResponseBody ModelAndView adicionarServico(String id){
	
		Optional<Servico> servico = servicoRepository.findById(Long.parseLong(id));
		
		itens.adicionarServico(servico.get());
				
		ModelAndView mv = new ModelAndView("ordem/TabelaItensServicoOrdemServico");
		
		mv.addObject("listaServicos",itens.getServicos());
		mv.addObject("valorTotalServicos",itens.getValorTotalServicos());
		mv.addObject("valorTotal",itens.getValorTotal());
		
		return mv;
	}
}
