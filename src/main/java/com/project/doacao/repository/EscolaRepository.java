package com.project.doacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doacao.model.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Long>{

	@Query("select e from Escola e where e.nome = ?1")
	Escola findEscolaByNome(String nome);
	
}
