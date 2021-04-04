package com.project.doacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.doacao.model.FilhoMaterial;

@Repository
public interface MaterialFilhoRepository extends JpaRepository<FilhoMaterial, Long> {
	
	@Query(nativeQuery = true, value = "select f.nome as nome_filho, e.uf, e.localidade, e.nome, f.url_foto from filho_material as fm inner join filho as f " + 
			"on fm.filho_id = f.id " + 
			"inner join escola as e on f.escola_id = e.id " + 
			"where fm.status_doacao != 'CONFIRMADO' group by f.nome, e.uf, e.localidade, f.url_foto, e.nome")
	List<Object> materiaisFilhoAberto();
	
	@Query(nativeQuery = true,value="select * from filho_material as fm inner join filho as f " + 
			"on filho_id = f.id " + 
			"inner join material as ma on fm.material_id = ma.id " + 
			"where fm.status_doacao != 'CONFIRMADO' and f.nome = :filho")
	List<FilhoMaterial> materiaisFilhoConfirmar(@Param("filho") String filho);
	
	@Query(nativeQuery = true,value="select * from filho_material as fm inner join filho as f " + 
			"on filho_id = f.id " + 
			"inner join material as ma on fm.material_id = ma.id " + 
			"where fm.status_doacao != 'CONFIRMADO' and f.id = :id")
	List<FilhoMaterial> materiaisFilhoPorID(@Param("id") Long id);
}
