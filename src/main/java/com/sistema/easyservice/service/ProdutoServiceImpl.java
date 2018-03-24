package com.sistema.easyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.easyservice.model.Produto;
import com.sistema.easyservice.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	@Transactional
	public void salvar(Produto produto) {
		
		produtoRepository.save(produto);
		
	}


}
