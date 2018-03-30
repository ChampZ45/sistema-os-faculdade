package com.sistema.easyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.easyservice.model.OrdemServico;
import com.sistema.easyservice.repository.OrdemServicoRepository;

@Service
public class OrdemServicoImpl implements OrdemServicoService {

	
	@Autowired private OrdemServicoRepository ordemServicoRepository;
	
	@Override
	@Transactional
	public void salvar(OrdemServico ordemServico) {

		ordemServicoRepository.save(ordemServico);
	}

}
