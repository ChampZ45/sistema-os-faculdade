package com.sistema.easyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.easyservice.model.Cliente;
import com.sistema.easyservice.repository.ClienteRepository;
import com.sistema.easyservice.service.exception.ClienteInvalidoException;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	@Transactional
	public void salvarCliente(Cliente cliente) {
	
		if(!this.validarCliente(cliente))
			throw new ClienteInvalidoException("Preencha todos os campos obrigatoria antes de continuar");
		
		clienteRepository.save(cliente);
		
	}

	@Override
	public boolean validarCliente(Cliente cliente) {
	
		if(cliente.getCnpjCpf().isEmpty())
			return false;
		
		if(cliente.getNome().isEmpty())
			return false;
	
		return true;
	}

}
