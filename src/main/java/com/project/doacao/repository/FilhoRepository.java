package com.project.doacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.doacao.model.Filho;

@Repository
public interface FilhoRepository extends JpaRepository<Filho, Long>{

	@Query("select * from Filho f where f.pai.id = ?1")
	List<Filho> findFilhoByID(Long id);
	
}
