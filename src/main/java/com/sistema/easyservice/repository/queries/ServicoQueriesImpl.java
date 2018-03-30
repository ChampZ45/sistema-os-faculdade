package com.sistema.easyservice.repository.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sistema.easyservice.model.Servico;
import com.sistema.easyservice.repository.filtro.ServicoFiltro;

public class ServicoQueriesImpl implements ServicoQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Servico> recuperarPorCodigoNomeEDescricao(ServicoFiltro filtro) {
		
		String sql = " from Servico ";
		
		if(filtro.getCodigo() != null){
			sql += " where id = :codigo";
		}
		
		if(filtro.getCodigo() == null && !filtro.getDescricao().isEmpty()){
			sql += " where descricao like :descricao";
		}
		
		if(filtro.getCodigo() == null && filtro.getDescricao().isEmpty() && !filtro.getNome().isEmpty()){
			sql += " where nome like :nome";
		}else if(!filtro.getNome().isEmpty()){
			sql += " and nome like :nome";
		}
				
		Query query = manager.createQuery(sql);
		
		if(filtro.getCodigo() != null){
			query.setParameter("codigo", filtro.getCodigo());
		}
		
		if(filtro.getCodigo() == null && !filtro.getDescricao().isEmpty()){
			query.setParameter("descricao", filtro.getDescricao() + "%");
		}
		
		if(filtro.getCodigo() == null && !filtro.getNome().isEmpty()){
			query.setParameter("nome", filtro.getNome() + "%");
		}
		
		return query.getResultList();
	}

}
