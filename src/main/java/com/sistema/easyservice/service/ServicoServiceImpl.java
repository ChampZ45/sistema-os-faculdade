package com.sistema.easyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.easyservice.model.Servico;
import com.sistema.easyservice.repository.ServicoRepository;

@Service
public class ServicoServiceImpl implements ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;
	
	@Override
	@Transactional
	public void salvar(Servico servico) {
		
		servicoRepository.save(servico);
		
	}

}
