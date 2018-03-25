package com.sistema.easyservice.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.easyservice.model.Produto;
import com.sistema.easyservice.repository.ProdutoRepository;
import com.sistema.easyservice.repository.filtro.ProdutoFiltro;
import com.sistema.easyservice.service.ProdutoService;

@Controller
@RequestMapping(value="produto")
public class ProdutoController {

	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping(value = "/novo")
	public ModelAndView novo(Produto produto){
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
			
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Produto produto,BindingResult bindingResult,RedirectAttributes attributes){
		
		if(bindingResult.hasErrors()){
			return novo(produto);
		}
		
		boolean edicao = false;		
		if(!produto.isNovo())
			edicao = true;
		
		produtoService.salvar(produto);
		attributes.addFlashAttribute("mensagem", edicao ? "Produto atualizado com sucesso" : "Produto Cadastrado com sucesso!");
		return new ModelAndView("redirect:/produto/novo");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(ProdutoFiltro filtro, BindingResult result){
		ModelAndView mv = new ModelAndView("produto/PesquisarProduto");
		
		mv.addObject("produtos", produtoRepository.recuperarPorIdEDescricao(filtro));
		
		return mv;
		
	}
	
	@GetMapping(value="{id}")
	public ModelAndView editar(@PathVariable Long id){
		Optional<Produto> produto = produtoRepository.findById(id);
				
		ModelAndView mv = novo(produto.get());
		mv.addObject(produto.get());
		return mv;
	}
	
	@GetMapping(value="excluir/{id}")
	public ModelAndView excluir(@PathVariable Long id,RedirectAttributes attributes){
		produtoRepository.delete(new Produto(id));
		
		attributes.addFlashAttribute("mensagem","Produto excluido com sucesso!");
		
		return new ModelAndView("redirect:/produto");
	}
}
