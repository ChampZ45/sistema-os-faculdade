package com.sistema.easyservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.easyservice.model.Servico;
import com.sistema.easyservice.repository.queries.ServicoQueries;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>,ServicoQueries {

	public List<Servico> findByNomeStartingWithIgnoreCase(String nome);
}
