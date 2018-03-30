package com.sistema.easyservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.easyservice.model.Cliente;
import com.sistema.easyservice.model.Usuario;
import com.sistema.easyservice.repository.UsuarioRepository;
import com.sistema.easyservice.service.UsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	
	@Autowired private UsuarioService usuarioService;
	
	@Autowired private UsuarioRepository usuarioRepository;
	
	@RequestMapping(value="/novo")
	public ModelAndView novo(Usuario usuario){
		ModelAndView mv = new ModelAndView("/usuario/CadastroUsuario");
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Usuario usuario,BindingResult bindingResult,RedirectAttributes attributes){
		
		if(bindingResult.hasErrors()){
			return novo(usuario);
		}
		
		boolean edicao = false;		
		if(!usuario.isNovo())
			edicao = true;
		
		usuarioService.salvar(usuario);
		attributes.addFlashAttribute("mensagem", edicao ? "Usuario atualizado com sucesso" : "Usuario Cadastrado com sucesso!");
		return new ModelAndView("redirect:/usuario/novo");
		
	}
	
	@RequestMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Usuario> pesquisar(String nome) {
		return usuarioRepository.findByNomeStartingWithIgnoreCase(nome);
	}
	
//	@GetMapping
//	public ModelAndView pesquisar(UsuarioFiltro filtro, BindingResult result){
//		ModelAndView mv = new ModelAndView("usuario/PesquisarUsuario");
//		
//		mv.addObject("usuarios", usuarioRepository.recuperarPorIdEDescricao(filtro));
//		
//		return mv;
//		
//	}
//	
//	@GetMapping(value="{id}")
//	public ModelAndView editar(@PathVariable Long id){
//		Optional<Usuario> usuario = usuarioRepository.findById(id);
//				
//		ModelAndView mv = novo(usuario.get());
//		mv.addObject(usuario.get());
//		return mv;
//	}
//	
//	@GetMapping(value="excluir/{id}")
//	public ModelAndView excluir(@PathVariable Long id,RedirectAttributes attributes){
//		usuarioRepository.delete(new Usuario(id));
//		
//		attributes.addFlashAttribute("mensagem","Usuario excluido com sucesso!");
//		
//		return new ModelAndView("redirect:/usuario");
//	}
}
