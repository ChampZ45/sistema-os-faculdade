package com.sistema.easyservice.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sistema.easyservice.model.Usuario;
import com.sistema.easyservice.repository.UsuarioRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String cpf = authentication.getName();
		String senha = authentication.getCredentials().toString();
		
		Usuario usuario = usuarioRepository.findByEmailAndSenha(cpf, senha);
		
		if(usuario == null)
			throw new BadCredentialsException("Usuario ou senha invalidos");
		
		
		UserDetails userDetails = new UsuarioLogado(usuario, Collections.emptyList());
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
	              (userDetails, senha, Collections.emptyList());
		
				
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
