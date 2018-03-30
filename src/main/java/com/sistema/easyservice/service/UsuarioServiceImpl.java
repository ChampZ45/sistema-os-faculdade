package com.sistema.easyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.easyservice.model.Usuario;
import com.sistema.easyservice.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	
	@Autowired private UsuarioRepository repository;
	
	@Override
	public void salvar(Usuario usuario) {
		
		repository.save(usuario);
		
	}

}
