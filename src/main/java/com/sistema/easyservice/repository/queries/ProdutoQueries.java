package com.sistema.easyservice.repository.queries;

import java.util.List;

import com.sistema.easyservice.model.Produto;
import com.sistema.easyservice.repository.filtro.ProdutoFiltro;

public interface ProdutoQueries {
	
	List<Produto> recuperarPorIdEDescricao(ProdutoFiltro filtro);

	Integer recuperarQuantidadeDeProdutoNoEstoque(Long codigoProduto);
	
}
