package com.project.doacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.doacao.model.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Long>{
	
}
