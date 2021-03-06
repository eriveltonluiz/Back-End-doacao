package com.project.doacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.doacao.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>{

}
