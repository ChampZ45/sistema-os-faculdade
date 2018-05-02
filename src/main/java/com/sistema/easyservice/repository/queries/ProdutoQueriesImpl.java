package com.sistema.easyservice.repository.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.sistema.easyservice.model.Produto;
import com.sistema.easyservice.repository.filtro.ProdutoFiltro;

public class ProdutoQueriesImpl implements ProdutoQueries {

	
	@PersistenceContext
	private EntityManager manager;
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Produto> recuperarPorIdEDescricao(ProdutoFiltro filtro) {
		
		String sql = " from Produto ";
		
		if(filtro.getCodigo() != null){
			sql += " where id = :codigo";
		}
		
		if(filtro.getCodigo() == null && !filtro.getDescricao().isEmpty()){
			sql += " where descricao like :descricao";
		}
				
		Query query = manager.createQuery(sql);
		
		if(filtro.getCodigo() != null){
			query.setParameter("codigo", filtro.getCodigo());
		}
		
		if(filtro.getCodigo() == null && !filtro.getDescricao().isEmpty()){
			query.setParameter("descricao", filtro.getDescricao() + "%");
		}
		
		return query.getResultList();
	}


	@Override
	public Integer recuperarQuantidadeDeProdutoNoEstoque(Long codigoProduto) {
		
		Query query = manager.createQuery("select estoque from Produto where id = :codigo").setParameter("codigo", codigoProduto);
		
		return query.getFirstResult();
	}

}
