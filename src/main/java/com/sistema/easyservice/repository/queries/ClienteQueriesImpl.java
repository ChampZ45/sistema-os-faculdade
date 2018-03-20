package com.sistema.easyservice.repository.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.sistema.easyservice.repository.filtro.ClienteFiltro;

public class ClienteQueriesImpl  implements ClienteQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<ClienteFiltro> recuperarClientesPorNomeECpfCnpj(ClienteFiltro filtro) {
		
		if(filtro == null)
			filtro = new ClienteFiltro();
		
		String queryString = " from Cliente  c ";
		
		if(!filtro.getNome().isEmpty()) {
			queryString +=" where c.nome like :nome ";
		}
		
		if(!filtro.getCpfCnpj().isEmpty() && filtro.getNome().isEmpty()) {
			queryString +=" where c.cnpjCpf like :cpf ";
		}else if(!filtro.getCpfCnpj().isEmpty()) {
			queryString +=" and c.cnpjCpf like :cpf ";
		}
		
		Query query = manager.createQuery(queryString);

		if(!filtro.getNome().isEmpty()) {
			query.setParameter("nome", filtro.getNome() + "%");
		}
		
		if(!filtro.getCpfCnpj().isEmpty()) {
			query.setParameter("cpf", filtro.getCpfCnpj() + "%");
		}
		
		return query.getResultList();
	}

}
