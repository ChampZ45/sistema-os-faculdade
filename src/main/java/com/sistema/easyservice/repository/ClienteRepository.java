package com.sistema.easyservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.easyservice.model.Cliente;
import com.sistema.easyservice.repository.queries.ClienteQueries;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>,ClienteQueries{

	public List<Cliente> findByNomeInOrCnpjCpf(String nome, String cpnj);
}
