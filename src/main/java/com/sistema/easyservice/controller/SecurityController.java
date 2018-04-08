package com.sistema.easyservice.controller;


import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {

	
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user){

		if(user != null)
			return "redirect:/ordemServico";
		
		return "Login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(@AuthenticationPrincipal User user, ModelMap model) {
	   return user != null ? "redirect:/ordemServico" : "Login";
	}
	
	@GetMapping("/403")
	public String acessoNegado(){
		return "403";
	}
}
