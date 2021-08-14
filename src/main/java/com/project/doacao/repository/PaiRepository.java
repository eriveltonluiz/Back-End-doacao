package com.project.doacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.doacao.model.Pai;

@Repository
public interface PaiRepository extends JpaRepository<Pai, Long>{

	@Query("select p from Pai p where p.email = ?1 and p.senha = ?2")
	Pai findPaiByEmailSenha(String email, String senha);
	
	Optional<Pai> findByEmail(String email);
}
